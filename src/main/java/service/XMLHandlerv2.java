package service;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.crypto.Data;

import data.DataRepository;
import data.Event;

/**
 * Klasa odpowiedzialna za zapis i odczyt z XML
 *
 */
public class XMLHandlerv2 {
    /**
     * Nazwa pliku, do kt�rego zostaja zapisane dane
     */
    private String filename;
    /**
     * Ustawia nazw� pliku, do kt�rego zostana zapisane dane
     * @param filename Nazwa pliku
     */
    public void setFilename(String filename) {
        this.filename=filename;
    }
    /**
     * �aduje dane zapisane w formacie XML do programu
     */
    public DataRepository LoadDataContext() throws Exception{
        File file = new File("XMLtest.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(DataRepository.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return  (DataRepository) jaxbUnmarshaller.unmarshal(file);
    }
    /**
     * Zapisuje dane do formatu XML
     */
    @SuppressWarnings("unchecked")
    public void SaveDataContext(DataRepository context) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(DataRepository.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(context, new File("XMLtest.xml"));
    }
}
