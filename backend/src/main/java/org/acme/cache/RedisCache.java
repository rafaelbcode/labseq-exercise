package org.acme.cache;

import java.math.BigInteger;
import java.util.Optional;

/**
 * Interface class for RedisCache, to store and retrieve values from in key,pair format
 */
public interface RedisCache {
    /**
     * Method to retrieve a cached BigInteger value
     *
     * @param key the String that represents the key
     * @return the BigInteger value linked to the key
     */
    Optional<BigInteger> get(String key);

    /**
     * Method to insert a key,value pair into the cache
     *
     * @param key   the String representing the key
     * @param value the BigInteger value
     */
    void set(String key, BigInteger value);
}
