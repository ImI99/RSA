package encrypt;

import java.math.BigInteger;

public class EncryptMath {

    public static BigInteger ModPow(BigInteger base, BigInteger power, BigInteger mod){

        BigInteger result = new BigInteger("1");

        BigInteger[] mods = new BigInteger[power.bitLength()];
        mods[0] = base.mod(mod);

        for (int i = 1; i < power.bitLength(); i++){
            mods[i] = mods[i-1].pow(2).mod(mod);
        }

        for (int i = 0; i < power.bitLength(); i++){
            if (power.testBit(i)){
                result = result.multiply(mods[i]);
            }
        }

        return result.mod(mod);
    }


    public static BigInteger ExtEuclidean(BigInteger m, BigInteger n){
        BigInteger a;
        BigInteger b;
        if(m.compareTo(n) == 1) {
            a = m;
            b = n;
        }
        else {
            b = m;
            a=n;
        }
        BigInteger q = new BigInteger("0");
        BigInteger r = new BigInteger("1");
        BigInteger s0 = new BigInteger("1");
        BigInteger s1 = new BigInteger("0");
        BigInteger t0 = new BigInteger("0");
        BigInteger t1 = new BigInteger("1");

        BigInteger buffer;

        while (!r.equals(BigInteger.ZERO)){

            Logger.Detailed("k= "+Logger.count()+ " r: "+a.toString()+" q: "+q.toString()+" x: "+s0.toString()+" y: "+t0.toString());

            q = a.divide(b);
            r = a.subtract(b.multiply(q));
            a = b;
            b = r;
            buffer = s1;
            s1 = s0.subtract(q.multiply(s1));
            s0 = buffer;

            buffer = t1;
            t1 = t0.subtract(q.multiply(t1));
            t0 = buffer;


        }

        Logger.Detailed("k= "+Logger.count()+ " r: "+a.toString()+" q: "+q.toString()+" x: "+s0.toString()+" y: "+t0.toString());

        return t0;
    }
}
