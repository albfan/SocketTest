package net.sf.sockettest.model;

public class SocketTestClientModel {
    StringBuffer messages;
    private boolean hexOutput;
    private boolean hexInput;
    private String ip;
    private int port;

    public SocketTestClientModel() {
        setMessages(new StringBuffer());
    }

    public StringBuffer getMessages() {
        return messages;
    }

    public void setMessages(StringBuffer messages) {
        this.messages = messages;
    }

    public boolean isHexOutput() {
        return hexOutput;
    }

    public void setHexOutput(boolean hexOutput) {
        this.hexOutput = hexOutput;
    }

    public boolean isHexInput() {
        return hexInput;
    }

    public void setHexInput(boolean hexInput) {
        this.hexInput = hexInput;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
