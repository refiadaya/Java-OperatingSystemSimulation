package operatingSystemSimulation;

import java.util.NoSuchElementException;

public class LinkedPriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T>{
    private Node headNode;
    private Node tailNode;
    private int numberOfEntries;


    public LinkedPriorityQueue() {
        this.headNode = null;
        this.tailNode = null;
        numberOfEntries = 0;
    }

    @Override
    public void add(int priority, T newEntry) {
        numberOfEntries++;
        Node currentNode=new Node(priority,newEntry);
        if(headNode == null) {
            headNode=tailNode=currentNode;
        } else {
            tailNode.setNextLink(currentNode);
            tailNode=currentNode;
        }
        sort();
    }

    // This private method sorts the Linked List Nodes from Highest Priority to low.
    private void sort() {
        T leftNodeData;
        int prioritySwapLeftNode;
        for(Node leftNode=headNode; leftNode!=null;leftNode=leftNode.getNextLink()) {
            for(Node rightNode=headNode;rightNode!=null; rightNode=rightNode.getNextLink()) {
                if(leftNode.getPriority() > rightNode.getPriority()) {
                    leftNodeData= leftNode.getData();
                    prioritySwapLeftNode=leftNode.getPriority();

                    leftNode.setData(rightNode.getData());
                    leftNode.setPriority(rightNode.getPriority());

                    rightNode.setData(leftNodeData);
                    rightNode.setPriority(prioritySwapLeftNode);

                }else if (leftNode.getPriority()==rightNode.getPriority()){
                    leftNodeData = leftNode.getData();

                    if (leftNodeData.compareTo(rightNode.getData())<0){
                        leftNode.setData(rightNode.getData());
                        rightNode.setData(leftNodeData);
                    }
                }
            }
        }
    }

    @Override
    public T remove() {
        if(isEmpty())
            throw new NoSuchElementException();
        else {
            T data= headNode.getData();
            numberOfEntries--;
            headNode=headNode.getNextLink();
            return data;
        }
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return (headNode==null);
    }

    @Override
    public int getSize() {
        return numberOfEntries;
    }

    @Override
    public void clear() {
        for(int count=0;count<numberOfEntries;count++) {
            headNode=headNode.getNextLink();
            System.gc();
        }
        headNode=null;
        tailNode=null;
        System.gc();
        this.numberOfEntries=0;
    }

    private T get(int index) {
        int indexCounter=0;
        Node currentNode=headNode;
        while(currentNode!=null) {
            if(index==indexCounter) {
                return (currentNode.getData());
            }
            currentNode=currentNode.getNextLink();
            indexCounter++;
        }
        return null;
    }

    @Override
    public boolean equals(T data) {
        for(int count=0;count<numberOfEntries;count++) {
            if(get(count).equals(data))
                return true;
        }
        return false;
    }

    public String toString() {
        if (isEmpty()) {
            return " ";
        }
        StringBuilder sb = new StringBuilder();
        Node next = headNode;
        while(next != null){
            sb.append(next.getData()).append("\n");
            next = next.getNextLink();
        }
        return sb.toString();
    }

    private class Node{
        private T data;
        private Node nextLink;
        private int priority;

        public Node(int priority,T data) {
            this.data = data;
            this.nextLink = null;
            this.priority = priority;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setNextLink(Node nextLink) {
            this.nextLink = nextLink;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public Node getNextLink() { return nextLink; }

    }
}
