package org.magmax.masterjava.tema1.dividir;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrdenarTest {
	private Ordenar sut;

	@Before 
	public void setUp () {
		sut = new Ordenar();
	}
	
	@Test
	public void test_ordenar_vacio () {
		assertArrayEquals(new int[] {}, sut.ordenar(new int[] {}));
	}
	
	@Test
	public void test_ordenar_1_numero () {
		assertArrayEquals(new int[] {1}, sut.ordenar(new int[] {1}));
	}
	
	@Test
	public void test_ordenar_2_numeros_ordenados () {
		assertArrayEquals(new int[] {1,2}, sut.ordenar(new int[] {1,2}));
	}
	
	@Test
	public void test_ordenar_2_numeros_desordenados () {
		assertArrayEquals(new int[] {1,2}, sut.ordenar(new int[] {2,1}));
	}
	
	@Test
	public void test_ordenar_3_numeros_ordenados () {
		assertArrayEquals(new int[] {1,2,3}, sut.ordenar(new int[] {1,2,3}));
	}
	
	@Test
	public void test_ordenar_3_numeros_desordenados () {
		assertArrayEquals(new int[] {1,2,3}, sut.ordenar(new int[] {3,2,1}));
	}
	
	@Test
	public void test_ordenar_10_numeros_desordenados () {
		assertArrayEquals(new int[] {1,2,3,5,7,11,15,18,20,2043}, sut.ordenar(new int[] {11,3,2043,7,18,2,20,5,1,15}));
	}
}
