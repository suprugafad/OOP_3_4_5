package sample.service.impl.deserialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.entity.Worker;
import sample.service.DeserializationService;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class BinaryDeserializationMethod implements DeserializationService {
    @Override
    public ObservableList<Worker> open(File file) throws IOException, ClassNotFoundException {
        @SuppressWarnings("unchecked")
        List<Worker> list = (List<Worker>) convertFromBytes(fromFileToBytes(file));
        ObservableList<Worker> observableList = FXCollections.observableArrayList();
        observableList.addAll(list);
        return observableList;
    }

    private static Object convertFromBytes(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream in = new ObjectInputStream(bis)) {
            return in.readObject();
        }
    }

    private static byte[] fromFileToBytes(File file) {
        List<Byte> byteList = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            int c;
            StringBuffer stringBuffer = new StringBuffer();
            while ((c = reader.read()) != -1) {
                if (c != ' ') {
                    stringBuffer.append((char) c);
                } else {
                    byteList.add(Byte.parseByte(String.valueOf(stringBuffer)));
                    stringBuffer = new StringBuffer();
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) {
            bytes[i] = byteList.get(i);
        }
        return bytes;
    }
}
