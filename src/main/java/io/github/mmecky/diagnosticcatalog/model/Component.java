package io.github.mmecky.diagnosticcatalog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Component {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    // Hibernate/JPA expects the no-argument constructor. Hide it from the application code.
    protected Component() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
