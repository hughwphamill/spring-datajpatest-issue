package com.github.hughwphamill.springdata.issue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTest {

    @Autowired
    FooRepository fooRepository;

    @Autowired
    BarRepository barRepository;

    @Test
    public void can_save_foo() throws Exception {
        // Given
        final Foo foo = new Foo("name");

        // When
        final Foo saved = fooRepository.save(foo);

        // Then
        assertThat(saved.getId()).isGreaterThan(0);
    }

    @Test
    public void can_associate_bar_with_foo() throws Exception {
        // Given
        final Foo foo = new Foo("name");
        final Bar bar = new Bar("description");

        // When
        final Bar savedBar = barRepository.save(bar);
        foo.addBar(savedBar);
        final int fooId = fooRepository.save(foo).getId();
        final Foo retrievedFoo = fooRepository.findOne(fooId);

        // Then
        assertThat(retrievedFoo.getBars()).containsExactly(savedBar);
    }

    @Test
    public void unmanaged_bar_cannot_be_saved_through_foo() throws Exception {
        // Given
        final Foo foo = new Foo("name");
        final Bar bar = new Bar("description");

        // When
        foo.addBar(bar);
        final int fooId = fooRepository.save(foo).getId();
        final Foo retrievedFoo = fooRepository.findOne(fooId);

        // Then
        assertThat(retrievedFoo.getBars()).isEmpty(); // <-- Not Empty!
    }
}