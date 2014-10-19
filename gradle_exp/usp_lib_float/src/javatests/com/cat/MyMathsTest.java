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
		assertEquals(5.0, maths.add(2.0f, 3.0f), 0.0f);
	}
	
	@Test
	public void testSub() {
		assertEquals(-1.0, maths.sub(2.0f, 3.0f), 0.0f);
	}
	
	@Test
	public void testMul() {
		assertEquals(6.0, maths.mul(2.0f, 3.0f), 0.0f);
	}
	
	@Test
	public void testDiv() {
		assertEquals(2.0f, maths.div(6.0f, 3.0f), 0.0f);
	}
}

