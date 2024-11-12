import schema.Task;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ProjectScheduler {
    private Map<String, Task> tasks;

    public ProjectScheduler() {
        tasks = new HashMap<>();
    }

    public void addTask(String id, int duration) {
        tasks.put(id, new Task(id, duration));
    }

    public void addDependency(String taskId, String dependencyId) {
        Task task = tasks.get(taskId);
        Task dependency = tasks.get(dependencyId);
        if (task != null && dependency != null) {
            task.addDependency(dependency);
        }
    }

    public void scheduleTasks(LocalDate projectStartDate) {
        Map<Task, Integer> indegreeMap = new HashMap<>();
        for (Task task : tasks.values()) {
            indegreeMap.put(task, 0);
        }
        for (Task task : tasks.values()) {
            for (Task dependency : task.dependencies) {
                indegreeMap.put(task, indegreeMap.get(task) + 1);
            }
        }

        Queue<Task> queue = new LinkedList<>();
        for (Task task : indegreeMap.keySet()) {
            if (indegreeMap.get(task) == 0) {
                task.startDate = projectStartDate;
                task.endDate = projectStartDate.plusDays(task.duration);
                queue.add(task);
            }
        }

        while (!queue.isEmpty()) {
            Task current = queue.poll();

            for (Task dependent : tasks.values()) {
                if (dependent.dependencies.contains(current)) {
                    LocalDate potentialStartDate = current.endDate.plusDays(1);
                    if (dependent.startDate == null || potentialStartDate.isAfter(dependent.startDate)) {
                        dependent.startDate = potentialStartDate;
                        dependent.endDate = dependent.startDate.plusDays(dependent.duration);
                    }

                    indegreeMap.put(dependent, indegreeMap.get(dependent) - 1);
                    if (indegreeMap.get(dependent) == 0) {
                        queue.add(dependent);
                    }
                }
            }
        }
    }

    public void printSchedule() {
        for (Task task : tasks.values()) {
            System.out.println(task);
        }
    }
}
