package com.github.bgalek.imagename;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

final class Base62 {

    private static final char[] DICTIONARY = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
            'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z'};

    private static final BigInteger BASE = new BigInteger(Integer.toString(DICTIONARY.length));

    static String encode(BigInteger value) {
        if (value.equals(BigInteger.ZERO)) {
            return "0";
        }
        List<Character> result = new ArrayList<>();
        BigInteger remaining = value;
        for (int exponent = 1; !remaining.equals(BigInteger.ZERO); exponent++) {
            BigInteger a = BASE.pow(exponent);
            BigInteger b = remaining.mod(a);
            BigInteger c = BASE.pow(exponent - 1);
            BigInteger d = b.divide(c);
            result.add(DICTIONARY[d.intValue()]);
            remaining = remaining.subtract(b);
        }
        Collections.reverse(result);
        return result.stream()
                .map(it -> Character.toString(it))
                .collect(Collectors.joining(""));
    }
}
