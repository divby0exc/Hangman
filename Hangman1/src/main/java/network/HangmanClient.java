package network;

public interface HangmanClient {
    public void sendMessage(String content);
    public void sendReady(boolean ready);
    public void sendClose();
}