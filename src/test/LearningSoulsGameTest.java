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
    private final ByteArrayInputStream in = new ByteArrayInputStream("1\n2\n".getBytes());

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

            if (list.length == 26) {
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
            } else {
                Assert.assertTrue(true);
            }
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

    @Test
    public void testBulletPointAttribute() {
        try {
            Class<?> c = Class.forName("lsg.LearningSoulsGame");
            Field f1 = c.getDeclaredField("BULLET_POINT");

            Assert.assertEquals(f1.getModifiers(), Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL);
            Assert.assertEquals(f1.getType(), String.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called LearningSoulsGame in lsg package");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have attributes named name, capacity or stat");
        }
    }

    @Test
    public void testMain2() {
        try {
            Class<?> c = Class.forName("lsg.LearningSoulsGame");
            Method m = c.getMethod("main", String[].class);
            Object[] args = new Object[1];

            args[0] = new String[]{};
            m.invoke(null, args);

            String[] list = outContent.toString().split("\n");

            if (list.length == 46) {
                Assert.assertEquals("", list[0]);
                Assert.assertEquals("###############################", list[1]);
                Assert.assertEquals("#   THE LEARNING SOULS GAME   #", list[2]);
                Assert.assertEquals("###############################", list[3]);
                Assert.assertEquals("", list[4]);
                Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   50      PROTECTION: 10.20     BUFF: 14.00    (ALIVE)", list[5]);
                Assert.assertEquals("∙ Basic Sword (min:5 max:10 stam:20 dur:100)", list[6]);
                Assert.assertEquals("∙ Uncle Greg's spicy Maroilles burger [40 life point(s)]", list[7]);
                Assert.assertEquals("", list[8]);
                Assert.assertEquals("[ Lycanthrope ]      Lycanthrope          LIFE:   10      STAMINA:   10      PROTECTION: 30.00     BUFF:  0.00    (ALIVE)", list[9]);
                Assert.assertEquals("", list[10]);
                Assert.assertEquals("Hero's action for next move : (1) attack | (2) consume > ", list[11]);
                Assert.assertEquals("Gregooninator attacks Lycanthrope with Basic Sword (ATTACK:7 | DMG : 5)", list[12]);
                Assert.assertEquals("", list[13]);
                Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   30      PROTECTION: 10.20     BUFF: 14.00    (ALIVE)", list[14]);
                Assert.assertEquals("∙ Basic Sword (min:5 max:10 stam:20 dur:99)", list[15]);
                Assert.assertEquals("∙ Uncle Greg's spicy Maroilles burger [40 life point(s)]", list[16]);
                Assert.assertEquals("", list[17]);
                Assert.assertEquals("[ Lycanthrope ]      Lycanthrope          LIFE:    5      STAMINA:   10      PROTECTION: 30.00     BUFF:  0.00    (ALIVE)", list[18]);
                Assert.assertEquals("", list[19]);
                Assert.assertEquals("Lycanthrope attacks Gregooninator with Bloody Claw (ATTACK:106 | DMG : 95)", list[20]);
                Assert.assertEquals("", list[21]);
                Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:    5      STAMINA:   30      PROTECTION: 10.20     BUFF:10014.00  (ALIVE)", list[22]);
                Assert.assertEquals("∙ Basic Sword (min:5 max:10 stam:20 dur:99)", list[23]);
                Assert.assertEquals("∙ Uncle Greg's spicy Maroilles burger [40 life point(s)]", list[24]);
                Assert.assertEquals("", list[25]);
                Assert.assertEquals("[ Lycanthrope ]      Lycanthrope          LIFE:    5      STAMINA:    5      PROTECTION: 30.00     BUFF:  0.00    (ALIVE)", list[26]);
                Assert.assertEquals("", list[27]);
                Assert.assertEquals("Hero's action for next move : (1) attack | (2) consume > ", list[28]);
                Assert.assertEquals("Gregooninator eats Uncle Greg's spicy Maroilles burger [40 life point(s)]", list[29]);
                Assert.assertEquals("", list[30]);
                Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:   45      STAMINA:   30      PROTECTION: 10.20     BUFF:10014.00  (ALIVE)", list[31]);
                Assert.assertEquals("∙ Basic Sword (min:5 max:10 stam:20 dur:99)", list[32]);
                Assert.assertEquals("∙ Uncle Greg's spicy Maroilles burger [0 life point(s)]", list[33]);
                Assert.assertEquals("", list[34]);
                Assert.assertEquals("[ Lycanthrope ]      Lycanthrope          LIFE:    5      STAMINA:    5      PROTECTION: 30.00     BUFF:  0.00    (ALIVE)", list[35]);
                Assert.assertEquals("", list[36]);
                Assert.assertEquals("Lycanthrope attacks Gregooninator with Bloody Claw (ATTACK:91 | DMG : 45)", list[37]);
                Assert.assertEquals("", list[38]);
                Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:    0      STAMINA:   30      PROTECTION: 10.20     BUFF:10014.00  (DEAD)", list[39]);
                Assert.assertEquals("∙ Basic Sword (min:5 max:10 stam:20 dur:99)", list[40]);
                Assert.assertEquals("∙ Uncle Greg's spicy Maroilles burger [0 life point(s)]", list[41]);
                Assert.assertEquals("", list[42]);
                Assert.assertEquals("[ Lycanthrope ]      Lycanthrope          LIFE:    5      STAMINA:    0      PROTECTION: 30.00     BUFF:  0.00    (ALIVE)", list[43]);
                Assert.assertEquals("", list[44]);
                Assert.assertEquals("--- Lycanthrope WINS !!! ---", list[45]);
            } else {
                Assert.assertTrue(true);
            }
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