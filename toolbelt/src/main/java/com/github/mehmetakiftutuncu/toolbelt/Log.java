package com.github.mehmetakiftutuncu.toolbelt;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Locale;

/**
 * Wraps {@link android.util.Log} methods to get dynamic tags using the caller reference
 * and supports message formatting as done in {@link String#format}.
 * It also simplifies log levels to just <code>DEBUG</code>, <code>WARN</code> and <code>ERROR</code>.
 *
 * @author Mehmet Akif Tutuncu
 */
public final class Log {
    /**
     * Logs with debug level
     *
     * @param caller  Reference to whoever is calling this method
     * @param message Message to log
     * @param args    Arguments to replace in the message
     */
    public static void debug(@Nullable Class<?> caller, String message, Object... args) {
        android.util.Log.d(tag(caller), String.format(Locale.ENGLISH, message, args));
    }

    /**
     * Logs with debug level
     *
     * @param tag     Tag for the log
     * @param message Message to log
     * @param args    Arguments to replace in the message
     */
    public static void debug(@NonNull String tag, String message, Object... args) {
        android.util.Log.d(tag, String.format(Locale.ENGLISH, message, args));
    }

    /**
     * Logs with warn level
     *
     * @param caller  Reference to whoever is calling this method
     * @param message Message to log
     * @param args    Arguments to replace in the message
     */
    public static void warn(@Nullable Class<?> caller, String message, Object... args) {
        android.util.Log.w(tag(caller), String.format(Locale.ENGLISH, message, args));
    }

    /**
     * Logs with warn level
     *
     * @param tag     Tag for the log
     * @param message Message to log
     * @param args    Arguments to replace in the message
     */
    public static void warn(@NonNull String tag, String message, Object... args) {
        android.util.Log.w(tag, String.format(Locale.ENGLISH, message, args));
    }

    /**
     * Logs with error level
     *
     * @param caller  Reference to whoever is calling this method
     * @param message Message to log
     * @param args    Arguments to replace in the message
     */
    public static void error(@Nullable Class<?> caller, String message, Object... args) {
        android.util.Log.e(tag(caller), String.format(Locale.ENGLISH, message, args));
    }

    /**
     * Logs with error level
     *
     * @param tag     Tag for the log
     * @param message Message to log
     * @param args    Arguments to replace in the message
     */
    public static void error(@NonNull String tag, String message, Object... args) {
        android.util.Log.e(tag, String.format(Locale.ENGLISH, message, args));
    }

    /**
     * Logs with error level and logs stack trace for the throwable
     *
     * @param caller    Reference to whoever is calling this method
     * @param throwable Throwable whose stack trace to log
     * @param message   Message to log
     * @param args      Arguments to replace in the message
     */
    public static void error(@Nullable Class<?> caller, Throwable throwable, String message, Object... args) {
        android.util.Log.e(tag(caller), String.format(Locale.ENGLISH, message, args), throwable);
    }

    /**
     * Logs with error level and logs stack trace for the throwable
     *
     * @param tag       Tag for the log
     * @param throwable Throwable whose stack trace to log
     * @param message   Message to log
     * @param args      Arguments to replace in the message
     */
    public static void error(@NonNull String tag, Throwable throwable, String message, Object... args) {
        android.util.Log.e(tag, String.format(Locale.ENGLISH, message, args), throwable);
    }

    /**
     * Generates a log tag using the caller reference's class name
     *
     * @param caller Reference to whoever is calling this method
     *
     * @return Empty string if caller is null or caller reference's class name
     */
    @NonNull private static String tag(@Nullable Class<?> caller) {
        return caller == null ? "" : caller.getSimpleName().replaceAll("\\$", "");
    }
}
