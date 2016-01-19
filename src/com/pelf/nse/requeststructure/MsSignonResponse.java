package com.pelf.nse.requeststructure;

import com.pelf.server.util.Utils;
import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

/**
 * Created by vaibhav on 1/18/16.
 */
public class MsSignonResponse {

    byte[] struct;

    MessageHeader messageHeader;            //40 bytes
    int userId;                             //4
    String password;                        //8
    String newPassword;                     //8
    String traderName;                      //26
    int lastPasswordChangeDate;             //4
    String brokerId;                        //5
    String reserved95;                      //1
    Short branchId;                         //2
    int versionNumber;                      //4
    int endTime;                            //4
    String reserved106;                      //1
    String colour;                          //50
    String reserved157;                     //1
    Short userType;                         //2
    long sequenceNumber;                    //8
    String reserved168;                     //1
    String wsClassName;                     //14
    String brokerStatus;                    //1
    String showIndex;                       //1
    byte[] stBrokerEligibilityPerMkt;       //2
    Short memberType;                       //2
    String clearingStatus;                  //1
    String brokerName;                      //25


    public MsSignonResponse(byte[] struct) throws Exception{
        byte[] structmessageHeader = new byte[40];

        System.arraycopy(struct , 22 , this.struct , 0 , 214);
        System.arraycopy(this.struct , 0 , structmessageHeader , 0 , 40);
        this.messageHeader = new MessageHeader(structmessageHeader);
        this.userId = Utils.getInt32(this.struct , 40);
        this.password = Utils.getString(this.struct , 44 , 8);
        this.newPassword = Utils.getString(this.struct , 52 , 8);;
        this.traderName = Utils.getString(this.struct , 60 , 26);;
        this.lastPasswordChangeDate = Utils.getInt32(this.struct , 86);
        this.brokerId = Utils.getString(this.struct , 90 , 5);;
        this.reserved95 = Utils.getString(this.struct , 95 , 1);;
        this.branchId = Utils.getInt16(this.struct , 96);
        this.versionNumber = Utils.getInt32(this.struct , 98);
        this.endTime = Utils.getInt32(this.struct , 102);
        this.reserved106 = Utils.getString(this.struct , 106 , 1);;
        this.colour = Utils.getString(this.struct , 107 , 50);;
        this.reserved157 = Utils.getString(this.struct , 157 , 1);;
        this.userType = Utils.getInt16(this.struct , 158);
        this.sequenceNumber = Utils.getLong(this.struct , 160);
        this.reserved168 = Utils.getString(this.struct , 168 , 14);;
        this.brokerStatus = Utils.getString(this.struct , 182 , 1);;
        this.showIndex = Utils.getString(this.struct , 183 , 1);;
        System.arraycopy(this.struct , 184 , this.stBrokerEligibilityPerMkt , 0 , 2);
        this.memberType = Utils.getInt16(this.struct , 186);
        this.clearingStatus = Utils.getString(this.struct , 188 , 1);;
        this.brokerName = Utils.getString(this.struct , 189 , 25);;
    }

    public String getBrokerId() {
        return brokerId;
    }

    public String getReserved95() {
        return reserved95;
    }

    public Short getBranchId() {
        return branchId;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public int getEndTime() {
        return endTime;
    }

    public String getReserved106() {
        return reserved106;
    }

    public String getColour() {
        return colour;
    }

    public String getReserved157() {
        return reserved157;
    }

    public Short getUserType() {
        return userType;
    }

    public long getSequenceNumber() {
        return sequenceNumber;
    }

    public String getReserved168() {
        return reserved168;
    }

    public String getWsClassName() {
        return wsClassName;
    }

    public String getBrokerStatus() {
        return brokerStatus;
    }

    public String getShowIndex() {
        return showIndex;
    }

    public MessageHeader getMessageHeader() {
        return messageHeader;
    }

    public int getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getTraderName() {
        return traderName;
    }

    public int getLastPasswordChangeDate() {
        return lastPasswordChangeDate;
    }

    public byte[] getStBrokerEligibilityPerMkt() {
        return stBrokerEligibilityPerMkt;
    }

    public Short getMemberType() {
        return memberType;
    }

    public String getClearingStatus() {
        return clearingStatus;
    }

    public String getBrokerName() {
        return brokerName;
    }
}
