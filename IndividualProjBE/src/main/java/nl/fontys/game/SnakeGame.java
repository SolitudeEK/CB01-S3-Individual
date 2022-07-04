package nl.fontys.game;
import nl.fontys.game.Object.AuthorizationCredentials;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class SnakeGame {
    public static void main(String[] args) {
        SpringApplication.run(SnakeGame.class, args);
    }
}