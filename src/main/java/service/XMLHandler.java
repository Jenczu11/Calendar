package service;

import data.DataRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Klasa odpowiedzialna za zapis i odczyt z XML
 *
 */
@SuppressWarnings("RedundantSuppression")
public class XMLHandler implements IOHandler {
    /**
     * Nazwa pliku, do ktorego zostaja zapisane dane
     */
    private String filename;
    /**
     * Ustawia nazwe pliku, do ktorego zostana zapisane dane
     * @param filename Nazwa pliku
     */
    public void setFilename(String filename) {
        this.filename=filename;
    }

    public XMLHandler(String filename) {
        if(filename.isEmpty())
            filename="XMLtest.xml";
        this.filename = filename;
    }

    /**
     * Laduje dane zapisane w formacie XML do programu
     */
    @Override
    public DataRepository LoadData() throws Exception {
        File file = new File(filename);
        JAXBContext jaxbContext = JAXBContext.newInstance(DataRepository.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return  (DataRepository) jaxbUnmarshaller.unmarshal(file);
    }

    /**
     * Zapisuje dane do formatu XML
     */
    @Override
    public void SaveData(DataRepository data) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(DataRepository.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(data, new File(filename));
    }



}
