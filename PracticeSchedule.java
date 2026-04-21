import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PracticeSchedule {
    
    public static void main(String[] args) throws Exception{
        //予約係（裏方）
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

         service.schedule(() -> {

            System.out.println("おはよう");
            //service.shutdown();
            //System.exit(0);
        }, 5, TimeUnit.SECONDS); //５秒たったら実行するよ


        service.schedule(() -> {

            System.out.println("Hello, World!");
            //service.shutdown();
            //System.exit(0);
        }, 10, TimeUnit.SECONDS); //１０秒たったら実行するよ

        service.schedule(() -> {

            System.out.println("こんにちは");
            //service.shutdown();
           //System.exit(0);
        }, 15, TimeUnit.SECONDS); //１０秒たったら実行するよ

        service.schedule(() -> {

            System.out.println("おやすみ");
            service.shutdown();
            System.exit(0);
        }, 20, TimeUnit.SECONDS); //１０秒たったら実行するよ

        int count = 0;
        while (true) {
            //Thread.sleep(1000);
            TimeUnit.SECONDS.sleep(1);//　でも同じことができる(多分何かimportが必要)
            System.out.println((++count)  + "秒");
            
        }
    }
}

/*
public class VendingMachine {
    // インスタンス変数：ずっと覚えておく
    private int totalSales = 0; 

    public void calculateTax(int price) {
        // ローカル変数：計算のためだけに一時的に使う
        double tax = 0.1; 
        int result = (int)(price * (1 + tax));
        
        System.out.println("税込み価格: " + result);
    } // ここで tax と result は消滅する
}
    
*/
