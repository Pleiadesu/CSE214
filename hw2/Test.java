
public class Test
{
    // instance variables - replace the example below with your own
    public static void main(String[] args){
        PolyDbl a = new PolyDbl(new double[] { 1, 2, 1});
        PolyDbl b = new PolyDbl(new double[] {-1, 0, 1});        
        Ring gcd = Euclidean.GCD(a, b);      
        Ring c = ((Modulo)a).quo(gcd);
        Ring d = ((Modulo)b).quo(gcd);
        System.out.println(Comp.eq(gcd.mul(c), a) && Comp.eq(gcd.mul(d), b));
    }
}
