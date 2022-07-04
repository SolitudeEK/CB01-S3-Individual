package nl.fontys.game.Controller;

import nl.fontys.game.Interface.IMultiGame;
import nl.fontys.game.Logic.MultiGame;
import nl.fontys.game.Object.JWTResponses;
import nl.fontys.game.Object.MGRequest;
import nl.fontys.game.Object.RoomRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@EnableAsync
public class MultiGameController {
    private  Map<String, List<String>> rooms=new HashMap<>(); //RoomName - users in it
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    private Map<String, IMultiGame> games = new HashMap<>();//Roomname - game
    private Map<String, Integer> turnHelper = new HashMap<>();//User Login - turn
    @MessageMapping("/mutiplayer/create/{update}")
    public void createRoom(RoomRequest room, @DestinationVariable String update)
    {
        Map<String, Object> headers= new HashMap<>();
        if(update.equals("1")){
            JWTResponses jwtResponse = new JWTResponses();
            jwtResponse.setResponse(room.getLogin());
            headers=jwtResponse.headers();
        }
        if(!rooms.containsKey(room.getName()))
        {
                List<String> login = new ArrayList<>();
                login.add(room.getLogin());
                rooms.put(room.getName(), login);
            messagingTemplate.convertAndSend(
                    "/user/room/"+room.getLogin()+"/result",
                    "Successful!",
                    headers
            );
        }
        else {
            messagingTemplate.convertAndSend(
                    "/user/room/"+room.getLogin()+"/result",
                    "Already used!",
                    headers
            );
        }
    }
    @MessageMapping("/mutiplayer/enter/{update}")
    public void joinRoom(RoomRequest room, @DestinationVariable String update)
    {
        Map<String, Object> headers= new HashMap<>();
        if(update.equals("1")){
            JWTResponses jwtResponse = new JWTResponses();
            jwtResponse.setResponse(room.getLogin());
            headers=jwtResponse.headers();
        }
        if(rooms.containsKey(room.getName()))
        {
            rooms.get(room.getName()).add(room.getLogin());
            messagingTemplate.convertAndSend(
                    "/user/room/"+rooms.get(room.getName()).get(0)+"/result",
                    "Start"
            );
            messagingTemplate.convertAndSend(
                    "/user/room/"+rooms.get(room.getName()).get(1)+"/result",
                    "Start",
                    headers
            );
            games.put(room.getName(),new MultiGame());
            this.ThickerGame(room.getName());
        }
        else {
            messagingTemplate.convertAndSend(
                    "/user/room/"+room.getLogin()+"/result",
                    "Not exist!",
                    headers
            );
        }
    }
    @MessageMapping("/mutiplayer/turn/{update}")
    public void MultiGameTurn(MGRequest mgRequest)
    {
        if(turnHelper.containsKey(mgRequest.getLogin())){
            System.out.println("Something going wrong!");
        }
        else {
            turnHelper.put(mgRequest.getLogin(), mgRequest.getTurn());
            if (turnHelper.containsKey(rooms.get(mgRequest.getRoom()).get(0)) &&
                    turnHelper.containsKey(rooms.get(mgRequest.getRoom()).get(1))) { //CHECK if both got Turn
                games.get(mgRequest.getRoom()).moveSnakes(turnHelper.get(rooms.get(mgRequest.getRoom()).get(1)),
                        turnHelper.get(rooms.get(mgRequest.getRoom()).get(0))); //make
                turnHelper.remove(rooms.get(mgRequest.getRoom()).get(0));
                turnHelper.remove(rooms.get(mgRequest.getRoom()).get(1));
                this.SendFrame(mgRequest.getRoom());
                if(!games.get(mgRequest.getRoom()).getIsPlaying()) {
                    this.DeleteGame(mgRequest.getRoom());
                }
                }
            }
        }

    @Async
    private void ThickerGame(String roomName){
        messagingTemplate.convertAndSend(
                "/user/room/"+roomName+"/thick",
                "turn"
        );
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            System.out.println(e+"");
        }
        if(games.get(roomName).getIsPlaying())
            this.ThickerGame(roomName);
    }

    @Async
    private void DeleteGame(String room){
        try {
            Thread.sleep(1000);
            games.remove(room);
            rooms.remove(room);
        } catch (InterruptedException e) {
            System.out.println(e+"");
        }
    }
    @Async
    private void SendFrame(String room){
        try{
            Thread.sleep(100);
            messagingTemplate.convertAndSend("/user/room/"+room+"/render",
                    games.get(room)
            );
        }catch (InterruptedException e) {
            System.out.println(e+"");
        }
    }

}
