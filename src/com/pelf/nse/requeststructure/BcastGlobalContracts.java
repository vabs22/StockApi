package com.pelf.nse.requeststructure;

public class BcastGlobalContracts {
	
	GMHeader messageHeader;
	int token;
	String nseSymbol; // 16 bytes
	String instName; // 6 bytes
	short expDay; 
	short expMonth;
	short expYear;
	String optionType; // 2 bytes
	int strikePrice;
	int bidPrice;
	int askPrice;
	double bidSize;
	double askSize;
	int open;
	int high;
	int low;
	int last;
	int close;
	int prevClose;
	int limitHigh;
	int limitLow;
	double totalTrades;
	double openInterest;
	int filler1;
	int filler2;
	int filler3;

}
