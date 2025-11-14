package pe.edu.upc.psychotest_platform.planning.domain.model.valueobjects;

import java.util.List;

public record Question(int id, String text, List<Option> options) {
}
