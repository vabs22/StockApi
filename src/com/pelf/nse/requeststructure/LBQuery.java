package com.pelf.nse.requeststructure;

import java.io.UnsupportedEncodingException;

import com.pelf.server.util.Utils;

public class LBQuery {
	
	byte[] struct;
	String memberId;
	String filler;
	short reserved;
	
	
	public LBQuery(short transactionCode) throws UnsupportedEncodingException {
		this.memberId = "11456";
		this.filler = "";
		this.reserved = 0;
		byte[] gmheaderbyte = new GMHeader(transactionCode).getStruct();
		struct = new byte[48];
		System.arraycopy(gmheaderbyte, 0, this.struct, 0, 40);
	}
	
	public byte[] getStruct() throws UnsupportedEncodingException {
		Utils.setString(memberId, 5, struct, 40);
		Utils.setString(filler, 1, struct, 45);
		Utils.setShort(reserved, struct, 46);
		
		return struct;
	}

}
