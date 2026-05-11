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
            System.out.println("4. 完了切り替え");
            System.out.println("5. 終了");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {

                addTask(scanner, tasks);

            } else if (choice == 2) {

                showTasks(tasks);

            } else if (choice == 3) {

                deleteTask(scanner, tasks);

            } else if (choice == 4) {
                toggleTaskCompletion(scanner, tasks);
            }
             else if (choice == 5) {

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
                    String[] parts = line.split(",");

                    boolean completed = Boolean.parseBoolean(parts[0]);

                    String name = parts[1];

                    Task task = new Task(name, completed);

                    tasks.add(task);
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

        tasks.add(new Task(task, false));

        System.out.println("追加しました！");
    }

    public static void showTasks(ArrayList<Task> tasks) {
            
        System.out.println("==== タスク一覧 ====");

        for (int i = 0; i < tasks.size(); i++) {
            
            System.out.println((i + 1) + ". " + tasks.get(i));
        
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

    public static void toggleTaskCompletion(Scanner scanner, ArrayList<Task> tasks) {

        System.out.println("完了状態を切り替える番号を入力してください:");

        int index = scanner.nextInt();
        scanner.nextLine();

        if (index > 0 && index <= tasks.size()) {

            Task task = tasks.get(index - 1);

            task.setCompleted(!task.isCompleted());

            System.out.println("完了状態を変更しました！");

        } else {

            System.out.println("無効な番号です");
        }
    }

    public static void saveTasks(ArrayList<Task> tasks){
        
        System.out.println("終了します");
                
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"));

            for (Task t : tasks) {
                    writer.write(t.isCompleted() + "," + t.getName());
                    writer.newLine();
                }

            writer.close();

        } catch (IOException e) {

            System.out.println("ファイル保存エラー");
        }
    }
}

