package pl.edu.agh.dsrg.sr.chat;

import org.apache.log4j.Logger;
import pl.edu.agh.dsrg.sr.chat.protos.ChatOperationProtos;

import java.util.*;

public class ChatClient {

    private final org.apache.log4j.Logger LOGGER = Logger.getLogger(ChatClient.class);
    private final Map<String, ChannelConnector> map = new HashMap<>();
    private final ManagementChannelConnector managementChannelConnector;
    private final Map<String, List<String>> usersMap = new HashMap<>();
    private final Set<String> channels = new HashSet<>();
    private final String nickName;

    private ChatClient(String nickName) throws Exception {
        this.nickName = nickName;
        managementChannelConnector = new ManagementChannelConnector(nickName, channels, usersMap,
                this::handleJoin, this::handleLeave);
        managementChannelConnector.start();
        managementChannelConnector.synchronizeState();
    }

    private void listen() throws Exception {
        Scanner scanner = new Scanner(System.in);
        Optional<String> activeChannel = Optional.empty();
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            String[] split = command.split(" ");
            if (command.startsWith("/")) {
                String cmd = split[0];
                switch (cmd) {
                    case "/join":
                        if (split.length > 1 && split[1].length() > 0) {
                            joinChannel(split[1], nickName);
                            activeChannel = Optional.of(split[1]);
                        }
                        break;
                    case "/leave":
                        if (split.length > 1) {
                            leaveChannel(split[1]);
                            activeChannel = Optional.empty();
                        }
                        break;
                    case "/listconnected":
                        listConnectedChannels();
                        break;
                    case "/list":
                        listChannels();
                        break;
                    case "/active":
                        if (split.length > 1) {
                            activeChannel = Optional.ofNullable(split[1]);
                        }
                        break;
                    case "/exit":
                        leaveAll();
                        System.exit(0);
                        break;
                }
            } else if (command.length() > 0) {
                if (activeChannel.isPresent()) {
                    String s = activeChannel.get();
                    ChannelConnector channelConnector = map.get(s);
                    if (channelConnector != null) {
                        channelConnector.sendMessage(command);
                    }
                } else {
                    System.out.println("No active channel");
                }
            }
        }
    }


    public static void main(String[] args) throws Exception {
        System.setProperty("java.net.preferIPv4Stack", "true");
        System.out.println("Nick :");
        Scanner scanner = new Scanner(System.in);
        String nickName = scanner.nextLine();
        ChatClient chatClient = new ChatClient(nickName);
        chatClient.listen();
    }

    private void leaveAll() {
        synchronized (map) {
            map.keySet().forEach(channel -> {
                try {
                    managementChannelConnector.leave(channel);
                    Optional.ofNullable(map.get(channel)).ifPresent(ChannelConnector::stop);
                } catch (Exception e) {
                    LOGGER.warn(e);
                }
            });
            map.clear();
        }
    }

    private void listChannels() {
        channels.forEach(channel -> {
            System.out.println(channel);
            List<String> users = usersMap.getOrDefault(channel, new ArrayList<>());
            users.forEach(u -> System.out.println("\t" + u));
        });
    }

    private void listConnectedChannels() {
        map.keySet().forEach(System.out::println);
    }

    private void leaveChannel(String channelName) throws Exception {
        synchronized (map) {
            managementChannelConnector.leave(channelName);
            Optional.ofNullable(map.get(channelName)).ifPresent(ChannelConnector::stop);
            map.remove(channelName);
        }
    }

    private void joinChannel(String channelName, String name) throws Exception {
        synchronized (map) {
            managementChannelConnector.join(channelName);
            channels.add(channelName);
            ChannelConnector channelConnector = new ChannelConnector(name, channelName, usersMap, channels, this::handleJoin, this::handleLeave);
            map.put(channelName, channelConnector);
            try {
                channelConnector.start();
            } catch (Exception e) {
                LOGGER.warn(e);
            }
        }
    }

    private void handleJoin(ChatOperationProtos.ChatAction action) {
        channels.add(action.getChannel());
        List<String> users = usersMap.getOrDefault(action.getChannel(), new ArrayList<>());
        users.add(action.getNickname());
        usersMap.put(action.getChannel(), users);
    }

    private void handleLeave(ChatOperationProtos.ChatAction action) {
        List<String> users = usersMap.get(action.getChannel());
        if (users != null) {
            users.remove(action.getNickname());
            usersMap.put(action.getChannel(), users);
        }
    }
}
