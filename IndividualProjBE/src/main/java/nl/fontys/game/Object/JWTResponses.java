package nl.fontys.game.Object;

import nl.fontys.game.JWT.JWTAccess;
import nl.fontys.game.JWT.JWTRefresh;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JWTResponses {
    private final JWTAccess jwtAccess;
    private final JWTRefresh jwtRefresh;
    private String access;
    private String refresh;
    public JWTResponses()
    {
        this.jwtAccess = new JWTAccess();
        this.jwtRefresh = new JWTRefresh();
    }
    public void setResponse(String login)
    {
        this.access = jwtAccess.generateToken(login);
        this.refresh= jwtRefresh.generateToken(login);
        //System.out.println(basic+" "+refresh);
    }
    public String getAccess(){ return access;}
    public String getRefresh(){ return refresh;}
    public Map<String, Object> headers(){
        return Map.of("access", access, "refresh", refresh);
    }



}
