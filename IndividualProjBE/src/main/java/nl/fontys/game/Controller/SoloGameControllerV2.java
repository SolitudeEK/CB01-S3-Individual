package nl.fontys.game.Controller;

import nl.fontys.game.Logic.SoloGame;
import nl.fontys.game.Object.AuthorizationCredentials;
import nl.fontys.game.Repository.ACRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sologame")
public class SoloGameControllerV2 {
    @Autowired
    private SoloGame soloGame;
    @GetMapping("/points")
        @ResponseBody
    public String getPoints()
    {
        return soloGame.getPoints()+"";
    }
    @GetMapping("/field")
        @ResponseBody
    public String getField()
    {
        return soloGame.frameToRender();
    }
    @GetMapping("/snake")
        @ResponseBody
    public String getSnake()
    {
        return soloGame.getSnakePos(0);
    }


}
