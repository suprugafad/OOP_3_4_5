package sample.service.impl.serialization;

import javafx.collections.ObservableList;
import sample.entity.Worker;
import sample.service.SerializationService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BinarySerializationMethod implements SerializationService {
   @Override
   public void save(ObservableList<? extends Worker> observableList, File file) throws IOException {
      PrintWriter printWriter = new PrintWriter(new FileOutputStream(file));
      byte[] bytes = convertToBytes(new ArrayList<>(observableList));
      for (byte aByte : bytes) {
         printWriter.write(String.valueOf(aByte) + " ");
      }
      printWriter.close();
   }

   private static byte[] convertToBytes(Object object) throws IOException {
      try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
           ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
         objectOutputStream.writeObject(object);
         return byteArrayOutputStream.toByteArray();
      }
   }

}
