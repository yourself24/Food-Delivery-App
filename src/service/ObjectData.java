package service;

import java.io.*;

/**
 * <p>Class that serialize objects</p>
 */
public class ObjectData {
   public static boolean writeObject(Object data, String filePath) {
      try {
         FileOutputStream outputFile = new FileOutputStream(filePath);
         ObjectOutputStream outputStream = new ObjectOutputStream(outputFile);
         outputStream.writeObject(data);
         outputStream.close();
         outputFile.close();
      } catch (IOException e) {
         e.printStackTrace();
         return false;
      }

      return true;
   }

   public static Object readObject(String filePath) {
      Object object = null;
      try {
         File file = new File(filePath);
         FileInputStream inputFile;
         if (file.exists()) {
            inputFile = new FileInputStream(file);
            ObjectInputStream inputStream = new ObjectInputStream(inputFile);
            object = inputStream.readObject();
            inputStream.close();
            inputFile.close();
         }
      } catch (IOException | ClassNotFoundException e) {
         e.printStackTrace();
      }
      return object;
   }
}
