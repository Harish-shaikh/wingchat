package wingchat.wingchat.webSocket;

import wingchat.wingchat.Entity.Message;

public interface ChatService {
    void sendMessageToRecipient(String recipient, Message message);
}
