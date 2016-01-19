package com.pelf.nse.requeststructure;

public class UpdateLocalDBData {
	byte[] struct;		//80 to 512 bytes
	MessageHeader messageHeader;	//40 bytes
	InnerMessageHeader innerHeader;		//40 bytes
	byte[] data;			//0 to 436 bytes
	
	public byte[] getStruct() {
		return struct;
	}

	public MessageHeader getMessageHeader() {
		return messageHeader;
	}

	public InnerMessageHeader getInnerHeader() {
		return innerHeader;
	}

	public byte[] getData() {
		return data;
	}
		
	public UpdateLocalDBData(byte[] struct) throws Exception {
		this.struct = struct;
		byte[] temp1 = new byte[40];
		System.arraycopy(this.struct, 22, temp1, 0, 40);
		messageHeader = new MessageHeader(temp1);
		byte[] temp2 = new byte[40];
		System.arraycopy(this.struct, 62, temp2, 0, 40);
		innerHeader = new InnerMessageHeader(temp2);
		data = new byte[struct.length - 102];
		System.arraycopy(struct, 102, data, 0, data.length);
	}
}
