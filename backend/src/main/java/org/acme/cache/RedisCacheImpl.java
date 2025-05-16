package org.acme.cache;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigInteger;
import java.util.Optional;

/**
 * Cache class implemented using RedisDataSource, used to store labseq values to increase performance
 */
@ApplicationScoped
public class RedisCacheImpl implements RedisCache {

    private ValueCommands<String, BigInteger> commands;

    /**
     * Constructor method to inject the RedisDataSource
     * @param dataSource the RedisDataSource
     */
    @Inject
    public RedisCacheImpl(RedisDataSource dataSource) {
        this.commands = dataSource.value(BigInteger.class);
    }

    /**
     * Method to retrieve a cached BigInteger value
     * @param key the String that represents the key
     * @return the BigInteger value linked to the key
     */
    @Override
    public Optional<BigInteger> get(String key) {
        return Optional.ofNullable(commands.get(key));
    }

    /**
     * Method to insert a key,value pair into the cache
     * @param key the String representing the key
     * @param value the BigInteger value
     */
    @Override
    public void set(String key, BigInteger value){
        commands.set(key,value);
    }


}
