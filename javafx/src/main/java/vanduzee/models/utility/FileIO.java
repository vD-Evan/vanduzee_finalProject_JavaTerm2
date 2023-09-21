package vanduzee.models.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import vanduzee.objects.MasterList;

public class FileIO {
    private static final String filename = "javafx/src/main/gradebook.txt";

    public static MasterList loadSingletonFromFile() {
        MasterList masterList = null;
        ManageConsole.clearConsole();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            masterList = (MasterList) ois.readObject();
            System.out.println("MasterList loaded from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return masterList;
    }

    public static void saveSingletonToFile() {
        MasterList masterList = MasterList.getInstance();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(masterList);
            ManageConsole.clearConsole();
            System.out.println("MasterList saved to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
