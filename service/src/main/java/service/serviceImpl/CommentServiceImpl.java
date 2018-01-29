package service.serviceImpl;

import com.querydsl.jpa.impl.JPAQuery;
import dao.common.BaseDaoImpl;
import dao.interfaceDao.CommentDao;
import entity.Comment;
import entity.QComment;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.serviceInterdace.CommentService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl extends BaseDaoImpl<Comment> implements CommentService {

    private final CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Override
    public void save(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public List<Comment> getAll() {
        return commentDao.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentDao.findById(id);
    }

    @Override
    public void update(Comment comment) {
            commentDao.update(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentDao.delete(comment);
    }


    @Override
    public List<Comment> findByTaskId(Long id) {
        Session session = getSessionFactory().getCurrentSession();
        QComment qComment = new QComment("myComment");
        JPAQuery<Comment> query = new JPAQuery<>(session);
        query.select(qComment).from(qComment).where(qComment.task.id.eq(id));
        return query.fetchAll().fetch();
    }
}
