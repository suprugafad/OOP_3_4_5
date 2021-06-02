package sample.entity;

import sample.entity.ItSpecialist;
import sample.entity.TesterWork;
import sample.entity.TypeWorker;

public class Tester extends ItSpecialist implements TesterWork {
    @Override
    public String testCode() {
        return "test code\n";
    }

    public enum TypeTester {
        USER_TESTER,
        DEVELOPER_TESTER,
        ANALYTICAL_TESTER;
    }

    public Tester() {
        typeWorker = TypeWorker.TESTER;
    }

    private TypeTester typeTester;

    public TypeTester getTypeTester() {
        return typeTester;
    }

    public void setTypeTester(TypeTester typeTester) {
        this.typeTester = typeTester;
    }
}
