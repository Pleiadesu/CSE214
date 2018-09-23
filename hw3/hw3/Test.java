public class Test
{
    public static void main(String[] args){
        System.out.println("testSubset...");
        SetImpl<Integer> x = new SetImpl<Integer>(new Integer[] {1, 1, 1});
        SetImpl<Integer> y = new SetImpl<Integer>(new Integer[] {1, 2, 1, 2});
        SetImpl<Integer> z = new SetImpl<Integer>(new Integer[] {3, 2, 3, 3});
        SetImpl<Integer> u = new SetImpl<Integer>(new Integer[] {1, 2, 3});
        Subset<Integer> a = new Subset<Integer>(x, u);
        Subset<Integer> b = new Subset<Integer>(y, u);
        Subset<Integer> c = new Subset<Integer>(z, u);
        Subset<Integer> notA = (Subset)a.not();
        Subset<Integer> notNotA = (Subset)notA.not();
        Subset<Integer> notB = (Subset)b.not();
        Subset<Integer> notNotB = (Subset)notB.not();
    }
}
