package com.github.bgalek.imagename;

import java.math.BigInteger;

public interface HashGenerator {
    /**
     * @return no negative numbers
     */
    BigInteger hash(byte[] data);
}
