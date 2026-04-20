import java.io.BufferedReader;
import java.io.File;
import java.util.List;
import java.io.FileReader;

public class CsvLoaderThread implements Runnable {

    private List<Product5> productList;
    private List<Integer> salesList;
    private static final String FILE_PATH = "drinkproduct5.csv";

    public CsvLoaderThread(List<Product5> productList, List<Integer> salesList) {
        this.productList = productList;
        this.salesList = salesList;
    }

    @Override
    public void run() {

        while (true) {
            try {
                File file = new File(FILE_PATH);

                if (!file.exists()) {
                    Thread.sleep(60 * 60 * 1000);
                    continue;
                }

                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {
                    String[] columns = line.split(",", 2);
                    String recordType = columns[0];

                    if (recordType.equals("1")) {

                        updateProduct(columns[1]);

                    } else if (recordType.equals("2")) {

                        updateSales(columns[1]);

                    }
                }
                br.close();

                Thread.sleep(60 * 60 * 1000);

            } catch (InterruptedException e) {
                break;
                // スレッドが割り込まれた場合の処理
            } catch (Exception e) {
                break;
            }
        }
    }

    private void updateProduct(String data) {

        String[] items = data.split("/", -1);

        int productNum = Integer.parseInt(items[0]);

        Product5 target = null;
        for (Product5 p : productList) {
            if (p.getNumber() == productNum) {
                target = p;
                break;
            }
        }

        if (target == null) {
            target = new Product5(productNum, "", 0, 0);
            productList.add(target);
        }

        if (items.length > 1 && !items[1].isEmpty())
            target.setName(items[1]);

        if (items.length > 2 && !items[2].isEmpty())
            target.setPrice(Integer.parseInt(items[2]));

        if (items.length > 3 && !items[3].isEmpty())
            target.setStock(Integer.parseInt(items[3]));

        /*
         * if (!items[1].isEmpty()) {
         * product.setName(items[1]);
         * }
         */
    }

    private void updateSales(String data) {
        String[] items = data.split("/", -1);

        int coinType = Integer.parseInt(items[0]);
        int count = Integer.parseInt(items[1]);

        for (int i = 0; i < count; i++) {
            salesList.add(coinType);
        }

    }
}
