package eg.edu.alexu.csd.datastructure.linkedList.cs53_cs28;

import static org.junit.Assert.*;

import org.junit.Test;

public class DoubleTest 
{
	@Test
	public void testdouble() 
	{
		Double test= new Double();
		test.add(1);
		test.add(2);
		test.add(4);
		test.add(5);
		
		Object result=test.get(0);
		assertEquals(1,result);
		result=test.get(1);
		assertEquals(2,result);
		result=test.get(2);
		assertEquals(4,result);
		result=test.get(3);
		assertEquals(5,result);
		
		test.add(2,3);
		result=test.get(2);
		assertEquals(3,result);
		
		test.set(0,0);
		result=test.get(0);
		assertEquals(0,result);
		
		
		ILinkedList list=test.sublist(1,3);
		ILinkedList explist= new Double();
		explist.add(2);
		explist.add(3);
		explist.add(4);
		assertEquals(explist, list);
		
		
		test.remove(0);
		result=test.size();
		assertEquals(4,result);
		
		
		assertEquals(true,test.contains(2));
		assertEquals(false,test.contains(0));
		
		
		test.clear();
		assertEquals(true,test.isEmpty());
		
		
		
	}

}
