package com.pelf.nse.requeststructure;

import com.pelf.server.util.Utils;

public class InnerMessageHeader {
	byte[] struct;	//40
	int traderId;	//4
	int logTime;	//4
	String alphaChar;	//2
	short transactionCode;	//2
	short errorCode;		//2
	String timestamp;		//8
	String timestamp1;		//8
	String timestamp2;		//8
	short messageLength;		//2
	
	public InnerMessageHeader(byte[] struct) throws Exception {
		this.struct = struct;
		this.traderId = Utils.getInt32(struct, 0);
		this.logTime = Utils.getInt32(struct, 4);
		this.alphaChar = Utils.getString(struct, 8, 2);
		this.transactionCode = Utils.getInt16(struct, 10);
		this.errorCode = Utils.getInt16(struct, 12);
		this.timestamp = Utils.getString(struct, 14, 8);
		this.timestamp1 = Utils.getString(struct, 22, 8);
		this.timestamp2 = Utils.getString(struct, 30, 8);
		this.messageLength = Utils.getInt16(struct, 38);
	}

	public byte[] getStruct() {
		return struct;
	}

	public int getTraderId() {
		return traderId;
	}

	public int getLogTime() {
		return logTime;
	}

	public String getAlphaChar() {
		return alphaChar;
	}

	public short getTransactionCode() {
		return transactionCode;
	}

	public short getErrorCode() {
		return errorCode;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public String getTimestamp1() {
		return timestamp1;
	}

	public String getTimestamp2() {
		return timestamp2;
	}

	public short getMessageLength() {
		return messageLength;
	}
}
