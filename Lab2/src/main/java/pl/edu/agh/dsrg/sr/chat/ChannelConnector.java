package pl.edu.agh.dsrg.sr.chat;

import com.google.common.collect.Lists;
import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.protocols.*;
import org.jgroups.protocols.pbcast.*;
import org.jgroups.stack.Protocol;
import org.jgroups.stack.ProtocolStack;
import pl.edu.agh.dsrg.sr.chat.handler.ChatMessageHandler;
import pl.edu.agh.dsrg.sr.chat.handler.impl.MessageHandler;
import pl.edu.agh.dsrg.sr.chat.protos.ChatOperationProtos;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class ChannelConnector {
    protected final String nickname;
    protected final String channelName;
    protected JChannel channel;
    protected final Map<String, List<String>> usersMap;
    private final List<ChatMessageHandler> receivers;
    protected final Set<String> channels;
    protected final Consumer<ChatOperationProtos.ChatAction> joinHandler;
    protected final Consumer<ChatOperationProtos.ChatAction> leaveHandler;

    public ChannelConnector(String nickname, String channelName, Map<String, List<String>> usersMap, Set<String> channels,
                            Consumer<ChatOperationProtos.ChatAction> joinHandler, Consumer<ChatOperationProtos.ChatAction> leaveHandler) {
        this.nickname = nickname;
        this.channelName = channelName;
        this.usersMap = usersMap;
        this.channels = channels;
        this.joinHandler = joinHandler;
        this.leaveHandler = leaveHandler;
        receivers = getReceivers();
    }

    protected List<ChatMessageHandler> getReceivers() {
        return Lists.newArrayList(
                new MessageHandler(this.channelName)
        );
    }


    public void start() throws Exception {
        channel = new JChannel();
        ProtocolStack protocolStack = getProtocolStack();
        channel.setProtocolStack(protocolStack);
        protocolStack.init();
        setReceiver(channel);
        channel.connect(channelName);
    }

    private void setReceiver(JChannel channel) {
        channel.setReceiver(new ReceiverAdapter() {
            @Override
            public void receive(Message msg) {
                receivers.forEach(receiver -> receiver.tryHandle(msg));
            }

            @Override
            public void viewAccepted(View view) {
                super.viewAccepted(view);
                handleAccepted(view);
            }
        });
    }

    protected void handleAccepted(View view) {

    }

    private ProtocolStack getProtocolStack() throws Exception {
        ProtocolStack stack = new ProtocolStack();
        stack.addProtocol(getMcast_group_addr())
                .addProtocol(new PING())
                .addProtocol(new MERGE3())
                .addProtocol(new FD_SOCK())
                .addProtocol(new FD_ALL().setValue("timeout", 12000).setValue("interval", 3000))
                .addProtocol(new VERIFY_SUSPECT())
                .addProtocol(new BARRIER())
                .addProtocol(new NAKACK2())
                .addProtocol(new UNICAST3())
                .addProtocol(new STABLE())
                .addProtocol(new GMS())
                .addProtocol(new UFC())
                .addProtocol(new MFC())
                .addProtocol(new FRAG2())
                .addProtocol(new STATE_TRANSFER())
                .addProtocol(new FLUSH());
        return stack;
    }

    protected Protocol getMcast_group_addr() throws UnknownHostException {
        return new UDP().setValue("mcast_group_addr", InetAddress.getByName(channelName));
    }

    public void sendMessage(String command) throws Exception {
        ChatOperationProtos.ChatMessage chatMessage = ChatOperationProtos.ChatMessage.newBuilder()
                .setMessage(nickname+": "+command)
                .build();
        channel.send(new Message(null, null, chatMessage.toByteArray()));
    }

    public void stop() {
        channel.close();
    }
}
