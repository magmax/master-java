package org.magmax.masterjava.tema1.dividir;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class DividirTest {
	private Dividir sut;

	@Before
	public void setUp() {
		sut = new Dividir();
	}

	@Test
	public void test_divide_2_and_1() {
		assertEquals(2, sut.divide(new String[] { "2", "1" }));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_few_arguments() {
		sut.divide(new String[] { "2" });
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_too_much_arguments() {
		sut.divide(new String[] { "1", "2", "3" });
	}

	@Test
	public void test_divide_6_and_2() {
		assertEquals (3, sut.divide(new String[] {"6", "2"}));
	}
	
	@Test
	public void test_divide_2_and_6() {
		assertEquals (3, sut.divide(new String[] {"2", "6"}));
	}
}
