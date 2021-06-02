package sample.patterns_lab5;

import sample.entity.PizzaChef;
import sample.service.PrintInfoAboutWork;

public class PizzaChefAdapter implements PrintInfoAboutWork {

    private final PizzaChef pizzaChef;

    public PizzaChefAdapter(PizzaChef accountant) {
        this.pizzaChef = accountant;
    }

    @Override
    public String printWork() {
        return pizzaChef.mixTheDough();
    }
}
