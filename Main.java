import java.io.*;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        addObject(new Item("TEST", "OBJECT"), new File("test.txt"));

        for (Item i : getObjects(new File("test.txt"))) {
            System.out.println(i.getKey() + ", " + i.getValue());
        }
    }

    public static ArrayList<Item> getObjects(File file) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        
        while(scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        for (String line : lines) {
            items.add(new Item(line.substring(line.indexOf("{") + 1, line.indexOf(":")), line.substring(line.indexOf(":") + 1, line.indexOf("}"))));
        }
        return items;
    }

    public static void addObject(Item item, File file) throws Exception {
        if (file.exists() != true || file.canWrite() != true) {
            throw new FileNotFoundException("addObject paramater file can't be written to or can't be found :(");
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getPath(), true));
        writer.write("{" + item.getKey() + ":" + item.getValue() + "}");
        writer.newLine();
        writer.close();
    }
}