package test;

import com.sun.istack.internal.Nullable;
import lsg.armor.ArmorItem;
import lsg.weapons.Weapon;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.*;

public class CharacterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testStaticAttributes() {
        try {
            Class<?> c = Class.forName("lsg.characters.Character");
            Field f1 = c.getDeclaredField("LIFE_STAT_STRING");
            Field f2 = c.getDeclaredField("STAM_STAT_STRING");

            Assert.assertEquals(f1.getModifiers(), Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL);
            Assert.assertEquals(f1.getType(), String.class);

            Assert.assertEquals(f2.getModifiers(), Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL);
            Assert.assertEquals(f2.getType(), String.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Character");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have an attribute named LIFE_STAT_STRING or STAM_STAT_STRING");
        }
    }

    @Test
    public void testHeroToString() {
        try {
            Class<?> c1 = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor1 = c1.getDeclaredConstructor();
            Object o1 = constructor1.newInstance();
            Method m1 = c1.getDeclaredMethod("setArmorItem", ArmorItem.class, int.class);
            Method ts = c1.getMethod("toString");
            Method m2 = c1.getMethod("getRings");

            Class<?> c2 = Class.forName("lsg.armor.RingedKnightArmor");
            Constructor<?> constructor2 = c2.getDeclaredConstructor();
            Object o2 = constructor2.newInstance();

            m1.setAccessible(true);

            m1.invoke(o1, o2, 1);
            if (m2 == null) {
                Assert.assertEquals((String) (ts.invoke(o1)), "[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   50      PROTECTION: 14.99    (ALIVE)");
            } else {
                Assert.assertEquals((String) (ts.invoke(o1)), "[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   50      PROTECTION: 14.99     BUFF:  0.00    (ALIVE)");
            }
        } catch (ClassNotFoundException e) {
            Assert.fail("should have classes called lsg.characters.Hero and lsg.armor.RingedKnightArmor");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called setArmorItem in Hero class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void testWeaponToString() {
        try {
            Class<?> c = Class.forName("lsg.weapons.Weapon");
            Constructor<?> constructor = c.getDeclaredConstructor(java.lang.String.class, int.class, int.class, int.class, int.class);
            Object o = constructor.newInstance("Basic Sword", 5, 10, 20, 100);
            Method ts = c.getMethod("toString");

            Assert.assertEquals("Basic Sword (min:5 max:10 stam:20 dur:100)", (String) (ts.invoke(o)));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.weapons.Weapon");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (NoSuchMethodException e) {
            Assert.fail("NoSuchMethodException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

}