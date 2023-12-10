package mobileclientassetmanagement.src.entity.project;

import java.util.List;

public interface ProjectInterface {
    void add(Project project);
    void update(Integer projectID, Project project);
    void delete(Integer projectID);
}
