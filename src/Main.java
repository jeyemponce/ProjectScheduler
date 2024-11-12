import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        ProjectScheduler scheduler = new ProjectScheduler();

        scheduler.addTask("TaskA", 5);
        scheduler.addTask("TaskB", 3);
        scheduler.addTask("TaskC", 2);
        scheduler.addTask("TaskD", 4);

        scheduler.addDependency("TaskB", "TaskA");
        scheduler.addDependency("TaskC", "TaskA");
        scheduler.addDependency("TaskD", "TaskB");
        scheduler.addDependency("TaskD", "TaskC");

        LocalDate projectStartDate = LocalDate.now();
        scheduler.scheduleTasks(projectStartDate);

        System.out.println("Project Schedule:");
        scheduler.printSchedule();
    }
}
