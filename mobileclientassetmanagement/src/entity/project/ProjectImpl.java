package mobileclientassetmanagement.src.entity.project;

import mobileclientassetmanagement.src.dbmanager.DataManager;
import java.util.Map;

public class ProjectImpl implements ProjectInterface {
    private Map<Integer, Project> projectData;

    ProjectImpl() {
        this.projectData = DataManager.getProjectData();
    }

    @Override
    public void add(Project project) {
        projectData.put(project.getProjectID(), project);
    }

    @Override
    public void update(Integer projectID, Project project) {
      projectData.put(projectID, project);
    }

    @Override
    public void delete(Integer projectID) {
      projectData.remove(projectID);
    }
}
