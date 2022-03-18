package tenpo.api.rest.daniel.leon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tenpo.api.rest.daniel.leon.entities.UserEntity;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findUserOptByUserName(String userName);

    Optional<UserEntity> findUserOptByUserNameAndPassword(String userName,
                                                          String password);

    UserEntity findUserByUserName(String userName);

}