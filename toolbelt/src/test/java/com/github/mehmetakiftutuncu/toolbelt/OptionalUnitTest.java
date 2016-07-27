package com.github.mehmetakiftutuncu.toolbelt;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class OptionalUnitTest {
    @Test public void createEmptyOptional() {
        Optional<Integer> empty = Optional.empty();

        assertNotNull(empty);
        assertTrue(empty.isEmpty());
        assertTrue(!empty.isDefined());
    }

    @Test public void createExistingOptional() {
        Optional<Integer> existing = Optional.with(3);

        assertNotNull(existing);
        assertTrue(existing.isDefined());
        assertTrue(!existing.isEmpty());
    }

    @Test public void getExistingOptional() {
        Optional<Integer> existing = Optional.with(3);

        assertNotNull(existing);
        assertEquals(Integer.valueOf(3), existing.get());
    }

    @Test(expected=NoSuchElementException.class) public void getEmptyOptionalThrows() {
        Optional<Integer> empty = Optional.empty();

        assertNotNull(empty);
        empty.get();
    }

    @Test public void getOrElseEmptyOptional() {
        Optional<Integer> empty = Optional.empty();

        assertNotNull(empty);
        assertEquals(Integer.valueOf(3), empty.getOrElse(3));
    }

    @Test public void orOptional() {
        Optional<Integer> empty       = Optional.empty();
        Optional<Integer> existing    = Optional.with(3);
        Optional<Integer> alternative = Optional.with(5);

        assertNotNull(empty);
        assertNotNull(existing);
        assertEquals(alternative, empty.or(alternative));
        assertEquals(existing, existing.or(alternative));
    }

    @Test public void toStringEmptyOptional() {
        Optional<Integer> empty = Optional.empty();

        assertNotNull(empty);
        assertEquals("None", empty.toString());
    }

    @Test public void toStringExistingOptional() {
        Optional<Integer> existing = Optional.with(3);

        assertNotNull(existing);
        assertEquals("Some(3)", existing.toString());
    }
}
