package com.github.hughwphamill.springdata.issue;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Foo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "foo_bar", joinColumns = {@JoinColumn(name = "foo_id")},
            inverseJoinColumns = {@JoinColumn(name = "bar_id")})
    private Set<Bar> bars = new LinkedHashSet<>();

    public Foo() {
    }

    public Foo(final String name) {
        this.name = name;
    }

    public Foo(final Integer id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<Bar> getBars() {
        return new LinkedHashSet<>(bars);
    }

    public void addBar(Bar bar) {
        bars.add(bar);
        bar.addFoo(this);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Foo foo = (Foo) o;
        return Objects.equals(id, foo.id) &&
                Objects.equals(name, foo.name) &&
                Objects.equals(bars, foo.bars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bars);
    }

    @Override
    public String toString() {
        return "Foo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bars=" + bars +
                '}';
    }
}
