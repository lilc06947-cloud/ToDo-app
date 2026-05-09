import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> tasks = new ArrayList<>();
        try {

            File file = new File("tasks.txt");

            if (file.exists()) {

                BufferedReader reader = new BufferedReader(new FileReader(file));

                String line;

                while ((line = reader.readLine()) != null) {
                tasks.add(line);
                }

                reader.close();
            }

        } catch (IOException e) {

            System.out.println("ファイル読み込みエラー");
        }

        while (true) {

            System.out.println("==== ToDo App ====");
            System.out.println("1. タスク追加");
            System.out.println("2. タスク一覧");
            System.out.println("3. タスク削除");
            System.out.println("4. 終了");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {

                System.out.println("タスクを入力してください:");
                String task = scanner.nextLine();

                tasks.add(task);

                System.out.println("追加しました！");

            } else if (choice == 2) {

                System.out.println("==== タスク一覧 ====");

                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }

            } else if (choice == 3) {


                System.out.println("削除する番号を入力してください:");

                int deleteIndex = scanner.nextInt();
                scanner.nextLine();

                if (deleteIndex > 0 && deleteIndex <= tasks.size()) {

                    tasks.remove(deleteIndex - 1);

                    System.out.println("削除しました！");

                } else {

                    System.out.println("無効な番号です");
                }
            }
             else if (choice == 4) {

                System.out.println("終了します");
                
                try {

                    BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"));

                    for (String task : tasks) {
                        writer.write(task);
                        writer.newLine();
                    }

                    writer.close();

                } catch (IOException e) {

                    System.out.println("ファイル保存エラー");
                }
                
                break;
            }
        }

        scanner.close();
    }
}
