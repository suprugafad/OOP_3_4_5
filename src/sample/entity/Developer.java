package sample.entity;

import sample.entity.DeveloperWork;
import sample.entity.ItSpecialist;
import sample.entity.TypeWorker;

public class Developer extends ItSpecialist implements DeveloperWork {

    public enum ProgramLanguage {
        JAVA,
        CPP,
        PYTHON,
        PHP;
    }

    public Developer() {
        typeWorker = TypeWorker.DEVELOPER;
    }

    private ProgramLanguage programLanguage;

    public ProgramLanguage getProgramLanguage() {
        return programLanguage;
    }


    public void setProgramLanguage(ProgramLanguage programLanguage) {
        this.programLanguage = programLanguage;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "programLanguage=" + programLanguage +
                ", currentProject='" + currentProject + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", decryption='" + decryption + '\'' +
                ", typeWorker=" + typeWorker +
                '}';
    }

    @Override
    public String writeCode() {
        return "write code\n";
    }

    @Override
    public String writeDocumentation() {
        return "write documentation\n";
    }
}
