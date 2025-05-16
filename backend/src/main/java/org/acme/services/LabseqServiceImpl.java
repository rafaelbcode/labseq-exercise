package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.cache.RedisCache;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class LabseqServiceImpl implements LabseqService {


    @Inject
    RedisCache cache;


    @Override
    public BigInteger labseqCalculation(BigInteger n) {
        if (n.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Input must be non-negative");
        }

        // Check the cache incase the value has already been calculated
        Optional<BigInteger> valueFromCache = cache.get(n.toString());
        if (valueFromCache.isPresent()) {
            return valueFromCache.get();
        }
        if (n.equals(BigInteger.ZERO)) {
            cache.set(n.toString(), BigInteger.ZERO);
            return BigInteger.ZERO;
        } else if (n.equals(BigInteger.ONE)) {
            cache.set(n.toString(), BigInteger.ONE);
            return BigInteger.ONE;
        } else if (n.equals(BigInteger.valueOf(2))) {
            cache.set(n.toString(), BigInteger.ZERO);
            return BigInteger.ZERO;
        } else if (n.equals(BigInteger.valueOf(3))) {
            cache.set(n.toString(), BigInteger.ONE);
            return BigInteger.ONE;
        } else {
            // handle n > 3 cases
            List<BigInteger> calculationList = new ArrayList<>();
            calculationList.add(BigInteger.ZERO);
            calculationList.add(BigInteger.ONE);
            calculationList.add(BigInteger.ZERO);
            calculationList.add(BigInteger.ONE);
            for (BigInteger i = BigInteger.valueOf(4); i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
                BigInteger newValue = calculationList.get(i.intValue() - 4).add(calculationList.get(i.intValue() - 3));
                calculationList.add(newValue);
                cache.set(i.toString(),newValue);
            }

            return calculationList.getLast();

        }


    }


    //Recursive version of the labseq, StackOverflow at larger numbers
    public BigInteger labseqCalculationRecursive(BigInteger n) {
        System.out.println(n.toString());

        if (n.equals(BigInteger.valueOf(0))) {
            return BigInteger.valueOf(0);
        } else if (n.equals(BigInteger.valueOf(1))) {
            return BigInteger.valueOf(1);
        } else if (n.equals(BigInteger.valueOf(2))) {
            return BigInteger.valueOf(0);
        } else if (n.equals(BigInteger.valueOf(3))) {
            return BigInteger.valueOf(1);
        } else if (n.compareTo(BigInteger.valueOf(3)) > 0) {
            return labseqCalculation(n.subtract(BigInteger.valueOf(4)))
                    .add(labseqCalculation(n.subtract(BigInteger.valueOf(3))));
        }

        return null;
    }
}
