 

public class SetImpl<E extends Comparable<E>> implements Set<E> {
    private CircularlyDblLinkedList<E> list;
    
    public SetImpl() {
        list = new CircularlyDblLinkedList<E>();
    }
    public SetImpl(SetImpl<E> set) {
        list = set.copyList();
        dedupe();
    }
    public SetImpl(E[] arr) {
        list = new CircularlyDblLinkedList<E>();
        for(int i = 0; i < arr.length; i++)
            list.add(i, arr[i]);
        dedupe();
    }
    
    //TODO: implement interface Set
    public boolean isEqual(Set<E> set) {
        SetImpl<E> s = (SetImpl<E>)set;
        if(list.size() != s.list.size())
            return false;
        for(int i = 0; i < list.size(); i++)
            if(list.get(i).compareTo(s.list.get(i)) != 0)
                return false;
        return true;
    }
    public Set<E> union(Set<E> set){
        System.out.println("Calculating Union between:");
        System.out.println(list + "\nand\n" + ((SetImpl)set).copyList());
        CircularlyDblLinkedList tempList = ((SetImpl)set).copyList();
        for(int i = 0; i < list.size(); i++){
            System.out.println("Union index: "+i);
            tempList.add(i, list.get(i));
        }
        SetImpl tempSet = new SetImpl();
        tempSet.list = tempList;
        return tempSet;
    }
    public Set<E> intersection(Set<E> set){
        CircularlyDblLinkedList tempList = ((SetImpl)set).copyList();
        for(int i = 0; i < list.size(); i++){
            boolean contains = true;
            int rmv_pos = 0;
            for(int j = 0; j < tempList.size(); j++){
                if(list.get(i).compareTo((E)tempList.get(j)) != 0){
                    contains = false;
                    rmv_pos = j;
                }
                else{
                    break;
                }
            }
            if(!contains){
                tempList.remove(rmv_pos);
            }
        }
        SetImpl tempSet = new SetImpl();
        tempSet.list = tempList;
        return tempSet;
    }
    public Set<E> difference(Set<E> set){
        SetImpl tempRmv = (SetImpl)intersection((SetImpl)set);
        CircularlyDblLinkedList tempList = new CircularlyDblLinkedList();
        //make a copy of this.list
        int f = 0;
        for(E e: list){
            tempList.add(f++, e);
        }
        //remove every element in tempRmv- that is the intersection- from tempList
        for(int i = 0; i < tempList.size(); i++){
            for(int j = 0; j < tempRmv.list.size(); j++){
                if(((E)tempList.get(i)).compareTo((E)tempRmv.list.get(j)) != 0){
                    tempList.remove(i);
                }
                else{
                    break;
                }
            }
        }
        SetImpl tempSet = new SetImpl();
        tempSet.list = tempList;
        return tempSet;
    }
    
    //helper methods
    private CircularlyDblLinkedList<E> copyList() {
        System.out.println("Copying list: " + list);
        CircularlyDblLinkedList<E> dst = new CircularlyDblLinkedList<E>();
        int i = 0;
        for(E e : list){
            //System.out.println("Element being copied: "+e);
            dst.add(i++, e);
        }
        return dst;
    }
    private void dedupe() {
        //TODO: 1) sort
        //      2) remove any consecutive duplicate elements
        sort();
        for(int currentPos = 0; currentPos < list.size() - 1; currentPos++){
            for(int comparePos = currentPos + 1; comparePos < list.size(); comparePos++){
                if(list.get(currentPos).equals(list.get(comparePos))){
                    break;
                }
                else{
                    list.remove(comparePos);
                }
            }
        }
        //for i in list(start~end-1)
        //  for j in list(i~end)
        //      if list(i) != list(j) then break;
        //      else if list(i) == list(j) then remove list(j) from list;
    }
    private void sort() {   //insertion-sort
        //TODO: using the insertion-sort, sort list
        if(list.size() <= 0){
            throw new ArrayIndexOutOfBoundsException("List being sorted is empty");
        }
        for(int currentPos = 1; currentPos < list.size(); currentPos++){
            int tempPos = currentPos;
            while(tempPos > 0 && list.get(tempPos - 1).compareTo(list.get(tempPos)) > 0){
                E temp = list.get(tempPos);
                list.set(tempPos, list.get(tempPos - 1));
                list.set(tempPos - 1, temp);
                // make list[tempPos] = list[tempPos - 1];
                // make list[tempPos - 1] = temp;
                tempPos--;
            }
        }
    }
}
