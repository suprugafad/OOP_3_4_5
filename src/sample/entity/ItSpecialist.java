package sample.entity;

public abstract class ItSpecialist extends Worker {
    protected String currentProject;

    public String getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(String currentProject) {
        this.currentProject = currentProject;
    }
}
