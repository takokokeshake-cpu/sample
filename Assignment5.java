import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Assignment5 {
    
    //ststic変数
    static int[] COINS = {500, 100, 50, 10, 5, 1};

    //インスタンス変数
    List<Integer> salesList = new ArrayList<>();
    List<Product5> productList = new ArrayList<>();


    public static void main(String[] args) {
        Assignment5 vm  = new Assignment5();

        Thread csvThread = new Thread(new CsvLoaderThread(vm.productList, vm.salesList));
        Thread stockThread = new Thread(new StockMonitorThread(vm.productList));
        csvThread.start();
        stockThread.start();

        try{
            Thread.sleep(500); //ちょっと待機
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        

        while (true){

            int price = vm.selectProduct();

            List<Integer> insertedList = vm.insertCoins(price);

            if (insertedList == null){
                continue; //商品選択に戻る
            }

            int amount = 0;
            for (int c : insertedList){
                amount += c;
            }
            amount -= price;

            try{
                vm.calcChange(amount, insertedList);
            } catch (OutOfChangeException e){
                System.out.println(e.getMessage());
                continue; //最初に戻る
            }
        }
    }


    //商品選択のメソッド
    public int selectProduct(){
        Scanner sc = new Scanner(System.in);

        while(true){

            for (Product5 p : productList) {
                System.out.println(p.getNumber() + " " + p.getName() + " " + p.getPrice() );
            }

            System.out.println("商品コードを入力してください: ");
            int inputnum = sc.nextInt();

            
            //商品コードが正しい場合
            Product5 selected = null;
            for(Product5 p : productList){
                if (p.getNumber() == inputnum){
                    selected = p;
                    break;
                }
            }

            //商品コードが一致しない場合
            if (selected == null) {
                System.out.println("商品コードが誤っています");
                continue;  //商品コード入力に戻る
            }

            //商品コードはあっているが、在庫がない場合
            if (selected.getStock() <= 0) {
                System.out.println("選択商品在庫なし");
                continue;  //商品コード入力に戻る
            }

            System.out.println(selected.getNumber() + " " + selected.getName() + " " + selected.getPrice() );

            return selected.getPrice();

        }
    }
    
    //コイン投入メソッド
    public List<Integer> insertCoins(int price){
        Scanner sc = new Scanner(System.in);
        List<Integer> insertedList = new ArrayList<>(); //投入金額のリスト

        while (true){
            System.out.println("コインを投入してください（CANCEL  で取消）: ");

            String inputcoin = sc.next();

            if (inputcoin.equals("CANCEL")){
                insertedList.clear(); //投入金額のリストをクリア
                return null; //入力に戻る
            }

            try{
                int coin = Integer.parseInt(inputcoin); //stringをintに変換

                boolean validCoin = false;
                for (int c : COINS){
                    if (c == coin){
                        validCoin = true;
                        break;
                    }
                }


                if (!validCoin){
                    System.out.println("金額エラー");
                    continue; //コイン投入に戻る
                }

                insertedList.add(coin); //投入金額のリストに追加

                int total = 0;
                for (int c : insertedList){
                    total += c;
                }


                if (total >= price){
                    System.out.println("投入金額は" + total + "円です"); //投入金額の表示
                    return insertedList; //お釣りの計算に進む
                }

            } catch (NumberFormatException e){
                System.out.println("金額エラー");
            }

        }
        
    }

    //お釣りのメソッド
    public List<Integer> calcChange(int amount, List<Integer> insertedList) throws OutOfChangeException {
        List<Integer> changeList = new ArrayList<>();
        int remaining = amount;

        for (int coin : COINS){
            while (remaining >= coin){
                int index = salesList.indexOf(coin);
                if (index == -1) break; //このコインは在庫なし

                salesList.set(index, coin * -1);
                changeList.add(coin);
                remaining -= coin;
            }
        }

        if (remaining > 0) {

            //釣銭切れの場合
            for (int i = 0; i < salesList.size(); i++){
                if (salesList.get(i) < 0){
                    salesList.set(i, salesList.get(i) * -1);
                }
            }

            throw new OutOfChangeException("釣銭切れです");

        }

        for (int i = 0; i < salesList.size(); i++){
            if (salesList.get(i) < 0){
                salesList.set(i,0);
            }
        }

        //おつりの表示
        salesList.addAll(insertedList);

            for (int c : changeList) {
                System.out.println( "おつり:  "+ c + "円 ");

            }

        return changeList;
    }
}
