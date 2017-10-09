package test;

import com.sun.istack.internal.Nullable;
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
    public void testSkinThicknessAttributes() {
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

}