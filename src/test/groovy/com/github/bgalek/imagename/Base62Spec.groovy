package com.github.bgalek.imagename

import spock.lang.Specification
import spock.lang.Unroll

class Base62Spec extends Specification {
    @Unroll
    def "should return encoded value #encoded for given #bigInt"() {
        expect:
        Base62.encode(bigInt) == encoded

        where:
        bigInt                                           || encoded
        BigInteger.ZERO                                  || '0'
        BigInteger.ONE                                   || '1'
        BigInteger.valueOf(1111111111L)                  || '1DC6kx'
        new BigInteger("012345678901234567890123456789") || 'G3TaPlArHEKXtTBV'
    }
}
