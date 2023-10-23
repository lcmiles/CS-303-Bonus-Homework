public class HashEntry {
    private Long key;
    private String name;
    private String amount;
    private String value;

    public HashEntry(Long key, String name, String amount, String value) {
        this.key = key;
        this.name = name;
        this.amount = amount;
        this.value = value;
    }

    public Long getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
