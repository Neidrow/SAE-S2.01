/**
 * TestPiece.java 					24/05/24
 * iut de Rodez, pas de Copyright
 */
package test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modele.Piece;

/**
 * TODO commenter
 * @author Groupe 41
 */
class PieceTest {

	/**
	 * Test method for {@link modele.Piece#Piece(int, int, boolean, java.lang.String)}.
	 */
	@Test
	void testPiece() {
		assertThrows(IllegalArgumentException.class,
				()-> new Piece(5, 0, true, "fze"));
		assertThrows(IllegalArgumentException.class,
				()-> new Piece(5, 0, false, "85ax"));
		assertThrows(IllegalArgumentException.class,
				()-> new Piece(5, 0, true, "noir2"));
		assertThrows(IllegalArgumentException.class,
				()-> new Piece(5, 1, false, "noir"));
		assertThrows(IllegalArgumentException.class,
				()-> new Piece(9, 9, true, "noir"));
		assertThrows(IllegalArgumentException.class,
				()-> new Piece(0, 0, true, "blanc"));
		assertThrows(IllegalArgumentException.class,
				()-> new Piece(59, 0, false, "noir"));
		assertThrows(IllegalArgumentException.class,
				()-> new Piece(4, 19, false, "noir"));
		assertThrows(IllegalArgumentException.class,
				()-> new Piece(45, 18, true, "noir"));
		assertThrows(IllegalArgumentException.class,
				()-> new Piece(8, -1, true, "blanc"));
		assertThrows(IllegalArgumentException.class,
				()-> new Piece(-1, 8, true, "noir"));
		assertThrows(IllegalArgumentException.class,
				()-> new Piece(5, -15, true, "noir"));
		assertThrows(IllegalArgumentException.class,
				()-> new Piece(-15, 1, true, "noir"));
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
	 * Test method for {@link modele.Piece#getX()}.
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
	 * Test method for {@link modele.Piece#setX(int)}.
	 */
	@Test
	void testSetX() {
		assertThrows(IllegalArgumentException.class,
				()-> new Piece(4, 5, true, "noir").setX(5));
		assertThrows(IllegalArgumentException.class,
				()-> new Piece(4, 5, false, "noir").setX(10));
		assertThrows(IllegalArgumentException.class,
				()-> new Piece(4, 5, false, "noir").setX(-1));
		assertThrows(IllegalArgumentException.class,
				()-> new Piece(4, 5, false, "noir").setX(-2));
		assertDoesNotThrow(()-> new Piece(4, 5, false, "noir").setX(2));
		assertDoesNotThrow(()-> new Piece(4, 5, true, "noir").setX(0));
		assertDoesNotThrow(()-> new Piece(5, 4, true, "noir").setX(9));
	}

	/**
	 * Test method for {@link modele.Piece#getY()}.
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
	 * Test method for {@link modele.Piece#setY(int)}.
	 */
	@Test
	void testSetY() {
		assertThrows(IllegalArgumentException.class,
				()-> new Piece(5, 8, true, "noir").setY(7));
		assertThrows(IllegalArgumentException.class,
				()-> new Piece(3, 0, false, "noir").setY(10));
		assertThrows(IllegalArgumentException.class,
				()-> new Piece(1, 2, false, "noir").setY(-1));
		assertThrows(IllegalArgumentException.class,
				()-> new Piece(7, 4, false, "noir").setY(-4));
		assertDoesNotThrow(()-> new Piece(5, 2, false, "noir").setY(4));
		assertDoesNotThrow(()-> new Piece(9, 6, true, "noir").setY(0));
		assertDoesNotThrow(()-> new Piece(4, 5, true, "noir").setY(9));
	}

	/**
	 * Test method for {@link modele.Piece#isDame()}.
	 */
	@Test
	void testIsDame() {
		assertEquals(false, new Piece(4, 5, false, "noir").isDame());
		assertEquals(true, new Piece(0, 9, true, "noir").isDame());
	}

	/**
	 * Test method for {@link modele.Piece#setDame(boolean)}.
	 */
	@Test
	void testSetDame() {
		assertDoesNotThrow(()-> new Piece(5, 2, false, "noir").setDame(true));
		assertDoesNotThrow(()-> new Piece(5, 2, false, "noir").setDame(false));
		assertDoesNotThrow(()-> new Piece(5, 2, true, "noir").setDame(true));
		assertDoesNotThrow(()-> new Piece(5, 2, true, "noir").setDame(false));
	}

}