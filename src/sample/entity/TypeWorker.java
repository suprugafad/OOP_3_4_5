package sample.entity;

public enum TypeWorker {
    PIZZACHEF("PizzaChef", new PizzaChef()),
    DEVELOPER("Developer", new Developer()),
    MANAGER("Manager", new Manager()),
    SUSHICHEF("SushiChef", new SushiChef()),
    TESTER("Tester", new Tester());

    private final String name;
    private final Worker worker;

    TypeWorker(String name, Worker worker) {
        this.name = name;
        this.worker = worker;
    }

    public String getName() {
        return name;
    }

    public Worker getWorker() {
        return worker;
    }


}
