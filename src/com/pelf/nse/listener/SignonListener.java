package com.pelf.nse.listener;

//import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.TimeZone;
//import java.util.logging.Level;



//import com.pelf.nse.requeststructure.LBQuery;
import com.pelf.nse.requeststructure.MSSignon;
import com.pelf.nse.requeststructure.NetworkHeader;
import com.pelf.server.util.Utils;
//import com.pelf.server.util.LoggerUtil;
//import com.pelf.server.util.Utils;


public class SignonListener {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		TimeZone.setDefault(TimeZone.getTimeZone("IST"));
		
		String ip = "172.19.245.145";
		int port = 10252;
		Socket echoSocket;
		System.out.println("Trying to create socket connection.....");
		try{
			/* */
			echoSocket = new Socket(ip, port);
	        echoSocket.setReceiveBufferSize(6092);
	        echoSocket.setSendBufferSize(1024);
	        echoSocket.setTcpNoDelay(true);
	        echoSocket.setSoTimeout(15*1000);
	        System.out.println("Socket Connection Created.");
	        
	        MSSignon signon = new MSSignon((short)2300);
	        System.out.println("MS_SIGNON Packet Created.");
	        NetworkHeader networkPacket = new NetworkHeader(signon.getStruct());
	        System.out.println("Network Packet Created.");
	        DataOutputStream outToServer = new DataOutputStream(echoSocket.getOutputStream());
	    	outToServer.write(networkPacket.getStruct());
	    	outToServer.flush();
	    	System.out.println("Packet Sent.");
	        
	        InputStream dIn = echoSocket.getInputStream();
	        /*System.out.println("Trying to receive response packet.");
	        byte []fresh = new byte[1024];
	        int size = -1;
	        while(size == -1) {
	        	size = dIn.read(fresh);
	        }
	        System.out.println("Packet received from NSE Server.");
	        System.out.println("Received packet size :"+size);
			/**/
			
	    	
	    	byte[] inNetworkPacket = new byte[240];
            int sizeIn = 0;
            while ((sizeIn =  dIn.read(inNetworkPacket)) > 0) {
                System.out.println(sizeIn);
                System.out.println(Arrays.toString(inNetworkPacket));
                int i;
                System.out.println("");
                String newstr = new String(inNetworkPacket, "UTF-8");
                System.out.println(newstr);
            }
            String errorKey = Utils.getString(inNetworkPacket, 40, 14);
            String errorMsg = Utils.getString(inNetworkPacket, 54, 128);
            short errorCode = Utils.getInt16(inNetworkPacket, 34);
            System.out.println("Error Key : "+errorKey);
            System.out.println("Error Message : "+errorMsg);
            System.out.println("Error code : "+errorCode);
			
			/* DEBUGGING CODE 
			 MSSignon signon = new MSSignon((short)2300);
		     System.out.println("MS_SIGNON Packet Created.");
		     byte[] temp = signon.getStruct();
		     System.out.println("Size of MS_SIGNON packet : "+temp.length);
		     String str = Utils.getString(temp, 107, 50);
		     String str2 = Utils.getString(temp, 189, 25);
		     long seqwe = Utils.getLong(temp, 160);
		     System.out.println("Colour is : "+str);
			 System.out.println("Colour size : "+str.length());
		     System.out.println("Broker is : "+str2);
			 System.out.println("Broker size : "+str2.length());
			 System.out.println("Sequence Number is : "+seqwe);
		     NetworkHeader networkPacket = new NetworkHeader(signon.getStruct());
		     System.out.println("Network Packet Created.");
			/* DEBUGGING CODE ENDS HERE */
			
			
		}catch(Exception e){
			System.out.println("EXception in SignonListener : "+e.getMessage());
		}
	}

}

