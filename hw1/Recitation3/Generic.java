public class Generic {
    public static interface Ordered {
        public boolean ge(Ordered a);   //greater than or equal to
    }
    
    public static class Int implements Ordered {
        public int n;
        public Int(int n) { this.n = n; }
        public String toString() { return Integer.toString(n); }
        public boolean ge(Ordered a) {
            int compare = this.toString().compareTo(((Int)a).toString());
            if(compare == -1){
                return false;
            }
            return true;
        }
    }
    
    public static class Str implements Ordered {
        public String str;
        public Str(String str) { this.str = str; }
        public String toString() { return str; }
        public boolean ge(Ordered a) {
            int compare = this.toString().compareTo(((Str)a).toString());
            if(compare == -1){
                return false;
            }
            return true;
        }
    }
    
    public static class Inverter<E> {
        private E[] arr;
        public Inverter(E[] arr) {
            this.arr = arr;
        }
        public void revert() {
            for(int i = 0; i < arr.length/2; i++){
                E temp;
                temp = arr[i];
                arr[i] = arr[arr.length - i - 1];
                arr[arr.length - i - 1] = temp;
            }
        }
    }
    
    public static class Sorter<E extends Ordered> {
        private E[] arr;
        public Sorter(E[] arr) {
            this.arr = arr;
        }
        public void sort() {
            for(int i = 0; i < arr.length; i++)
                for(int j = i+1; j < arr.length; j++)
                    if(arr[i].ge(arr[j])) {
                        E temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
                    }
        }
    }

    public static void main(String[] args) {
        Ordered[][] arr = new Ordered[][] {
            new Int[] { new Int(5), new Int(3), new Int(7), new Int(1), new Int(4) },
            new Str[] { new Str("a"), new Str("c"), new Str("d"), new Str("e"), new Str("b") }
        };
        
        for(int i = 0; i < 2; i++) {
            Sorter<Ordered> sorter = new Sorter<Ordered>(arr[i]);
            sorter.sort();
            for(int j = 0; j < arr[i].length; j++)
                System.out.print(" " + arr[i][j]);
            System.out.println("");
        }
        
        for(int i = 0; i < 2; i++) {
            Inverter<Ordered> inverter = new Inverter<Ordered>(arr[i]);
            inverter.revert();
            for(int j = 0; j < arr[i].length; j++)
                System.out.print(" " + arr[i][j]);
            System.out.println("");
        }
    }
}