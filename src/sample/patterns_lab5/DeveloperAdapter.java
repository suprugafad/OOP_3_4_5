package sample.patterns_lab5;

import sample.entity.Developer;
import sample.service.PrintInfoAboutWork;

public class DeveloperAdapter implements PrintInfoAboutWork {

    private final Developer developer;

    public DeveloperAdapter(Developer developer) {
        this.developer = developer;
    }

    @Override
    public String printWork() {
        return developer.writeCode() + developer.writeDocumentation();
    }
}
