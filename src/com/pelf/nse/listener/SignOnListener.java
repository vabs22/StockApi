package com.pelf.nse.listener;

import com.pelf.nse.requeststructure.LBQuery;
import com.pelf.nse.requeststructure.MessageHeader;
import com.pelf.nse.requeststructure.MsSignonRequest;
import com.pelf.nse.requeststructure.MsSystemInfoData;
import com.pelf.nse.requeststructure.MsSystemInfoRequest;
import com.pelf.nse.requeststructure.MsSignonRequest;
import com.pelf.nse.requeststructure.NetworkHeader;
import com.pelf.nse.requeststructure.UpdateLocalDBIn;
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


            MsSignonRequest packet  = new MsSignonRequest((short)2300 , (short) 214 , 7814 );
            NetworkHeader networkPacket = new NetworkHeader(packet.getStruct());
            DataOutputStream outToServer = new DataOutputStream(echoSocket.getOutputStream());
            outToServer.write(networkPacket.getStruct());
            outToServer.flush();

            //System.out.println(Arrays.toString(networkPacket.getStruct()));

            InputStream dIn = echoSocket.getInputStream();
            byte[] inNetworkPacket = new byte[1240];
            int sizeIn = 0;
            sizeIn =  dIn.read(inNetworkPacket);
                System.out.println("Size of Sign_On_Request_Out"+sizeIn);
                //System.out.println(Arrays.toString(inNetworkPacket));
                //System.out.println("");
                String newstr = new String(inNetworkPacket, "UTF-8");
                //System.out.println(newstr);
            //System.out.println("sizein : " + sizeIn);

            MsSystemInfoRequest infoPacket=new MsSystemInfoRequest((short)1600, (short)44,7814);
            networkPacket = new NetworkHeader(infoPacket.getStruct());
            outToServer = new DataOutputStream(echoSocket.getOutputStream());
            outToServer.write(networkPacket.getStruct());
            outToServer.flush();
            
            //System.out.println(Arrays.toString(networkPacket.getStruct()));
            inNetworkPacket = new byte[1240];
            sizeIn = 0;
            sizeIn =  dIn.read(inNetworkPacket);
                System.out.println("Size of System_Information_Out"+sizeIn);
                //System.out.println(Arrays.toString(inNetworkPacket));
                //System.out.println("");
                newstr = new String(inNetworkPacket, "UTF-8");
                //System.out.println(newstr);
            System.out.println("sizein : " + sizeIn);
            
            
            
            UpdateLocalDBIn dbPacket=new UpdateLocalDBIn((short)7300);
            networkPacket = new NetworkHeader(dbPacket.getStruct());
            outToServer = new DataOutputStream(echoSocket.getOutputStream());
            outToServer.write(networkPacket.getStruct());
            outToServer.flush();
            
            //System.out.println(Arrays.toString(networkPacket.getStruct()));
            short transactionCode=-1,innerTransactionCode=-1;
            while(true){
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
                }
                else if(innerTransactionCode == 7326) {
                	short noOfRec = Utils.getInt16(inNetworkPacket, 102);
                	for(short i=0;i<noOfRec;i++) {
                		String bcastName = Utils.getString(inNetworkPacket, (104+i*41), 26);
                		System.out.println("Broadcast Name "+(i+1)+" : "+bcastName);
                	}
                }
            }
            //System.out.println(Arrays.toString(inNetworkPacket));
            //System.out.println("Packet Ended");
            //newstr = new String(inNetworkPacket, "UTF-8");
            //System.out.println(newstr);
            //System.out.println("PacketEnded");
            //System.out.println("sizein : " + sizeIn);
            }
                
        } catch (Exception e) {
        	System.out.println("Exception : " +  e.getMessage());
            //LoggerUtil.getLogger().log(Level.SEVERE, "Exception in main thread", e);
        }
    }
}