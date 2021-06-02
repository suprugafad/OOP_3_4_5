package sample.entity;

import sample.entity.ItSpecialist;
import sample.entity.ManagerWork;
import sample.entity.TypeWorker;

public class Manager extends ItSpecialist implements ManagerWork {
    private String nameProject;
    private int countPersonsInProject;

    public Manager() {
        typeWorker = TypeWorker.MANAGER;
    }

    public Manager(String nameProject, int countPersonInProject) {
        this.nameProject = nameProject;
        this.countPersonsInProject = countPersonInProject;
    }

    public String getNameProject() {
        return nameProject;
    }

    public void setNameProject(String nameProject) {
        this.nameProject = nameProject;
    }

    public int getCountPersonInProject() {
        return countPersonsInProject;
    }

    public void setCountPersonInProject(int countPersonInProject) {
        this.countPersonsInProject = countPersonInProject;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "nameProject='" + nameProject + '\'' +
                ", countPersonInProject=" + countPersonsInProject +
                '}';
    }

    @Override
    public String manageProject() {
        return "manage project\n";
    }
}
