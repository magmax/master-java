package org.magmax.masterjava.tema1.dividir;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SatisfaccionTest {
	private Satisfaccion sut;

	@Before
	public void setUp() {
		sut = new Satisfaccion();
	}

	@Test
	public void test_muy_insatisfecho() {
		assertEquals("Muy insatisfecho", sut.show(1));
	}
	
	@Test
	public void test_insatisfecho() {
		assertEquals("Insatisfecho", sut.show(2));
	}
	
	@Test
	public void test_normal() {
		assertEquals("Normal", sut.show(3));
	}
	
	@Test
	public void test_satisfecho() {
		assertEquals("Satisfecho", sut.show(4));
	}
	
	@Test
	public void test_muy_satisfecho() {
		assertEquals("Muy satisfecho", sut.show(5));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_too_low()
	{
		sut.show(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_too_high()
	{
		sut.show(6);
	}
}
