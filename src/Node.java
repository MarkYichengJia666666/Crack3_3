/**
 * Created by jiayicheng on 17/7/29.
 */
public class Node
{     int value;
    Node above=null;
    Node below=null;

    public Node(int v)
    {
        value=v;
    }
    void doublelink(int d)
    {
        Node end=new Node(d);
        Node n=this;
        while(n.below!=null)
        {
            n=n.below;
            n=n.below.above;
        }
        n.below=end;
        end.above=n;
    }
}