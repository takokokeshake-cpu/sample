import java.util.List;

public class StockMonitorThread implements Runnable {

    private List<Product5> productList;


    public StockMonitorThread(List<Product5> productList) {
        this.productList = productList;
    }

    @Override
    public void run() {
        while (true) {
            try{
                Thread.sleep(60 * 1000); // 1分ごとに在庫をチェック

                for (Product5 p : productList) {
                    if (p.getStock() <= 10) {
                        System.out.println("商品" + p.getName() + "の在庫が少なくなりました。注文してください。");
                    }
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }
    
}
