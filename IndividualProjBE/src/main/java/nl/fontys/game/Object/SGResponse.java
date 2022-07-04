package nl.fontys.game.Object;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SGResponse {
    private String login;
    private int turn;
    @JsonCreator
    public SGResponse(@JsonProperty("login")String login,@JsonProperty("turn") int turn)
    {
        this.login=login;
        this.turn=turn;
    }
    public SGResponse(){}
    public int getTurn() {
        return turn;
    }
    public String getLogin() {
        return login;
    }

}
