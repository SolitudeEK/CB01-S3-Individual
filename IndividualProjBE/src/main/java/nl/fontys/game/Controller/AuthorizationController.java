package nl.fontys.game.Controller;

import nl.fontys.game.Interface.IAuthorization;
import nl.fontys.game.Object.JWTResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import nl.fontys.game.Object.AuthorizationCredentials;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Controller
public class AuthorizationController {
    @Autowired
    private IAuthorization authorization;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/authorization")
    public void AuthorizationEnter(AuthorizationCredentials authorizationCredentials) throws Exception
    {
        if(authorization.AuthorizationEnter(authorizationCredentials)) {
            JWTResponses jwtResponse = new JWTResponses();
            jwtResponse.setResponse(authorizationCredentials.getLogin());
            messagingTemplate.convertAndSend("/user/" + authorizationCredentials.getLogin() + "/result",
                    "true",
                    jwtResponse.headers());
        }
        else
            messagingTemplate.convertAndSend("/user/"+authorizationCredentials.getLogin()+"/result"
                    ,"false");
    }
    @MessageMapping("/authentication/{update}")
    public void authenticationEnter(String login) throws Exception
    {
        JWTResponses jwtResponse = new JWTResponses();
        jwtResponse.setResponse(login);
        messagingTemplate.convertAndSend("/user/"+login+"/result",
                "true",
                jwtResponse.headers());
    }

    @MessageMapping("/registration")
    public void Register(AuthorizationCredentials authorizationCredentials) throws Exception
    {
        System.out.println("Registering...");
        messagingTemplate.convertAndSend("/user/"+authorizationCredentials.getLogin()+"/result",
         authorization.Register(authorizationCredentials));
    }

}
