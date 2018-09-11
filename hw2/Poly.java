 

import ring.Comp;
import ring.Field;

@SuppressWarnings("unchecked")
public class Poly<F extends Field> implements Ring, Modulo, Ordered {
    private F[] coef;
    public Poly(F[] coef) {
        int n = coef.length;
        while(n >= 2 && Comp.eq(coef[n-1], coef[0].addIdentity()))
            n--;
        this.coef = (F[])new Field[n];
        for(int i = 0; i < n; i++)
            this.coef[i] = coef[i];
            
    }    
    public F[] getCoef() {
        return coef;
    }
    public String toString() {
        String str = "";
        for(int i = coef.length - 1; i > 0; i--)
            str = str + coef[i] + "x^" + i + " + ";
        str = str + coef[0];
        return str;
    }
    public Ring add(Ring a) {
        Poly that = (Poly)a;
        F[] thatCoef = (F[])(that.getCoef());
        F[] newCoef, smallerCoef;
        if(this.coef.length >= thatCoef.length){
            newCoef = this.coef.clone();
            smallerCoef = thatCoef.clone();
        }
        else{
            newCoef = thatCoef.clone();
            smallerCoef = this.coef.clone();
        }
        for(int i = 0; i < smallerCoef.length; i++){
            newCoef[i].add(smallerCoef[i]);
        }
        return new Poly(newCoef);
    }
    public Poly del(Poly that) {
        return (Poly)this.add(that.addInverse());
    }
    public Ring mul(Ring a) {
        Poly that = (Poly)a;
        F[] thatCoef = (F[])(that.getCoef());
        F[] newCoef = (F[])new Field[this.coef.length + thatCoef.length - 1];
        for(int i = 0; i < this.coef.length; i++){
            for (int j = 0; j < thatCoef.length; j++){
                newCoef[i+j].add(this.coef[i].mul(thatCoef[j]));
            }
        }
        return new Poly(newCoef);
    }
    public Poly[] longdiv(Poly that) {
        //return value: longdiv(...)[0]: quotient,
        //              longdiv(...)[1]: remainder
        
        //degree of divisor
        int dd = that.coef.length - 1; 
        
        //quotient
        F[] q = (F[])new Field[this.coef.length - that.coef.length + 1]; 
        
        //remainder
        F[] r = (F[])new Field[this.coef.length];  
        
        //copy coef to r
        for(int i = 0; i < this.coef.length; i++)
        {
            r[i] = this.coef[i];
        }
        
        //the long division algorithm
        for(int qi = q.length-1; qi >= 0; qi--) 
        {
            q[qi] = r[qi + dd].mul(that.coef[dd].mulInverse());
            
            for(int i = 0; i <= dd; i++)
            {
                r[qi+i] = r[qi+i].add((q[qi].mul(that.coef[i])).addInverse());
            }
        }
        
        return new Poly[] {new Poly(q), new Poly(r)};
    }
    public Ring addIdentity() {
        return new Poly((F[])(new Field[]{}));
    }
    public Ring addInverse() {
        F[] tempCoef = this.coef.clone();
        tempCoef.getClass().getComponentType();
        for(int i = 0; i < tempCoef.length; i++){
            tempCoef[i] = (F)this.coef[i].addInverse();
        }
        return new Poly(tempCoef);
    }
    public Ring mod(Ring a) {
        return this.longdiv((Poly)a)[1];
    }//remainder
    public Ring quo(Ring a) {
        return this.longdiv((Poly)a)[0];
    }//quotient
    public boolean ge(Ordered a){   //greater than or equal to
        Poly tempA = (Poly)a;
        F[] aCoef = (F[])(tempA.getCoef());
        Poly tempPoly = this.del(tempA);
        if(tempPoly.getCoef().length == 0){
                return true;
        }
        else if(this.coef.length > aCoef.length){
            return true;
        }
        else if(this.coef.length == aCoef.length){
            if(Comp.gt(tempPoly.getCoef()[tempPoly.getCoef().length - 1], tempPoly.addIdentity())){
                return true;
            }
        }
        return false;
    }
}