package com.bettersms.badippe.database;

/**
 * Created by badache on 19/10/15.
 */
public class Message {
    private int Id;
    private String sendingTime;
    private String sendingTo;
    private String messageContent;


    public String getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(String sendingTime) {
        this.sendingTime = sendingTime;
    }

    public String getSendingTo() {
        return sendingTo;
    }

    public void setSendingTo(String sendingTo) {
        this.sendingTo = sendingTo;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    // Stateless message constructor
    public Message (int Id)
    {

    }

    // Stateful message constructor
    public Message (int Id, String sendingTime, String sendingTo, String messageContent) {
        this(Id);
        this.sendingTime = sendingTime;
        this.sendingTo = sendingTo;
        this.messageContent = messageContent;
    }
}
