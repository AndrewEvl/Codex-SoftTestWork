package service.serviceImpl;

import dao.interfaceDao.CommentDao;
import entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.serviceInterdace.CommentService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

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
}
