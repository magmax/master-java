package org.magmax.masterjava.tema1.dividir;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
public class PrimoTest {
	private Primo sut;

	@Before
	public void setUp() {
		sut = new Primo();
	}
	
	@Test
	public void test_1_es_primo () {
		assertTrue(sut.esPrimo(1));
	}
	
	@Test
	public void test_2_es_primo () {
		assertTrue(sut.esPrimo(2));
	}
	
	@Test
	public void test_3_es_primo () {
		assertTrue(sut.esPrimo(3));
	}
	
	@Test
	public void test_4_no_es_primo () {
		assertFalse(sut.esPrimo(4));
	}
	
	@Test
	public void test_5_es_primo () {
		assertTrue(sut.esPrimo(5));
	}
	
	@Test
	public void test_6_no_es_primo () {
		assertFalse(sut.esPrimo(6));
	}
	
	@Test
	public void test_7_es_primo () {
		assertTrue(sut.esPrimo(7));
	}
	
	@Test 
	public void test_8_no_es_primo() {
		assertFalse(sut.esPrimo(8));
	}
	
	@Test 
	public void test_9_no_es_primo() {
		assertFalse(sut.esPrimo(9));
	}
	
	@Test 
	public void test_25_no_es_primo() {
		assertFalse(sut.esPrimo(25));
	}
}
