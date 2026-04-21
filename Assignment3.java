import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



class Product3 {
    int num;
    String numText;
    String name;
    int price;
    int stock;

    public Product3(int num, String numText, String name, int price, int stock) {
        this.num = num;
        this.numText = numText;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}

public class Assignment3 {
    public static void main(String[] args) {
        List<Product3> productList = new ArrayList<>();
        Map<Integer, Product3> productMap = new HashMap<>();
        int successCount = 0;
        int errorCount = 0;

        try {
            File f = new File("AssignProduct.csv");
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                try {
                    String[] data = line.split(",");
                    if (data.length != 4) {
                        System.out.println("エラー： CSV形式が正しくありません。 行： " + line);
                        errorCount++;
                        continue;
                    }

                    String numText = data[0];
                    int num = Integer.parseInt(numText);
                    String name = data[1];
                    int price = Integer.parseInt(data[2]);
                    int stock = Integer.parseInt(data[3]);

                    boolean valid = true;

                    if (name.length() < 1 || name.length() > 12) {
                        System.out.println("エラー： 商品名は1文字以上12文字以下でなければなりません。 行： " + line);
                        valid = false;
                    }
                    if (String.valueOf(Math.abs(price)).length() > 9) {
                        System.out.println("エラー： 価格は9桁以下でなければなりません。 行： " + line);
                        valid = false;
                    }
                    if (String.valueOf(Math.abs(stock)).length() > 9) {
                        System.out.println("エラー： 在庫数は9桁以下でなければなりません。 行： " + line);
                        valid = false;
                    }

                    if (valid) {
                        if (productMap.containsKey(num)) {
                            System.out.println("重複商品番号 " + numText + " を上書きしました。");
                            Product3 p = productMap.get(num);
                            p.name = name;
                            p.price = price;
                            p.stock = stock;
                        } else {
                            Product3 p = new Product3(num, numText, name, price, stock);
                            productList.add(p);
                            productMap.put(num, p);
                        }
                        successCount++;
                    } else {
                        errorCount++;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("エラー： 数値の形式が正しくありません。 行： " + line);
                    errorCount++;
                    continue;
                }

                // ここに表示を移動しない
            }
            br.close();

            // 全件読み込み後に表示
            

            Collections.sort(productList, Comparator.comparingInt(p -> p.num));

            System.out.println("格納されたリスト");
            for (Product3 p : productList) {
                System.out.println("商品番号：" + p.numText + " 商品名：" + p.name + " 価格：" + p.price + " 在庫数：" + p.stock);
            }

            System.out.println("正常件数：" + successCount);
            System.out.println("異常件数：" + errorCount);

            br.close();

        } catch (IOException e) {
            System.out.println("ファイル読み込みエラー：" + e.getMessage());
        }

    }

}
