import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Assignment4 {
    public static void main(String[] args) {
        // ここにコードを追加してください

        try{
            // ファイルを読み込む
            File f = new File("AssignProduct2.csv");
            BufferedReader br = new BufferedReader(new FileReader(f));
            br.readLine(); // ヘッダー行を読み飛ばす
            // ファイルを読み込む

            String line; //

            String csvCfile = "outputC.csv"; // 出力ファイル名C
            String csvTfile = "outputT.csv"; // 出力ファイル名T
            
            
            // line = br.readLine()：１行読んだものを変数lineに書き込むよ
            // != null:１行読み終わって、まだ行があったらループを続けるよ
            while ((line = br.readLine()) != null) {
                /* 失敗作　これだとほかのところも消えてしまう。columnsで各々置換。
                line = line.replace("/",""); // replaceで/を空に
                line = line.replace("-",""); // replaceで-を空に
                line = line.replace(" ",""); // replaceで空白を空に
                */
                String[] columns = line.split(","); // 読み込んだ行をカンマで分割して、配列columnsにいれるよ

                columns[1] = columns[1].replace("/",""); // columns[1]<-birth欄に関して、replaceで/を空に
                columns[3] = columns[3].replace("/",""); // columns[3]<-hire欄に関して、replaceで/を空に
                columns[7] = columns[7].replace("-",""); // columns[7]<-tel欄に関して、replaceで-を空に
                
                if(!columns[0].matches("^[CT]\\d{3}$")){
                    continue; //間違いがあれば、それ以降の書き込み処理を行わず、次の行へ行く
                }

                if (!columns[1].matches("^\\d{4}/\\d{2}/\\d{2}$")) {
                    continue;
                }

                if (!columns[2].matches("男") && !columns[2].matches("女")) {
                    continue;
                }




                

                if (columns[2].equals("男")){   //columns[2]<-gender欄に関して、男であれば０に
                    columns[2] = "0";
                }else if (columns[2].equals("女")){ //女であれば１に変えるよ
                    columns[2] = "1";
                }

                                          //charAtは（）で指定した位置の文字を取得するよ
                char firstChar = columns[0].charAt(0); // columns[0]の最初の文字を取得して、firstChar（最初の文字を確認してくれる）にいれるよ
                if (firstChar == 'C') { // firstCharがCだったら

                    //PrintWriter = ファイルとか書きだすクラス
                    try(PrintWriter pwc = new PrintWriter(new BufferedWriter //新しくcsvファイルを作成するよ

                        //OutputStreamWriter = ファイルに書き出すときの文字コードを指定するクラス。書き出すのに重要
                        (new OutputStreamWriter(new FileOutputStream(csvCfile, true), "UTF-8")))) { // csvCfileにUTF-8など詳細が記載されてるよ
                            line = columns[0] + "," + columns[1] + "," + columns[2]
                             + "," + columns[3] + "," + columns[4] + "," + columns[5]
                              + "," + columns[6] + "," + columns[7]; // 変更された列を再結合するよ
                            pwc.println(line); // lineを書き込むよ
                    }catch (IOException e) {
                        e.printStackTrace(); //既にファイルがある場合、追記するよ（例外処理）
                    }
                } else if (firstChar == 'T') { // firstCharがTだったら
                    //Cのファイルと同じように機能を動かしているよ
                    try(PrintWriter pwt = new PrintWriter(new BufferedWriter
                        (new OutputStreamWriter(new FileOutputStream(csvTfile, true), "UTF-8")))) { // csvTfileにUTF-8など詳細が記載されてるよ
                            line = columns[0] + "," + columns[1] + "," + columns[2]
                             + "," + columns[3] + "," + columns[4] + "," + columns[5]
                              + "," + columns[6] + "," + columns[7]; // 変更された列を再結合するよ
                            pwt.println(line); // lineを書き込むよ
                    }catch (IOException e) {
                        e.printStackTrace(); //既にファイルがある場合、追記するよ（例外処理）
                    }
                }


                

                /* 
                //lineが分割されたから,用いて再度結合するよ
                line = columns[0] + "," + columns[1] + "," + columns[2] + "," + columns[3] + "," + columns[4] + "," + columns[5] + "," + columns[6] + "," + columns[7]; // 変更された列を再結合するよ
                System.out.println(line); // 読み込んだ行を表示するよ
                */
            }

        //Fileのtryの読み取れなかった場合の処理欄
        }catch (FileNotFoundException e) {
            System.out.println("エラー： ファイルが見つかりません。");
        }
        //上に続き、そもそもうまくいかなかった場合の処理欄
        catch (IOException e) {
            System.out.println("エラー： ファイルの読み込みに失敗しました。");
        }
            
    }
}
