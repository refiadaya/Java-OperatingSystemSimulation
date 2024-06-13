package operatingSystemSimulation;

public class LinkedSortedList<T extends Comparable<? super T>>
             implements SortedListInterface<T> {
    private Node firstNode;
    private int numberOfEntries;

    public LinkedSortedList(){
        firstNode = null;
        numberOfEntries = 0;
    }


    @Override
    public void add(T newEntry) {
        Node newNode = new Node(newEntry);
        Node nodeBefore = getNodeBefore(newEntry);

        if (isEmpty() || (nodeBefore == null)){
            newNode.setNextNode(firstNode);
            firstNode = newNode;
        }else {
            Node nodeAfter = nodeBefore.getNextNode();
            newNode.setNextNode(nodeAfter);
            nodeBefore.setNextNode(newNode);
        }
        numberOfEntries++;
    }

    private Node getNodeBefore(T anEntry){
        Node currentNode = firstNode;
        Node nodeBefore = null;

        while ((currentNode != null) && (anEntry.compareTo(currentNode.getData())>0)){
            nodeBefore = currentNode;
            currentNode = currentNode.getNextNode();
        }
        return nodeBefore;
    }

    @Override
    public boolean remove(T anEntry) {
        boolean result = false;
        int position = getPosition(anEntry);
        if (position > 0) {
            remove(position);
            result = true;
        }
        return result;
    }

    @Override
    public int getPosition(T anEntry) {
        int position = 1;

        while ( (position <= numberOfEntries) &&
                (anEntry.compareTo(getEntry(position)) > 0) ) {
            position++;
        }

        if ((position > numberOfEntries) ||
                (anEntry.compareTo(getEntry(position)) != 0)) {
            position = -position; // anEntry is not in list
        }
        return position;
    }

    @Override
    public T getEntry(int givenPosition) {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)) {
            assert !isEmpty();
            return getNodeAt(givenPosition).getData();
        }else
            throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null)){
            if (anEntry.equals(currentNode.getData()))
                found = true;
            else
                currentNode = currentNode.getNextNode();
        }
        return found;
    }

    @Override
    public T remove(int givenPosition) {
        T result = null;

        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries)){
            assert !isEmpty();
            if (givenPosition==1){ // case 1: remove first entry
                result = firstNode.getData(); // save entry to be removed
                firstNode = firstNode.getNextNode(); // remove entry
            } else { // case 2: not first entry
                Node nodeBefore = getNodeAt(givenPosition-1);
                Node nodeToRemove = nodeBefore.getNextNode();
                Node nodeAfter = nodeToRemove.getNextNode();

                result = nodeToRemove.getData(); // save entry to be removed
                nodeBefore.setNextNode(nodeAfter); // remove entry
            }
            numberOfEntries--;
            return result;
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
    }

    private Node getNodeAt(int givenPosition){
        assert (firstNode != null) &&
                (givenPosition >= 1) && (givenPosition <= numberOfEntries);
        Node currentNode = firstNode;

        for (int counter = 1; counter < givenPosition; counter++)
            currentNode = currentNode.getNextNode();

        assert currentNode != null;
        return currentNode;
    }

    @Override
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public int getLength() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        boolean result;

        if (numberOfEntries==0){
            assert firstNode == null;
            result = true;
        } else {
            assert firstNode != null;
            result = false;
        }
        return result;
    }

    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Comparable[numberOfEntries];

        int index = 0;
        Node currentNode = firstNode;

        while ((index < numberOfEntries) && (currentNode != null)){
            result[index] = currentNode.getData();
            currentNode = currentNode.getNextNode();
            index++;
        }
        return result;
    }

    public String toString() {
        if (isEmpty()) {
            return " ";
        }
        StringBuilder sb = new StringBuilder();
        Node next = firstNode;
        while(next != null){
            sb.append(next.getData()).append("\n");
            next = next.getNextNode();
        }
        return sb.toString();
    }

    private class Node{
        private T data;
        private Node next;

        private Node(T dataPortion){
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode){
            data = dataPortion;
            next = nextNode;
        }

        private T getData(){
            return data;
        }

        private void setData(T newData){
            data = newData;
        }

        private Node getNextNode(){
            return next;
        }

        private void setNextNode(Node nextNode){
            next = nextNode;
        }
    }
}
