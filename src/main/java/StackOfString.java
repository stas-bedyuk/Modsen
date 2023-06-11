import java.util.ArrayList;
import java.util.List;

public class StackOfString {
    private List<String> stack;

    public StackOfString() {
        stack = new ArrayList<>();
    }

    public void push(String value) {
        stack.add(value);
    }

    public String pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.remove(stack.size() - 1);
    }

    public String peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.get(stack.size() - 1);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }
}