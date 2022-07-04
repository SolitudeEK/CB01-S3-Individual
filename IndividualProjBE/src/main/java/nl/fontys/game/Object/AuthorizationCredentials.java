package nl.fontys.game.Object;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name ="ACCOUNTS")
public class AuthorizationCredentials {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(name ="login")
    private String login;
    @Column(name = "password")
    private String password;

    public AuthorizationCredentials() {
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }
    public Long getId()
    {
        return this.id;
    }
    @JsonCreator
    public AuthorizationCredentials(@JsonProperty("login") String login,@JsonProperty("password") String password)
    {
        this.login=login;
        this.password=password;
    }

}
