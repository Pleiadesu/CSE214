import java.util.Arrays;
public class Sort {
    //TODO
    //Submit your course evaluation at
    //https://stonybrook.campuslabs.com/courseeval/
    //
    protected static void swap(int[] a, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
    }
    protected static String toString(int[] a) {
        StringBuilder sb = new StringBuilder();
        for(Integer e : a)
            sb.append(e + ", ");
        return sb.toString();
    }    
    protected static int checkBit(int a, int shift) {
        //return the shift-th bit of a
        return (a & (1 << shift)) != 0 ? 1 : 0; 
    }
    public static void radixSort1(int[] a, int l, int r, int b) {
        //TODO
        //return if l >= r or b < 0
        //repeat
        //  from l find the number whose b-th bit is 1
        //  from r find the number whose b-th bit is 0
        //  swap the two numbers
        //until the indexes of the numbers meet
        //recursively call radixSort1 with b-1 and new l, r
        int tempr = r;
        int templ = l;
        if(l >= r || b < 0) return;
		while(l < r){			
			while(l<a.length){
				if(checkBit(a[l], b) == 1){//find first 1
					break;
				}
				l++;
			}
			while(r>=0){	
				if(checkBit(a[r], b) == 0){//find last 0
					break;
				}
				r--;
			}
			if(l < r){
				swap(a, l, r);//swap- 1 > 0, so in this case 1 is supposed to come after 0
			}
		}
		radixSort1(a, templ, r, b-1); //sort within the 0s
		radixSort1(a, r+1, tempr, b-1); //sort within the 1s
    }
    public static void radixSort1(int[] a) {
        radixSort1(a, 0, a.length-1, 31);
    }
    public static void radixSort2(int[] a) {
        int[] b = new int[a.length];
        //TODO
        //foreach i in [0, 31]
        //  let i0 be 0, and
        //  let i1 be the # of a[..] with its i-th bit = 0
        //  copy a[j] to b[i0++] if a[j]'s i-th bit is 0
        //  copy a[j] to b[i1++] otherwise
        //  copy b to a
        
        for(int i = 0; i <= 31; i++){
			int i0 = 0;
			int i1 = 0;
			for(int check: a){
				if(checkBit(check, i) == 0){
					i1++;//index where 1s should be placed- add 1 whenever there's a 0
				}
			}
			for(int j = 0; j < a.length; j++){
				if(checkBit(a[j], i) == 0){
					b[i0++] = a[j];
				}
				else{
					b[i1++] = a[j];
				}
			}
			
			for(int j = 0; j < b.length; j++){
				a[j] = b[j];//copy b to a then repeat for every bit from 0~31
			}
		}
    }
    
    public static void main(String[] args) {
        int[] a = new int[] {3, 1, 2, 4, 5, 7, 9, 0, 8, 6};
        radixSort1(a);
        System.out.println("Radix sort 1: " + toString(a));

        a = new int[] {3, 1, 2, 4, 5, 7, 9, 0, 8, 6};
        radixSort2(a);
        System.out.println("Radix sort 2: " + toString(a));
    }
}
