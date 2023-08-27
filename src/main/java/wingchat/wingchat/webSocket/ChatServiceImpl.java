package wingchat.wingchat.webSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import wingchat.wingchat.Entity.Message;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public void sendMessageToRecipient(String recipient, Message message) {
        messagingTemplate.convertAndSendToUser(
                recipient,
                "/queue/messages",
                message);
    }
}