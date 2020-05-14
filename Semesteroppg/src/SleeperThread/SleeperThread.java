package SleeperThread;


import javafx.concurrent.Task;

public class SleeperThread extends Task<Integer> {
    private final int value;

    public SleeperThread(int value, String msg, String title){
        this.value = value;
    }

    @Override
    protected Integer call() {
        try{
            Thread.sleep(value);
        }
        catch (InterruptedException e){
            e.printStackTrace();

        }
        return value;
    }
}
