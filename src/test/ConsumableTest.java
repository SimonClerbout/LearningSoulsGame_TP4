package test;

import lsg.armor.ArmorItem;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.*;

public class ConsumableTest {
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
    public void testAttributes() {
        try {
            Class<?> c = Class.forName("lsg.consumables.Consumable");
            Field f1 = c.getDeclaredField("name");
            Field f2 = c.getDeclaredField("capacity");
            Field f3 = c.getDeclaredField("stat");

            Assert.assertEquals(f1.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f1.getType(), String.class);

            Assert.assertEquals(f2.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f2.getType(), int.class);

            Assert.assertEquals(f3.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f3.getType(), String.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Consumable in consumables package");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have attributes named name, capacity or stat");
        }
    }

    @Test
    public void testConsumableGetters() {
        try {
            Class<?> c = Class.forName("lsg.consumables.Consumable");
            Method m1 = c.getDeclaredMethod("getName");
            Method m2 = c.getDeclaredMethod("getCapacity");
            Method m3 = c.getDeclaredMethod("getStat");

            Assert.assertEquals(m1.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals(m2.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals(m3.getModifiers(), Modifier.PUBLIC);
            Assert.assertTrue("wrong return type (String) of getName", m1.getReturnType() == String.class);
            Assert.assertTrue("wrong return type (String) of getCapacity", m2.getReturnType() == int.class);
            Assert.assertTrue("wrong return type (String) of getStat", m3.getReturnType() == String.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Consumable in consumables package");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have getters for consumable attributes");
        }
    }

    @Test
    public void testConsumableConstructor() {
        try {
            Class<?> c = Class.forName("lsg.consumables.Consumable");
            Constructor<?> constructor = c.getDeclaredConstructor(java.lang.String.class, int.class, java.lang.String.class);
            Object o = constructor.newInstance("Hot Coffee", 10, "stamina");

            Assert.assertTrue(true);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Consumable in consumables package");
        } catch (NoSuchMethodException e) {
            Assert.fail("NoSuchMethodException");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void testConsumableToString() {
        try {
            Class<?> c = Class.forName("lsg.consumables.Consumable");
            Constructor<?> constructor = c.getDeclaredConstructor(java.lang.String.class, int.class, java.lang.String.class);
            Object o = constructor.newInstance("Hot Coffee", 10, "stamina");
            Method ts = c.getMethod("toString");

            Assert.assertEquals(ts.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals("Hot Coffee [10 stamina point(s)]", (String) (ts.invoke(o)));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Consumable in consumables package");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called toString in Consumable class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void testConsumableSetter() {
        try {
            Class<?> c = Class.forName("lsg.consumables.Consumable");
            Method m = c.getDeclaredMethod("setCapacity", int.class);

            Assert.assertEquals(m.getModifiers(), Modifier.PROTECTED);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Consumable in consumables package");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have setters for capacity of consumable");
        }
    }

    @Test
    public void testConsumableUse() {
        try {
            Class<?> c = Class.forName("lsg.consumables.Consumable");
            Constructor<?> constructor = c.getDeclaredConstructor(java.lang.String.class, int.class, java.lang.String.class);
            Object o = constructor.newInstance("Hot Coffee", 10, "stamina");
            Method m1 = c.getMethod("use");
            Method m2 = c.getMethod("getCapacity");

            Assert.assertEquals(m1.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals(10, (int) (m2.invoke(o)));
            Assert.assertEquals(10, (int) (m1.invoke(o)));
            Assert.assertEquals(0, (int) (m2.invoke(o)));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Consumable in consumables package");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have methods called use and getCapacity in Consumable class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void existSubClasses() {
        try {
            Class<?> c1 = Class.forName("lsg.consumables.drinks.Coffee");
            Class<?> c2 = Class.forName("lsg.consumables.drinks.Drink");
            Class<?> c3 = Class.forName("lsg.consumables.drinks.Whisky");
            Class<?> c4 = Class.forName("lsg.consumables.drinks.Wine");
            Class<?> c5 = Class.forName("lsg.consumables.Consumable");

            Assert.assertTrue("Drink should be a subclass of Consumable", c5.isAssignableFrom(c2));
            Assert.assertTrue("Coffee should be a subclass of Drink", c2.isAssignableFrom(c1));
            Assert.assertTrue("Whisky should be a subclass of Drink", c2.isAssignableFrom(c3));
            Assert.assertTrue("Wine should be a subclass of Drink", c2.isAssignableFrom(c4));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have subclasses of Consumable");
        }
    }

    @Test
    public void testDrinksToString() {
        try {
            Class<?> c0 = Class.forName("lsg.consumables.Consumable");
            Class<?> c1 = Class.forName("lsg.consumables.drinks.Coffee");
            Class<?> c2 = Class.forName("lsg.consumables.drinks.Whisky");
            Class<?> c3 = Class.forName("lsg.consumables.drinks.Wine");

            Constructor<?> constructor1 = c1.getDeclaredConstructor();
            Constructor<?> constructor2 = c2.getDeclaredConstructor();
            Constructor<?> constructor3 = c3.getDeclaredConstructor();

            Object o1 = constructor1.newInstance();
            Object o2 = constructor2.newInstance();
            Object o3 = constructor3.newInstance();

            Method m = c0.getDeclaredMethod("toString");

            Assert.assertEquals("Hot Grandmother Coffee [10 stamina point(s)]", (String) (m.invoke(o1)));
            Assert.assertEquals("12 years old Oban [150 stamina point(s)]", (String) (m.invoke(o2)));
            Assert.assertEquals("Pomerol 2008 [30 stamina point(s)]", (String) (m.invoke(o3)));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Consumable and subclasses in consumables package");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called toString in Consumable class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

}