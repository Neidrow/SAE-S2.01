/**
 * TestPiece.java 					24/05/24
 * iut de Rodez, pas de Copyright
 */
package test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import vue.Piece;

/**
 * TODO commenter
 * @author Adrien Vigué
 */
class TestPiece {

	/**
	 * Test method for {@link vue.Piece#Piece(int, int, boolean, java.lang.String)}.
	 */
	@Test
	void testPiece() {
		assertThrows(IllegalArgumentException.class, ()-> new Piece(5, 0, true, null));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(5, 0, true, "fze"));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(5, 0, false, "85ax"));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(5, 0, true, "noir2"));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(5, 1, false, "noir"));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(9, 9, true, "noir"));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(0, 0, true, "blanc"));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(59, 0, false, "noir"));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(4, 19, false, "noir"));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(45, 18, true, "noir"));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(-1, 0, true, "noir"));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(5, -15, true, "noir"));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(-45, -18, true, "noir"));
		assertDoesNotThrow(()-> new Piece(1, 0, false, "noir"));
		assertDoesNotThrow(()-> new Piece(0, 1, true, "noir"));
		assertDoesNotThrow(()-> new Piece(9, 8, false, "noir"));
		assertDoesNotThrow(()-> new Piece(8, 9, false, "noir"));
		assertDoesNotThrow(()-> new Piece(9, 0, true, "noir"));
		assertDoesNotThrow(()-> new Piece(0, 9, true, "noir"));
		assertDoesNotThrow(()-> new Piece(8, 1, false, "noir"));
		assertDoesNotThrow(()-> new Piece(1, 8, true, "noir"));
	}

	/**
	 * Test method for {@link vue.Piece#getX()}.
	 */
	@Test
	void testGetX() {
		assertEquals(4, new Piece(4, 5, false, "noir").getX());
		assertEquals(0, new Piece(0, 9, true, "noir").getX());
		assertEquals(9, new Piece(9, 0, true, "noir").getX());
		assertNotEquals(5, new Piece(4, 5, true, "noir").getX());
		assertNotEquals(9, new Piece(0, 9, false, "noir").getX());
		assertNotEquals(0, new Piece(9, 0, true, "noir").getX());
	}

	/**
	 * Test method for {@link vue.Piece#setX(int)}.
	 */
	@Test
	void testSetX() {
		assertThrows(IllegalArgumentException.class, ()-> new Piece(4, 5, true, "noir").setX(5));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(4, 5, false, "noir").setX(10));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(4, 5, false, "noir").setX(-1));
		assertDoesNotThrow(()-> new Piece(4, 5, false, "noir").setX(2));
		assertDoesNotThrow(()-> new Piece(4, 5, true, "noir").setX(0));
		assertDoesNotThrow(()-> new Piece(5, 4, true, "noir").setX(9));
	}

	/**
	 * Test method for {@link vue.Piece#getY()}.
	 */
	@Test
	void testGetY() {
		assertEquals(5, new Piece(4, 5, false, "noir").getY());
		assertEquals(9, new Piece(0, 9, false, "noir").getY());
		assertEquals(0, new Piece(9, 0, false, "noir").getY());
		assertNotEquals(4, new Piece(4, 5, false, "noir").getY());
		assertNotEquals(0, new Piece(0, 9, false, "noir").getY());
		assertNotEquals(9, new Piece(9, 0, false, "noir").getY());
	}

	/**
	 * Test method for {@link vue.Piece#setY(int)}.
	 */
	@Test
	void testSetY() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link vue.Piece#isDame()}.
	 */
	@Test
	void testIsDame() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link vue.Piece#setDame(boolean)}.
	 */
	@Test
	void testSetDame() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link vue.Piece#getProprietaire()}.
	 */
	@Test
	void testGetProprietaire() {
		fail("Not yet implemented");
	}

}
