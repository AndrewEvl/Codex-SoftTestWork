package service.serviceInterdace;

import entity.Comment;

import java.util.List;

public interface CommentService {

    void save (Comment comment);

    List<Comment> getAll ();

    Comment findById (Long id);

    void update (Comment comment);

    void delete (Comment comment);

    List<Comment> findByTaskId (Long id);
}
