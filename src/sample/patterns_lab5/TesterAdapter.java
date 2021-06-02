package sample.patterns_lab5;

import sample.entity.Tester;
import sample.service.PrintInfoAboutWork;

public class TesterAdapter implements PrintInfoAboutWork {

    private final Tester tester;

    public TesterAdapter(Tester tester) {
        this.tester = tester;
    }

    @Override
    public String printWork() {
        return tester.testCode();
    }
}
