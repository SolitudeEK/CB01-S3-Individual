package nl.fontys.game.Object;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MGRequest {
    private String login;
    private String room;
    private Integer turn;
    @JsonCreator
    public MGRequest(@JsonProperty("login") String login,
                     @JsonProperty("room") String room,
                     @JsonProperty("turn") Integer turn)
    {
        this.turn = turn;
        this.login=login;
        this.room=room;
    }
    public String getLogin() {return login;}
    public String getRoom() {return room;}
    public Integer getTurn() {return turn;}
}
