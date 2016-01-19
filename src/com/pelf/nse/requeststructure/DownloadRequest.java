package com.pelf.nse.requeststructure;

import java.io.UnsupportedEncodingException;

import com.pelf.server.util.Utils;

public class DownloadRequest {
	byte[] struct;
	MessageHeader msgHeader;	//4
	long seqNumber;	//8
	
	public DownloadRequest() {
		msgHeader = new MessageHeader((short)7000, (short)48);
		msgHeader.setAlphaChar("  ");
		struct = new byte[48];
		System.arraycopy(msgHeader, 0, this.struct, 0, 40);
	}
	
	public byte[] getstruct() throws UnsupportedEncodingException {
		Utils.setLong(seqNumber, struct, 40);
		return struct;
	}
}
