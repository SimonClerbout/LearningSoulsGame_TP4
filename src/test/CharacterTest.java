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
            Assert.fail("should have a class called Monster");
        } catch (NoSuchFieldException e) {
            Assert.fail("should have an attribute named talisman");
        }
    }

}