package com.pelf.nse.requeststructure;

import java.security.MessageDigest;

import com.pelf.server.util.SequenceGenerator;
import com.pelf.server.util.Utils;

public class NetworkHeader {
	
	byte[] struct;
	short length;
	int sequence;
	byte[] checksum;
	
	
	public NetworkHeader(byte[] messageData) throws Exception {
		this.length = (short) (messageData.length + 22);
		this.sequence = SequenceGenerator.nextGloballyUniqueInt();
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(messageData);
		this.checksum = md.digest();
		struct = new byte[length];
		Utils.setShort(length, struct, 0);
		Utils.setInt(sequence, struct, 2);
		System.arraycopy(checksum, 0, this.struct, 6, 16);
		System.arraycopy(messageData, 0, this.struct, 22, messageData.length);

	}
	
	public byte[] getStruct() {
		return struct;
	}

}
