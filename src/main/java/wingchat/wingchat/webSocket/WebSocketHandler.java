package wingchat.wingchat.webSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import wingchat.wingchat.Entity.Message;
import wingchat.wingchat.Entity.WorldMessage;

@Controller
public class WebSocketHandler {

    @Autowired
    private ChatService chatService;

    @MessageMapping("/chat/{recipient}")
    public void sendMessage(@DestinationVariable String recipient, Message message) {
        chatService.sendMessageToRecipient(recipient, message);
    }

    @MessageMapping("/sendmessage")
    @SendTo("/user/worldjoin")
    public WorldMessage getContent(@RequestBody WorldMessage message) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        return message;
    }

}
