package com.pelf.nse.requeststructure;

import com.pelf.server.util.Utils;

import java.io.UnsupportedEncodingException;

/**
 * Created by vaibhav on 1/18/16.
 */
public class MsSystemInfoData {

    byte[] struct;

    MessageHeader messageHeader;                        //40 bytes
    StMarketStatus stMarketStatus;                      //8
    StMarketStatus stExMarketStatus;                    //8
    StMarketStatus stPlMarketStatus;                    //8
    String updatePortfolio;                             //1
    int marketIndex;                                    //4
    short defaultSettlementPeriod_normal;               //2
    short defaultSettlementPeriod_spot;                 //2
    short defaultSettlementPeriod_auction;              //2
    short competitorPeriod;                             //2
    short solicitorPeriod;                              //2
    short warningPercent;                               //2
    short volumeFreezePercent;                          //2
    short snapQuoteTime;                                //2
    String reserved;                                    //2
    int boardLotQuantity;                               //4
    int tickSize;                                       //4
    short maximumGtcDays;                               //2
    byte[] stStockEligibleIndicators;                   // to be done
    short disclosedQuanitityPercentAllowed;             //2
    int riskFreeInterestRate;                           //4

    public MsSystemInfoData(byte[] struct) throws Exception {
        byte[] structmessageHeader = new byte[40];
        byte[] structstExMarketStatus = new byte[8];

        this.struct = new byte[106];

        System.arraycopy(struct , 22 , this.struct , 0 , 106);
        System.arraycopy(this.struct , 0 , structmessageHeader , 0 , 40);
        this.messageHeader = new MessageHeader(structmessageHeader);

        System.arraycopy(this.struct , 40 , structstExMarketStatus , 0 , 8);
        this.stMarketStatus = new StMarketStatus(structstExMarketStatus);

        System.arraycopy(this.struct , 48 , structstExMarketStatus , 0 , 8);
        this.stExMarketStatus = new StMarketStatus(structstExMarketStatus);

        System.arraycopy(this.struct , 56 , structstExMarketStatus , 0 , 8);
        this.stPlMarketStatus = new StMarketStatus(structstExMarketStatus);

        this.updatePortfolio = Utils.getString(this.struct , 64 , 1);
        this.marketIndex = Utils.getInt32(this.struct , 65);
        this.defaultSettlementPeriod_normal = Utils.getInt16(this.struct , 69);
        this.defaultSettlementPeriod_spot = Utils.getInt16(this.struct , 71);
        this.defaultSettlementPeriod_auction = Utils.getInt16(this.struct , 73);
        this.competitorPeriod = Utils.getInt16(this.struct , 75);
        this.solicitorPeriod = Utils.getInt16(this.struct , 77);
        this.warningPercent =   Utils.getInt16(this.struct , 79);
        this.volumeFreezePercent = Utils.getInt16(this.struct , 81);
        this.snapQuoteTime = Utils.getInt16(this.struct , 83);
        this.reserved = Utils.getString(this.struct , 85 , 1);
        this.boardLotQuantity = Utils.getInt32(this.struct , 87);
        this.tickSize = Utils.getInt32(this.struct , 91);
        this.maximumGtcDays =  Utils.getInt16(this.struct , 95);
        System.arraycopy(this.struct , 97 , this.stStockEligibleIndicators , 0 , 2);
        this.disclosedQuanitityPercentAllowed = Utils.getInt16(this.struct , 99);
        this.riskFreeInterestRate = Utils.getInt32(this.struct , 101);

    }

    public MessageHeader getmessageHeader(){
        return messageHeader;
    }
    public StMarketStatus getStMarketStatus() {
        return stMarketStatus;
    }

    public StMarketStatus getStExMarketStatus() {
        return stExMarketStatus;
    }

    public StMarketStatus getStPlMarketStatus() {
        return stPlMarketStatus;
    }

    public String getUpdatePortfolio() {
        return updatePortfolio;
    }

    public int getMarketIndex() {
        return marketIndex;
    }

    public short getDefaultSettlementPeriod_spot() {
        return defaultSettlementPeriod_spot;
    }

    public short getDefaultSettlementPeriod_auction() {
        return defaultSettlementPeriod_auction;
    }

    public short getCompetitorPeriod() {
        return competitorPeriod;
    }

    public short getSolicitorPeriod() {
        return solicitorPeriod;
    }

    public short getWarningPercent() {
        return warningPercent;
    }

    public short getVolumeFreezePercent() {
        return volumeFreezePercent;
    }

    public short getSnapQuoteTime() {
        return snapQuoteTime;
    }

    public String getReserved() {
        return reserved;
    }

    public int getBoardLotQuantity() {
        return boardLotQuantity;
    }

    public int getTickSize() {
        return tickSize;
    }

    public short getMaximumGtcDays() {
        return maximumGtcDays;
    }

    public byte[] getStStockEligibleIndicators() {
        return stStockEligibleIndicators;
    }

    public short getDisclosedQuanitityPercentAllowed() {
        return disclosedQuanitityPercentAllowed;
    }

    public int getRiskFreeInterestRate() {
        return riskFreeInterestRate;
    }


}
