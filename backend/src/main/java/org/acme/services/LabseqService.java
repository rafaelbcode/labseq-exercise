package org.acme.services;

import java.math.BigInteger;

/**
 * Interface class for the LabseqService, responsible for the labseq calculation
 */
public interface LabseqService {

    BigInteger labseqCalculation(BigInteger n);

}
