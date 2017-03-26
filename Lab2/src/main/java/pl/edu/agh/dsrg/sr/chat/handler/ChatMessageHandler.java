package pl.edu.agh.dsrg.sr.chat.handler;


import org.jgroups.Message;

public interface ChatMessageHandler {

    void tryHandle(Message msg);
}
