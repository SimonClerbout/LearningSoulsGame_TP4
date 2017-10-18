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

    @Test
    public void testNewStaticAttributes() {
        try {
            Class<?> c = Class.forName("lsg.characters.Character");
            Field f1 = c.getDeclaredField("PROTECTION_STAT_STRING");
            Field f2 = c.getDeclaredField("BUFF_STAT_STRING");

            Assert.assertEquals(f1.getModifiers(), Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL);
            Assert.assertEquals(f1.getType(), String.class);

            Assert.assertEquals(f2.getModifiers(), Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL);
            Assert.assertEquals(f2.getType(), String.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Character");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have an attribute named PROTECTION_STAT_STRING or BUFF_STAT_STRING");
        }
    }

    @Test
    public void testCharacterDrink() {
        try {
            Class<?> c = Class.forName("lsg.characters.Hero");
            Class<?> c2 = Class.forName("lsg.characters.Character");
            Class<?> c3 = Class.forName("lsg.consumables.drinks.Drink");
            Class<?> c4 = Class.forName("lsg.consumables.drinks.Whisky");
            Constructor<?> constructor1 = c.getDeclaredConstructor();
            Constructor<?> constructor2 = c4.getDeclaredConstructor();
            Object o1 = constructor1.newInstance();
            Object o2 = constructor2.newInstance();
            Method m = c2.getDeclaredMethod("drink", c3);
            Method m2 = c2.getDeclaredMethod("getStamina");

            m.setAccessible(true);
            m.invoke(o1, o2);
            Assert.assertEquals(outContent.toString(), "Gregooninator drinks 12 years old Oban [150 stamina point(s)]\n");
            Assert.assertEquals(m2.invoke(o1), 50);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have classes called lsg.characters.Hero, lsg.characters.Character, lsg.characters.Character and lsg.consumables.drinks.Whisky");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called drink in Character class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void testCharacterEat() {
        try {
            Class<?> c = Class.forName("lsg.characters.Hero");
            Class<?> c2 = Class.forName("lsg.characters.Character");
            Class<?> c3 = Class.forName("lsg.consumables.food.Food");
            Class<?> c4 = Class.forName("lsg.consumables.food.Hamburger");
            Constructor<?> constructor1 = c.getDeclaredConstructor();
            Constructor<?> constructor2 = c4.getDeclaredConstructor();
            Object o1 = constructor1.newInstance();
            Object o2 = constructor2.newInstance();
            Method m = c2.getDeclaredMethod("eat", c3);
            Method m2 = c2.getDeclaredMethod("getStamina");

            m.setAccessible(true);
            m.invoke(o1, o2);
            Assert.assertEquals(outContent.toString(), "Gregooninator eats Uncle Greg's spicy Maroilles burger [40 life point(s)]\n");
            Assert.assertEquals(m2.invoke(o1), 50);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have classes called lsg.characters.Hero, lsg.characters.Character, lsg.consumables.food.Food and lsg.consumables.food.Hamburger");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called drink in Character class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

}