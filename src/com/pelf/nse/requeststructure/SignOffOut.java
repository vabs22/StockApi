package com.pelf.nse.requeststructure;

import com.pelf.server.util.Utils;

public class SignOffOut {
	MessageHeader messageHeader;	//40
	int userId;						//4
	String reserved;				//145

	public MessageHeader getMessageHeader() {
		return messageHeader;
	}

	public int getUserId() {
		return userId;
	}

	public String getReserved() {
		return reserved;
	}	
	
	public SignOffOut(byte[] struct) throws Exception {
		byte temp[] = new byte[40];
		System.arraycopy(struct, 22, temp, 0, 40);
		messageHeader = new MessageHeader(temp);
		reserved = Utils.getString(struct, 66, 145);
		userId = Utils.getInt32(struct, 62);
	}

}
