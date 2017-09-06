package com.github.bgalek.imagename;

public final class ImageNameException extends Throwable {

    ImageNameException(String message, Throwable cause) {
        super("ImageName generator error: " + message, cause);
    }
}
