package com.pelf.nse.requeststructure;

public class GInnerMHeader {

	int traderId;
	int logTime;
	String alphaChar; //2 bytes
	short transactionCode;
	short errorCode;
	String timeStamp; //8 bytes
	String timeStamp1; //8 bytes
	String timeStamp2; //8 bytes
	short messageLen; 
	
}
