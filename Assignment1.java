import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.util.Scanner;

public class Assignment1 {

    public void inputBirth() {

        Scanner in = new Scanner(System.in);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd")
                .withResolverStyle(java.time.format.ResolverStyle.STRICT);

        while (true) {

            System.out.println("生年月日を入力してください。");
            System.out.println("ただし、yyyy/mm/dd形式で入力してださい。例）2000/01/01");

            String birth = in.nextLine().trim();

            try {
                LocalDate.parse(birth, formatter);

                // 入力した文字列があっているかの条件

                // 合っていた場合
                // 入力した生年月日を分ける
                String birth1 = birth.substring(0, 4);
                String birth2 = birth.substring(5, 7);
                String birth3 = birth.substring(8, 10);

                int num1 = Integer.parseInt(birth1);
                int num2 = Integer.parseInt(birth2);
                int num3 = Integer.parseInt(birth3);

                // 現在の日時を取得
                LocalDateTime nowDate = LocalDateTime.now();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
                String formatNowDate = dtf.format(nowDate);

                // 現在の日時を年・月・日を分ける
                String dtf1 = formatNowDate.substring(0, 4);
                String dtf2 = formatNowDate.substring(4, 6);
                String dtf3 = formatNowDate.substring(6, 8);
                /* System.out.println(dtf1 + "年" + dtf2 + "月" + dtf3+ "日" ); */

                int now1 = Integer.parseInt(dtf1);
                int now2 = Integer.parseInt(dtf2);
                int now3 = Integer.parseInt(dtf3);

                // 月が1～12月までの入力範囲かどうか
                if (num2 >= 1 && num2 <= 12) {
                    /* System.out.println(num1 + "年" + num2 + "月" + num3 + "日ですね"); */

                    // 今年、年を迎えたか迎えてないか
                    if (now2 >= num2 || now2 == num2 && now3 > num3) {
                        System.out.println("現在" + (now1 - num1) + "歳ですね");

                    } else {
                        System.out.println("現在" + (now1 - num1 - 1) + "歳ですね");
                    }

                    break;

                } else {

                    // 月が1~12月までではなかった場合（反していた場合）
                    System.out.println("-----");
                    System.out.println("入力が誤っています。月エラー");
                    System.out.println("-----");

                }

            } catch (DateTimeException e) {
                // 入力に誤りがあった場合
                System.out.println("-----");
                System.out.println("yyyy/mm/dd 形式で入力してください。記入エラー");
                System.out.println("-----");
                continue;
            }

        }
        in.close();

    }


    public static void main(String[] args) {
    Assignment1 ass5 = new Assignment1();
    ass5.inputBirth();

}

}


