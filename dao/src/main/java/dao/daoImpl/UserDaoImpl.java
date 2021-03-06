package dao.daoImpl;

import com.querydsl.jpa.impl.JPAQuery;
import dao.common.BaseDaoImpl;
import dao.interfaceDao.UserDao;
import dto.UserDto;
import entity.QUser;
import entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User fundByFirstAndLastName(String firstName, String lastName) {
        Session session = getSessionFactory().getCurrentSession();
        QUser qUser = new QUser("myUser");
        JPAQuery<User> query = new JPAQuery<>(session);
        query.select(qUser)
                .from(qUser)
                .where(qUser.firstName.eq(firstName)
                        .and(qUser.lastName.eq(lastName)));
        return query.fetchOne();
    }

    @Override
    public User findByMail(String mail) {
        Session session = getSessionFactory().getCurrentSession();
        QUser qUser = new QUser("myUser");
        JPAQuery<User> query = new JPAQuery<>(session);
        query.select(qUser)
                .from(qUser)
                .where(qUser.mail.eq(mail));
        return query.fetchOne();
    }

    @Override
    public User findByToken(String token) {
        Session session = getSessionFactory().getCurrentSession();
        QUser qUser = new QUser("myUser");
        JPAQuery<User> query = new JPAQuery<>(session);
        query.select(qUser)
                .from(qUser)
                .where(qUser.token.eq(token));
        return query.fetchOne();
    }

    @Override
    public UserDto findByIdDto(Long id) {
        Session session = getSessionFactory().getCurrentSession();
        QUser qUser = new QUser("myUser");
        JPAQuery<User> query = new JPAQuery<>(session);
        query.select(qUser)
                .from(qUser)
                .where(qUser.id.eq(id));
        User user = query.fetchOne();
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setMail(user.getMail());
        userDto.setTasks(user.getTasks());
        userDto.setProjects(user.getProjects());
        return userDto;
    }
}
