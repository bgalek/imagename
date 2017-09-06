package com.github.bgalek.imagename;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.function.Function;

public class ImageName {

    private static final String GIF_EXTENSION = "gif";
    private static final String PNG_EXTENSION = "png";
    private static final String JPG_EXTENSION = "jpg";

    private final HashGenerator hashGenerator;

    public ImageName() {
        this(new Md5HashGenerator());
    }

    public ImageName(HashGenerator hashGenerator) {
        this.hashGenerator = hashGenerator;
    }

    public String generate(byte[] data) throws ImageNameException {
        try {
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(data));
            int height = bufferedImage.getHeight();
            int width = bufferedImage.getWidth();
            String extension = detectFileFormat(new ByteArrayInputStream(data));
            String filename = Base62.encode(hashGenerator.hash(data));
            return String.format("%s-w%d-h%d.%s", filename, width, height, extension);
        } catch (Exception e) {
            throw new ImageNameException("Cannot read image from provided byte array", e);
        }
    }

    public String generate(byte[] data, Function<? super Throwable, String> onError) {
        try {
            return generate(data);
        } catch (ImageNameException exception) {
            return onError.apply(exception);
        }
    }

    private String detectFileFormat(ByteArrayInputStream input) throws IOException {
        switch (URLConnection.guessContentTypeFromStream(input)) {
            case "image/gif": {
                return GIF_EXTENSION;
            }
            case "image/png": {
                return PNG_EXTENSION;
            }
            default: {
                return JPG_EXTENSION;
            }
        }
    }
}