package service;

import java.io.*;

/**
 * <p>Serialization class</p>
 */
public class FileWriter {
    public static boolean writeObject(Object data, String filePath) {
        try {
            FileOutputStream fout = new FileOutputStream(filePath);
            ObjectOutputStream outputStream = new ObjectOutputStream(fout);
            outputStream.writeObject(data);
            outputStream.close();
            fout.close();
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
            FileInputStream fin;
            if (file.exists()) {
                fin = new FileInputStream(file);
                ObjectInputStream inputStream = new ObjectInputStream(fin);
                object = inputStream.readObject();
                inputStream.close();
                fin.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}
