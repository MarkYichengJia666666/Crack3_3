import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Set;

/**
 * Created by jiayicheng on 17/7/27.
 */
public class SetofStacks {
    //可变长度－－动态列表   可以在任意栈顶取出元素－－下一个得挪过来－－递归 栈的构造－－得有界限，有上限有下限

    ArrayList<Stack> stacks = new ArrayList<Stack>();
    int capacity;

    public SetofStacks(int capacity) {
        this.capacity = capacity;
    }

    public void push(int v) {
        Stack a = getlast();
        if (a != null && a.isnotFull())
            a.push(v);
        else {
            Stack b = new Stack(capacity);
            b.push(v);
            stacks.add(b);
        }
    }

    public int pop() {
        Stack a = getlast();
        int v;
        if (a != null)
            v = a.pop();
        else
            throw new EmptyStackException();
        if (a.isEmpty())
            stacks.remove(stacks.size() - 1);
        return v;
    }

    public Stack getlast() {
        if (stacks.size() == 0) return null;
        return stacks.get(stacks.size() - 1);
    }

    public boolean isEmpty() {
        Stack c = getlast();
        return c == null || c.isEmpty();
    }

    public int popAt(int index) {
        return leftshift(index, true);
    }

    public int leftshift(int index, boolean removeTop) {
        Stack stack = stacks.get(index);
        int item;
        if (removeTop) item = stack.pop();
        else item = stack.removeBottom();
        if (stack.isEmpty())
            stacks.remove(index);
        else if (stacks.size() > index + 1) {
            int v = leftshift(index + 1, false);
            stack.push(v);
        }
        return item;
    }


    public class Stack
    {
        private int capacity;
        public Node bottom,top;
        public int size=0;

        public Stack(int capacity){this.capacity=capacity;}
        public boolean isnotFull(){return size==capacity;}
        public boolean isEmpty(){return size==0;}

        public int pop()
        {
            Node t=top;
            top=top.below;
            size--;
            return t.value;
        }

        public int removeBottom()
        {
            Node t=bottom;
            bottom=bottom.above;
            if(bottom!=null) bottom.below=null;
            size--;
            return t.value;
        }

        public boolean push(int v)
        {
            if(size>=capacity) return false;
            Node t=new Node(v);
            size++;
            if(size==1) bottom=t;
            if(top!=null)
            {top.above=t;
            t.below=top;}
            top=t;
            return true;
        }
    }



}


