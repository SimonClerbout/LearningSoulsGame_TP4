package test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.*;

public class LearningSoulsGameTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final ByteArrayInputStream in = new ByteArrayInputStream("\n\n\n".getBytes());

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        System.setIn(in);
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
        System.setIn(null);
    }

    @Test
    public void testCreateExhaustedHero() {
        try {
            Class<?> c = Class.forName("lsg.LearningSoulsGame");
            Constructor<?> constructor = c.getDeclaredConstructor();

            constructor.setAccessible(true);

            Object o = constructor.newInstance();
            Method m = c.getDeclaredMethod("createExhaustedHero");

            m.setAccessible(true);
            m.invoke(o);

            Assert.assertEquals(outContent.toString(), "[ Hero ]             Gregooninator        LIFE:    1      STAMINA:    0      PROTECTION:  0.00     BUFF:  0.00    (ALIVE)\n");
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called LearningSoulsGame");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called createExhaustedHero");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        }
    }
}