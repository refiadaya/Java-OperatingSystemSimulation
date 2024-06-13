package operatingSystemSimulation;

import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayStack<T> implements StackInterface<T> {
    private T[] stack; // Array of stack entries
    private int topIndex;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;

    public ArrayStack(){
        this(DEFAULT_CAPACITY);
    }

    public ArrayStack(int initialCapacity){
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[]) new Object[initialCapacity];
        stack = tempStack;

        topIndex = -1;
        initialized = true;
    }

    private void checkInitialization(){
        if (!initialized){
            throw new SecurityException("ArrayStack object is not initialized properly.");
        }
    }

    @Override
    public void push(T newEntry){
        checkInitialization();
        ensureCapacity();
        stack[topIndex+1] = newEntry;

        topIndex++;
    }

    private void ensureCapacity() {
        if (topIndex == stack.length-1){
            int newLength = 2* stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack,newLength);
        }

    }

    private void checkCapacity(int newLength) {
        if (newLength>MAX_CAPACITY){
            throw new SecurityException("The capacity exceeds the max capacity.");
        }
    }

    @Override
    public T peek(){
        checkInitialization();

        if (isEmpty()){
            throw new EmptyStackException();
        } else {
            return stack[topIndex];
        }
    }

    @Override
    public boolean isEmpty() {
        return topIndex==-1;
    }

    @Override
    public void clear() {
        if (!isEmpty()){
            pop();
        }
    }

    @Override
    public T pop(){
        checkInitialization();

        if (isEmpty()){
            throw new EmptyStackException();
        }else{
            T top = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;

            return top;
        }
    }

    @Override
    public int getSize(){
        return topIndex+1;
    }

    public String toString() {
        if (isEmpty())
            return " ";
        StringBuilder sb = new StringBuilder();
        for (int i = topIndex; i >= 0; i--) {
            if (!(stack[i]==null)) sb.append(stack[i] + "\n");
        }
        return sb.toString();
    }
}
