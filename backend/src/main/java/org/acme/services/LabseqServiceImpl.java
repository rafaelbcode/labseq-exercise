package org.acme.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.cache.RedisCache;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation class of the LabseqService interface
 */
@ApplicationScoped
public class LabseqServiceImpl implements LabseqService {

    @Inject
    RedisCache cache;

    /**
     * Method to calculate the nth value of the labseq sequence
     * @param n the index of the sequence value to be return
     * @return the value present at index n in the labseq sequence
     */
    @Override
    public BigInteger labseqCalculation(BigInteger n) {
        // Throw an exception if a negative value is received
        if (n.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("Input must be non-negative");
        }

        // Check the cache in case the value has already been calculated
        Optional<BigInteger> valueFromCache = cache.get(n.toString());
        if (valueFromCache.isPresent()) {
            // Return the cached value if it exists
            return valueFromCache.get();
        }
        // Handling the first four iterations separately, adding them to cache if needed
        if (n.equals(BigInteger.ZERO)) {
            // l(0) = 0
            cache.set(n.toString(), BigInteger.ZERO);
            return BigInteger.ZERO;
        } else if (n.equals(BigInteger.ONE)) {
            // l(1) = 1
            cache.set(n.toString(), BigInteger.ONE);
            return BigInteger.ONE;
        } else if (n.equals(BigInteger.valueOf(2))) {
            // l(2) = 0
            cache.set(n.toString(), BigInteger.ZERO);
            return BigInteger.ZERO;
        } else if (n.equals(BigInteger.valueOf(3))) {
            // l(3) = 1
            cache.set(n.toString(), BigInteger.ONE);
            return BigInteger.ONE;
        } else {
            // Handle the cases when n > 3
            List<BigInteger> sequenceList = new ArrayList<>();
            //Add the first four cases to the sequence list
            sequenceList.add(BigInteger.ZERO);
            sequenceList.add(BigInteger.ONE);
            sequenceList.add(BigInteger.ZERO);
            sequenceList.add(BigInteger.ONE);
            // Loop all values until and including n
            for (BigInteger i = BigInteger.valueOf(4); i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {

                BigInteger newValue;

                //check if value exists in cache, else calculate
                Optional<BigInteger> checkForValueFromCache = cache.get(i.toString());
                if (checkForValueFromCache.isPresent()) {
                    // Use the cached value if it exists
                   newValue = checkForValueFromCache.get();
                } else {
                    // Calculate the new value
                    newValue = sequenceList.get(i.intValue() - 4).add(sequenceList.get(i.intValue() - 3));
                    // save it to the cache for future calculations
                    cache.set(i.toString(),newValue);
                }
                sequenceList.add(newValue);
            }
            //Return the nth value of the labseq sequence
            return sequenceList.getLast();

        }

    }

}
