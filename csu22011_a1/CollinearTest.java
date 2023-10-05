package csu22011_a1;

import static org.junit.Assert.*;

import org.junit.Test;
//import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author Rory Ward
 *  @version 03/10/22 22:33:19
 */
@RunWith(JUnit4.class)
public class CollinearTest
{
	public static void main(String[] args)
	{
		final int NUMBER_OF_TESTS = 100;
//		Use this instantiation block for countCollinear
//		In in1 = new In("r05000-1.txt");
//		int[] testArray1 = in1.readAllInts();
//		In in2 = new In("r05000-2.txt");
//		int[] testArray2 = in2.readAllInts();
//		In in3 = new In("r05000-3.txt");
//		int[] testArray3 = in3.readAllInts();
		
		double totalTime = 0;
		double elapsedTime;
		Stopwatch timer;
		for(int i=0; i<NUMBER_OF_TESTS; i++) {
//			Use this instantiation block for countCollinearFast to avoid using
//			the same sorted arrays every time after the first iteration
			In in1 = new In("r05000-1.txt");
			int[] testArray1 = in1.readAllInts();
			In in2 = new In("r05000-2.txt");
			int[] testArray2 = in2.readAllInts();
			In in3 = new In("r05000-3.txt");
			int[] testArray3 = in3.readAllInts();
			timer = new Stopwatch();
			Collinear.countCollinearFast(testArray1, testArray2, testArray3);
		    elapsedTime = timer.elapsedTime();
		    totalTime += elapsedTime;
		}
		double meanTime = totalTime/NUMBER_OF_TESTS;
		StdOut.printf("countCollinear took %.9f seconds", meanTime);
	}
	
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new Collinear();
    }
    
    @Test
    public void testEmpty()
    {
        int expectedResult = 0;
        int[] testArray = new int[3];
        testArray[0] = 1;
        testArray[1] = 2;
        testArray[2] = 4;

        assertEquals("countCollinear with 3 empty arrays should return zero",     expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
        assertEquals("countCollinearFast with 3 empty arrays should return zero", expectedResult, Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
        assertEquals("binarySearch with false argument", false, Collinear.binarySearch(testArray, 5));
        testArray = new int[0];
        Collinear.sort(testArray);
        assertEquals("sort with empty array", expectedResult, testArray.length);
    }
    
    @Test
    public void testSimpleParams()
    {
    	int[] testArray = createTestArray();
        int[] expectedArray = createSortedArray();
        Collinear.sort(testArray);
        assertEquals("sort with simple 5 element array", expectedArray[3], testArray[3]);
        assertEquals("sort with simple 5 element array", expectedArray[0], testArray[0]);
        testArray = createSortedArray();
        assertTrue("binarySearch with true argument", Collinear.binarySearch(testArray, 2));
        assertTrue("binarySearch with true argument", Collinear.binarySearch(testArray, 4));
        testArray = createBigSortedArray();
        assertTrue("binarySearch with true argument", Collinear.binarySearch(testArray, 100));
        assertTrue("binarySearch with true argument", Collinear.binarySearch(testArray, 4));
        assertTrue("binarySearch with true argument", Collinear.binarySearch(testArray, 62));
        assertTrue("binarySearch with true argument", Collinear.binarySearch(testArray, 87));
        testArray = createTestArray();
        assertEquals("countCollinear with 3 of the same simple array", 13, Collinear.countCollinear(testArray, testArray, testArray));
        assertEquals("countCollinearFast with 3 of the same simple array", 13, Collinear.countCollinearFast(testArray, testArray, testArray));
    }
    
    public static int[] createTestArray() {
    	int[] testArray = {3,1,4,2,5};
        return testArray;
    }
    
    public static int[] createSortedArray() {
    	int[] sortedArray = {1,2,3,4,5};
        return sortedArray;
    }
    
    public static int[] createBigSortedArray() {
    	int[] testArray = new int[100];
    	for(int i=0; i<testArray.length; i++)
    		testArray[i] = i+1;
        return testArray;
    }
}