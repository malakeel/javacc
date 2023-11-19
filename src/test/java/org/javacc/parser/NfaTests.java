package org.javacc.parser;

import junit.framework.TestCase;

public class NfaTests extends TestCase {

	Nfa nfa;

	public void test() {

		nfa = new Nfa();

//		nfa
		System.out.println(nfa.start.stateName);

		System.out.println("exiting");
	}

}
