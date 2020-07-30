package ca.cmpt213.a4.onlinehangman.model;

import java.util.Arrays;

/**
 * String wrapper class for html?
 */
public class Message {
    private String message;
    private char[] censoredMessage;

    public Message() {
        this.message = "";
        this.censoredMessage = new char[0];
    }

    public Message(String s) {
        setMessage(s);
    }

    public String getMessage() {
        return message;
    }

    public int getMessageLength() {
        return message.length();
    }

    public void setMessage(String message) {
        this.message = message;
        this.censoredMessage = new char[(this.message.length() * 2) - 1];
        for (int i = 0; i < (this.message.length() * 2) - 1; i++) {


            if (i % 2 == 0)
                censoredMessage[i] = '_';

            if (i % 2 != 0)
                censoredMessage[i] = ' ';
        }
    }

    public String getCensoredMessage() {
        return String.valueOf(this.censoredMessage);
    }

    public void updateCensoredMessage(char letter, int index) {

        //if (index > this.message.length() - 1) { //error handling
        // } else {
        this.censoredMessage[index] = letter; // e.g rewrite _ with 'a'
        //   }
    }

}
