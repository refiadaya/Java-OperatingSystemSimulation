package operatingSystemSimulation;

public interface StackInterface <T>{
    public void push(T newEntry);

    // removes and returns this stack's top entry
    public T pop();

    // retrieves the stack's top entry
    public T peek();

    public boolean isEmpty();

    public void clear();

    // number of elements in a stack
    public int getSize();
}
