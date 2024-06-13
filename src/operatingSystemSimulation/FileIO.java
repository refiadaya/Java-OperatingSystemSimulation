package operatingSystemSimulation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;

public class FileIO {
    SortedListInterface<Task> taskLinkedSortedList = new LinkedSortedList<>(); // linked sorted list by arrival date and time
    PriorityQueueInterface<Task> taskLinkedPriorityQueue = new LinkedPriorityQueue<>(); // waiting priority line
    StackInterface<Task> taskArrayStack = new ArrayStack<>(); // pile of waiting burst time


    public void readTasks() {
        try {
            File file = new File("tasks.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();

            while (line != null) {

                String[] taskWords = line.split(",");
                String taskType = taskWords[0];
                int burstTime = Integer.parseInt(taskWords[1]);
                String[] arrivalDate = taskWords[2].split("/");
                String hour = taskWords[3] + ":00";

                String time = arrivalDate[2] + "-" + arrivalDate[1] + "-" + arrivalDate[0] + "T" + hour;
                // arrivalDate[2], arrivalDate[1], arrivalDate[0] represent year, day, month, respectively.
                LocalDateTime arriveDateTime = LocalDateTime.parse(time);

                Task taskObj = new Task(taskType, burstTime, arriveDateTime);
                taskLinkedSortedList.add(taskObj); // task-1

                int priority;

                switch (taskType){
                    case "security management":
                        priority = 6;
                        break;
                    case "process management":
                        priority = 5;
                        break;
                    case "memory management":
                        priority = 4;
                        break;
                    case "user management":
                        priority = 3;
                        break;
                    case "device management":
                        priority = 2;
                        break;
                    case "file management":
                        priority = 1;
                        break;
                    default:
                        priority = 0;
                }

                taskLinkedPriorityQueue.add(priority,taskObj); // task-3.a
                line = br.readLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        SortedListInterface<Task> templsl = new LinkedSortedList<>();
        int sortedListLength = taskLinkedSortedList.getLength();

        for (int i = 1; i <= sortedListLength; i++) {
            templsl.add(taskLinkedSortedList.getEntry(i));
        } // we will use a temporary list (templsl) to be able to push them to stack according to their burst time.

        Task taskWithMaxBurstTime;

        while (!templsl.isEmpty()){
            taskWithMaxBurstTime = getMaxBurstTimeTask(templsl);
            taskArrayStack.push(taskWithMaxBurstTime);
            templsl.remove(taskWithMaxBurstTime);
        }
    }

    private Task getMaxBurstTimeTask(SortedListInterface<Task> templsl){
        Task taskWithMaxBurstTime = templsl.getEntry(1);
        for (int i = 1; i <= templsl.getLength(); i++) {
                if (templsl.getEntry(i).getBurstTime() >= taskWithMaxBurstTime.getBurstTime()) {
                    taskWithMaxBurstTime = templsl.getEntry(i);
                }
        }
        return taskWithMaxBurstTime;
    }
}

