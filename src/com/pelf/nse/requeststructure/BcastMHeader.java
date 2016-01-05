package com.pelf.nse.requeststructure;

public class BcastMHeader {

	String reserved1; // 2 bytes
	String reserved2; // 2 bytes
	int logTime; // 4 bytes
	String alphaChar; // 2 bytes
	short transCode; // 2 bytes
	short errorCode; // 2 bytes
	int bcSeqNo; // 4 bytes
	String reserved3; // 1 bytes
	String reserved4; // 3 bytes
	String timestamp2; // 8 bytes
	byte[] filler2; // 8 bytes
	short messageLen; // 2 bytes
}
