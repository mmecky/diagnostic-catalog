package io.github.mmecky.diagnosticcatalog.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Set;

@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String symptom;
    private String cause;
    private String remedy;
    @ManyToOne
    private Component component;
    @ElementCollection
    private Set<String> tags;

    // Hibernate/JPA expects the no-argument constructor. Hide it from the application code.
    protected Entry() {
    }

    public Entry(String symptom, String cause, String remedy, Component component, Set<String> tags) {
        this.symptom = symptom;
        this.cause = cause;
        this.remedy = remedy;
        this.component = component;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public String getSymptom() {
        return symptom;
    }

    public String getCause() {
        return cause;
    }

    public String getRemedy() {
        return remedy;
    }

    public Component getComponent() {
        return component;
    }

    public Set<String> getTags() {
        return tags;
    }
}
