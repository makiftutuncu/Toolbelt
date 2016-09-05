package com.github.mehmetakiftutuncu.toolbelt;

import android.support.annotation.NonNull;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TimerUnitTest {
    @Test public void startTimer() {
        long now = System.currentTimeMillis();

        assertTrue(Timer.start("foo") >= now);
    }

    @Test public void stopTimer() {
        Timer.start("foo");

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(Timer.stop("foo") >= 300);
    }

    @Test public void stopTimerWithoutStarting() {
        assertEquals(Timer.stop("bar"), 0);
    }

    @Test public void time() {
        Timer.ActionResult<Integer> result = Timer.time(new Timer.Action<Integer>() {
            @NonNull @Override public Integer perform() {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return 5;
            }
        });

        assertNotNull(result);
        assertEquals(result.result, Integer.valueOf(5));
        assertTrue(result.duration >= 300);
    }
}
