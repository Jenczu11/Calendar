package service;

import data.DataRepository;
import data.Event;

import javax.xml.crypto.Data;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
/*
Class not in use
 */
@Deprecated
public class XMLHandlerNotWorking {

    public ArrayList<Event> LoadData() throws Exception {
        ArrayList<Event> base = new ArrayList<Event>();
//        DataRepository base = new DataRepository();
        FileInputStream fis2 = null;
        try {
            fis2 = new FileInputStream("base.xml");
            XMLDecoder decoder = new XMLDecoder(fis2);
            base = (ArrayList<Event>) decoder.readObject();
            decoder.close();
            fis2.close();
            System.out.println("Import zakonczony");
            return base;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }


    public void SaveData(ArrayList<Event> data) throws Exception {
        FileOutputStream fos2 = new FileOutputStream("base.xml");
        XMLEncoder encoder = new XMLEncoder(fos2);
        encoder.writeObject(data);
        encoder.flush();
        encoder.close();
        fos2.close();

    }
}
