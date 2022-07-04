package nl.fontys.game.Object;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RoomRequest {
    private String login;
    private String name;
    @JsonCreator
    public RoomRequest(@JsonProperty("login") String login, @JsonProperty("name") String name)
    {
        this.login=login;
        this.name=name;
    }
    public String getLogin() {return login;}
    public String getName() {return name;}
}
