package test;

import lsg.armor.ArmorItem;
import lsg.consumables.Consumable;
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
    public void existDrinkSubClasses() {
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

    @Test
    public void existFoodSubClasses() {
        try {
            Class<?> c1 = Class.forName("lsg.consumables.food.Americain");
            Class<?> c2 = Class.forName("lsg.consumables.food.Food");
            Class<?> c3 = Class.forName("lsg.consumables.food.Hamburger");
            Class<?> c4 = Class.forName("lsg.consumables.Consumable");

            Assert.assertTrue("Food should be a subclass of Consumable", c4.isAssignableFrom(c2));
            Assert.assertTrue("American should be a subclass of Food", c2.isAssignableFrom(c1));
            Assert.assertTrue("Hamburger should be a subclass of Food", c2.isAssignableFrom(c3));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have subclasses of Consumable");
        }
    }

    @Test
    public void testFoodToString() {
        try {
            Class<?> c0 = Class.forName("lsg.consumables.Consumable");
            Class<?> c1 = Class.forName("lsg.consumables.food.Americain");
            Class<?> c2 = Class.forName("lsg.consumables.food.Hamburger");

            Constructor<?> constructor1 = c1.getDeclaredConstructor();
            Constructor<?> constructor2 = c2.getDeclaredConstructor();

            Object o1 = constructor1.newInstance();
            Object o2 = constructor2.newInstance();

            Method m = c0.getDeclaredMethod("toString");

            Assert.assertEquals("Friterie 2000's Best of the Best [3000 life point(s)]", (String) (m.invoke(o1)));
            Assert.assertEquals("Uncle Greg's spicy Maroilles burger [40 life point(s)]", (String) (m.invoke(o2)));
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

    @Test
    public void existMenuBestOfV1Class() {
        try {
            Class<?> c = Class.forName("lsg.consumables.MenuBestOfV1");
            Field f = c.getDeclaredField("menu");

            Assert.assertEquals(f.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f.getType(), Consumable[].class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a MenuBestOfV1 class");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have attribute named menu");
        }
    }

    @Test
    public void testMenuBestOfV1ToString() {
        try {
            Class<?> c = Class.forName("lsg.consumables.MenuBestOfV1");
            Constructor<?> constructor = c.getDeclaredConstructor();
            Object o = constructor.newInstance();
            Method m = c.getDeclaredMethod("toString");
            String str = (String) (m.invoke(o));
            String[] list = str.toString().split("\n");

            Assert.assertEquals("MenuBestOfV1 :", list[0]);
            Assert.assertEquals("1 : Uncle Greg's spicy Maroilles burger [40 life point(s)]", list[1]);
            Assert.assertEquals("2 : Pomerol 2008 [30 stamina point(s)]", list[2]);
            Assert.assertEquals("3 : Friterie 2000's Best of the Best [3000 life point(s)]", list[3]);
            Assert.assertEquals("4 : Hot Grandmother Coffee [10 stamina point(s)]", list[4]);
            Assert.assertEquals("5 : 12 years old Oban [150 stamina point(s)]", list[5]);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called MenuBestOfV1 in consumables package");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called toString in MenuBestOfV1 class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void testMenuBestOfV1Main() {
        try {
            Class<?> c = Class.forName("lsg.consumables.MenuBestOfV1");

            Method m = c.getMethod("main", String[].class);
            Object[] args = new Object[1];

            args[0] = new String[]{};
            m.invoke(null, args);

            String[] list = outContent.toString().split("\n");

            Assert.assertEquals("MenuBestOfV1 :", list[0]);
            Assert.assertEquals("1 : Uncle Greg's spicy Maroilles burger [40 life point(s)]", list[1]);
            Assert.assertEquals("2 : Pomerol 2008 [30 stamina point(s)]", list[2]);
            Assert.assertEquals("3 : Friterie 2000's Best of the Best [3000 life point(s)]", list[3]);
            Assert.assertEquals("4 : Hot Grandmother Coffee [10 stamina point(s)]", list[4]);
            Assert.assertEquals("5 : 12 years old Oban [150 stamina point(s)]", list[5]);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called MenuBestOfV1 in consumables package");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called toString in MenuBestOfV1 class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void existMenuBestOfV2Class() {
        try {
            Class<?> c = Class.forName("lsg.consumables.MenuBestOfV2");
            Field f = c.getDeclaredField("menu");

            Assert.assertEquals(f.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f.getType(), java.util.HashSet.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a MenuBestOfV2 class");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have attribute named menu");
        }
    }

    @Test
    public void testMenuBestOfV2ToString() {
        try {
            Class<?> c = Class.forName("lsg.consumables.MenuBestOfV2");
            Constructor<?> constructor = c.getDeclaredConstructor();
            Object o = constructor.newInstance();
            Method m = c.getDeclaredMethod("toString");
            String str = (String) (m.invoke(o));
            String[] list = str.toString().split("\n");

            Assert.assertEquals("MenuBestOfV2 :", list[0]);

            String[] values = new String[]{"Uncle Greg's spicy Maroilles burger [40 life point(s)]", "Pomerol 2008 [30 stamina point(s)]", "Friterie 2000's Best of the Best [3000 life point(s)]",
                    "Hot Grandmother Coffee [10 stamina point(s)]", "12 years old Oban [150 stamina point(s)]"};

            Assert.assertTrue(java.util.Arrays.asList(values).indexOf(list[1].substring(4)) != -1);
            Assert.assertTrue(java.util.Arrays.asList(values).indexOf(list[2].substring(4)) != -1);
            Assert.assertTrue(java.util.Arrays.asList(values).indexOf(list[3].substring(4)) != -1);
            Assert.assertTrue(java.util.Arrays.asList(values).indexOf(list[4].substring(4)) != -1);
            Assert.assertTrue(java.util.Arrays.asList(values).indexOf(list[5].substring(4)) != -1);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called MenuBestOfV2 in consumables package");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called toString in MenuBestOfV2 class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void testMenuBestOfV2Main() {
        try {
            Class<?> c = Class.forName("lsg.consumables.MenuBestOfV2");

            Method m = c.getMethod("main", String[].class);
            Object[] args = new Object[1];

            args[0] = new String[]{};
            m.invoke(null, args);

            String[] list = outContent.toString().split("\n");

            Assert.assertEquals("MenuBestOfV2 :", list[0]);

            String[] values = new String[]{"Uncle Greg's spicy Maroilles burger [40 life point(s)]", "Pomerol 2008 [30 stamina point(s)]", "Friterie 2000's Best of the Best [3000 life point(s)]",
                    "Hot Grandmother Coffee [10 stamina point(s)]", "12 years old Oban [150 stamina point(s)]"};

            Assert.assertTrue(java.util.Arrays.asList(values).indexOf(list[1].substring(4)) != -1);
            Assert.assertTrue(java.util.Arrays.asList(values).indexOf(list[2].substring(4)) != -1);
            Assert.assertTrue(java.util.Arrays.asList(values).indexOf(list[3].substring(4)) != -1);
            Assert.assertTrue(java.util.Arrays.asList(values).indexOf(list[4].substring(4)) != -1);
            Assert.assertTrue(java.util.Arrays.asList(values).indexOf(list[5].substring(4)) != -1);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called MenuBestOfV2 in consumables package");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called toString in MenuBestOfV2 class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void existMenuBestOfV3Class() {
        try {
            Class<?> c = Class.forName("lsg.consumables.MenuBestOfV3");

            Assert.assertTrue("Food should be a subclass of Consumable", java.util.HashSet.class.isAssignableFrom(c));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a MenuBestOfV3 class");
        }
    }

    @Test
    public void testMenuBestOfV3ToString() {
        try {
            Class<?> c = Class.forName("lsg.consumables.MenuBestOfV3");
            Constructor<?> constructor = c.getDeclaredConstructor();
            Object o = constructor.newInstance();
            Method m = c.getDeclaredMethod("toString");
            String str = (String) (m.invoke(o));
            String[] list = str.toString().split("\n");

            Assert.assertEquals("MenuBestOfV3 :", list[0]);

            String[] values = new String[]{"Uncle Greg's spicy Maroilles burger [40 life point(s)]", "Pomerol 2008 [30 stamina point(s)]", "Friterie 2000's Best of the Best [3000 life point(s)]",
                    "Hot Grandmother Coffee [10 stamina point(s)]", "12 years old Oban [150 stamina point(s)]"};

            Assert.assertTrue(java.util.Arrays.asList(values).indexOf(list[1].substring(4)) != -1);
            Assert.assertTrue(java.util.Arrays.asList(values).indexOf(list[2].substring(4)) != -1);
            Assert.assertTrue(java.util.Arrays.asList(values).indexOf(list[3].substring(4)) != -1);
            Assert.assertTrue(java.util.Arrays.asList(values).indexOf(list[4].substring(4)) != -1);
            Assert.assertTrue(java.util.Arrays.asList(values).indexOf(list[5].substring(4)) != -1);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called MenuBestOfV3 in consumables package");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called toString in MenuBestOfV3 class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void testMenuBestOfV3Main() {
        try {
            Class<?> c = Class.forName("lsg.consumables.MenuBestOfV3");

            Method m = c.getMethod("main", String[].class);
            Object[] args = new Object[1];

            args[0] = new String[]{};
            m.invoke(null, args);

            String[] list = outContent.toString().split("\n");

            Assert.assertEquals("MenuBestOfV3 :", list[0]);

            String[] values = new String[]{"Uncle Greg's spicy Maroilles burger [40 life point(s)]", "Pomerol 2008 [30 stamina point(s)]", "Friterie 2000's Best of the Best [3000 life point(s)]",
                    "Hot Grandmother Coffee [10 stamina point(s)]", "12 years old Oban [150 stamina point(s)]"};

            Assert.assertTrue(java.util.Arrays.asList(values).indexOf(list[1].substring(4)) != -1);
            Assert.assertTrue(java.util.Arrays.asList(values).indexOf(list[2].substring(4)) != -1);
            Assert.assertTrue(java.util.Arrays.asList(values).indexOf(list[3].substring(4)) != -1);
            Assert.assertTrue(java.util.Arrays.asList(values).indexOf(list[4].substring(4)) != -1);
            Assert.assertTrue(java.util.Arrays.asList(values).indexOf(list[5].substring(4)) != -1);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called MenuBestOfV3 in consumables package");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called toString in MenuBestOfV3 class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void existMenuBestOfV4Class() {
        try {
            Class<?> c = Class.forName("lsg.consumables.MenuBestOfV4");

            Assert.assertTrue("Food should be a subclass of Consumable", java.util.LinkedHashSet.class.isAssignableFrom(c));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a MenuBestOfV4 class");
        }
    }

    @Test
    public void testMenuBestOfV4ToString() {
        try {
            Class<?> c = Class.forName("lsg.consumables.MenuBestOfV4");
            Constructor<?> constructor = c.getDeclaredConstructor();
            Object o = constructor.newInstance();
            Method m = c.getDeclaredMethod("toString");
            String str = (String) (m.invoke(o));
            String[] list = str.toString().split("\n");

            Assert.assertEquals("MenuBestOfV4 :", list[0]);

            Assert.assertEquals("1 : Uncle Greg's spicy Maroilles burger [40 life point(s)]", list[1]);
            Assert.assertEquals("2 : Pomerol 2008 [30 stamina point(s)]", list[2]);
            Assert.assertEquals("3 : Friterie 2000's Best of the Best [3000 life point(s)]", list[3]);
            Assert.assertEquals("4 : Hot Grandmother Coffee [10 stamina point(s)]", list[4]);
            Assert.assertEquals("5 : 12 years old Oban [150 stamina point(s)]", list[5]);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called MenuBestOfV4 in consumables package");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called toString in MenuBestOfV4 class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void testMenuBestOfV4Main() {
        try {
            Class<?> c = Class.forName("lsg.consumables.MenuBestOfV4");

            Method m = c.getMethod("main", String[].class);
            Object[] args = new Object[1];

            args[0] = new String[]{};
            m.invoke(null, args);

            String[] list = outContent.toString().split("\n");

            if (list.length == 6) {
                Assert.assertEquals("1 : Uncle Greg's spicy Maroilles burger [40 life point(s)]", list[1]);
                Assert.assertEquals("2 : Pomerol 2008 [30 stamina point(s)]", list[2]);
                Assert.assertEquals("3 : Friterie 2000's Best of the Best [3000 life point(s)]", list[3]);
                Assert.assertEquals("4 : Hot Grandmother Coffee [10 stamina point(s)]", list[4]);
                Assert.assertEquals("5 : 12 years old Oban [150 stamina point(s)]", list[5]);
            } else {
                Assert.assertEquals("6 : Repair Kit [10 durability point(s)]", list[6]);
            }
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called MenuBestOfV4 in consumables package");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called toString in MenuBestOfV4 class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void existRepairKitClass() {
        try {
            Class<?> c1 = Class.forName("lsg.consumables.repair.RepairKit");
            Class<?> c2 = Class.forName("lsg.consumables.Consumable");
            Constructor<?> constructor = c1.getDeclaredConstructor();
            Object o = constructor.newInstance();
            Method m = c2.getDeclaredMethod("toString");

            Assert.assertTrue("Repair should be a subclass of Consumable", c2.isAssignableFrom(c1));
            Assert.assertEquals("Repair Kit [10 durability point(s)]", (String) (m.invoke(o)));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have classes named Consumable and RepairKit");
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
    public void existRepairKitUse() {
        try {
            Class<?> c = Class.forName("lsg.consumables.repair.RepairKit");
            Constructor<?> constructor = c.getDeclaredConstructor();
            Object o = constructor.newInstance();
            Method m1 = c.getDeclaredMethod("use");
            Method m2 = c.getMethod("getCapacity");
            int capacity = (int) (m2.invoke(o));

            Assert.assertEquals(m1.getModifiers(), Modifier.PUBLIC);

            Assert.assertEquals(1, (int) (m1.invoke(o)));
            Assert.assertEquals(capacity - 1, (int) (m2.invoke(o)));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have classe named RepairKit");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called use in RepairKit class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void existWeaponRepairWith() {
        try {
            Class<?> c1 = Class.forName("lsg.consumables.repair.RepairKit");
            Constructor<?> constructor1 = c1.getDeclaredConstructor();
            Object o1 = constructor1.newInstance();
            Class<?> c2 = Class.forName("lsg.weapons.Weapon");
            Constructor<?> constructor2 = c2.getDeclaredConstructor(String.class, int.class, int.class, int.class, int.class);
            Object o2 = constructor2.newInstance("Grosse Arme", 0, 0, 1000, 100);
            Method m1 = c2.getDeclaredMethod("repairWith", c1);
            Method m2 = c2.getDeclaredMethod("getDurability");
            int durability;

            Assert.assertEquals(m1.getModifiers(), Modifier.PUBLIC);

            for (int i = 0; i < 10; ++i) {
                durability = (int) (m2.invoke(o2));
                m1.invoke(o2, o1);
                Assert.assertEquals(durability + 1, (int) (m2.invoke(o2)));
            }
            durability = (int) (m2.invoke(o2));
            m1.invoke(o2, o1);
            Assert.assertEquals(durability, (int) (m2.invoke(o2)));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have classes named RepairKit and Weapon");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called use in RepairKit class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

}