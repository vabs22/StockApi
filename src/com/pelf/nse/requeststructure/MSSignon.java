package com.pelf.nse.requeststructure;

import java.io.UnsupportedEncodingException;

import com.pelf.server.util.SequenceGenerator;
import com.pelf.server.util.Utils;

public class MSSignon {
	byte[] struct;
	int userId;			//4 bytes
	String password;	//8 bytes
	String newPassword;	//8 bytes
	String traderName;	//26 bytes
	int lastPasswordChangeDate;	//4 bytes
	String brokerId;	//5 bytes
	String reserved1;	//1
	short branchId;		//2
	int versionNumber;	//4
	int batch2StartTime;	//4
	String hostSwitchContext;	//1
	String colour;		//50
	String reserved2;	//1
	short userType;		//2
	long sequenceNumber;	//8
	String wsClassName;	//14
	String brokerStatus;	//1
	String showIndex;	//1
	byte[] StBrokerEligibilityPerMarket = new byte[2];	//2
	short memberType; 	//2
	String clearingStatus;	//1
	String brokerName;	//25
	
	public MSSignon(short transactionCode) throws UnsupportedEncodingException {
		this.userId = 11456;
		this.password = "Neat@FO1";
		this.newPassword = "";
		this.traderName = "";
		this.lastPasswordChangeDate = 0;
		this.brokerId = " 7814";
		this.reserved1 = "";
		this.branchId = 6;
		this.versionNumber = 93800;
		this.batch2StartTime = 0;
		this.hostSwitchContext = "";
		this.colour = "";
		this.reserved2 = "";
		this.userType = 0;
		this.sequenceNumber = SequenceGenerator.nextGloballyUniqueInt();
		this.wsClassName = "            P8";
		this.brokerStatus = "";
		this.showIndex = "N";
		String ch = "  ";
		System.arraycopy(ch.getBytes("ISO-8859-1"),0,this.StBrokerEligibilityPerMarket,0,2);
		this.memberType = 0;
		this.clearingStatus = "";
		this.brokerName = "";
		byte[] gmheaderbyte = new MessageHeader(transactionCode,(short)214).getStruct();
		struct = new byte[214];
		System.arraycopy(gmheaderbyte, 0, this.struct, 0, 40);
	}
	
	public byte[] getStruct() throws UnsupportedEncodingException {
		Utils.setInt(userId,struct,40);
		Utils.setString(password, 8, struct, 44);
		Utils.setString(newPassword, 8, struct, 52);
		Utils.setString(traderName, 26, struct, 60);
		Utils.setInt(lastPasswordChangeDate, struct, 86);
		Utils.setString(brokerId, 5, struct, 90);
		Utils.setString(reserved1, 1, struct, 95);
		Utils.setShort(branchId, struct, 96);
		Utils.setInt(versionNumber, struct, 98);
		Utils.setInt(batch2StartTime, struct, 102);
		Utils.setString(hostSwitchContext, 1, struct, 106);
		Utils.setString(colour, 50, struct, 107);
		Utils.setString(reserved2, 1, struct,157);
		Utils.setShort(userType, struct, 158);
		Utils.setLong(sequenceNumber, struct, 160);
		Utils.setString(wsClassName, 14, struct, 168);
		Utils.setString(brokerStatus, 1, struct, 182);
		Utils.setString(showIndex, 1, struct, 183);
		System.arraycopy(this.StBrokerEligibilityPerMarket, 0, this.struct, 184, 2);
		Utils.setShort(memberType, struct, 186);
		Utils.setString(clearingStatus, 1, struct, 188);
		Utils.setString(brokerName, 25, struct, 189);
		return struct;
	}
	
	public String getColour() {
		return this.colour;
	}
	public String getBrokerName() {
		return this.brokerName;
	}
	public long getSequenceNumber() {
		return this.sequenceNumber;
	}
}




























