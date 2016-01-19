package com.pelf.nse.requeststructure;

import com.pelf.server.util.Utils;

/**
 * Created by vaibhav on 1/18/16.
 */
public class StMarketStatus {
    byte[] struct;

    short normal;                       //2 bytes
    short oddlot;                       //2
    short spot;                         //2
    short auction;                      //2

    public StMarketStatus(byte[] struct){
        this.struct = new byte[8];
        System.arraycopy(struct , 0 , this.struct , 0 , 8 );

        this.normal = Utils.getInt16(this.struct , 0);
        this.oddlot = Utils.getInt16(this.struct , 2);
        this.spot = Utils.getInt16(this.struct , 4);
        this.auction = Utils.getInt16(this.struct , 6);
    }

    public short getNormal() {
        return normal;
    }

    public short getOddlot() {
        return oddlot;
    }

    public short getSpot() {
        return spot;
    }

    public short getAuction() {
        return auction;
    }

}
