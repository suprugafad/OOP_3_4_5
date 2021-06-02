package sample.patterns_lab5;

import sample.entity.Manager;
import sample.service.PrintInfoAboutWork;

public class ManagerAdapter implements PrintInfoAboutWork {

    private final Manager manager;

    public ManagerAdapter(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String printWork() {
        return manager.manageProject();
    }
}
