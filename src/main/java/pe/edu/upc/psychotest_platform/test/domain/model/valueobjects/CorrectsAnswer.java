package pe.edu.upc.psychotest_platform.test.domain.model.valueobjects;

public enum CorrectsAnswer {
    A (1),
    B (2),
    C (3);

    private final int value;

    CorrectsAnswer(int value) {
        this.value = value;
    }

    public static CorrectsAnswer fromName(String name) {
        for (CorrectsAnswer type : values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid correct answer type: " + name);
    }
}
