public class DoublyLinkedList<E extends Comparable<E>> {
    private static class Node<E extends Comparable<E>> {
        private E e;
        private Node<E> next, prev;
        public Node(E e, Node<E> p, Node<E> n) { this.e = e; prev = p; next = n; }
        public E getElement() { return e; }
        public Node<E> getPrev() { return prev; }
        public Node<E> getNext() { return next; }
        public void setPrev(Node<E> p) { prev = p; }
        public void setNext(Node<E> n) { next = n; }
    }
    private Node<E> head;
    private Node<E> tail;
    private int size;
    
    public DoublyLinkedList() {
        head = new Node<E>(null, null, null);
        tail = new Node<E>(null, head, null);
        head.setNext(tail);
    }
    public int size()   { return size; }
    public boolean isEmpty() { return size == 0; }
    public E first() {
        return isEmpty() ? null : head.getNext().getElement();
    }
    public E last() {
        return isEmpty() ? null : tail.getPrev().getElement();
    }
    public void addFirst(E e) {
        addBetween(e, head, head.getNext());
    }
    public void addLast(E e) {
        addBetween(e, tail.getPrev(), tail);
    }
    public E removeFirst() {
        if(isEmpty()) 
            return null;
        return remove(head.getNext());
    }
    public E removeLast() {
        if(isEmpty()) 
            return null;
        return remove(tail.getPrev());
    }
    
    public E find(E e) {
        //TODO: implement this using the methods of Node
        Node curNode = head.next;
        while(curNode.next != tail){
            if(curNode.e.compareTo(e) == 0){
                return e;
            }
            curNode = curNode.next;
        }
        return null;
    }
    
    public void sort() {
        //TODO: implement this using the methods of Node
    }
    
    private void swap(Node<E> n, Node<E> m) {
        //TODO: implement this using the methods of Node
        n.prev.setNext(m);
        n.next.setPrev(m);
        m.prev.setNext(n);       
        m.next.setPrev(n);
        onFalseThrow(n.prev.next == m && n.next.prev == m && m.prev.next == n && m.next.prev == n);
        Node tempPrev = n.prev, tempNext = n.next;
        if(n != m.prev && m != n.prev){
            n.setPrev(m.prev);
            n.setNext(m.next);
            m.setPrev(tempPrev);
            m.setNext(tempNext);
        }
    }
    
    private void addBetween(E e, Node<E> pred, Node<E>succ) {
        //TODO: implement this. See the lecture slide
        Node tempNode = new Node(e, pred, succ);
        pred.setNext(tempNode);
        succ.setPrev(tempNode);
    }
    private E remove(Node<E> node) {
        //TODO: implement this. See the lecture slide
        node.next.setPrev(node.prev);
        node.prev.setNext(node.next);
        size--;
        return (E)node.e;
    }
    
    private static void onFalseThrow(boolean b) {
        if(!b)
            throw new RuntimeException("Error: unexpected");            
    }
    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
        list.addLast(4);
        list.addLast(2);
        list.addLast(1);
        list.addFirst(3);
        onFalseThrow(list.find(4) == 4); 
        onFalseThrow(list.find(5) == null);
        
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addFirst(4);
        list.swap(list.head.next, list.head.next.next);
        onFalseThrow(list.removeFirst() == 3); 
        onFalseThrow(list.removeFirst() == 4); 
        list.swap(list.head.next.next, list.head.next);
        onFalseThrow(list.removeFirst() == 1); 
        onFalseThrow(list.removeFirst() == 2); 
        
        list.sort();
        onFalseThrow(list.removeLast()  == 4); 
        onFalseThrow(list.removeLast()  == 3); 
        onFalseThrow(list.removeFirst() == 1); 
        onFalseThrow(list.removeLast()  == 2); 

        System.out.println("Success!");
    }
}