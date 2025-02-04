import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws FileNotFoundException {
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
}