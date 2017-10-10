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
    public void testMain() {
        try {
            Class<?> c = Class.forName("lsg.LearningSoulsGame");
            Method m = c.getMethod("main", String[].class);
            Object[] args = new Object[1];

            args[0] = new String[]{};
            m.invoke(null, args);

            String[] list = outContent.toString().split("\n");

            if (list.length == 14) {
                Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   50      PROTECTION: 0.00     (ALIVE)", list[0]);
                Assert.assertEquals("[ Monster ]          Monster_1            LIFE:   10      STAMINA:   10      PROTECTION: 20.00    (ALIVE)", list[1]);
                Assert.assertEquals("", list[2]);
                Assert.assertEquals("Hit enter key for next move > ", list[3]);
                Assert.assertEquals("Gregooninator attacks Monster_1 with Basic Sword (ATTACK:7 | DMG : 6)", list[4]);
                Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   30      PROTECTION: 0.00     (ALIVE)", list[5]);
                Assert.assertEquals("[ Monster ]          Monster_1            LIFE:    4      STAMINA:   10      PROTECTION: 20.00    (ALIVE)", list[6]);
                Assert.assertEquals("", list[7]);
                Assert.assertEquals("Hit enter key for next move > ", list[8]);
                Assert.assertEquals("Monster_1 attacks Gregooninator with Bloody Claw (ATTACK:106 | DMG : 100)", list[9]);
                Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:    0      STAMINA:   30      PROTECTION: 0.00     (DEAD)", list[10]);
                Assert.assertEquals("[ Monster ]          Monster_1            LIFE:    4      STAMINA:    5      PROTECTION: 20.00    (ALIVE)", list[11]);
                Assert.assertEquals("", list[12]);
                Assert.assertEquals("--- Monster_1 WINS !!! ---", list[13]);
            } else {
                if (list[0].equals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   50      PROTECTION: 14.99    (ALIVE)")) {
                    Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   50      PROTECTION: 14.99    (ALIVE)", list[0]);
                    if (list[1].equals("[ Monster ]          Monster_1            LIFE:   10      STAMINA:   10      PROTECTION: 20.00    (ALIVE)")) {
                        Assert.assertEquals("[ Monster ]          Monster_1            LIFE:   10      STAMINA:   10      PROTECTION: 20.00    (ALIVE)", list[1]);
                        Assert.assertEquals("", list[2]);
                        Assert.assertEquals("Hit enter key for next move > ", list[3]);
                        Assert.assertEquals("Gregooninator attacks Monster_1 with Basic Sword (ATTACK:7 | DMG : 6)", list[4]);
                        Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   30      PROTECTION: 14.99    (ALIVE)", list[5]);
                        Assert.assertEquals("[ Monster ]          Monster_1            LIFE:    4      STAMINA:   10      PROTECTION: 20.00    (ALIVE)", list[6]);
                        Assert.assertEquals("", list[7]);
                        Assert.assertEquals("Hit enter key for next move > ", list[8]);
                        Assert.assertEquals("Monster_1 attacks Gregooninator with Bloody Claw (ATTACK:106 | DMG : 90)", list[9]);
                        Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:   10      STAMINA:   30      PROTECTION: 14.99    (ALIVE)", list[10]);
                        Assert.assertEquals("[ Monster ]          Monster_1            LIFE:    4      STAMINA:    5      PROTECTION: 20.00    (ALIVE)", list[11]);
                        Assert.assertEquals("", list[12]);
                        Assert.assertEquals("Hit enter key for next move > ", list[13]);
                        Assert.assertEquals("Gregooninator attacks Monster_1 with Basic Sword (ATTACK:7 | DMG : 4)", list[14]);
                        Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:   10      STAMINA:   10      PROTECTION: 14.99    (ALIVE)", list[15]);
                        Assert.assertEquals("[ Monster ]          Monster_1            LIFE:    0      STAMINA:    5      PROTECTION: 20.00    (DEAD)", list[16]);
                        Assert.assertEquals("", list[17]);
                        Assert.assertEquals("--- Gregooninator WINS !!! ---", list[18]);
                    } else {
                        Assert.assertEquals("[ Lycanthrope ]      Lycanthrope          LIFE:   10      STAMINA:   10      PROTECTION: 30.00    (ALIVE)", list[1]);
                        Assert.assertEquals("", list[2]);
                        Assert.assertEquals("Hit enter key for next move > ", list[3]);
                        Assert.assertEquals("Gregooninator attacks Lycanthrope with Basic Sword (ATTACK:7 | DMG : 5)", list[4]);
                        Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   30      PROTECTION: 14.99    (ALIVE)", list[5]);
                        Assert.assertEquals("[ Lycanthrope ]      Lycanthrope          LIFE:    5      STAMINA:   10      PROTECTION: 30.00    (ALIVE)", list[6]);
                        Assert.assertEquals("", list[7]);
                        Assert.assertEquals("Hit enter key for next move > ", list[8]);
                        Assert.assertEquals("Lycanthrope attacks Gregooninator with Bloody Claw (ATTACK:106 | DMG : 90)", list[9]);
                        Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:   10      STAMINA:   30      PROTECTION: 14.99    (ALIVE)", list[10]);
                        Assert.assertEquals("[ Lycanthrope ]      Lycanthrope          LIFE:    5      STAMINA:    5      PROTECTION: 30.00    (ALIVE)", list[11]);
                        Assert.assertEquals("", list[12]);
                        Assert.assertEquals("Hit enter key for next move > ", list[13]);
                        Assert.assertEquals("Gregooninator attacks Lycanthrope with Basic Sword (ATTACK:7 | DMG : 5)", list[14]);
                        Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:   10      STAMINA:   10      PROTECTION: 14.99    (ALIVE)", list[15]);
                        Assert.assertEquals("[ Lycanthrope ]      Lycanthrope          LIFE:    0      STAMINA:    5      PROTECTION: 30.00    (DEAD)", list[16]);
                        Assert.assertEquals("", list[17]);
                        Assert.assertEquals("--- Gregooninator WINS !!! ---", list[18]);
                    }
                } else {
                    Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   50      PROTECTION: 10.20     BUFF: 14.00    (ALIVE)", list[0]);
                    Assert.assertEquals("[ Lycanthrope ]      Lycanthrope          LIFE:   10      STAMINA:   10      PROTECTION: 30.00     BUFF:  0.00    (ALIVE)", list[1]);
                    Assert.assertEquals("", list[2]);
                    Assert.assertEquals("Hit enter key for next move > ", list[3]);
                    Assert.assertEquals("Gregooninator attacks Lycanthrope with Basic Sword (ATTACK:7 | DMG : 5)", list[4]);
                    Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:  100      STAMINA:   30      PROTECTION: 10.20     BUFF: 14.00    (ALIVE)", list[5]);
                    Assert.assertEquals("[ Lycanthrope ]      Lycanthrope          LIFE:    5      STAMINA:   10      PROTECTION: 30.00     BUFF:  0.00    (ALIVE)", list[6]);
                    Assert.assertEquals("", list[7]);
                    Assert.assertEquals("Hit enter key for next move > ", list[8]);
                    Assert.assertEquals("Lycanthrope attacks Gregooninator with Bloody Claw (ATTACK:106 | DMG : 95)", list[9]);
                    Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:    5      STAMINA:   30      PROTECTION: 10.20     BUFF:10014.00  (ALIVE)", list[10]);
                    Assert.assertEquals("[ Lycanthrope ]      Lycanthrope          LIFE:    5      STAMINA:    5      PROTECTION: 30.00     BUFF:  0.00    (ALIVE)", list[11]);
                    Assert.assertEquals("", list[12]);
                    Assert.assertEquals("Hit enter key for next move > ", list[13]);
                    Assert.assertEquals("Gregooninator attacks Lycanthrope with Basic Sword (ATTACK:7 | DMG : 5)", list[14]);
                    Assert.assertEquals("[ Hero ]             Gregooninator        LIFE:    5      STAMINA:   10      PROTECTION: 10.20     BUFF:10014.00  (ALIVE)", list[15]);
                    Assert.assertEquals("[ Lycanthrope ]      Lycanthrope          LIFE:    0      STAMINA:    5      PROTECTION: 30.00     BUFF:  0.00    (DEAD)", list[16]);
                    Assert.assertEquals("", list[17]);
                    Assert.assertEquals("--- Gregooninator WINS !!! ---", list[18]);
                }
            }
        } catch (ClassNotFoundException e) {
            Assert.fail("should have a class called LearningSoulsGame");
        } catch (NoSuchMethodException e) {
            Assert.fail("should have a static method called main");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}