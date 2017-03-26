package pl.edu.agh.dsrg.sr.chat.handler.impl;

import org.apache.log4j.Logger;
import org.jgroups.Message;
import pl.edu.agh.dsrg.sr.chat.handler.ChatMessageHandler;
import pl.edu.agh.dsrg.sr.chat.protos.ChatOperationProtos;

import java.util.function.Consumer;

import static pl.edu.agh.dsrg.sr.chat.ManagementChannelConnector.CHAT_MANAGEMENT_321321;

public class ChatActionHandler implements ChatMessageHandler {

    private static final Logger LOGGER = Logger.getLogger(ChatActionHandler.class);
    private Consumer<ChatOperationProtos.ChatAction> joinHandler;
    private Consumer<ChatOperationProtos.ChatAction> leaveHandler;

    public ChatActionHandler(Consumer<ChatOperationProtos.ChatAction> joinHandler, Consumer<ChatOperationProtos.ChatAction> leaveHandler) {
        this.joinHandler = joinHandler;
        this.leaveHandler = leaveHandler;
    }

    @Override
    public void tryHandle(Message msg) {
        try {
            byte[] rawBuffer = msg.getRawBuffer();
            ChatOperationProtos.ChatAction action = ChatOperationProtos.ChatAction.parseFrom(rawBuffer);
            if (!action.getChannel().equals(CHAT_MANAGEMENT_321321)) {
                switch (action.getAction()) {
                    case JOIN:
                        joinHandler.accept(action);
                        break;
                    case LEAVE:
                        leaveHandler.accept(action);
                        break;
                }
            }
        } catch (Exception e) {
            LOGGER.warn(e);
        }
    }
}
