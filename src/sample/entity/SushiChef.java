package sample.entity;

import sample.entity.Chef;
import sample.entity.SushiChefWork;
import sample.entity.TypeWorker;

public class SushiChef extends Chef implements SushiChefWork {
    private int levelUsingChineseChopsticks;
    private int levelOfSpeedCooking;

    public SushiChef() {
        typeWorker = TypeWorker.SUSHICHEF;
    }

    public SushiChef(int levelUsingChineseChopsticks, int levelOfSpeedCooking) {
        this.levelUsingChineseChopsticks = levelUsingChineseChopsticks;
        this.levelOfSpeedCooking = levelOfSpeedCooking;
    }

    public int getLevelUsingChineseChopsticks() {
        return levelUsingChineseChopsticks;
    }

    public void setLevelUsingChineseChopsticks(int levelUsingChineseChopsticks) {
        this.levelUsingChineseChopsticks = levelUsingChineseChopsticks;
    }

    public int getLevelOfSpeedCooking() {
        return levelOfSpeedCooking;
    }

    public void setLevelOfSpeedCooking(int levellOfSpeedCooking) {
        this.levelOfSpeedCooking = levellOfSpeedCooking;
    }


    @Override
    public String toString() {
        return "SushiChef{" +
                "levelUsingChineseChopsticks=" + levelUsingChineseChopsticks +
                ", levelOfSpeedCooking=" + levelOfSpeedCooking +
                '}';
    }

    @Override
    public String makeSushi() {
        return "wrap rolls\n";
    }
}
