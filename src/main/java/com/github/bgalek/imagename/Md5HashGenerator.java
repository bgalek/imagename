package com.github.bgalek.imagename;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

final class Md5HashGenerator implements HashGenerator {

    @Override
    public BigInteger hash(byte[] data) {
        MessageDigest messageDigest = md5Digest();
        messageDigest.update(data);
        byte[] digest = messageDigest.digest();
        return new BigInteger(1, digest);
    }

    private static MessageDigest md5Digest() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
