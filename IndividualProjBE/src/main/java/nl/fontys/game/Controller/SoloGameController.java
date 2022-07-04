package nl.fontys.game.Controller;

import nl.fontys.game.Interface.ISoloGame;
import nl.fontys.game.Logic.SoloGame;
import nl.fontys.game.Object.JWTResponses;
import nl.fontys.game.Object.SGResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class SoloGameController {
    @Autowired
    private Map<String, ISoloGame> games;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @MessageMapping("/sologame/start/{update}")
    public void ThickerGame(String login, @DestinationVariable String update){
        games.put(login, new SoloGame());
        if(update.equals("1")){
            JWTResponses jwtResponse = new JWTResponses();
            jwtResponse.setResponse(login);
            messagingTemplate.convertAndSend(
                    "/user/sologame/" + login + "/render",
                    games.get(login), jwtResponse.headers());
        }
        else {
            messagingTemplate.convertAndSend(
                    "/user/sologame/" + login + "/render",
                    games.get(login));
        }
    }
    @MessageMapping("/sologame/turn/{update}")
    public void TurnInGame(SGResponse response, @DestinationVariable String update){
        games.get(response.getLogin()).moveSnake(response.getTurn());
        if(update.equals("1")) {
            JWTResponses jwtResponse = new JWTResponses();
            jwtResponse.setResponse(response.getLogin());
            messagingTemplate.convertAndSend(
                    "/user/sologame/" + response.getLogin() + "/render",
                    games.get(response.getLogin()),
                    jwtResponse.headers());
        }
        else {
            messagingTemplate.convertAndSend(
                    "/user/sologame/" + response.getLogin() + "/render",
                    games.get(response.getLogin()));
        }
        if(!games.get(response.getLogin()).getIsPlaying())  games.remove(response.getLogin());
    }
    @MessageMapping("/sologame/end/{update}")
    public void EndGame(String login){
        games.remove(login);
    }
}
