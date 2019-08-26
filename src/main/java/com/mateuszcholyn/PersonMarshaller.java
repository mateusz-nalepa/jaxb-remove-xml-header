package com.mateuszcholyn;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class PersonMarshaller {

    private final JAXBContext jaxbContext;

    public PersonMarshaller() {
        try {
            this.jaxbContext = JAXBContext.newInstance(Person.class);
        } catch (JAXBException e) {
            throw new IllegalStateException("Failed to initialise JaxbContext");
        }
    }

    Marshaller createMarshaller(String property, boolean value) {
        try {
            var marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(property, value);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            return marshaller;
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    String marshallUsing(Marshaller marshaller, Person person) {
        try {
            var stringWriter = new StringWriter();
            marshaller.marshal(person, stringWriter);
            return stringWriter.toString();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

}
