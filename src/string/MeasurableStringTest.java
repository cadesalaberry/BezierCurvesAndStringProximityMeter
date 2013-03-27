package string;

import static org.junit.Assert.*;

import org.junit.Test;

public class MeasurableStringTest {

	@Test
	public void beatingSpaces() {
		
		MeasurableString first = new MeasurableString("   abc  def");
		MeasurableString second = new MeasurableString("abc   def ");

		assertTrue(0 == first.distance(second));		
	}

	@Test
	public void beatingSimilarities() {
		
		MeasurableString ooo = new MeasurableString("ooo");
		MeasurableString eee = new MeasurableString("eee");
		MeasurableString www = new MeasurableString("www");
		
		assertTrue(ooo.distance(eee) < ooo.distance(www));		
	}
	
	@Test
	public void beatingReciprocality() {
		
		MeasurableString ooo = new MeasurableString("ooo");
		MeasurableString eee = new MeasurableString("eee");
		
		assertTrue(ooo.distance(eee) == eee.distance(ooo));		
	}
}
