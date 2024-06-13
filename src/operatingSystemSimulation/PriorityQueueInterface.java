package operatingSystemSimulation;

public interface PriorityQueueInterface <T extends Comparable<? super T>> {

    public void add(int priority, T newEntry);

    public T remove();

    public T peek();

    public boolean isEmpty();

    public int getSize();

    public void clear();

    public boolean equals(T data);

}
