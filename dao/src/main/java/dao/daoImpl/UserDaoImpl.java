package dao.daoImpl;

import com.querydsl.jpa.impl.JPAQuery;
import dao.common.BaseDaoImpl;
import dao.interfaceDao.UserDao;
import entity.QUser;
import entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Set;

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
        Session session =getSessionFactory().getCurrentSession();
        QUser qUser = new QUser("myUser");
        JPAQuery<User> query = new JPAQuery<>(session);
        query.select(qUser)
                .from(qUser)
                .where(qUser.mail.eq(mail));
        return query.fetchOne();
    }


}
