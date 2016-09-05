package com.github.mehmetakiftutuncu.toolbelt;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A simple utility class to time stuff
 */
public final class Timer {
    /**
     * An simple, atomic map of timer keys and start times so they can be timed
     */
    private static final AtomicReference<Map<String, Long>> map = new AtomicReference<Map<String, Long>>(new HashMap<String, Long>());

    /**
     * Defines a simple action to perform that is going to be timed
     *
     * @param <R> Type of the result of the action
     *
     * @see ActionResult
     */
    public interface Action<R> {
        /**
         * Performs an action and returns it's result
         *
         * @return Result of the action performed
         */
        @NonNull R perform();
    }

    /**
     * Defines the result of a timed action
     *
     * @param <R> Type of the result of the action
     *
     * @see Action
     */
    public static final class ActionResult<R> {
        /** Result of the timed action */
        @NonNull public final R result;

        /** Duration that the action took in milliseconds */
        public final long duration;

        /**
         * Constructor to create an action result
         *
         * @param result      Result of the action
         * @param duration    Duration it took for the action to complete
         */
        public ActionResult(@NonNull R result, long duration) {
            this.result = result;
            this.duration = duration;
        }
    }

    /**
     * Starts a timer for specified key, overrides any previous timers with the same key
     *
     * @param key Key for the action to time
     *
     * @return Timestamp of when the action started
     *
     * @see System#currentTimeMillis()
     */
    public static long start(@NonNull String key) {
        long now = System.currentTimeMillis();
        Map<String, Long> currentMap = map.get();
        Optional<Long> maybePreviousInstant = Optional.with(currentMap.get(key));

        if (maybePreviousInstant.isDefined()) {
            Log.warn(Timer.class, "There was already a timer started for key '%s'. It will be overridden!", key);
        }

        currentMap.put(key, now);
        map.getAndSet(currentMap);

        return now;
    }

    /**
     * Stops the timer for specified key
     *
     * @param key Key for the action for which a timer has been started
     *
     * @return Duration it took for the action to complete in milliseconds
     *         or 0 if there was no timer started for specified key
     */
    public static long stop(@NonNull String key) {
        long now = System.currentTimeMillis();
        Map<String, Long> currentMap = map.get();
        Optional<Long> maybePreviousInstant = Optional.with(currentMap.get(key));

        if (maybePreviousInstant.isEmpty()) {
            Log.warn(Timer.class, "There is no timer started for key '%s'. It might have already been stopped!", key);

            return 0;
        }

        long duration = now - maybePreviousInstant.get();
        currentMap.remove(key);
        map.set(currentMap);

        return duration;
    }

    /**
     * Performs given action and times it
     *
     * @param action    Action to perform
     * @param <R>       Type of the result of the action
     *
     * @return An ActionResult containing both the result of the action
     *         and the duration it took to complete in milliseconds
     */
    @NonNull public static<R> ActionResult<R> time(@NonNull Action<R> action) {
        String key = UUID.randomUUID().toString();

        start(key);
        R result = action.perform();
        long duration = stop(key);

        return new ActionResult<>(result, duration);
    }
}
