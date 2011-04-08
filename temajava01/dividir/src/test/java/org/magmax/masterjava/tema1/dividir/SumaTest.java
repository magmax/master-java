package org.magmax.masterjava.tema1.dividir;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class SumaTest {
	private Suma sut;

	@Before
	public void setUp() {
		sut = new Suma();
	}
	
	@Test
	public void test_suma_100() {
		assertEquals(100, sut.suma(100));
		
	}
	
	@Test
	public void test_suma_50() {
		assertEquals(101, sut.suma(50));
		
	}
	
	@Test
	public void test_suma_99() {
		assertEquals(199, sut.suma(99));
		
	}
	
	@Test
	public void test_suma_1() {
		assertEquals(105, sut.suma(1));
		
	}
}
