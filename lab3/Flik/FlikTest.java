import static org.junit.Assert.*;

import org.junit.Test;

public class FlikTest {

	@Test
	public void testFlik() {
		boolean f = Flik.isSameNumber(128, 128);
		System.out.print(f);
		boolean g = (500 == 500);
		System.out.print(g);
		assertTrue(f);
	}
}