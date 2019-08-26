package com.mateuszcholyn

import spock.lang.Specification
import spock.lang.Unroll

import javax.xml.bind.Marshaller

class PersonMarshallerTest extends Specification {

    def personMarshaller = new PersonMarshaller()

    @Unroll
    def "marshall using '#key=#value'"() {
        given:
        def marshaller = personMarshaller.createMarshaller(key, value)

        when:
        def marshalledPerson = personMarshaller.marshallUsing(marshaller, createDummyPerson())

        then:
        assert marshalledPerson == result

        where:
        key                               | value
        Marshaller.JAXB_FRAGMENT          | true
        "com.sun.xml.bind.xmlDeclaration" | false
    }

    static Person createDummyPerson() {
        new Person(name: "Jon", address: new Address(city: "Cracow"))
    }

    def result =
            """<Person xmlns="https://github.com/starwarsjk/person" xmlns:ns2="https://github.com/starwarsjk/address">
    <Name>Jon</Name>
    <Address>
        <ns2:City>Cracow</ns2:City>
    </Address>
</Person>"""

}
