package com.cat;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MyMathsTest {

	private MyMaths maths;
	
	@Before
	public void setUp() throws Exception {
		maths = new MyMaths();
	}

	@Test
	public void testAdd() {
		assertEquals(5, maths.add(2, 3));
	}
	
	@Test
	public void testSub() {
		assertEquals(-1, maths.sub(2, 3));
	}
	
	@Test
	public void testMul() {
		assertEquals(6, maths.mul(2, 3));
	}
	
	@Test
	public void testDiv() {
		assertEquals(2, maths.div(6, 3));
	}
}

