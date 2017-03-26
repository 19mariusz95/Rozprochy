package pl.edu.agh.dsrg.sr.chat;

import com.google.common.collect.Lists;
import org.apache.log4j.Logger;
import org.jgroups.Message;
import org.jgroups.View;
import org.jgroups.protocols.UDP;
import org.jgroups.stack.Protocol;
import pl.edu.agh.dsrg.sr.chat.handler.ChatMessageHandler;
import pl.edu.agh.dsrg.sr.chat.handler.impl.ChatActionHandler;
import pl.edu.agh.dsrg.sr.chat.handler.impl.ChatStateHandler;
import pl.edu.agh.dsrg.sr.chat.protos.ChatOperationProtos;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class ManagementChannelConnector extends ChannelConnector {

    public static final String CHAT_MANAGEMENT_321321 = "ChatManagement321321";
    private static final Logger LOGGER = Logger.getLogger(ManagementChannelConnector.class);

    public ManagementChannelConnector(String nickname, Set<String> channels, Map<String, List<String>> usersMap,
                                      Consumer<ChatOperationProtos.ChatAction> joinHandler,
                                      Consumer<ChatOperationProtos.ChatAction> leaveHandler) {
        super(nickname, CHAT_MANAGEMENT_321321, usersMap, channels, joinHandler, leaveHandler);
    }

    public void join(String channelName) throws Exception {
        ChatOperationProtos.ChatAction action = ChatOperationProtos.ChatAction.newBuilder()
                .setAction(ChatOperationProtos.ChatAction.ActionType.JOIN)
                .setChannel(channelName)
                .setNickname(this.nickname)
                .build();
        channel.send(new Message(null, null, action.toByteArray()));
    }

    @Override
    protected List<ChatMessageHandler> getReceivers() {
        return Lists.newArrayList(
                new ChatStateHandler(channels, usersMap),
                new ChatActionHandler(joinHandler, leaveHandler)
        );
    }


    @Override
    protected void handleAccepted(View view) {
        ChatOperationProtos.ChatState.Builder builder = ChatOperationProtos.ChatState.newBuilder();
        channels.forEach(channel -> usersMap.get(channel).forEach(user -> {
            ChatOperationProtos.ChatAction action = ChatOperationProtos.ChatAction.newBuilder()
                    .setAction(ChatOperationProtos.ChatAction.ActionType.JOIN)
                    .setChannel(channel)
                    .setNickname(user)
                    .build();
            builder.addState(action);
        }));
        ChatOperationProtos.ChatState chatState = builder.build();
        try {
            channel.send(new Message(null, null, chatState.toByteArray()));
        } catch (Exception e) {
            LOGGER.warn(e);
        }
    }

    @Override
    protected Protocol getMcast_group_addr() throws UnknownHostException {
        return new UDP();
    }

    public void leave(String channelName) throws Exception {
        ChatOperationProtos.ChatAction action = ChatOperationProtos.ChatAction.newBuilder()
                .setAction(ChatOperationProtos.ChatAction.ActionType.LEAVE)
                .setChannel(channelName)
                .setNickname(this.nickname)
                .build();
        channel.send(new Message(null, null, action.toByteArray()));
    }

    public void synchronizeState() throws Exception {
        ChatOperationProtos.ChatAction action = ChatOperationProtos.ChatAction.newBuilder()
                .setAction(ChatOperationProtos.ChatAction.ActionType.JOIN)
                .setChannel(channelName)
                .setNickname(this.nickname)
                .build();
        channel.send(new Message(null, null, action.toByteArray()));
    }

}
