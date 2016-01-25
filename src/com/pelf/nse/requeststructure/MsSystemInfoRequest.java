package com.pelf.nse.requeststructure;

import com.pelf.server.util.Utils;

import java.io.UnsupportedEncodingException;

/**
 * Created by vaibhav on 1/18/16.
 */
public class MsSystemInfoRequest {

    byte[] struct;

    byte[] messageHeader;                   //40 bytes
    int lastUpdatePortfolioTime;            //4

    public MsSystemInfoRequest(byte[] messageHeader)throws UnsupportedEncodingException{
        this.messageHeader = new byte[40];
        System.arraycopy(messageHeader , 0 , this.messageHeader , 0 , 40);
        this.lastUpdatePortfolioTime = 0;

        this.struct = new byte[44];
    }

    public MsSystemInfoRequest(short transactionCode , short messageLen , int traderId) throws UnsupportedEncodingException {
        this(new MessageHeader(transactionCode , messageLen , traderId).getStruct());
    }

    public byte[] getStruct() throws  UnsupportedEncodingException{
        System.arraycopy(this.messageHeader , 0 , this.struct , 0 , 40);
        Utils.setInt(this.lastUpdatePortfolioTime , struct , 40);

        return  struct;
    }
}
