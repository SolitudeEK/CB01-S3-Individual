package nl.fontys.game.Test;
import nl.fontys.game.Interface.ISoloGame;
import nl.fontys.game.Logic.SoloGame;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

import static org.mockito.Mockito.*;

public class SoloGameTestM {

    @Test
    public void testVerifyBasics() {
        SoloGame sg = mock(SoloGame.class);
        sg.createFruit();
        verify(sg).createFruit();
        sg.getIsPlaying();
        verify(sg).getIsPlaying();
        sg.getFruitPos();
        verify(sg).getFruitPos();
        sg.moveSnake(0);
        verify(sg).moveSnake(0);
        sg.getSnakePos();
        verify(sg).getSnakePos();
        sg.frameToRender();
        verify(sg).frameToRender();
    }

}
