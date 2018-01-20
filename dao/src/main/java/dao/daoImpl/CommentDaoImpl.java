package dao.daoImpl;

import dao.common.BaseDaoImpl;
import dao.interfaceDao.CommentDao;
import entity.Comment;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDao {
}
