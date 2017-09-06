package com.github.bgalek.imagename

import spock.lang.Specification
import spock.lang.Unroll

class Md5HashGeneratorSpec extends Specification {

    @Unroll
    def "should generate hash"() {
        given:
        def generator = new Md5HashGenerator()

        expect:
        generator.hash(input as byte[]).toString() == output

        where:
        input                          || output
        [0x0]                          || "196354609347928108891364980146585313137"
        [0x1]                          || "113842407384990359002707962975597223745"
        [0xf]                          || "287406147772345588351247021840257287124"
        [0xf1]                         || "315987756180644147564476063937161036721"
        []                             || "281949768489412648962353822266799178366"
        [0x0, 0x1, 0x0, 0x1, 0x0, 0x1] || "326157087853104707081798958080066584826"
    }
}
