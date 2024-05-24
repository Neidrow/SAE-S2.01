/**
 * TestPiece.java 					24/05/24
 * iut de Rodez, pas de Copyright
 */
package test;

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
		assertThrows(IllegalArgumentException.class, ()-> new Piece(5, 0, false, null));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(5, 0, false, "fze"));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(5, 0, false, "85ax"));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(5, 0, false, "noir2"));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(5, 1, false, "noir"));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(9, 9, false, "noir"));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(0, 0, false, "blanc"));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(59, 0, false, "noir"));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(4, 19, false, "noir"));
		assertThrows(IllegalArgumentException.class, ()-> new Piece(45, 18, false, "noir"));
		assertDoesNotThrow(()-> new Piece(1, 0, false, "noir"));
	}

	/**
	 * Test method for {@link vue.Piece#getX()}.
	 */
	@Test
	void testGetX() {
		assertEquals(4, new Piece(4, 1, false, "noir").getX());
		assertEquals(0, new Piece(0, 9, false, "noir").getX());
		assertEquals(9, new Piece(9, 0, false, "noir").getX());
	}

	/**
	 * Test method for {@link vue.Piece#setX(int)}.
	 */
	@Test
	void testSetX() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link vue.Piece#getY()}.
	 */
	@Test
	void testGetY() {
		fail("Not yet implemented");
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
