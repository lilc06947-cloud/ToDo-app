import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<String> tasks = new ArrayList<>();

        while (true) {

            System.out.println("==== ToDo App ====");
            System.out.println("1. タスク追加");
            System.out.println("2. タスク一覧");
            System.out.println("3. 終了");

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

                System.out.println("終了します");
                break;
            }
        }

        scanner.close();
    }
}
