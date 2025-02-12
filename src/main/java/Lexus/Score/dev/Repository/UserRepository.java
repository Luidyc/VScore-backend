package Lexus.Score.dev.Repository;

import Lexus.Score.dev.Entity.User.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity,String> {

    Optional<UserEntity> findByLogin(String login);
}
