
package com.pelf.nse.requeststructure;
import java.io.UnsupportedEncodingException;
import com.pelf.server.util.Utils;

public class MsSignonRequest {

    byte[] struct;

    byte[] messageHeader;                   //40 bytes
    int userId;                             //4
    String password;                        //8
    String newPassword;                     //8
    String traderName;                      //26
    int lastPasswordChangeDate;             //4
    String brokerId;                        //5
    String reserved95;                      //1
    Short branchId;                         //2
    int versionNumber;                      //4
    int batch2StartTime;                    //4
    String hostSwitchContext;               //1
    String colour;                          //50
    String reserved157;                     //1
    Short userType;                         //2
    long sequenceNumber;                    //8
    String wsClassName;                     //14
    String brokerStatus;                    //1
    String showIndex;                       //1
    byte[] stBrokerEligibilityPerMkt;       //2
    Short memberType;                       //2
    String clearingStatus;                  //1
    String brokerName;                      //25


    public MsSignonRequest(byte[] messageHeader)throws UnsupportedEncodingException{
        this.messageHeader = new byte[40];
        System.arraycopy(messageHeader , 0 , this.messageHeader , 0 , 40);

        this.userId = 7814;
        this.password = "Pelf@FO1";
        this.newPassword = "";
        this.traderName = "";
        this.lastPasswordChangeDate = 0 ;
        this.brokerId = "11456";
        this.reserved95 = "";
        this.branchId = 6 ;
        this.versionNumber = 93800 ;
        this.batch2StartTime = 0;
        this.hostSwitchContext = "";
        this.colour = "";
        this.reserved157 = "";
        this.userType = 0;
        this.sequenceNumber = 0;
        this.wsClassName = "            P8" ;
        this.brokerStatus = "";
        this.showIndex = "N";
        this.stBrokerEligibilityPerMkt = new byte[2];
        String ch = "  ";
        System.arraycopy( ch.getBytes("ISO-8859-1") , 0 , this.stBrokerEligibilityPerMkt , 0 , 2);
        this.memberType = 0;
        this.clearingStatus = "";
        this.brokerName = "";

        this.struct = new byte[214];
    }
    public MsSignonRequest(short transactionCode , short messageLen , int traderId  )throws UnsupportedEncodingException{
        this(new MessageHeader(transactionCode , messageLen , traderId ).getStruct());
    }

    public byte[] getStruct() throws UnsupportedEncodingException{
        System.arraycopy(this.messageHeader, 0, this.struct, 0, 40);
        Utils.setInt(this.userId , struct , 40);
        Utils.setString(this.password , 8 , struct , 44);
        Utils.setString(this.newPassword , 8 , struct , 52);
        Utils.setString(this.traderName , 26 , struct , 60);
        Utils.setInt(this.lastPasswordChangeDate , struct , 86 );
        Utils.setString(this.brokerId , 5 , struct , 90);
        Utils.setString(this.reserved95 , 1 , struct , 95);
        Utils.setShort(this.branchId , struct , 96);
        Utils.setInt(this.versionNumber , struct , 98);
        Utils.setInt(this.batch2StartTime, struct , 102);
        Utils.setString(this.hostSwitchContext , 1 , struct , 106);
        Utils.setString(this.colour , 50 , struct , 107);
        Utils.setString(this.reserved157 , 1 , struct , 157);
        Utils.setShort(this.userType , struct , 158);
        Utils.setLong(this.sequenceNumber , struct , 160);
        Utils.setString(this.wsClassName , 14 , struct , 168);
        Utils.setString(this.brokerStatus , 1 , struct , 182);
        Utils.setString(this.showIndex , 1 , struct , 183);
        System.arraycopy(this.stBrokerEligibilityPerMkt, 0, this.struct, 184, 2);
        Utils.setShort(this.memberType , struct , 186);
        Utils.setString(this.clearingStatus , 1 , struct , 188);
        Utils.setString(this.brokerName , 25 , struct , 189);
        return struct;
    }

    private byte[] pop_stBrokerEligibilityPerMkt(){
        byte[] struct = new byte[2];

        return struct;
    }









}