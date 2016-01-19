package com.pelf.nse.requeststructure;

import java.io.UnsupportedEncodingException;

import com.pelf.server.util.Utils;

public class UpdateLocalDBIn {
	byte[] struct;					//82 bytes
	int lastUpdateSecurityTime;		//4 bytes
	int lastUpdateParticipantTime;	//4 bytes
	int lastUpdateInstrumentTime;	//4bytes
	int lastUpdateIndexTime;		//4 bytes
	String reqForOpenOrders;		//1 byte
	String reserved;				//1 byte
	byte[] stMarketStatus = new byte[8];	//8 bytes
	byte[] stExMarketStatus = new byte[8];	//8 bytes
	byte[] stPlMarketStatus = new byte[8];	//8 bytes
	
	public UpdateLocalDBIn(short transactionCode) throws UnsupportedEncodingException {
		this.lastUpdateSecurityTime = 0;
		this.lastUpdateParticipantTime = 0;
		this.lastUpdateInstrumentTime = 0;
		this.lastUpdateIndexTime = 0;
		this.reqForOpenOrders = "G";
		this.reserved = " ";
		byte[] gmheaderbyte = new MessageHeader(transactionCode,(short)82).getStruct();
		struct = new byte[82];
		System.arraycopy(gmheaderbyte, 0, this.struct, 0, 40);
	}
	
	public byte[] getStruct() throws UnsupportedEncodingException {
		Utils.setInt(lastUpdateSecurityTime, struct, 40);
		Utils.setInt(lastUpdateParticipantTime, struct, 44);
		Utils.setInt(lastUpdateInstrumentTime, struct, 48);
		Utils.setInt(lastUpdateIndexTime, struct, 52);
		Utils.setString(reqForOpenOrders, 1, struct, 56);
		Utils.setString(reserved, 1, struct, 57);
		System.arraycopy(this.stMarketStatus, 0, struct, 58, 8);
		System.arraycopy(this.stExMarketStatus, 0, struct, 66, 8);
		System.arraycopy(this.stPlMarketStatus, 0, struct, 74, 8);
		return struct;
	}
}
