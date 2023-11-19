package org.javacc.parser;

import java.util.LinkedList;
import java.util.List;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/*
 * 
 * Focus on testing public instance methods for NfaState using only ASCII
 * 
 * 
 */
public class NfaState_ascii_Test extends TestCase {

	private NfaState state;

	public void setUp() throws Exception {
		System.out.println("creating new NfaState");
		this.state = new NfaState();
	}

	public void tearDown() throws Exception {
		// clean up not applicable ATM
	}

	// instance methods first

	/**
	 * Assuming AddChar is working properly, since it has no return, we can not
	 * directly test it. Therefore, this test covers {@link NfaState#AddChar(char)}
	 * implicitly.
	 * 
	 */
	public void testCanMove_with_addChar() {

		// these characters will allow to move to next state
		this.state.AddChar('a');
		this.state.AddChar('b');
		this.state.AddChar('c');

		boolean canMove;

		canMove = this.state.CanMoveUsingChar('b');
		assertTrue("can move using 'b'", canMove);

		canMove = this.state.CanMoveUsingChar('d');
		assertFalse("can move using 'd'", canMove);

		// add 'd', and check
		this.state.AddChar('d');
		canMove = this.state.CanMoveUsingChar('d');
		// we should be able to move (true)
		assertTrue("can move using 'd'", canMove);

	}

	/*
	 * Check an added char, can be found with
	 */
	public void testGetFirstValidPos_with_addChar() {

		this.state.AddChar('a');
		this.state.AddChar('b');
		this.state.AddChar('c');

		String input;
		/* The position for the first occurrence of a character that will be accepted */
		int validPos;
		int expected;

		input = "letter a should be found in this string";
		validPos = this.state.getFirstValidPos(input, 0, input.length());
		expected = 7;
		assertEquals(expected, validPos);

		// this should match the char 'b' in "be found in this string"
		validPos = this.state.getFirstValidPos(input, 8, input.length());
		expected = 16;

		assertEquals(input, expected, validPos);

		System.out.println(input.substring(validPos));

		input = "no letter";
		validPos = this.state.getFirstValidPos(input, 0, 7);

		expected = 0;
		assertEquals(input, expected, validPos);
	}

	public void testAddRange() {
		this.state.AddRange('a', 'c');

		boolean canMove;

		canMove = this.state.CanMoveUsingChar('a');
		assertTrue("must be able to move using 'a'", canMove);

		canMove = this.state.CanMoveUsingChar('c');
		assertTrue("must be able to move using 'c'", canMove);

		canMove = this.state.CanMoveUsingChar('d');
		assertFalse("must NOT be able to move using 'd'", canMove);

		this.state.AddRange('d', 'd');
		canMove = this.state.CanMoveUsingChar('d');
		assertTrue("must be able to move using 'd'", canMove);

	}

	public void testHasTransitions() {

		boolean hasTransitions = this.state.HasTransitions();

//		this.state.kind
		fail("Not yet implemented"); // TODO
	}

	public void testMoveFrom() {
		fail("Not yet implemented"); // TODO
	}

	public void testSetNextState() {
		fail("Not yet implemented"); // TODO
	}

//	AddChar(char)
//	AddRange(char, char)
//	getFirstValidPos(String, int, int)
//	MoveFrom(char, List)
//	MergeMoves
//	setNextState(NfaState)
//	AddMove(state);
//	CanMoveUsingChar( )
//	HasTransitions()

	public void testCreateClone() {

		this.state.AddRange('a', 'c');
		this.state.AddRange('x', 'z');

		this.state.isFinal = true; // we are using non-default value

		this.state.kind = 1;

		NfaState clone = this.state.CreateClone();

		assertEquals(this.state.kind, clone.kind);
		assertEquals(this.state.inNextOf, clone.inNextOf);

		assertEquals(this.state.epsilonMoves, clone.epsilonMoves);

		assertEquals(this.state.charMoves, clone.charMoves);

		// The logic in MergeMoves does not populate asciiMoves !
		// confirm this is not a bug
		// assertEquals(this.state.asciiMoves, clone.asciiMoves);

		// there's no way to test "rangeMoves" since it is private. We need getter to be
		// able to test
		// assertEquals(this.state.rangeMoves, this.state.rangeMoves);

	}

}
