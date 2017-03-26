package pl.edu.agh.dsrg.sr.chat.handler.impl;

import org.apache.log4j.Logger;
import org.jgroups.Message;
import pl.edu.agh.dsrg.sr.chat.ManagementChannelConnector;
import pl.edu.agh.dsrg.sr.chat.handler.ChatMessageHandler;
import pl.edu.agh.dsrg.sr.chat.protos.ChatOperationProtos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChatStateHandler implements ChatMessageHandler {

    private static final Logger LOGGER = Logger.getLogger(ChatStateHandler.class);
    private final Set<String> channels;
    private Map<String, List<String>> usersMap;

    public ChatStateHandler(Set<String> channels, Map<String, List<String>> usersMap) {
        this.channels = channels;
        this.usersMap = usersMap;
    }

    @Override
    public void tryHandle(Message msg) {
        try {
            byte[] rawBuffer = msg.getRawBuffer();
            ChatOperationProtos.ChatState chatState = ChatOperationProtos.ChatState.parseFrom(rawBuffer);
            chatState.getStateList()
                    .stream().filter(state -> !state.getChannel().equals(ManagementChannelConnector.CHAT_MANAGEMENT_321321))
                    .forEach(state -> {
                        channels.add(state.getChannel());
                        List<String> users = usersMap.getOrDefault(state.getChannel(), new ArrayList<>());
                        if (!users.contains(state.getNickname())) {
                            users.add(state.getNickname());
                        }
                        usersMap.put(state.getChannel(), users);
                    });
        } catch (Exception e) {
            LOGGER.warn(e);
        }
    }
}
