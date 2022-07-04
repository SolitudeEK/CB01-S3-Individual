package nl.fontys.game.Websocket;

import com.fasterxml.jackson.databind.ObjectMapper;

import nl.fontys.game.JWT.JWTAccess;
import nl.fontys.game.JWT.JWTRefresh;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

@Configuration
@Order(HIGHEST_PRECEDENCE + 99)
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final JWTAccess jwt=new JWTAccess();
    private final JWTRefresh jwtRefresh=new JWTRefresh();
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker( "/user");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:3001", "http://localhost:3000")
                .withSockJS();
    }
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
            registration.interceptors(new ChannelInterceptor() {
                @Override
                public Message<?> preSend(Message<?> message, MessageChannel channel) {
                    StompHeaderAccessor accessor =
                            MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                    if(accessor!=null) {
                        String con = accessor.getHeader("simpMessageType").toString();
                        if (con.equals("CONNECT") || con.equals("SUBSCRIBE")||con.equals("DISCONNECT"))
                            return message;
                        else if (con.equals("MESSAGE")) {
                            String token = accessor.getNativeHeader("token").
                                    toString().replaceAll("[\"\\[\\]]","");
                            String destination = accessor.getNativeHeader("destination").
                                    toString().replaceAll("[\"\\[\\]]", "");
                            if (token.equals("Credentials")&&
                                    (destination.equals("/app/authorization") || destination.equals("/app/registration")))
                            {
                                return message;
                            }
                            if(accessor.getNativeHeader("refresh")!=null) {
                                String refresh= accessor.getNativeHeader("refresh").
                                        toString().replaceAll("[\"\\[\\]]", "");
                                try {
                                    if (jwt.validateToken(token, jwt.extractUsername(token))) {
                                        accessor.setDestination(destination+"/0");
                                        return message;
                                    }
                                }
                                catch(Exception e)
                                {
                                    if (jwtRefresh.validateToken(refresh, jwtRefresh.extractUsername(refresh))) {
                                        accessor.setDestination(destination+"/1");
                                        return message;
                                    }
                                }
                            }
                        }
                    }
                    return null;
                }
            });
    }



    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setContentTypeResolver(resolver);
        messageConverters.add(converter);
        return false;
    }


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer () {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*")
                        .allowedOrigins("http://localhost:3001", "http://localhost:3000");
            }
        };
    }
}

