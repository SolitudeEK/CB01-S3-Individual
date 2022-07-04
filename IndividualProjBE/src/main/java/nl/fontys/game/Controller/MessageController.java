package nl.fontys.game.Controller;

import nl.fontys.game.Object.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @MessageMapping("/message")
    public void recieveMessage(Message message)
    {
        System.out.println(message.getMessage());
        messagingTemplate.convertAndSend("/user/message/result", message);
    }

}
