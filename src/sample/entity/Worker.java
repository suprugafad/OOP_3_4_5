package sample.entity;

import java.io.Serializable;

public abstract class Worker implements Serializable {
    protected String firstname;
    protected String lastname;
    protected String decryption;
    protected TypeWorker typeWorker;
    protected String aboutWork;

    public Worker() {

    }

    public Worker(String firstname, String lastname, String decryption, TypeWorker typeWorker) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.decryption = decryption;
        this.typeWorker = typeWorker;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDecryption() {
        return decryption;
    }

    public void setDecryption(String decryption) {
        this.decryption = decryption;
    }

    public TypeWorker getTypeWorker() {
        return typeWorker;
    }

    public String getAboutWork() {
        return aboutWork;
    }

    public void setAboutWork(String aboutWork) {
        this.aboutWork = aboutWork;
    }
}
