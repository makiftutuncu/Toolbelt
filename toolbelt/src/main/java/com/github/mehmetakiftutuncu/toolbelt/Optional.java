package com.github.mehmetakiftutuncu.toolbelt;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.NoSuchElementException;

/**
 * <p>Represents an optional value that may or may not exist</p>
 * <p>This is an attempt to avoid using <code>null</code>s as they are not safe.</p>
 *
 * @param <T> Type of the value that may be held by this
 *
 * @see Some
 * @see None
 *
 * @author Mehmet Akif Tutuncu
 */
public class Optional<T> {
    /**
     * Represents an existing optional value
     *
     * @param <S> Type of the value
     */
    static final class Some<S> extends Optional<S> {
        private Some(@NonNull S value) {
            this.value = value;
        }

        @NonNull @Override public S get() throws NoSuchElementException {
            return value;
        }

        @NonNull @Override public String toString() {
            return "Some(" + value + ")";
        }
    }

    /** Represents an empty optional value */
    static final class None<N> extends Optional<N> {
        private None() {
            this.value = null;
        }

        @Override public N get() throws NoSuchElementException {
            throw new NoSuchElementException("get() called on an empty optional! Use getOrElse() or check for existence with isDefined() first.");
        }

        @NonNull @Override public String toString() {
            return "None";
        }
    }

    /** The value hold */
    @Nullable protected T value;

    /** Private constructor doing nothing */
    private Optional() {}

    /**
     * Creates an optional with given value
     *
     * @param value Value to hold
     * @param <W>   Type of the value
     *
     * @return An empty optional if the value is null or an existing optional otherwise
     */
    @NonNull public static<W> Optional<W> with(W value) {
        return (value == null) ? new None<W>() : new Some<>(value);
    }

    /**
     * Creates an empty optional
     *
     * @param <E> Type of the optional
     *
     * @return An empty optional
     */
    @NonNull public static<E> Optional<E> empty() {
        return new None<>();
    }

    /**
     * <p>Gets the value if it exists</p>
     * <p><strong>Use with caution and prefer {@link #getOrElse(Object)} if possible.</strong></p>
     *
     * @return The value held by this if it exists
     *
     * @throws NoSuchElementException If the value does not exist
     *
     * @see #getOrElse(Object)
     */
    public T get() throws NoSuchElementException {
        // This will be overridden.
        return null;
    }

    /**
     * Gets the value if it exists or gets given default value if the value does not exist
     *
     * @param defaultValue A default value to return if the value does not exist
     *
     * @return The value or given default value
     */
    @NonNull public T getOrElse(@NonNull T defaultValue) {
        return isDefined() ? value : defaultValue;
    }

    /**
     * Changes this to given alternative if the value does not exist, just returns itself if the value exists
     *
     * @param alternative Alternative optional value
     *
     * @return Alternative if the value does not exist ot itself otherwise
     */
    @NonNull public Optional<T> or(@NonNull Optional<T> alternative) {
        return isDefined() ? this : alternative;
    }

    /**
     * Checks whether the value exists
     *
     * @return true if the value exists
     *
     * @see #isEmpty()
     */
    public boolean isDefined() {
        return value != null && this instanceof Some;
    }

    /**
     * Checks whether the value does not exist
     *
     * @return true if the value does not exist
     *
     * @see #isDefined()
     */
    public boolean isEmpty() {
        return !isDefined();
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Optional<?> optional = (Optional<?>) o;

        return value != null ? value.equals(optional.value) : optional.value == null;

    }

    @Override public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
