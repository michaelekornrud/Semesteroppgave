package SleeperThread;

import javafx.concurrent.Task;
import javafx.fxml.Initializable;

public class SleeperThread extends Task<Integer> {
    private final int value;

    public SleeperThread(int value){
        this.value = value;
    }

    @Override
    protected Integer call() {
        try{
            Thread.sleep(1500);
        }
        catch (InterruptedException e){

        }
        return value;
    }
}
