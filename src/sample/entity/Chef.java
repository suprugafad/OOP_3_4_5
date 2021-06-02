package sample.entity;

public abstract class Chef extends Worker {
    protected int levelOfCooking;

    public int getLevelOfCooking() {
        return levelOfCooking;
    }

    public void setLevelOfCooking(int levelOfCooking) {
        this.levelOfCooking = levelOfCooking;
    }
}
