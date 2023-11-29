package mobileclientassetmanagement.src.entity.project;

import java.util.List;

public interface ProjectInterface {
    Project get(Integer projectID);
    void add(Project project);
    void update(Integer projectID, Project project);
    void delete(Integer projectID);
    List<Project> getAll();
}
