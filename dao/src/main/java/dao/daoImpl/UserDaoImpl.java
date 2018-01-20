package dao.daoImpl;

import dao.common.BaseDaoImpl;
import dao.interfaceDao.UserDao;
import entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
}
