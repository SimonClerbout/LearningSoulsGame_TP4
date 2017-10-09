package test;

import com.sun.istack.internal.Nullable;
import lsg.armor.ArmorItem;
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

    @Nullable
    private Constructor<?> searchDefaultConstructor(Class<?> c) {
        for (Constructor<?> constructor : c.getConstructors()) {
            if (constructor.getParameterCount() == 0) {
                return constructor;
            }
        }
        return null;
    }

    @Test
    public void testSkinThicknessAttribute() {
        try {
            Class<?> c = Class.forName("lsg.characters.Monster");
            Field f = c.getDeclaredField("skinThickness");

            Assert.assertEquals(f.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f.getType(), float.class);

            Constructor<?> constructor = searchDefaultConstructor(c);
            Object o = constructor.newInstance();

            f.setAccessible(true);

            Assert.assertEquals((float) (f.get(o)), 20f, 0f);

        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Monster");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have an attribute named skinThickness");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSkinThicknessGetterSetter() {
        try {
            Class<?> c = Class.forName("lsg.characters.Monster");
            Method mg = c.getDeclaredMethod("getSkinThickness");
            Method ms = c.getDeclaredMethod("setSkinThickness", float.class);

            Assert.assertEquals(mg.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals(ms.getModifiers(), Modifier.PRIVATE);
            Assert.assertTrue("wrong return type (float) of getSkinThickness", mg.getReturnType() == float.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Monster");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have accessors for skinThickness attribute");
        }
    }

    @Test
    public void existArmorItemClass() {
        try {
            Class.forName("lsg.armor.ArmorItem");
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called ArmorItem in package lsg.armor");
        }
    }

    @Test
    public void testArmorItemAttributes() {
        try {
            Class<?> c = Class.forName("lsg.armor.ArmorItem");
            Field f1 = c.getDeclaredField("name");
            Field f2 = c.getDeclaredField("armorValue");

            Assert.assertEquals(f1.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f2.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f1.getType(), String.class);
            Assert.assertEquals(f2.getType(), float.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called ArmorItem");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have some missed attribute: name and armorValue");
        }
    }

    @Test
    public void testArmorItemGetterSetter() {
        try {
            Class<?> c = Class.forName("lsg.armor.ArmorItem");
            Method m1 = c.getDeclaredMethod("getName");
            Method m2 = c.getDeclaredMethod("getArmorValue");

            Assert.assertEquals(m1.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals(m2.getModifiers(), Modifier.PUBLIC);
            Assert.assertTrue("wrong return type (string) of getName", m1.getReturnType() == String.class);
            Assert.assertTrue("wrong return type (float) of getArmorValue", m2.getReturnType() == float.class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called ArmorItem");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have accessors for name and armorValue attributes");
        }
    }

    @Test
    public void testArmorItemConstructor() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.armor.ArmorItem");
            Constructor<?> constructor = c.getDeclaredConstructor(java.lang.String.class, float.class);

            Assert.assertEquals(constructor.getModifiers(), Modifier.PUBLIC);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.armor.ArmorItem");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a constructor with two parameters (String and float) for lsg.armor.ArmorItem class");
        }
    }

    @Test
    public void existArmorItemToString() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.armor.ArmorItem");
            Constructor<?> constructor = c.getDeclaredConstructor(java.lang.String.class, float.class);
            Object o = constructor.newInstance("Black Witch Veil", 4.6f);
            Method ts = c.getDeclaredMethod("toString");

            Assert.assertEquals(ts.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals("Black Witch Veil(4.6)", (String) (ts.invoke(o)));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.armor.ArmorItem");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called toString in ArmorItem class");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void existBlackWitchVeilClass() {
        try {
            Class.forName("lsg.armor.BlackWitchVeil");
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called BlackWitchVeil in package lsg.armor");
        }
    }

    @Test
    public void testBlackWitchVeilConstructor() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.armor.BlackWitchVeil");
            Constructor<?> constructor = c.getDeclaredConstructor();

            Assert.assertEquals(constructor.getModifiers(), Modifier.PUBLIC);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.armor.BlackWitchVeil");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a default constructor for lsg.armor.BlackWitchVeil class");
        }
    }

    @Test
    public void testBlackWitchVeilToString() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.armor.BlackWitchVeil");
            Constructor<?> constructor = c.getDeclaredConstructor();
            Object o = constructor.newInstance();
            Method ts = c.getMethod("toString");

            Assert.assertEquals(ts.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals("Black Witch Veil(4.6)", (String) (ts.invoke(o)));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.armor.BlackWitchVeil");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called toString in ArmorItem class");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void existDragonSlayerLeggingsClass() {
        try {
            Class.forName("lsg.armor.DragonSlayerLeggings");
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called DragonSlayerLeggings in package lsg.armor");
        }
    }

    @Test
    public void testDragonSlayerLeggingsConstructor() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.armor.DragonSlayerLeggings");
            Constructor<?> constructor = c.getDeclaredConstructor();

            Assert.assertEquals(constructor.getModifiers(), Modifier.PUBLIC);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.armor.DragonSlayerLeggings");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a default constructor for lsg.armor.DragonSlayerLeggings class");
        }
    }

    @Test
    public void testDragonSlayerLeggingsToString() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.armor.DragonSlayerLeggings");
            Constructor<?> constructor = c.getDeclaredConstructor();
            Object o = constructor.newInstance();
            Method ts = c.getMethod("toString");

            Assert.assertEquals(ts.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals("Dragon Slayer Leggings(10.2)", (String) (ts.invoke(o)));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.armor.DragonSlayerLeggings");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called toString in ArmorItem class");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void existRingedKnightArmorClass() {
        try {
            Class.forName("lsg.armor.RingedKnightArmor");
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called RingedKnightArmor in package lsg.armor");
        }
    }

    @Test
    public void testRingedKnightArmorConstructor() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.armor.RingedKnightArmor");
            Constructor<?> constructor = c.getDeclaredConstructor();

            Assert.assertEquals(constructor.getModifiers(), Modifier.PUBLIC);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.armor.RingedKnightArmor");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a default constructor for lsg.armor.RingedKnightArmor class");
        }
    }

    @Test
    public void testRingedKnightArmorToString() {
        Class<?> c = null;
        try {
            c = Class.forName("lsg.armor.RingedKnightArmor");
            Constructor<?> constructor = c.getDeclaredConstructor();
            Object o = constructor.newInstance();
            Method ts = c.getMethod("toString");

            Assert.assertEquals(ts.getModifiers(), Modifier.PUBLIC);
            Assert.assertEquals("Ringed Knight Armor(14.99)", (String) (ts.invoke(o)));
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.armor.RingedKnightArmor");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called toString in ArmorItem class");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void notExistToStringInArmorItemSubclasses() {
        Class<?> c1 = null;
        Class<?> c2 = null;
        Class<?> c3 = null;
        Method ts;

        try {
            c1 = Class.forName("lsg.armor.BlackWitchVeil");
            c2 = Class.forName("lsg.armor.DragonSlayerLeggings");
            c3 = Class.forName("lsg.armor.RingedKnightArmor");
            c1.getDeclaredMethod("toString");
            Assert.fail("toString method should be not override");
        } catch (ClassNotFoundException e) {
            Assert.fail("should have classes called BlackWitchVeil, DragonSlayerLeggings, RingedKnightArmor");
        } catch (NoSuchMethodException e1) {
            try {
                c2.getDeclaredMethod("toString");
                Assert.fail("toString method should be not override");
            } catch (NoSuchMethodException e2) {
                try {
                    c3.getDeclaredMethod("toString");
                    Assert.fail("toString method should be not override");
                } catch (NoSuchMethodException e3) {
                    Assert.assertTrue(true);
                }
            }
        }
    }

    @Test
    public void testArmorAttribute() {
        try {
            Class<?> c = Class.forName("lsg.characters.Hero");
            Field f = c.getDeclaredField("armor");

            Assert.assertEquals(f.getModifiers(), Modifier.PRIVATE);
            Assert.assertEquals(f.getType(), lsg.armor.ArmorItem[].class);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Hero");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have an attribute named armor");
        }
    }

    @Test
    public void testMaxArmorPiecesAttribute() {
        try {
            Class<?> c = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor = c.getDeclaredConstructor(java.lang.String.class);

            if (c == null) {
                Assert.fail("should have a constructor with a string parameter");
            } else {
                Field f = c.getDeclaredField("MAX_ARMOR_PIECES");
                Object o = constructor.newInstance("supertoto");

                Assert.assertEquals(f.getModifiers(), Modifier.PRIVATE | Modifier.STATIC);
                Assert.assertEquals(f.getType(), int.class);
                Assert.assertEquals((int) (f.get(o)), 3);
            }
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Hero");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have an static attribute named MAX_ARMOR_PIECES");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHeroConstructorWithArmor() {
        try {
            Class<?> c = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor = c.getDeclaredConstructor(java.lang.String.class);

            if (constructor == null) {
                Assert.fail("should have a constructor with a string parameter");
            } else {
                Object o = constructor.newInstance("supertoto");
                Field f = c.getDeclaredField("armor");
                lsg.armor.ArmorItem[] armor = (lsg.armor.ArmorItem[]) (f.get(o));

                Assert.assertEquals(armor.length, 3);
                Assert.assertEquals(armor[0], null);
                Assert.assertEquals(armor[1], null);
                Assert.assertEquals(armor[2], null);
            }
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called Hero");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            Assert.fail("should have methods called getName, getLife and getStamina");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            Assert.fail("should have an attribute named armor");
        }
    }

    @Test
    public void testSetArmorItem() {
        try {
            Class<?> c1 = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor1 = c1.getDeclaredConstructor(java.lang.String.class);
            Object o1 = constructor1.newInstance("supertoto");
            Method m1 = c1.getMethod("setArmorItem", ArmorItem.class, int.class);

            Assert.assertEquals(m1.getModifiers(), Modifier.PUBLIC);

            {
                Class<?> c2 = Class.forName("lsg.armor.BlackWitchVeil");
                Constructor<?> constructor2 = c2.getDeclaredConstructor();
                Object o2 = constructor2.newInstance();

                m1.invoke(o1, o2, 4);

                Field f = c1.getDeclaredField("armor");
                lsg.armor.ArmorItem[] armor = (lsg.armor.ArmorItem[]) (f.get(o1));

                Assert.assertEquals(armor.length, 3);
                Assert.assertEquals(armor[0], null);
                Assert.assertEquals(armor[1], null);
                Assert.assertEquals(armor[2], null);

                m1.invoke(o1, o2, 0);

                armor = (lsg.armor.ArmorItem[]) (f.get(o1));
                Assert.assertEquals(armor.length, 3);
                Assert.assertEquals(armor[0], null);
                Assert.assertEquals(armor[1], null);
                Assert.assertEquals(armor[2], null);
            }
            {
                Class<?> c2 = Class.forName("lsg.armor.BlackWitchVeil");
                Constructor<?> constructor2 = c2.getDeclaredConstructor();
                Object o2 = constructor2.newInstance();

                m1.invoke(o1, o2, 1);

                Field f = c1.getDeclaredField("armor");
                lsg.armor.ArmorItem[] armor = (lsg.armor.ArmorItem[]) (f.get(o1));

                Assert.assertEquals(armor.length, 3);
                Assert.assertEquals(armor[0], o2);
                Assert.assertEquals(armor[1], null);
                Assert.assertEquals(armor[2], null);
            }
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Hero");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called setArmorItem in Hero class");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetTotalArmor() {
        try {
            Class<?> c1 = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor1 = c1.getDeclaredConstructor(java.lang.String.class);
            Object o1 = constructor1.newInstance("supertoto");
            Method m1 = c1.getMethod("getTotalArmor");
            Method m2 = c1.getMethod("setArmorItem", ArmorItem.class, int.class);

            Assert.assertEquals(m1.getModifiers(), Modifier.PUBLIC);
            Assert.assertTrue("wrong return type (float) of getTotalArmor", m1.getReturnType() == float.class);

            Class<?> c2 = Class.forName("lsg.armor.BlackWitchVeil");
            Constructor<?> constructor2 = c2.getDeclaredConstructor();

            m2.invoke(o1, constructor2.newInstance(), 1);
            m2.invoke(o1, constructor2.newInstance(), 2);
            m2.invoke(o1, constructor2.newInstance(), 3);

            Assert.assertEquals((float) (m1.invoke(o1)), 13.8f, 0.01f);

        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Hero");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called getTotalArmor in Hero class");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testArmorToString() {
        try {
            Class<?> c1 = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor1 = c1.getDeclaredConstructor(java.lang.String.class);
            Object o1 = constructor1.newInstance("supertoto");
            Method m1 = c1.getMethod("setArmorItem", ArmorItem.class, int.class);
            Method m2 = c1.getMethod("armorToString");

            Assert.assertEquals(m2.getModifiers(), Modifier.PUBLIC);
            Assert.assertTrue("wrong return type (String) of armorToString", m2.getReturnType() == String.class);

            Class<?> c2 = Class.forName("lsg.armor.BlackWitchVeil");
            Constructor<?> constructor2 = c2.getDeclaredConstructor();
            Class<?> c3 = Class.forName("lsg.armor.RingedKnightArmor");
            Constructor<?> constructor3 = c3.getDeclaredConstructor();

            m1.invoke(o1, constructor2.newInstance(), 1);
            m1.invoke(o1, constructor3.newInstance(), 3);

            Assert.assertEquals((String) (m2.invoke(o1)), "ARMOR   1:Black Witch Veil(4.6)           2:empty                           3:Ringed Knight Armor(14.99)    TOTAL:19.59");
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Hero");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called armorToString in Hero class");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetArmorItems() {
        try {
            Class<?> c1 = Class.forName("lsg.characters.Hero");
            Constructor<?> constructor1 = c1.getDeclaredConstructor(java.lang.String.class);
            Object o1 = constructor1.newInstance("supertoto");
            Method m1 = c1.getMethod("setArmorItem", ArmorItem.class, int.class);
            Method m2 = c1.getMethod("getArmorItems");

            Assert.assertEquals(m2.getModifiers(), Modifier.PUBLIC);
            Assert.assertTrue("wrong return type (lsg.armor.ArmorItem[]) of getArmorItems", m2.getReturnType() == lsg.armor.ArmorItem[].class);

            Class<?> c2 = Class.forName("lsg.armor.BlackWitchVeil");
            Constructor<?> constructor2 = c2.getDeclaredConstructor();
            Object o2 = constructor2.newInstance();
            Class<?> c3 = Class.forName("lsg.armor.RingedKnightArmor");
            Constructor<?> constructor3 = c3.getDeclaredConstructor();
            Object o3 = constructor3.newInstance();

            m1.invoke(o1, o2, 1);
            m1.invoke(o1, o3, 3);

            lsg.armor.ArmorItem[] list = (lsg.armor.ArmorItem[]) (m2.invoke(o1));

            Assert.assertEquals(list.length, 2);
            Assert.assertEquals(list[0], o2);
            Assert.assertEquals(list[1], o3);
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called lsg.characters.Hero");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a method called getArmorItems in Hero class");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}