package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigInteger;

@ApplicationScoped
public class LabseqServiceImpl implements LabseqService {


    @Override
    public BigInteger labseqCalculation(BigInteger n) {

        //Check cache for value

        if(n.equals(BigInteger.valueOf(0))){
            return BigInteger.valueOf(0);
        }else if(n.equals(BigInteger.valueOf(1))){
            return BigInteger.valueOf(1);
        }else if (n.equals(BigInteger.valueOf(2))){
            return BigInteger.valueOf(0);
        } else if (n.equals(BigInteger.valueOf(3))){
            return BigInteger.valueOf(1);
        } else if(n.compareTo(BigInteger.valueOf(3))>0){
            return labseqCalculation(n.subtract(BigInteger.valueOf(4)))
                    .add(labseqCalculation(n.subtract(BigInteger.valueOf(3))));
        }

        return null;
    }
}
