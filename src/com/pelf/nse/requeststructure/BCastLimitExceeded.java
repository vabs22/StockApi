package com.pelf.nse.requeststructure;

public class BCastLimitExceeded {
	
	BcastMHeader bcastHeader;
	String brokerCode; // 5 bytes
	String counterBrokerCode; //5 bytes
	short warningType;
	int token;
	String instName; // 6 bytes
	String symbol; // 10 bytes
	int expiryDate; 
	int strikePrice;
	String optionType; // 2 bytes
	short caLevel;
	int tradeNumber;
	int tradePrice;
	int tradeVolume;
	String finalfield; // 1 byte
	String cFiller; // 1 byte

}
