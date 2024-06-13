package operatingSystemSimulation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task implements Comparable<Task> {
    private String taskType;
    private int burstTime;
    private LocalDateTime arrivalDateTime;
    private static DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Task() {
    }

    public Task(String taskType, int burstTime, LocalDateTime arrivalDateTime) {
        this.taskType = taskType;
        this.burstTime = burstTime;
        this.arrivalDateTime = arrivalDateTime;
    }

    public int compareTo(Task other) {
        if (this.arrivalDateTime.isBefore(other.arrivalDateTime)) {
            return -1;
        } else if (this.arrivalDateTime.isAfter(other.arrivalDateTime)) {
            return 1;
        } else {
            return 0;
        }
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(LocalDateTime arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    @Override
    public String toString() {
        return "Task -> " +
                "Name: " + taskType +
                ", Burst Time: " + burstTime +
                ", Arrival Date and Time: " + arrivalDateTime.format(myFormatObj);
    }
}
