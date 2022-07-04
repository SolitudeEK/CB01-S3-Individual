package nl.fontys.game.Repository;

import nl.fontys.game.Object.AuthorizationCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ACRepository extends JpaRepository<AuthorizationCredentials,Long > {
    boolean existsByLoginAndPassword(String login, String password);
    Optional<AuthorizationCredentials> findByLogin(String login);
}
