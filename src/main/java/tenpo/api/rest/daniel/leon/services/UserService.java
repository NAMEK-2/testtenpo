package tenpo.api.rest.daniel.leon.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tenpo.api.rest.daniel.leon.repositories.UserEntityRepository;

@Service
public class UserService {

    @Autowired
    UserEntityRepository repository;

    public String getTokenForUserName(String userName){
        return repository.findUserByUserName(userName).getToken();
    }

}
