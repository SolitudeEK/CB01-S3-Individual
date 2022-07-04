package nl.fontys.game.Interface;

import nl.fontys.game.Object.AuthorizationCredentials;

public interface IAuthorization {
    public boolean Register(AuthorizationCredentials authorizationCredentials);
    public boolean AuthorizationEnter(AuthorizationCredentials authorizationCredentials);
}
