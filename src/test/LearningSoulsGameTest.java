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

    @Test
    public void testATable() {
        try {
            Class<?> c = Class.forName("lsg.LearningSoulsGame");
            Constructor<?> constructor = c.getDeclaredConstructor();

            constructor.setAccessible(true);

            Object o = constructor.newInstance();
            Method m1 = c.getDeclaredMethod("createExhaustedHero");

            m1.setAccessible(true);
            m1.invoke(o);

            Method m2 = c.getDeclaredMethod("aTable");

            m2.setAccessible(true);
            m2.invoke(o);

            String[] list = outContent.toString().split("\n");

            if (list.length == 22) {
                Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:    1      STAMINA:    0      PROTECTION:  0.00     BUFF:  0.00    (ALIVE)", list[0]);
                Assert.assertEquals("", list[1]);
                Assert.assertEquals("Gregooninator eats Uncle Greg's spicy Maroilles burger [40 life point(s)]", list[2]);
                Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:   41      STAMINA:    0      PROTECTION:  0.00     BUFF:  0.00    (ALIVE)", list[3]);
                Assert.assertEquals("Apres utilisation : Uncle Greg's spicy Maroilles burger [0 life point(s)]", list[4]);
                Assert.assertEquals("", list[5]);
                Assert.assertEquals("Gregooninator drinks Pomerol 2008 [30 stamina point(s)]", list[6]);
                Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:   41      STAMINA:   30      PROTECTION:  0.00     BUFF:  0.00    (ALIVE)", list[7]);
                Assert.assertEquals("Apres utilisation : Pomerol 2008 [0 stamina point(s)]", list[8]);
                Assert.assertEquals("", list[9]);
                Assert.assertEquals("Gregooninator eats Friterie 2000's Best of the Best [3000 life point(s)]", list[10]);
                Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   30      PROTECTION:  0.00     BUFF:  0.00    (ALIVE)", list[11]);
                Assert.assertEquals("Apres utilisation : Friterie 2000's Best of the Best [0 life point(s)]", list[12]);
                Assert.assertEquals("", list[13]);
                Assert.assertEquals("Gregooninator drinks Hot Grandmother Coffee [10 stamina point(s)]", list[14]);
                Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   40      PROTECTION:  0.00     BUFF:  0.00    (ALIVE)", list[15]);
                Assert.assertEquals("Apres utilisation : Hot Grandmother Coffee [0 stamina point(s)]", list[16]);
                Assert.assertEquals("", list[17]);
                Assert.assertEquals("Gregooninator drinks 12 years old Oban [150 stamina point(s)]", list[18]);
                Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   50      PROTECTION:  0.00     BUFF:  0.00    (ALIVE)", list[19]);
                Assert.assertEquals("Apres utilisation : 12 years old Oban [0 stamina point(s)]", list[20]);
                Assert.assertEquals("Grosse Arme (min:0 max:0 stam:1000 dur:99)", list[21]);
            }
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called LearningSoulsGame");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have methods called createExhaustedHero and aTable");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        }
    }

    @Test
    public void testMain() {
        try {
            Class<?> c = Class.forName("lsg.LearningSoulsGame");
            Method m = c.getMethod("main", String[].class);
            Object[] args = new Object[1];

            args[0] = new String[]{};
            m.invoke(null, args);

            String[] list = outContent.toString().split("\n");

            Assert.assertEquals(list.length, 26);

            Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:    1      STAMINA:    0      PROTECTION:  0.00     BUFF:  0.00    (ALIVE)", list[0]);
            Assert.assertEquals("", list[1]);
            Assert.assertEquals("Gregooninator eats Uncle Greg's spicy Maroilles burger [40 life point(s)]", list[2]);
            Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:   41      STAMINA:    0      PROTECTION:  0.00     BUFF:  0.00    (ALIVE)", list[3]);
            Assert.assertEquals("Apres utilisation : Uncle Greg's spicy Maroilles burger [0 life point(s)]", list[4]);
            Assert.assertEquals("", list[5]);
            Assert.assertEquals("Gregooninator drinks Pomerol 2008 [30 stamina point(s)]", list[6]);
            Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:   41      STAMINA:   30      PROTECTION:  0.00     BUFF:  0.00    (ALIVE)", list[7]);
            Assert.assertEquals("Apres utilisation : Pomerol 2008 [0 stamina point(s)]", list[8]);
            Assert.assertEquals("", list[9]);
            Assert.assertEquals("Gregooninator eats Friterie 2000's Best of the Best [3000 life point(s)]", list[10]);
            Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   30      PROTECTION:  0.00     BUFF:  0.00    (ALIVE)", list[11]);
            Assert.assertEquals("Apres utilisation : Friterie 2000's Best of the Best [0 life point(s)]", list[12]);
            Assert.assertEquals("", list[13]);
            Assert.assertEquals("Gregooninator drinks Hot Grandmother Coffee [10 stamina point(s)]", list[14]);
            Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   40      PROTECTION:  0.00     BUFF:  0.00    (ALIVE)", list[15]);
            Assert.assertEquals("Apres utilisation : Hot Grandmother Coffee [0 stamina point(s)]", list[16]);
            Assert.assertEquals("", list[17]);
            Assert.assertEquals("Gregooninator drinks 12 years old Oban [150 stamina point(s)]", list[18]);
            Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   50      PROTECTION:  0.00     BUFF:  0.00    (ALIVE)", list[19]);
            Assert.assertEquals("Apres utilisation : 12 years old Oban [0 stamina point(s)]", list[20]);
            Assert.assertEquals("", list[21]);
            Assert.assertEquals("Gregooninator repairs Grosse Arme (min:0 max:0 stam:1000 dur:99) with Repair Kit [10 durability point(s)]", list[22]);
            Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   50      PROTECTION:  0.00     BUFF:  0.00    (ALIVE)", list[23]);
            Assert.assertEquals("Apres utilisation : Repair Kit [9 durability point(s)]", list[24]);
            Assert.assertEquals("Grosse Arme (min:0 max:0 stam:1000 dur:100)", list[25]);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called LearningSoulsGame");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have static method called main");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }
}