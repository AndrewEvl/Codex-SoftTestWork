package dao.interfaceDao;

import dao.common.BaseDao;
import entity.User;

import java.util.Set;

public interface UserDao extends BaseDao<User> {

    User fundByFirstAndLastName (String firstName, String lastName);

    User findByMail (String mail);
}
