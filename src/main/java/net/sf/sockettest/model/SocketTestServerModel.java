package net.sf.sockettest.model;

public class SocketTestServerModel {
    private boolean hexInput;
    private boolean hexOutput;
    private String ip;
    private int port;
    private StringBuffer messages;

    public void setHexInput(boolean hexInput) {
        this.hexInput = hexInput;
    }

    public boolean isHexInput() {
        return hexInput;
    }

    public void setHexOutput(boolean hexOutput) {
        this.hexOutput = hexOutput;
    }

    public boolean isHexOutput() {
        return hexOutput;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public void setMessages(StringBuffer messages) {
        this.messages = messages;
    }

    public StringBuffer getMessages() {
        if (messages == null) {
            messages = new StringBuffer();
        }
        return messages;
    }
}
