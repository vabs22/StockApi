package com.pelf.nse.requeststructure;

import java.io.UnsupportedEncodingException;

import com.pelf.server.util.Utils;



public class GMHeader {

	short transactionCode;
	int logTime;
	String alphaChar; //2 bytes
	int traderId;
	short errorCode;
	String timeStamp; //8 bytes
	String timeStamp1; //8 bytes
	String timeStamp2; //8 bytes
	short messageLen; 
	
	byte[] struct;
	
	public GMHeader(short transactionCode) {
		this.struct = new byte[40];
		this.transactionCode = transactionCode;
		this.traderId = 7814;
		this.logTime = 0;
		this.alphaChar = "";
		this.errorCode = 0;
		this.timeStamp = "0";
		this.timeStamp1 = "0";
		this.timeStamp2 = "0";
		this.messageLen = 48;
	}
	
	public byte[] getStruct() throws UnsupportedEncodingException{
		Utils.setShort(transactionCode, struct, 0);
		Utils.setInt(logTime, struct, 2);
		Utils.setString(alphaChar, 2, struct, 6);
		Utils.setInt(traderId, struct, 8);
		Utils.setShort(errorCode, struct, 12);
		Utils.setString(timeStamp, 8, struct, 14);
		Utils.setString(timeStamp1, 8, struct, 22);
		Utils.setString(timeStamp2, 8, struct, 30);
		Utils.setShort(messageLen, struct, 38);
		
		return struct;
	}
}
