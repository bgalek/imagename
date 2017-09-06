package com.github.bgalek.imagename

import spock.lang.Specification

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class ImageNameSpec extends Specification {

    def "should include width and height in file name"() {
        given:
        def width = 100
        def height = 200
        def image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR)
        def outputStream = new ByteArrayOutputStream()
        ImageIO.write(image, "jpg", outputStream)

        expect:
        new ImageName().generate(outputStream.toByteArray()) == "MFHckFZLtABxpP1yJK2w5-w${width}-h${height}.jpg"
    }

    def "should be able to provide own hashing function"() {
        given:
        def customHashFunction = { it -> BigInteger.ZERO }
        def width = 100
        def height = 200
        def image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR)
        def outputStream = new ByteArrayOutputStream()
        ImageIO.write(image, "png", outputStream)

        expect:
        new ImageName(customHashFunction as HashGenerator).generate(outputStream.toByteArray()) == '0-w100-h200.png'
    }

    def "should throw exception for invalid image"() {
        given:
        def outputStream = new ByteArrayOutputStream()

        when:
        new ImageName().generate(outputStream.toByteArray())

        then:
        thrown ImageNameException
    }

    def "should be able to provide fallback function"() {
        given:
        def outputStream = new ByteArrayOutputStream()

        expect:
        new ImageName().generate(outputStream.toByteArray(), { it -> "defaultName" }) == "defaultName"
    }
}
