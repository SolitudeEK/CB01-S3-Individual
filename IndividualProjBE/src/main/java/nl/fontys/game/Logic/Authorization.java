package nl.fontys.game.Logic;

import nl.fontys.game.Interface.IAuthorization;
import nl.fontys.game.Repository.ACRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import nl.fontys.game.Object.AuthorizationCredentials;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Component
public class Authorization implements IAuthorization {
    @Autowired
    private ACRepository repository;
    public boolean AuthorizationEnter(AuthorizationCredentials authorizationCredentials)
    {
        return repository.existsByLoginAndPassword
                (authorizationCredentials.getLogin(), authorizationCredentials.getPassword());
    }
    public boolean Register(AuthorizationCredentials authorizationCredentials)
    {
        if(repository.existsByLoginAndPassword
                (authorizationCredentials.getLogin(), authorizationCredentials.getPassword()))
        {
            return false;
        }
        else
        {
            repository.save(authorizationCredentials);
            return true;
        }
    }

}
