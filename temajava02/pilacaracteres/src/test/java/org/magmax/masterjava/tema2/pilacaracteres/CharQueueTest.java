package org.magmax.masterjava.tema2.pilacaracteres;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
public class CharQueueTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_creation () {
		CharQueue sut = new CharQueue (5);
		assertNotNull(sut);
		assertTrue (sut instanceof CharQueue);
	}

	@Test
	public void test_creation_without_params () {
		CharQueue sut = new CharQueue ();
		assertNotNull(sut);
		assertTrue (sut instanceof CharQueue);
	}

	@Test
	public void test_añadir() {
		CharQueue sut = new CharQueue();
		sut.añadir("Hell Bells");
		assertEquals("[Hell Bells]", sut.toString());
	}
	
	@Test
	public void test_añadir_triangulacion() {
		CharQueue sut = new CharQueue();
		sut.añadir("Inject the venom");
		assertEquals("[Inject the venom]", sut.toString());
	}
	
	@Test
	public void test_añadir_supera_maximo() {
		CharQueue sut = new CharQueue(5);
		sut.añadir("Black Ice");
		assertEquals("[Black]", sut.toString());
	}
	
	@Test
	public void test_añadir_2_strings() {
		CharQueue sut = new CharQueue();
		sut.añadir("JailBreak");
		sut.añadir("Soul Stripper");
		assertEquals("[JailBreak, Soul Stripper]", sut.toString());
	}
	
	@Test
	public void test_eliminar() {
		CharQueue sut = new CharQueue();
		sut.añadir("Show Business");
		sut.añadir("You ain't got a hold on me");
		sut.eliminar();
		assertEquals("[Show Business]", sut.toString());
	}
	
	@Test
	public void test_total() {
		CharQueue sut = new CharQueue();
		
		assertEquals(0, sut.total());
		
		sut.añadir("Wheels");
		assertEquals(1, sut.total());
		
		sut.añadir("Smash n grab");
		assertEquals(2, sut.total());
		
		sut.eliminar();
		assertEquals(1, sut.total());
	}
}
