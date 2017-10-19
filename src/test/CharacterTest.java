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

            Assert.assertEquals(m.getModifiers(), Modifier.PRIVATE);

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

            Assert.assertEquals(m.getModifiers(), Modifier.PRIVATE);

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

    @Test
    public void testCharacterUse() {
        try {
            Class<?> c = Class.forName("lsg.characters.Hero");
            Class<?> c2 = Class.forName("lsg.characters.Character");
            Class<?> c3 = Class.forName("lsg.consumables.Consumable");
            Class<?> c4 = Class.forName("lsg.consumables.food.Hamburger");
            Class<?> c5 = Class.forName("lsg.consumables.drinks.Whisky");

            Constructor<?> constructor1 = c.getDeclaredConstructor();
            Constructor<?> constructor2 = c4.getDeclaredConstructor();
            Constructor<?> constructor3 = c5.getDeclaredConstructor();
            Object o1 = constructor1.newInstance();
            Object o2 = constructor2.newInstance();
            Object o3 = constructor3.newInstance();
            Method m = c2.getDeclaredMethod("use", c3);

            Assert.assertEquals(m.getModifiers(), Modifier.PUBLIC);

            m.invoke(o1, o2);
            m.invoke(o1, o3);
            Assert.assertEquals(outContent.toString(), "Gregooninator eats Uncle Greg's spicy Maroilles burger [40 life point(s)]\nGregooninator drinks 12 years old Oban [150 stamina point(s)]\n");
        } catch (ClassNotFoundException e) {
            Assert.fail("should have classes called lsg.characters.Hero, lsg.characters.Character, lsg.consumables.Consumable, lsg.consumables.food.Hamburger and lsg.consumables.drinks.Whisky");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called use in Character class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void existWeaponRepairWithInCharacter() {
        try {
            Class<?> c1 = Class.forName("lsg.consumables.repair.RepairKit");
            Constructor<?> constructor1 = c1.getDeclaredConstructor();
            Object o1 = constructor1.newInstance();
            Class<?> c2 = Class.forName("lsg.weapons.Weapon");
            Constructor<?> constructor2 = c2.getDeclaredConstructor(String.class, int.class, int.class, int.class, int.class);
            Object o2 = constructor2.newInstance("Grosse Arme", 0, 0, 1000, 100);
            Class<?> c3 = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor3 = c3.getDeclaredConstructor();
            Object o3 = constructor3.newInstance();
            Method m1 = c3.getMethod("setWeapon", c2);
            Class<?> c4 = Class.forName("lsg.characters.Character");
            Method m2 = c4.getDeclaredMethod("repairWeaponWith", c1);
            Method m3 = c4.getDeclaredMethod("attack");
            Method m4 = c2.getDeclaredMethod("getDurability");

            Assert.assertEquals(m2.getModifiers(), Modifier.PRIVATE);

            m2.setAccessible(true);

            m1.invoke(o3, o2);
            m3.invoke(o3); // attack
            m2.invoke(o3, o1);
            Assert.assertEquals(outContent.toString(), "Gregooninator repairs Grosse Arme (min:0 max:0 stam:1000 dur:99) with Repair Kit [10 durability point(s)]\n");
            Assert.assertEquals(100, (int)(m4.invoke(o2)));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have classes named RepairKit, Hero and Weapon");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have methods called setWeapon and repairWeaponWith in Character class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void existWeaponRepairWithInUseMethod() {
        try {
            Class<?> c1 = Class.forName("lsg.consumables.repair.RepairKit");
            Constructor<?> constructor1 = c1.getDeclaredConstructor();
            Object o1 = constructor1.newInstance();
            Class<?> c2 = Class.forName("lsg.weapons.Weapon");
            Constructor<?> constructor2 = c2.getDeclaredConstructor(String.class, int.class, int.class, int.class, int.class);
            Object o2 = constructor2.newInstance("Grosse Arme", 0, 0, 1000, 100);
            Class<?> c3 = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor3 = c3.getDeclaredConstructor();
            Object o3 = constructor3.newInstance();
            Method m1 = c3.getMethod("setWeapon", c2);
            Class<?> c4 = Class.forName("lsg.characters.Character");
            Class<?> c5 = Class.forName("lsg.consumables.Consumable");
            Method m2 = c4.getDeclaredMethod("use", c5);
            Method m3 = c4.getDeclaredMethod("attack");
            Method m4 = c2.getDeclaredMethod("getDurability");

            m1.invoke(o3, o2);
            m3.invoke(o3); // attack
            m2.invoke(o3, o1);
            Assert.assertEquals(outContent.toString(), "Gregooninator repairs Grosse Arme (min:0 max:0 stam:1000 dur:99) with Repair Kit [10 durability point(s)]\n");
            Assert.assertEquals(100, (int)(m4.invoke(o2)));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have classes named RepairKit, Hero and Weapon");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have methods called setWeapon and use in Character class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

    @Test
    public void testConsumableAttribute() {
        try {
            Class<?> c1 = Class.forName("lsg.characters.Character");
            Class<?> c2 = Class.forName("lsg.consumables.Consumable");
            Field f = c1.getDeclaredField("consumable");

            Assert.assertEquals(f.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f.getType(), c2);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have classes called Character and Consumable");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have an attribute named consumable");
        }
    }

    @Test
    public void testConsumableGetterAndSetter() {
        try {
            Class<?> c1 = Class.forName("lsg.characters.Character");
            Class<?> c2 = Class.forName("lsg.consumables.Consumable");
            Method m1 = c1.getDeclaredMethod("getConsumable");
            Method m2 = c1.getDeclaredMethod("setConsumable", c2);

            Assert.assertEquals(m1.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals(m2.getModifiers(), Modifier.PUBLIC);
            Assert.assertTrue("wrong return type (Consumable) of getConsumable", m1.getReturnType() == c2);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have classes called Consumable and Character");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have getter and setter for consumable attribute");
        }
    }

    @Test
    public void existConsumeMethod() {
        try {
            Class<?> c1 = Class.forName("lsg.characters.Character");
            Method m1 = c1.getDeclaredMethod("consume");

            Assert.assertEquals(m1.getModifiers(), Modifier.PUBLIC);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have class called Character");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method named consume");
        }
    }

    @Test
    public void testConsumeMethod() {
        try {
            Class<?> c1 = Class.forName("lsg.characters.Hero");
            Class<?> c2 = Class.forName("lsg.characters.Character");
            Class<?> c3 = Class.forName("lsg.consumables.drinks.Whisky");
            Class<?> c4 = Class.forName("lsg.consumables.Consumable");
            Constructor<?> constructor1 = c1.getDeclaredConstructor();
            Constructor<?> constructor2 = c3.getDeclaredConstructor();
            Object o1 = constructor1.newInstance();
            Object o2 = constructor2.newInstance();
            Method m1 = c2.getDeclaredMethod("setConsumable", c4);
            Method m2 = c2.getDeclaredMethod("consume");

            m1.invoke(o1, o2);
            m2.invoke(o1);

            Assert.assertEquals(outContent.toString(), "Gregooninator drinks 12 years old Oban [150 stamina point(s)]\n");
        } catch (ClassNotFoundException e) {
            Assert.fail("should have classes called lsg.characters.Hero, lsg.characters.Character, lsg.consumables.Consumable and lsg.consumables.drinks.Whisky");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have methods called setConsumable and consume in Character class");
        } catch (IllegalAccessException e) {
            Assert.fail("IllegalAccessException");
        } catch (InstantiationException e) {
            Assert.fail("InstantiationException");
        } catch (InvocationTargetException e) {
            Assert.fail("InvocationTargetException");
        }
    }

}