package operatingSystemSimulation;

public class Main {
    public static void main(String[] args) {
        FileIO fileIOObj = new FileIO();
        fileIOObj.readTasks();

        System.out.println("--------------The Tasks In the Linked Sorted List by Arrival Date and Time:--------------");
        System.out.println(fileIOObj.taskLinkedSortedList.toString());
        // task-2

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("----------------------------------WAITING PRIORITY LINE----------------------------------");

        int count = 0;
        int taskLinkedPriorityQueueSize = fileIOObj.taskLinkedPriorityQueue.getSize();
        for (int i = 1; i <= taskLinkedPriorityQueueSize; i++) {
            if (count==0) System.out.println("Now, the following tasks are being executed respectively: ");
            System.out.println(fileIOObj.taskLinkedPriorityQueue.remove().toString());
            count++;
            if (count==5){ // task-6
                System.out.println("Remaining tasks: ");
                if (fileIOObj.taskLinkedPriorityQueue.isEmpty()) System.out.println("There are no tasks left in the waiting priority line.");
                else System.out.println(fileIOObj.taskLinkedPriorityQueue.toString());
                System.out.println("*********************************************************");
                count=0;
            }
        } // task-4.a
        System.out.println("\n-----------------------------------------------------------------------------------------");

        System.out.println("-------------------------------PILE OF WAITING BURST TIME--------------------------------");
        count = 0;
        int taskArrayStackSize = fileIOObj.taskArrayStack.getSize();
        for (int i = 0; i < taskArrayStackSize; i++) {
            if (count==0) System.out.println("Now, the following tasks are being executed respectively: ");
            System.out.println(fileIOObj.taskArrayStack.pop());
            count++;
            if (count==5){ // task-6
                System.out.println("Remaining tasks: ");
                if (fileIOObj.taskArrayStack.isEmpty()) System.out.println("There are no tasks left in the pile of waiting burst time.");
                else System.out.println(fileIOObj.taskArrayStack.toString());
                System.out.println("*********************************************************");
                count=0;
            }
        } // task-4.b
        System.out.println("---------------------------------------------------------------------------------------");
    }

}
