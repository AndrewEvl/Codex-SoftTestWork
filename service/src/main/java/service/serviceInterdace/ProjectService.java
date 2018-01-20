package service.serviceInterdace;

import entity.Project;

import java.util.List;


public interface ProjectService {

    void save (Project project);

    List<Project> getAll ();

    Project findById (Long id);

    void update (Project project);

    void delete (Project project);
}
