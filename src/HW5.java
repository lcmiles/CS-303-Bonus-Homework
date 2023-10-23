import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HW5 {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        List<Long> keys = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> amounts = new ArrayList();

        // Load data from CSV file to ArrayLists
        loadCSVData("UPC-10.csv", keys, names, amounts);

        // Add data from ArrayLists to the HashMap using put
        for (int i = 0; i < keys.size(); i++) {
            hashMap.put(keys.get(i), names.get(i), amounts.get(i));
        }

        // Print all entries in the HashMap
        System.out.println("HashMap using put:");
        hashMap.printAllEntries();
        System.out.println();

        // Search for specific keys and print their data
        System.out.println("Search results using get:");
        long startTimeGet = System.nanoTime();
        searchAndPrint(hashMap, keys);
        long endTimeGet = System.nanoTime();
        System.out.println();
        Long getTime = endTimeGet - startTimeGet;
        System.out.println("Time taken to search using get: " + getTime + " nanoseconds, " + (float)getTime / 1000000 + " milliseconds, or " + (float)getTime / 1000000000 + " seconds");


        // Search for specific keys and print their data using linear probing
        HashMap hashMapLinear = new HashMap();
        for (int i = 0; i < keys.size(); i++) {
            hashMapLinear.putLinear(keys.get(i), names.get(i), amounts.get(i));
        }
        System.out.println("Search results using linear probing:");
        long startTimeLinearProbe = System.nanoTime();
        searchAndPrint(hashMapLinear, keys);
        long endTimeLinearProbe = System.nanoTime();
        System.out.println();
        Long linearProbeTime = endTimeLinearProbe - startTimeLinearProbe;
        System.out.println("Time taken to search using linear probing: " + linearProbeTime + " nanoseconds, " + (float)linearProbeTime / 1000000 + " milliseconds, or " + (float)linearProbeTime / 1000000000 + " seconds");



        // Search for specific keys and print their data using quadratic probing
        HashMap hashMapQuadratic = new HashMap();
        for (int i = 0; i < keys.size(); i++) {
            hashMapQuadratic.put(keys.get(i), names.get(i), amounts.get(i));
        }
        System.out.println("Search results using quadratic probing:");
        long startTimeQuadraticProbe = System.nanoTime();
        searchAndPrint(hashMapQuadratic, keys);
        long endTimeQuadraticProbe = System.nanoTime();
        System.out.println();
        Long quadraticProbeTime = endTimeQuadraticProbe - startTimeQuadraticProbe;
        System.out.println("Time taken to search using quadratic probing: " + quadraticProbeTime + " nanoseconds, " + (float)quadraticProbeTime / 1000000 + " milliseconds, or " + (float)quadraticProbeTime / 1000000000 + " seconds");
    }

    public static void searchAndPrint(HashMap hashMap, List<Long> searchKeys) {
        for (Long key : searchKeys) {
            String name = hashMap.getName(key);
            String amount = hashMap.getAmount(key);
            System.out.println("Key: " + key + ", Name: " + name + ", Amount: " + amount);
        }
    }
    

    public static void loadCSVData(String filename, List<Long> keys, List<String> names, List<String> amounts) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    Long key = Long.parseLong(parts[0]);
                    String amount = parts[1];
                    String name = parts[2];
                    keys.add(key);
                    names.add(name);
                    amounts.add(amount);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadSearchKeys(String filename, List<Long> searchKeys) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1) {
                    Long key = Long.parseLong(parts[0]);
                    searchKeys.add(key);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
