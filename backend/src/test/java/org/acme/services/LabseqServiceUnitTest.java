package org.acme.services;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.cache.RedisCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigInteger;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

/**
 * Class for LabseqService unit testing, mocking the Redis cache
 */
@QuarkusTest
class LabseqServiceUnitTest {

    @InjectMock
    RedisCache redisCacheMock;

    @Inject
    LabseqService labseqService;

    /**
     * Ensure that the values retrieved from the cache are always empty
     */
    @BeforeEach
    void setUp() {
        Optional<BigInteger> empty = Optional.empty();
        Mockito.when(redisCacheMock.get(anyString())).thenReturn(empty);
    }

    /**
     * Test l(0) = 0
     */
    @Test
    void shouldReturnZeroWhenGivenZero() {
        BigInteger expectedResult = BigInteger.ZERO;
        assertEquals(expectedResult, labseqService.labseqCalculation(BigInteger.ZERO));

    }

    /**
     * Test l(1) = 1
     */
    @Test
    void shouldReturnOneWhenGivenOne() {
        BigInteger expectedResult = BigInteger.ONE;
        assertEquals(expectedResult, labseqService.labseqCalculation(BigInteger.ONE));
    }

    /**
     * Test l(2) = 0
     */
    @Test
    void shouldReturnZeroWhenGivenTwo() {
        BigInteger expectedResult = BigInteger.ZERO;
        assertEquals(expectedResult, labseqService.labseqCalculation(BigInteger.valueOf(2)));

    }

    /**
     * Test l(3) = 1
     */
    @Test
    void shouldReturnOneWhenGivenThree() {
        BigInteger expectedResult = BigInteger.ONE;
        assertEquals(expectedResult, labseqService.labseqCalculation(BigInteger.valueOf(3)));
    }

    /**
     * Test l(10) = 3
     */
    @Test
    void shouldReturnThreeWhenGivenTen() {
        BigInteger expectedResult = BigInteger.valueOf(3);
        assertEquals(expectedResult, labseqService.labseqCalculation(BigInteger.valueOf(10)));
    }

    /**
     * Test l(-1), service should throw IllegalArgumentException
     */
    @Test
    void shouldFailWhenGivenNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            labseqService.labseqCalculation(BigInteger.valueOf(-1));
        });
    }

    /**
     * Performance test, l(10000) should run under 10 seconds.
     */
    @Test
    void shouldRunUnderTenSecondsTenThousand() {
        long startTime = System.nanoTime();
        labseqService.labseqCalculation(BigInteger.valueOf(10000));
        long endTime = System.nanoTime();
        long elapsedSeconds = (endTime - startTime) / 1000000000;
        assertTrue(elapsedSeconds < 10);
    }

    /**
     * Verify that mock cache is called by the service, even if unused.
     */
    @Test
    void shouldCallCacheCorrectly() {
        labseqService.labseqCalculation(BigInteger.valueOf(1));
        verify(redisCacheMock).get(String.valueOf(BigInteger.valueOf(1)));
    }


}