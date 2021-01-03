package jsu.bean;

import java.util.LinkedList;
import java.util.List;

public class MessageList {
    private List<Message> messageListlist=new LinkedList<Message>();
    public MessageList(){}
    public MessageList(List<Message> messageListlist) {
        this.messageListlist = messageListlist;
    }

    public List<Message> getMessageListlist() {
        return messageListlist;
    }

    public void setMessageListlist(List<Message> messageListlist) {
        this.messageListlist = messageListlist;
    }
}
