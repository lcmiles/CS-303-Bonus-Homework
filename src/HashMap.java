public class HashMap {
    private static final int INITIAL_SIZE = 100;
    private HashEntry[] table;

    public HashMap() {
        table = new HashEntry[INITIAL_SIZE];
    }

    private int simpleHash(Long key) {
        return (int) (key % INITIAL_SIZE);
    }

    public String getName(Long key) {
        int index = simpleHash(key);
        int i = 1;
        while (table[index] != null && !table[index].getKey().equals(key)) {
            index = (index + i * i) % INITIAL_SIZE; // Quadratic probing
            i++;
        }
        if (table[index] != null && table[index].getKey().equals(key)) {
            return table[index].getName();
        } else {
            return "Name not found"; // Key not found
        }
    }

    public String getAmount(Long key) {
        int index = simpleHash(key);
        int i = 1;
        while (table[index] != null && !table[index].getKey().equals(key)) {
            index = (index + i * i) % INITIAL_SIZE; // Quadratic probing
            i++;
        }
        if (table[index] != null && table[index].getKey().equals(key)) {
            return table[index].getAmount();
        } else {
            return "Amount not found"; // Key not found
        }
    }

    public String get(Long key) {
        int index = simpleHash(key);
        while (table[index] != null) {
            if (table[index].getKey().equals(key)) {
                return table[index].getValue();
            }
            index = (7 * index + 1) % INITIAL_SIZE; // Linear probing
        }
        return null; // Key not found
    }

    public void put(Long key, String name, String amount) {
        String value = "Name: " + name + ", Amount: " + amount;
        int index = simpleHash(key);
        while (table[index] != null && !table[index].getKey().equals(key)) {
            index = (7 * index + 1) % INITIAL_SIZE; // Linear probing
        }
        if (table[index] != null) {
            // Key already exists; replace the value
            table[index].setValue(value);
        } else {
            table[index] = new HashEntry(key, name, amount, value);
        }
    }

    public void linearProbe(Long key, String name, String amount) {
        // Implement linear probing
        String value = "Name: " + name + ", Amount: " + amount;
        int index = simpleHash(key);
        int i = 1;
        while (table[index] != null && !table[index].getKey().equals(key)) {
            index = (index + i) % INITIAL_SIZE;
            i++;
        }
        if (table[index] != null) {
            // Key already exists; replace the value
            table[index].setValue(value);
        } else {
            table[index] = new HashEntry(key, name, amount, value);
        }
    }

    public void quadraticProbe(Long key, String name, String amount) {
        int index = simpleHash(key);
        int i = 1;
        while (table[index] != null && !table[index].getKey().equals(key)) {
            index = (index + i * i) % INITIAL_SIZE; // Quadratic probing
            i++;
        }
        if (table[index] != null) {
            // Key already exists; replace the value
            table[index].setValue("Name: " + name + ", Amount: " + amount);
        } else {
            table[index] = new HashEntry(key, name, amount, "Name: " + name + ", Amount: " + amount);
        }
    }

    public void putLinear(Long key, String name, String amount) {
        linearProbe(key, name, amount);
    }

    public void putQuadratic(Long key, String name, String amount) {
        quadraticProbe(key, name, amount); // Use quadratic probing
    }

    public void printAllEntries() {
        for (HashEntry entry : table) {
            if (entry != null) {
                Long key = entry.getKey();
                String name = entry.getName();
                String amount = entry.getAmount();
                System.out.println("Key: " + key + ", Name: " + name + ", Amount: " + amount);
            }
        }
    }
}
