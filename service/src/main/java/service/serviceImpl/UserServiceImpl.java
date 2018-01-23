package service.serviceImpl;

import dao.interfaceDao.UserDao;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import service.serviceInterdace.UserService;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save(User user) {
        String password = user.getPassword();
        String passwordEncoder = passwordEncoder(password);
        user.setPassword(passwordEncoder);
        userDao.save(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public User findByMail(String mail) {
        return userDao.findByMail(mail);
    }

    @Override
    public User findByLastAndFirstName(String firstName, String lastName) {
        return userDao.fundByFirstAndLastName(firstName, lastName);
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        User userByMail = userDao.findByMail(mail);
        if (userByMail ==null){
            throw new UsernameNotFoundException("Wrong mail" + mail);
        }
        return new org.springframework.security.core.userdetails
                .User(userByMail.getMail(), userByMail.getPassword(), getUserAuthorizes(userByMail));
    }

    private Set<GrantedAuthority> getUserAuthorizes(User user){
        return Collections.singleton(new SimpleGrantedAuthority(user.getAuthorization().toString()));
    }

    private String passwordEncoder (String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
