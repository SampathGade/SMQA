package entity.project;

public class ProjectFactoryImpl implements ProjectFactory{
    @Override
    public ProjectInterface createProject() {
        return new ProjectImpl();
    }
}
