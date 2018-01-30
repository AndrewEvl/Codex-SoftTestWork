package dao.interfaceDao;

import dao.common.BaseDao;
import dto.UserDto;
import entity.User;

public interface UserDao extends BaseDao<User> {

    User fundByFirstAndLastName (String firstName, String lastName);

    User findByMail (String mail);

    User findByToken (String token);

    UserDto findByIdDto (Long id);
}
