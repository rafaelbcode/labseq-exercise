package org.acme.cache;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigInteger;
import java.util.Optional;

@ApplicationScoped
public class RedisCache {

    private ValueCommands<String, BigInteger> commands;

    @Inject
    public RedisCache(RedisDataSource dataSource) {
        this.commands = dataSource.value(BigInteger.class);
    }

    public Optional<BigInteger> get(String key) {
        return Optional.ofNullable(commands.get(key));
    }

    public void set(String key, BigInteger value){
        commands.set(key,value);
    }


}
