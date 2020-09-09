
public class DLList<T> {


    protected DNode<T> Head;//header to the nodes
    protected DNode<T> Tail; //tail to the nodes
    protected int size;


    public DLList() { //DLL constructor
        Head = null;
        Tail = null;
        size = 0;
    }


    public int DLListSize() { //getter for the list size
        return size;
    }


    public boolean isEmpty() {//returns the empty list
        return size == 0;
    }


    public DNode getFront() {//getter for the front
        if (size ==0) {
            return null;
        }

        return Head;
    }


    public DNode getTail() {//getter for the  rear
        if (size == 0) {
            return null;
        }
        return Tail;
    }


    public void insertAtFront(DNode NewOne) {//inserts at the front of the list


        NewOne.setNext(Head);//creates a node to contain NewOne, and then it set the  Next member to be what the Head points to


        if (size == 0) {  //if this is the first node then it just sets the tail
            Tail = NewOne;
        }

        else { //since not the first node so it  links current node to the first node

            Head.setPrevious(NewOne);  //back to newNode
        }


        Head = NewOne;//this sets the  header to the newNode
        size++;


    }


    private void zapSingle() {//this method  removes a list of size 1
        Tail = null;
        Head = null;
        size = 0;
        return;
    }
public void removeAtFront() { //if the list is empty this just returns

    if (isEmpty()) {
        return;
    }


    if (size == 1) {//if list the list is of size one it goes here
        zapSingle();
        return;
    }


    Head = Head.getNext(); //if the list is of at least two elements this is the remove it goes to
    Head.setPrevious(null);
    size--;
}


    public void insertAtRear(T NewOne) { // this method inserts at the rear of list
        DNode<T> newNode = new DNode<T>(NewOne);//this creates a  node and sets the previous member
        newNode.setPrevious(Tail);

        if (size == 0) {        //if its the first insertion, this sets Header

            Head = newNode;
        } else
        //otherwise it sets the  next member of current node
        {
            Tail.setNext(newNode);
        }
        //this has the  Tail refer to the ' newNode'
        Tail = newNode;
        size++;

    }

    public void removeAtRear() {
        if (this.isEmpty()) { //if list is empty  this will just return
            return;
        }
        if (size == 1) {        //if the list has one node this removes it

            zapSingle();
            return;
        }
        Tail = Tail.getPrevious(); //if there are at least two node
        Tail.setNext(null);
        size--;
        return;
    }
    public DNode<T> find (T input) { //this has a complexity o(1) (big o of 1)
        for (DNode<T> i = Head; i != null; i = i.getNext()) {
            if (input.equals(i.getElement())) {
                return i;
            }
        }
        return null;
    }

public boolean delete(T x) { //complexity o(1) (big o of 1)
    DNode<T> input = new DNode<T>(x);
    if (input == null) {
        return false;
    }
    if (input.getPrevious() != null) {
        input.getPrevious().setNext(input.getNext());
    } else {
        Head = input.getNext();
    }

    if (input.getNext() != null) {
        input.getNext().setPrevious(input.getPrevious());
    } else {
        Tail.getPrevious();
    }
    size--;  //current = null

    return true;
}
public boolean insertBefore(DNode <T> current, T input) {
    if (current == null) {
        return false;
    }
    DNode<T> temp = new DNode<T>(input);
    temp.setPrevious(current.getPrevious());
    temp.setNext(current);
    size++;
    return true;
}




    }



