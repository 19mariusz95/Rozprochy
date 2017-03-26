package pl.edu.agh.dsrg.sr.chat.handler.impl;

import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.log4j.Logger;
import org.jgroups.Message;
import pl.edu.agh.dsrg.sr.chat.handler.ChatMessageHandler;
import pl.edu.agh.dsrg.sr.chat.protos.ChatOperationProtos;

public class MessageHandler implements ChatMessageHandler {

    private static final Logger LOGGER = Logger.getLogger(MessageHandler.class);
    private String channelName;

    public MessageHandler(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public void tryHandle(Message msg) {
        try {
            ChatOperationProtos.ChatMessage chatMessage = ChatOperationProtos.ChatMessage.parseFrom(msg.getRawBuffer());
            System.out.println(channelName + ":" + chatMessage.getMessage());
        } catch (InvalidProtocolBufferException e) {
            LOGGER.warn(e);
        }
    }
}
