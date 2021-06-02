package sample.entity;

import sample.entity.PizzaChefWork;
import sample.entity.Chef;
import sample.entity.TypeWorker;

public class PizzaChef extends Chef implements PizzaChefWork {
    private boolean isKnowItalicHistory;

    public PizzaChef() {
        typeWorker = TypeWorker.PIZZACHEF;
    }


    public boolean getKnowledgeOfItalicHistory() {
        return isKnowItalicHistory;
    }

    public void setKnowledgeOfItalicHistory(boolean knowItalicHistory) {
        isKnowItalicHistory = knowItalicHistory;
    }

    @Override
    public String mixTheDough() {
        return "mixing the dough\n";
    }
}
