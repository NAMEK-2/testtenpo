package tenpo.api.rest.daniel.leon.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" )
    private Integer id;
    @Column(name = "user_name" )
    private String userName;
    @Column(name = "password" )
    private String password;
    @Column(name = "token")
    private String token;

}
