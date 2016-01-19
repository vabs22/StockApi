package com.pelf.nse.requeststructure;

public class MsSecurityUpdateInfo {
	byte[] struct;	//298
	int token;		//4
	SecInfo secInfo;	//30
	short perToTrade;	//2
	long issuedCapital;	//8
	int warnQty;		//4
	int freezeQty;		//4
	String creditRating;	//12
	StSecEligibilityMarket stSecElgPerMarket;	//3
	short issueRate;	//2
	int issueStartDate;	//4
	int interestPayDate;	//4
	int issueMaturityDate;	//4
	int marginPercentage;	//4
	int minLotQty;			//4
	int boardLotQty;		//4
	int tickSize;			//4
	String name;			//25
	String reserved;		//1
	int listingDate;		//4
	int expulsionDate;		//4
	int reAdmissionDate;	//4
	int recordDate;			//4
	int lowPriceRange;		//4
	int highPriceRange;		//4
	int expiryDate;			//4
	int noDeliveryStartDate;	//4
	int noDeliveryEndDate;		//4
	byte[] stEligibilityIndicators;	//2
	int blockClosureStartDate;		//
}
