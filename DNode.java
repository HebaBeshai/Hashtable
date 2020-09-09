
public class DNode<T> {

    private T element;
    private DNode next;
    private DNode previous;
    private int counter = 1;

    //constructors
    public DNode() {
        next = null;
        previous = null;
    }
    public DNode(T XXX) {
        next = null;
        previous = null;
        element = XXX;
    }

    //getters and setter for next and previous
    public void setNext(DNode link) {
        next = link;
    }
    public DNode getNext() {
        return next;
    }
    public void setPrevious(DNode link) {
        previous = link;
    }
    public DNode getPrevious(){
        return previous;
    }
    public T getElement() {
        return element;
    }

    public int getCounter() {
        return counter;
    }
    public void incCounter() {
        counter++;
    }
    public void decCounter() {
        counter--;
    }


    }

