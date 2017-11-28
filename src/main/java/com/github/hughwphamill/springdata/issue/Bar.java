package com.github.hughwphamill.springdata.issue;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Bar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    @OneToMany(mappedBy = "bars", fetch = FetchType.LAZY)
    private Set<Foo> foos = new LinkedHashSet<>();

    public Bar() {
    }

    public Bar(final String description) {
        this.description = description;
    }

    public Bar(final Integer id, final String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Set<Foo> getFoos() {
        return new LinkedHashSet<>(foos);
    }

    void addFoo(Foo foo) {
        foos.add(foo);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Bar bar = (Bar) o;
        return Objects.equals(id, bar.id) &&
                Objects.equals(description, bar.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }

    @Override
    public String toString() {
        return "Bar{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
