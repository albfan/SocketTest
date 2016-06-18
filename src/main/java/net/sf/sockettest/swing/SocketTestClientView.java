package net.sf.sockettest.swing;

public interface SocketTestClientView extends AskView {
    void focusOnIp();

    void focusOnPort();

    void startWaitInfo();

    void stopWaitInfo();

    void connected();

    void showConnectionInfo(String ip);

    void clearMessages();

    void focusSendField();

    void disconnected();

    void appendMessage(String message);

    void resetSend();

    String getMessages();

    String chooseFile();
}
