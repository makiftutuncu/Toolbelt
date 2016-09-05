package com.github.mehmetakiftutuncu.toolbelt;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Collection;
import java.util.Iterator;

/**
 * Contains some utility methods related to {@link String}s
 *
 * @author Mehmet Akif Tutuncu
 */
public final class StringUtilities {
    /**
     * Checks whether given string is null or empty
     *
     * @param s String to check
     *
     * @return true if given string is null or empty or false otherwise
     */
    public static boolean isEmpty(@Nullable String s) {
        return s == null || s.isEmpty();
    }

    /**
     * <p>Combines elements in given collection to make a string</p>
     *
     * <p>Example:</p>
     * <pre>
     *     List&lt;String&gt; list = new ArrayList&lt;&gt;();
     *     list.add("foo");
     *     list.add("bar");
     *     list.add("baz");
     *
     *     // This will return: ['foo', 'bar', 'baz']
     *     StringUtilities.makeString(list, "['", "', '", "']");
     * </pre>
     *
     * @param collection Collection from which to make a string
     * @param prefix     String to add at the beginning of the resulting string
     * @param separator  String to add between elements of the collection
     * @param suffix     String to add to the end of the resulting string
     *
     * @return Combined string or empty string if collection is null
     */
    public static String makeString(@Nullable Collection collection,
                                    @NonNull String prefix,
                                    @NonNull String separator,
                                    @NonNull String suffix) {
        if (collection == null) return "";

        StringBuilder stringBuilder = new StringBuilder(prefix);

        int size = collection.size();

        if (size > 0) {
            Iterator iterator = collection.iterator();

            for (int i = 0; iterator.hasNext(); i++) {
                stringBuilder.append(iterator.next());

                if (i < size - 1) {
                    stringBuilder.append(separator);
                }
            }
        }

        stringBuilder.append(suffix);

        return stringBuilder.toString();
    }

    /**
     * <p>Combines elements in given collection to make a string</p>
     *
     * <p>Example:</p>
     * <pre>
     *     List&lt;String&gt; list = new ArrayList&lt;&gt;();
     *     list.add("foo");
     *     list.add("bar");
     *     list.add("baz");
     *
     *     // This will return: foo, bar, baz
     *     StringUtilities.makeString(list, ", ");
     * </pre>
     *
     * @param collection Collection from which to make a string
     * @param separator  String to add between elements of the collection
     *
     * @return Combined string or empty string if collection is null
     */
    public static String makeString(@Nullable Collection collection, @NonNull String separator) {
        return makeString(collection, "", separator, "");
    }
}
