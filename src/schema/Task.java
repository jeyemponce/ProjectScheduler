package schema;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task {
    String id;
    public int duration;
    public LocalDate startDate;
    public LocalDate endDate;
    public List<Task> dependencies;

    public Task(String id, int duration) {
        this.id = id;
        this.duration = duration;
        this.dependencies = new ArrayList<>();
    }

    public void addDependency(Task task) {
        this.dependencies.add(task);
    }

    @Override
    public String toString() {
        return String.format("Task %s: Start Date: %s, End Date: %s", id, startDate, endDate);
    }
}