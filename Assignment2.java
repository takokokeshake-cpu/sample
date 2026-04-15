import java.util.InputMismatchException;
import java.util.Scanner;

public class Assignment2 {

    public static class Product {

        private String name;
        private int price;
        private int stock;

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public int getStock() {
            return stock;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }
    }

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);
        Product[] master = new Product[100];
        int count = 0;

        while (true){

            System.out.println("1 商品登録");
            System.out.println("2 商品検索");
            System.out.println("3 終了");
            try {
                int choice = in.nextInt();
                switch (choice) {
                case 1:
                    // 商品登録のロジック
                    while (true) {
                        if (count >= 100) {
                            System.out.println("商品マスタの上限に達しました。");
                            break;
                        }

                        Product p = new Product();
                        System.out.println((count + 1) + "件目の商品を入力");
                        System.out.println("商品名を入力");
                        p.setName(in.next());
                        System.out.println("価格を入力");
                        p.setPrice(in.nextInt());
                        System.out.println("在庫を入力");
                        p.setStock(in.nextInt());

                        master[count] = p;
                        count++;

                        System.out.println("商品登録を継続しますか？(y/n):");
                        String cont = in.next();
                        if (!cont.equalsIgnoreCase("y")) {
                            break;
                        }
                    }

                    break;
                case 2:
                    // 商品一覧表示のロジック
                    System.out.println("検索する商品名を入力");
                    System.out.println("終了する場合は 'END' と入力");
                    String searchName = in.next();
                    if (searchName.equals("END")) {
                        System.out.println("プログラムを終了します.");
                        in.close();
                        return;
                    }
                    boolean found = false;
                    for (int i = 0; i < count; i++) {
                        if (master[i].getName().equals(searchName)) {
                            System.out.println("商品名: " + master[i].getName());
                            System.out.println("価格: " + master[i].getPrice());
                            System.out.println("在庫: " + master[i].getStock());
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("該当する商品が見つかりませんでした。");
                    }
                    break;
                case 3:
                    // 終了のロジック
                    System.out.println("プログラムを終了します.");
                    in.close();
                    return;
                default:
                    System.out.println("無効な選択です。");
                }
            } catch (InputMismatchException e) {
                System.out.println("入力ミスです。");
                in.nextLine(); // 不正な入力を読み捨てる
                continue;
            }
        }
        

        /*(count <= 100) {
            Product p = new Product();
            System.out.println((count + 1) + "件目の商品を入力");
            p.setName(in.next());
            System.out.println("価格を入力");
            p.setPrice(in.nextInt());
            System.out.println("在庫を入力");
            p.setStock(in.nextInt());

            master[count] = p;
            count++;

            System.out.println("商品登録を継続しますか？(y/n):");
            if (!in.next().equalsIgnoreCase("y"))
                break;
        }

        if (count > 101) {
            System.out.println("商品マスタの上限に達しました。");
        }*/
    }

}
