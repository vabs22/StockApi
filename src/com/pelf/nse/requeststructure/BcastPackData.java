package com.pelf.nse.requeststructure;

import java.io.UnsupportedEncodingException;

public class BcastPackData {
	
	String cNetId;
	short iNoPackets;
	short iCompLen;
	byte[] broadcastStruct;
	
	public byte[] getBroadCastStruct() throws UnsupportedEncodingException{
        return broadcastStruct;
    }

}
