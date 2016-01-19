package com.pelf.nse.requeststructure;

import java.io.UnsupportedEncodingException;

import com.pelf.server.util.Utils;



public class MessageHeader {

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

	public short getTransactionCode() {
		return transactionCode;
	}

	public int getLogTime() {
		return logTime;
	}

	public String getAlphaChar() {
		return alphaChar;
	}

	public int getTraderId() {
		return traderId;
	}

	public short getErrorCode() {
		return errorCode;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public String getTimeStamp1() {
		return timeStamp1;
	}

	public String getTimeStamp2() {
		return timeStamp2;
	}

	public short getMessageLen() {
		return messageLen;
	}

	public void setAlphaChar(String alphaChar) {
		this.alphaChar = alphaChar;
	}
	
	public MessageHeader(short transactionCode) {
		this(transactionCode, (short)48);
		/*this.struct = new byte[40];
		this.transactionCode = transactionCode;
		this.traderId = 7814;
		this.logTime = 0;
		this.alphaChar = "";
		this.errorCode = 0;
		this.timeStamp = "0";
		this.timeStamp1 = "0";
		this.timeStamp2 = "0";
		this.messageLen = 48;*/
	}
	
	public MessageHeader(short transactionCode, short messageLength) {
		this(transactionCode, messageLength, 7814);
		/*this.struct = new byte[40];
		this.transactionCode = transactionCode;
		this.traderId = 7814;
		this.logTime = 0;
		this.alphaChar = "";
		this.errorCode = 0;
		this.timeStamp = "0";
		this.timeStamp1 = "0";
		this.timeStamp2 = "0";
		this.messageLen = messageLength;*/
	}
	
	public MessageHeader(short transactionCode, short messageLength, int traderId) {
		this.struct = new byte[40];
		this.transactionCode = transactionCode;
		this.traderId = traderId;
		this.logTime = 0;
		this.alphaChar = "";
		this.errorCode = 0;
		this.timeStamp = "0";
		this.timeStamp1 = "0";
		this.timeStamp2 = "0";
		this.messageLen = messageLength;
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
	

	public MessageHeader(byte[] struct) throws Exception {
		transactionCode = Utils.getInt16(struct, 0);
		logTime = Utils.getInt32(struct, 2);
		alphaChar = Utils.getString(struct, 6, 2);
		traderId = Utils.getInt32(struct, 8);
		errorCode = Utils.getInt16(struct, 12);
		timeStamp = Utils.getString(struct, 14, 8);
		timeStamp1 = Utils.getString(struct, 22, 8);
		timeStamp2 = Utils.getString(struct, 30, 8);
		messageLen = Utils.getInt16(struct, 38);
	}

}
