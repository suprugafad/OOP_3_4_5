package sample.service;

public class AboutWorkService {

    private static AboutWorkService instance;

    public static AboutWorkService getInstance() {
        if (instance == null) {
            instance = new AboutWorkService();
        }
        return instance;
    }

    public String printAboutWork(PrintInfoAboutWork printInfoAboutWork) {
        return printInfoAboutWork.printWork();
    }

}
