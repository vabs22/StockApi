package com.pelf.nse.listener;

import com.pelf.nse.requeststructure.*;
import com.pelf.server.util.LoggerUtil;
import com.pelf.server.util.Utils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.TimeZone;
import java.util.logging.Level;

/**
 * Created by vaibhav on 1/10/16.
 */

public class SignOnListener {



    private static void initInNetworkPacket(byte[] inNetworkPacket){
        inNetworkPacket = new byte[1240];
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        TimeZone.setDefault(TimeZone.getTimeZone("IST"));

        String ip = "172.19.245.145";
        int port = 10252;
        Socket echoSocket;

        try {
            echoSocket = new Socket(ip, port);
            echoSocket.setReceiveBufferSize(6092);
            echoSocket.setSendBufferSize(1024);
            echoSocket.setTcpNoDelay(true);
            echoSocket.setSoTimeout(15 * 1000);
            echoSocket.setKeepAlive(true);

            // variables
            int sizeIn = 0;
            byte[] inNetworkPacket = new byte[1240];
            short errorcode = 0 , transcode = 0;
            NetworkHeader networkPacket;
            DataOutputStream outToServer = new DataOutputStream(echoSocket.getOutputStream());
            InputStream dIn = echoSocket.getInputStream();
            String newstr;
            MessageHeader pckHdr;

            // sign on request
            MsSignonRequest packetSignOn  = new MsSignonRequest((short)2300 , (short) 214 , 7814 );
            networkPacket = new NetworkHeader(packetSignOn.getStruct());
            outToServer.write(networkPacket.getStruct());
            outToServer.flush();
            System.out.println("Sign on Packet sent");
            //System.out.println(Arrays.toString(networkPacket.getStruct()));

            // sign on response
            initInNetworkPacket(inNetworkPacket);
            sizeIn = dIn.read(inNetworkPacket);
            MsSignonResponse packetSignOnResponse = new MsSignonResponse(inNetworkPacket);
            pckHdr = packetSignOnResponse.getMessageHeader();
            errorcode = pckHdr.getErrorCode();
            transcode = pckHdr.getTransactionCode();
            System.out.println("Sign on Packet response received");
            System.out.println("Error code is :" + errorcode + " , transaction code is : " + transcode + ", size of packet received :" + sizeIn);


            // system info request
            MsSystemInfoRequest packetInfoRequest = new MsSystemInfoRequest((short)1600 , (short) 44 , 7814);
            networkPacket = new NetworkHeader((packetInfoRequest.getStruct()));
            outToServer = new DataOutputStream(echoSocket.getOutputStream());
            outToServer.write(networkPacket.getStruct());
            outToServer.flush();
            System.out.println("System info Packet sent");
            //System.out.println(Arrays.toString(networkPacket.getStruct()));

            // system info response
            initInNetworkPacket(inNetworkPacket);
            sizeIn = dIn.read(inNetworkPacket);
            MsSystemInfoData packetInfoData = new MsSystemInfoData(inNetworkPacket);
            pckHdr = packetInfoData.getMessageHeader();
            errorcode = pckHdr.getErrorCode();
            transcode = pckHdr.getTransactionCode();
            System.out.println("System info Packet response received");
            System.out.println("Error code is :" + errorcode + " , transaction code is : " + transcode + ", size of packet received :" + sizeIn);


            // update local database request
            UpdateLocalDBIn dbPacket=new UpdateLocalDBIn((short)7300);
            networkPacket = new NetworkHeader(dbPacket.getStruct());
            outToServer = new DataOutputStream(echoSocket.getOutputStream());
            outToServer.write(networkPacket.getStruct());
            outToServer.flush();
            System.out.println("update local database request Packet sent");
            //System.out.println(Arrays.toString(networkPacket.getStruct()));

            // update local database response
            short transactionCode = -1 , innerTransactionCode = -1 ;
            System.out.println("update local database response Packets are :");
            while(true){
<<<<<<< HEAD
            inNetworkPacket = new byte[1240];
            sizeIn = 0;
            sizeIn =  dIn.read(inNetworkPacket);
            transactionCode = Utils.getInt16(inNetworkPacket, 22);
            System.out.println();
            System.out.println("Size of Packet Received : "+sizeIn);
            System.out.println("Transaction Code is : "+transactionCode);
            if(transactionCode == (short)7304) {
            	innerTransactionCode = Utils.getInt16(inNetworkPacket, 62);
                System.out.println("Inner Transaction Code is : "+innerTransactionCode);
                if(innerTransactionCode == (short)7324) {
                	System.out.println("InstrumentID : "+Utils.getInt16(inNetworkPacket, 102));
                	System.out.println("Instrument Name : "+Utils.getString(inNetworkPacket, 104, 6));
                }
                else if(innerTransactionCode == 7325) {
                	short noOfRec = Utils.getInt16(inNetworkPacket, 102);
                	for(short i=0; i<noOfRec;i++) {
                		String indexName = Utils.getString(inNetworkPacket, (104+i*24), 15);
                		int token = Utils.getInt32(inNetworkPacket, (119+i*24));
                		System.out.println("Index Name "+(i+1)+" : "+indexName);
                		System.out.println("Token "+(i+1)+" : "+token);
                	}
=======

                initInNetworkPacket(inNetworkPacket);
                sizeIn =  dIn.read(inNetworkPacket);
                if(sizeIn <= 0)
                    break;
                transactionCode = Utils.getInt16(inNetworkPacket, 22);
                System.out.println("\nSize of Packet Received : " + sizeIn);
                System.out.println("Transaction Code is : " + transactionCode);

                if(transactionCode == (short)7304) {
                    UpdateLocalDBData packetUpdateLocalDBData = new UpdateLocalDBData(inNetworkPacket);
                    InnerMessageHeader packetInnerMessageHeader = packetUpdateLocalDBData.getInnerHeader();
                    innerTransactionCode = packetInnerMessageHeader.getTransactionCode();
                    //innerTransactionCode = Utils.getInt16(inNetworkPacket, 62);
                    System.out.println("Inner Transacction Code is : "+innerTransactionCode);

                    if(innerTransactionCode == (short)7324) {

                        System.out.println("InstrumentID : "+Utils.getInt16(inNetworkPacket, 102));
                        System.out.println("Instrument Name : "+Utils.getString(inNetworkPacket, 104, 6));
                    }
                    else if(innerTransactionCode == 7325) {

                        short noOfRec = Utils.getInt16(inNetworkPacket, 102);
                        for(short i=0; i<noOfRec;i++) {
                            String indexName = Utils.getString(inNetworkPacket, (104+i*24), 15);
                            int token = Utils.getInt32(inNetworkPacket, (119+i*24));
                            System.out.println("Index Name "+(i+1)+" : "+indexName);
                            System.out.println("Token "+(i+1)+" : "+token);
                        }
                    }
                    else if(innerTransactionCode == 7326) {

                        short noOfRec = Utils.getInt16(inNetworkPacket, 102);
                        for(short i=0;i<noOfRec;i++) {
                            String bcastName = Utils.getString(inNetworkPacket, (104+i*41), 26);
                            System.out.println("Broadcast Name "+(i+1)+" : "+bcastName);
                        }
                    }
>>>>>>> origin/master
                }
                else if(transactionCode == (short)7308) {
                    System.out.println("Data update trailer received");
                    break;
                }
                //System.out.println(Arrays.toString(inNetworkPacket));
                //System.out.println("Packet Ended");
                //newstr = new String(inNetworkPacket, "UTF-8");
                //System.out.println(newstr);
                //System.out.println("PacketEnded");
                //System.out.println("sizein : " + sizeIn);
            }

            // sign out request
            MessageHeader packetSignOff = new MessageHeader((short)2320 , (short) 40 , 7814);
            networkPacket = new NetworkHeader((packetSignOff.getStruct()));
            outToServer = new DataOutputStream(echoSocket.getOutputStream());
            outToServer.write(networkPacket.getStruct());
            outToServer.flush();
            System.out.println("Sign off Packet sent");
            //System.out.println(Arrays.toString(networkPacket.getStruct()));

            // sign off response
            inNetworkPacket = new byte[500];
            sizeIn = dIn.read(inNetworkPacket);
            SignOffOut packetSignOffOut = new SignOffOut(inNetworkPacket);
            pckHdr = packetSignOffOut.getMessageHeader();
            errorcode = pckHdr.getErrorCode();
            transcode = pckHdr.getTransactionCode();
            System.out.println("Sign off Packet response received");
            System.out.println("Error code is :" + errorcode + " , transaction code is : " + transcode + ", size of packet received :" + sizeIn);


        } catch (Exception e) {
            System.out.println("Exception : " +  e.getMessage());
            e.printStackTrace();
            //LoggerUtil.getLogger().log(Level.SEVERE, "Exception in main thread", e);
        }
    }
}