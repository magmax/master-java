package org.magmax.masterj2ee.practica1;

import java.io.Reader;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Miguel Angel Garcia <miguelangel.garcia@gmail.com>
 */
public class LectorTest {

    private Lector sut;

    public LectorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        sut = new Lector();
    }

    @After
    public void tearDown() {
    }

    private ByteArrayInputStream getInputStream(String s) {
        return new ByteArrayInputStream(s.getBytes());
    }

    private Reader getReader(Object s) {
        return new InputStreamReader(getInputStream(s.toString()));
    }

    @Test
    public void exists_Lector_class() {
        assertTrue(Lector.class != null);
    }

    @Test
    public void is_a_BufferReader() {
        assertTrue(sut instanceof java.io.BufferedReader);
    }

    @Test
    public void default_constructor_is_linkedto_Stdin() throws IOException {
        String expected = "Ace of Spades";
        System.setIn(getInputStream(expected));
        sut = new Lector(); // recargo para que use el nuevo stream.

        assertEquals(expected, sut.readLine());
    }

    @Test
    public void input_reader_is_used() throws IOException {
        String expected = "Ace of Spades";
        sut = new Lector(getReader(expected));

        assertEquals(expected, sut.readLine());
    }

    @Test
    public void readInt_gets_last_line_as_int() throws IOException, NumeroNoValidoExcepcion {
        Integer expected = 123456;
        sut = new Lector(getReader(expected));

        assertEquals(expected.intValue(), sut.readInt());
    }

    @Test
    public void readInt_gets_last_line_as_int_always() throws IOException, NumeroNoValidoExcepcion {
        Integer expected = 123;
        sut = new Lector(getReader(expected));

        assertEquals(expected.intValue(), sut.readInt());
    }

    @Test
    public void readDouble_gets_last_line_as_double() throws IOException, NumeroNoValidoExcepcion {
        Double expected = 123.231;
        sut = new Lector(getReader(expected));

        assertEquals(expected.doubleValue(), sut.readDouble(), 0.0001);
    }

    @Test
    public void readManyInt_gets_last_line_as_intArray() throws IOException, NumeroNoValidoExcepcion {
        int[] expected = new int[]{1, 2, 3, 4, 7};
        String values = "1,2,3,4,7";
        sut = new Lector(getReader(values));

        assertArrayEquals(expected, sut.readManyInt());
    }

    @Test(expected = NumeroNoValidoExcepcion.class)
    public void readInt_launches_exception() throws IOException, NumeroNoValidoExcepcion {
        sut = new Lector(getReader("abcde"));

        sut.readInt();
    }

    @Test(expected = NumeroNoValidoExcepcion.class)
    public void readDouble_launches_exception() throws IOException, NumeroNoValidoExcepcion {
        sut = new Lector(getReader("abcde"));

        sut.readDouble();
    }

    @Test(expected = NumeroNoValidoExcepcion.class)
    public void readManyInt_launches_exception() throws IOException, NumeroNoValidoExcepcion {
        sut = new Lector(getReader("1,2,abcde,4,5"));

        sut.readManyInt();
    }
}
