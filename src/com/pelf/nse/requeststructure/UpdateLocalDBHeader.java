package com.pelf.nse.requeststructure;

import com.pelf.server.util.Utils;

public class UpdateLocalDBHeader {
	byte[] struct;	//42+22 bytes
	String reserved;
	
	public UpdateLocalDBHeader(byte[] struct) {
		this.struct = struct;
	}
	
	public int getLength() {
		return struct.length;	//Return message length in bytes
	}
	
	public String getReserved() throws Exception {
		reserved = Utils.getString(struct, 62, 2);
		return reserved;
	}
}
