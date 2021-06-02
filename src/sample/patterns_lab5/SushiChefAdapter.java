package sample.patterns_lab5;

import sample.entity.SushiChef;
import sample.service.PrintInfoAboutWork;

public class SushiChefAdapter implements PrintInfoAboutWork {

    private final SushiChef sushiChef;

    public SushiChefAdapter(SushiChef sushiChef) {
        this.sushiChef = sushiChef;
    }

    @Override
    public String printWork() {
        return sushiChef.makeSushi();
    }
}
