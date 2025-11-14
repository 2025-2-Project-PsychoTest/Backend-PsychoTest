package pe.edu.upc.psychotest_platform.test.domain.model.valueobjects;

public enum CategoriesExam {
    PREMIUN(1),
    NOPREMIUN(2);

    private final int value;

    CategoriesExam(int value) {
        this.value = value;
    }

    public static CategoriesExam fromName(String name) {
        for (CategoriesExam type : values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid category type: " + name);
    }
}
