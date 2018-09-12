
public class Test
{
    // instance variables - replace the example below with your own
    public static void main(String[] args){
        /*PolyDbl a = new PolyDbl(new double[] { 1, 2, 1});
        PolyDbl b = new PolyDbl(new double[] {-1, 0, 1});        
        Ring gcd = Euclidean.GCD(a, b);      
        Ring c = ((Modulo)a).quo(gcd);
        Ring d = ((Modulo)b).quo(gcd);
        System.out.println(Comp.eq(gcd.mul(c), a) && Comp.eq(gcd.mul(d), b));*/
        /*Rat a = new Rat(1, 1);
        System.out.println(""+Comp.gt(a, a.addIdentity()));
        System.out.println(""+Comp.gt(a.addIdentity(), a));*/
        /*Poly a = new Poly(new Rat[] {new Rat(1, 1), new Rat(2, 1), new Rat(1, 1)});
        Poly b = new Poly(new Rat[] {new Rat(-1, 1), new Rat(0, 1), new Rat(1, 1)});
        System.out.println(a.add(b));
        Poly<IntMod> c = new Poly<IntMod>(new IntMod[] {new IntMod( 1,3), new IntMod(2,3), new IntMod(1,3)});
        Poly<IntMod> d = new Poly<IntMod>(new IntMod[] {new IntMod(-1,3), new IntMod(0,3), new IntMod(1,3)});
        System.out.println(c.ge(d));
        System.out.println(d.ge(c));
        Poly<Rat> a = new Poly<Rat>(new Rat[] {new Rat( 1,1), new Rat(2,1), new Rat(1,1)});
        Poly<Rat> b = new Poly<Rat>(new Rat[] {new Rat(-1,1), new Rat(0,1), new Rat(1,1)});
        Poly<Rat> c = new Poly<Rat>(new Rat[] {new Rat( 1,1), new Rat(1,1), new Rat(1,1)});
        System.out.println(c+" added to "+b+" is: "+c.add(b));
        System.out.println("reverse: "+b.add(c));*/
        Poly<IntMod> a = new Poly<IntMod>(new IntMod[] {new IntMod( 1,3), new IntMod(2,3), new IntMod(1,3)});
        Poly<IntMod> b = new Poly<IntMod>(new IntMod[] {new IntMod(-1,3), new IntMod(0,3), new IntMod(1,3)});
        Poly<IntMod> c = new Poly<IntMod>(new IntMod[] {new IntMod( 1,3), new IntMod(1,3), new IntMod(1,3)});
        System.out.println("a is bigger than b: "+a.ge(b));
        System.out.println("b is bigger than a: "+b.ge(a));
        System.out.println(new IntMod(-1, 3).ge(new IntMod(0, 3)));
        System.out.println(new IntMod(0, 3).ge(new IntMod(-1, 3)));
    }
}
