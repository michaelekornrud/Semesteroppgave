package SleeperThread;


import javafx.concurrent.Task;

public class SleeperThread extends Task<Integer> {
    private final int value;
    //private final String msg;
    //private final String title;

    public SleeperThread(int value, String msg, String title){
        this.value = value;
        //this.msg = msg;
        //this.title = title;
    }

    @Override
    protected Integer call() {
        try{
            //AlertBox.display(title,msg);
            Thread.sleep(value);
            //AlertBox.display(title,msg);
            //Thread.sleep(value);
        }
        catch (InterruptedException e){

        }
        return value;
    }
}
