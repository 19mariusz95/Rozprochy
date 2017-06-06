package pl.edu.agh.ki.sr;

import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.Arrays;
import java.util.List;

public class DataMonitor implements Watcher, StatCallback {
    private ZooKeeper zk;
    private String znode;
    private Watcher chainedWatcher;
    private DataMonitorListener listener;

    byte prevData[];

    public DataMonitor(ZooKeeper zk, String znode, Watcher chainedWatcher,
                       DataMonitorListener listener) throws KeeperException, InterruptedException {
        this.zk = zk;
        this.znode = znode;
        this.chainedWatcher = chainedWatcher;
        this.listener = listener;
        zk.exists(znode, true, this, null);
        try {
            zk.getChildren(znode, this);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public interface DataMonitorListener {
        /**
         * The existence status of the node has changed.
         */
        void exists(byte data[]);

        /**
         * The ZooKeeper session is no longer valid.
         *
         * @param rc
         *                the ZooKeeper reason code
         */
        void closing(int rc);
    }

    public void process(WatchedEvent event) {
        String path = event.getPath();
        if (event.getType() == Event.EventType.None) {
            switch (event.getState()) {
                case SyncConnected:
                    break;
                case Expired:
                    listener.closing(KeeperException.Code.SessionExpired);
                    break;
            }
        } else if(event.getType() == Event.EventType.NodeChildrenChanged) {
            try {
                System.out.println(countDescendants());
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            if (path != null && path.equals(znode)) {
                zk.exists(znode, true, this, null);
            }
        }
        if (chainedWatcher != null) {
            chainedWatcher.process(event);
        }
    }

    private long countDescendants() throws KeeperException, InterruptedException {
        List<String> children = zk.getChildren(znode, this);
        return children.size() + children.stream().mapToLong(child -> countDescendants(znode+"/"+child)).sum();
    }

    private long countDescendants(String path) {
        try {
            List<String> children = zk.getChildren(path, this);
            return children.size() + children.stream().mapToLong(c -> countDescendants(path+"/"+c)).sum();
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void processResult(int rc, String path, Object ctx, Stat stat) {
        boolean exists;
        switch (rc) {
            case Code.Ok:
                exists = true;
                break;
            case Code.NoNode:
                exists = false;
                break;
            case Code.SessionExpired:
            case Code.NoAuth:
                listener.closing(rc);
                return;
            default:
                zk.exists(znode, true, this, null);
                return;
        }

        byte b[] = null;
        if (exists) {
            try {
                b = zk.getData(znode, false, null);
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        if ((b == null && b != prevData)
                || (b != null && !Arrays.equals(prevData, b))) {
            listener.exists(b);
            prevData = b;
        }
    }
}