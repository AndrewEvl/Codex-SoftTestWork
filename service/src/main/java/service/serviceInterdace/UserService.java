package service.serviceInterdace;

import entity.User;

import java.util.List;

public interface UserService {

    void save (User user);

    List<User> getAll ();

    User findById (Long id);

    void update (User user);

    void delete (User user);
}
