package pe.edu.upc.psychotest_platform.planning.domain.model.aggregates;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import pe.edu.upc.psychotest_platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Table(name = "tests")
public class Test extends AuditableAbstractAggregateRoot<Test> {
    private String name;
    private String description;
}
