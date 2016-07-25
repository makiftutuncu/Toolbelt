package com.github.mehmetakiftutuncu.toolbelt;

import java.util.Locale;

/**
 * Wraps {@link android.util.Log} methods to get dynamic tags using the caller reference
 * and supports message formatting as in {@link String#format}
 *
 * @author Mehmet Akif Tütüncü
 */
public final class Log {
    /**
     * Logs with debug level
     *
     * @param caller  Reference to whoever is calling this method
     * @param message Message to log
     * @param args    Arguments to replace in the message
     */
    public static void debug(Class<?> caller, String message, Object... args) {
        android.util.Log.d(tag(caller), String.format(Locale.ENGLISH, message, args));
    }

    /**
     * Logs with warn level
     *
     * @param caller  Reference to whoever is calling this method
     * @param message Message to log
     * @param args    Arguments to replace in the message
     */
    public static void warn(Class<?> caller, String message, Object... args) {
        android.util.Log.w(tag(caller), String.format(Locale.ENGLISH, message, args));
    }

    /**
     * Logs with error level
     *
     * @param caller  Reference to whoever is calling this method
     * @param message Message to log
     * @param args    Arguments to replace in the message
     */
    public static void error(Class<?> caller, String message, Object... args) {
        android.util.Log.e(tag(caller), String.format(Locale.ENGLISH, message, args));
    }

    /**
     * Logs with error level and logs stack trace for the throwable
     *
     * @param caller    Reference to whoever is calling this method
     * @param throwable Throwable whose stack trace to log
     * @param message   Message to log
     * @param args      Arguments to replace in the message
     */
    public static void error(Class<?> caller, Throwable throwable, String message, Object... args) {
        android.util.Log.e(tag(caller), String.format(Locale.ENGLISH, message, args), throwable);
    }

    /**
     * Generates a log tag using the caller reference's class name
     *
     * @param caller Reference to whoever is calling this method
     *
     * @return Empty string if caller is null or caller reference's class name
     */
    private static String tag(Class<?> caller) {
        return caller == null ? "" : caller.getSimpleName().replaceAll("\\$", "");
    }
}
