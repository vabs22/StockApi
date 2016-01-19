package com.pelf.nse.requeststructure;

/**
 * Created by vaibhav on 1/18/16.
 */
public class Constants {
    static short transactionCode;      //2 bytes
    static int logTime;                //4 bytes
    static String alphaChar;           //2 bytes
    static int traderId;               //4 bytes
    static short errorCode;            //2 bytes
    static String timeStamp;           //8 bytes
    static String timeStamp1;          //8 bytes
    static String timeStamp2;          //8 bytes
    static short messageLen;           //2 bytes

    public static short getTransactionCode() {
        return transactionCode;
    }

    public static int getLogTime() {
        return logTime;
    }

    public static String getAlphaChar() {
        return alphaChar;
    }

    public static int getTraderId() {
        return traderId;
    }

    public static short getErrorCode() {
        return errorCode;
    }

    public static String getTimeStamp() {
        return timeStamp;
    }

    public static String getTimeStamp1() {
        return timeStamp1;
    }

    public static String getTimeStamp2() {
        return timeStamp2;
    }

    public static short getMessageLen() {
        return messageLen;
    }

    public static void setTransactionCode(short transactionCode) {

        Constants.transactionCode = transactionCode;
    }

    public static void setLogTime(int logTime) {
        Constants.logTime = logTime;
    }

    public static void setAlphaChar(String alphaChar) {
        Constants.alphaChar = alphaChar;
    }

    public static void setTraderId(int traderId) {
        Constants.traderId = traderId;
    }

    public static void setErrorCode(short errorCode) {
        Constants.errorCode = errorCode;
    }

    public static void setTimeStamp(String timeStamp) {
        Constants.timeStamp = timeStamp;
    }

    public static void setTimeStamp1(String timeStamp1) {
        Constants.timeStamp1 = timeStamp1;
    }

    public static void setTimeStamp2(String timeStamp2) {
        Constants.timeStamp2 = timeStamp2;
    }

    public static void setMessageLen(short messageLen) {
        Constants.messageLen = messageLen;
    }
}
