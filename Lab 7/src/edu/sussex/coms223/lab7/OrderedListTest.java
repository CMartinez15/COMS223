package edu.sussex.coms223.lab7;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/*-
 * The class OrderedListTest contains a set of JUnit tests that exercise class
 * OrderedList.
 * 
 * At the beginning of each test, create a local instance of an OrderedList
 * using Integer as the generic template type and the default constructor:
 * 
 *   OrderList<Integer> list = new OrderedList<>();
 *   
 * However, to get 100% code coverage of OrderedList.java, you must use the other
 * constructor in at least one test method:
 * 
 *   OrderList<Integer> list = new OrderedList<>(10);
 * 
 * The following assertion methods should be used to implement your tests:
 * 
 *   assertTrue(condition) 
 *   assertFalse(condition) 
 *   assertEquals(expected value, expression) 
 *   assertThrows(exception class, executable)
 *   assertNull(expression)
 * 
 * Below are some examples taken from the previous labs StackTest.java:
 * 
 *   assertEquals(0, s.size());
 *   assertNull(s.first()); 
 *   assertTrue(s.isEmpty());
 *   assertThrows(NoSuchElementException.class, () -> { s.pop(); });
 * 
 */
class OrderedListTest {

	@Test
	void testEmptySizeAndClear() {
		OrderedList<Integer> list = new OrderedList<>(10);
		list.insert(1);
		assertFalse(list.isEmpty());
		assertEquals(1, list.size());
		list.clear();
		assertEquals(0, list.size());
	}

	@Test
	void testAdd() {
		OrderedList<Integer> list = new OrderedList<>(10);
		list.add(0);
		assertThrows(OrderViolationException.class, () -> {
			list.add(-1);
		});
		list.clear();
		assertEquals(0, list.size());
		
		assertThrows(IndexOutOfBoundsException.class, () -> {
			list.add(-1, 1);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			list.add(11, 2);
		});
		list.clear();
		assertEquals(0, list.size());

		list.add(-1);
		list.add(0);
		assertThrows(OrderViolationException.class, () -> {
			list.add(1, 1);
		});

		assertThrows(OrderViolationException.class, () -> {
			list.add(0, 0);
		}); 
	}

	@Test
	void testInsertGet() {
		OrderedList<Integer> list = new OrderedList<>(10);
		list.insert(7);
		list.insert(1);
		list.insert(5);
		assertEquals(1,list.first() );
		assertThrows(OrderViolationException.class, () -> {
			list.insert(1);
			list.insert(1);
		});
		
		list.clear();
		assertEquals(0, list.size());
		assertThrows(IndexOutOfBoundsException.class, () -> {
			list.get(-1);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			list.get(10);
		});
		
	}

	@Test
	void testRemove() {
		OrderedList<Integer> list = new OrderedList<>(10);
		list.insert(0);
		assertEquals(0, list.get(0));
		list.remove(0);
		assertTrue(list.isEmpty());
		list.insert(1);
		list.remove(Integer.valueOf(1));
		assertTrue(list.isEmpty());
		assertThrows(IndexOutOfBoundsException.class, () -> {
			list.remove(-1);
		});
		assertThrows(IndexOutOfBoundsException.class, () -> {
			list.remove(10);
		});
		assertThrows(NoSuchElementException.class, () -> {
			list.remove(Integer.valueOf(0));
		});
		list.insert(1);
		assertThrows(NoSuchElementException.class, () -> {
			list.remove(Integer.valueOf(0));
		});
	}

	@Test
	void testFirstNext() {
		OrderedList<Integer> list = new OrderedList<>();
		assertNull(list.first());
		
		assertNull(list.next());
//		assertNull(list.next() == list.first());
		list.insert(7);
		list.insert(1);
		list.insert(5);
		assertEquals(1,list.first() );
		assertEquals(5, list.next());
		assertEquals(7, list.next());

	}
}
