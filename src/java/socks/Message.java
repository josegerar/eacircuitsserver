/*
 */
package socks;

/**
 * The message object will contain the parts of the header, where it seeks to specify data as identifiers of recipients or others.
 * The content would be the message Config is some extra configuration you want to give, validations etc
 * @author ANBREZ
 */
public class Message {

    private String header = "";
    private String content = "";
    private String config = "";
    

    public Message() {
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

}
