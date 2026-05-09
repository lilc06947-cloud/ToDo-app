import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> tasks = new ArrayList<>();
        
        loadTasks(tasks);

        while (true) {

            System.out.println("==== ToDo App ====");
            System.out.println("1. タスク追加");
            System.out.println("2. タスク一覧");
            System.out.println("3. タスク削除");
            System.out.println("4. 終了");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {

                addTask(scanner, tasks);

            } else if (choice == 2) {

                showTasks(tasks);

            } else if (choice == 3) {

                deleteTask(scanner, tasks);

            }
             else if (choice == 4) {

                saveTasks(tasks);

                break;
            }
        }

        scanner.close();
    }

    public static void loadTasks(ArrayList<Task> tasks){
        try {

            File file = new File("tasks.txt");

            if (file.exists()) {

                BufferedReader reader = new BufferedReader(new FileReader(file));

                String line;

                while ((line = reader.readLine()) != null) {
                tasks.add(new Task(line));
                }

                reader.close();
            }

        } catch (IOException e) {

            System.out.println("ファイル読み込みエラー");
        }
    }

    public static void addTask(Scanner scanner, ArrayList<Task> tasks){
        
        System.out.println("タスクを入力してください:");
        
        String task = scanner.nextLine();

        tasks.add(new Task(task));

        System.out.println("追加しました！");
    }

    public static void showTasks(ArrayList<Task> tasks) {
            
        System.out.println("==== タスク一覧 ====");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).name);
        }
    }

    public static void deleteTask(Scanner scanner, ArrayList<Task> tasks){
        
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

    public static void saveTasks(ArrayList<Task> tasks){
        
        System.out.println("終了します");
                
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"));

            for (Task t : tasks) {
                    writer.write(t.name);
                    writer.newLine();
                }

            writer.close();

        } catch (IOException e) {

            System.out.println("ファイル保存エラー");
        }
    }
}

