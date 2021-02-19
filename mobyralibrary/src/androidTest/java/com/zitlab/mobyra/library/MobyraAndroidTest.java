package com.zitlab.mobyra.library;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.zitlab.mobyra.library.exception.MobyraException;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MobyraAndroidTest {

    private CountDownLatch lock = new CountDownLatch(1);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.zitlab.mobyra.library.test", appContext.getPackageName());
    }

    @Test
    public void findById() throws InterruptedException {
        MobyraClient mobyraClient = new MobyraClient("http://api.fluwiz.com", "admin", "ad", "palmyra" );
        final boolean[] isSuccess = {false};
        mobyraClient.findById("879", "mrci_series", new MobyraResponseCallback() {
            @Override
            public <T> void onMobyraResponse(final boolean status, final T response, final MobyraException exception) {
                lock.countDown();
                isSuccess[0] = status;
            }
        });

        lock.await(30000, TimeUnit.MILLISECONDS);
        assertFalse(isSuccess[0]);
    }

//    @Test
//    public void query() throws InterruptedException {
//        MobyraClient mobyraClient = new MobyraClient("http://api.fluwiz.com", "admin", "ad", "palmyra" );
//        final boolean[] isSuccess = {false};
//        mobyraClient.query("{}", String.class, new MobyraResponseCallback() {
//            @Override
//            public <T> void onMobyraResponse(final boolean status, final T response, final MobyraException exception) {
//                lock.countDown();
//                isSuccess[0] = status;
//            }
//        });
//
//        lock.await(30000, TimeUnit.MILLISECONDS);
//        assertTrue(isSuccess[0]);
//    }

    @Test
    public void list() throws InterruptedException {
        MobyraClient mobyraClient = new MobyraClient("http://api.fluwiz.com", "admin", "ad", "palmyra" );
        final boolean[] isSuccess = {false};
        mobyraClient.list("{}", String.class, new MobyraResponseCallback() {
            @Override
            public <T> void onMobyraResponse(final boolean status, final T response, final MobyraException exception) {
                lock.countDown();
                isSuccess[0] = status;
            }
        });

        lock.await(30000, TimeUnit.MILLISECONDS);
        assertTrue(isSuccess[0]);
    }
}