package service.serviceInterdace;

import dto.UserDto;
import entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void save (User user);

    List<User> getAll ();

    User findById (Long id);

    void update (User user);

    void delete (User user);

    User findByMail (String mail);

    User findByLastAndFirstName (String firstName, String lastName);

    User findByToken (String token);

    UserDto findByIdDto (Long id);
}
