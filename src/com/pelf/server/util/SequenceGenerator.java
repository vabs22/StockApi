package com.pelf.server.util;

public class SequenceGenerator {
	
	private static int globalSeqNum = 1;

	
	/**
	 * Returns a globally unique long integer.
	 */
	public synchronized static int nextGloballyUniqueInt() {
		return globalSeqNum++;
	}
	

}
