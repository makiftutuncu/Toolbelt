package com.github.mehmetakiftutuncu.toolbelt;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StringUtilitiesUnitTest {
    @Test public void stringIsEmpty() {
        assertTrue(StringUtilities.isEmpty(null));
        assertTrue(StringUtilities.isEmpty(""));
    }

    @Test public void makeString() {
        List<String> list = new ArrayList<>();
        list.add("foo");
        list.add("bar");
        list.add("baz");

        assertEquals("", StringUtilities.makeString(null, "[", ", ", "]"));
        assertEquals("[]", StringUtilities.makeString(new ArrayList<String>(), "[", ", ", "]"));
        assertEquals("['foo', 'bar', 'baz']", StringUtilities.makeString(list, "['", "', '", "']"));

        assertEquals("", StringUtilities.makeString(null, ", "));
        assertEquals("", StringUtilities.makeString(new ArrayList<String>(), ", "));
        assertEquals("foo, bar, baz", StringUtilities.makeString(list, ", "));
    }
}
