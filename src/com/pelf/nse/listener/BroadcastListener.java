package com.pelf.nse.listener;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.TimeZone;
import java.util.logging.Level;

import com.pelf.nse.requeststructure.LBQuery;
import com.pelf.nse.requeststructure.NetworkHeader;
import com.pelf.server.util.LoggerUtil;
import com.pelf.server.util.Utils;


public class BroadcastListener {

	public static void main(String[] args) throws UnknownHostException, IOException {
		TimeZone.setDefault(TimeZone.getTimeZone("IST"));
		
		String ip = "179.19.245.145";
		int port = 10252;
		Socket echoSocket;
		try{
		echoSocket = new Socket(ip, port);
        echoSocket.setReceiveBufferSize(6092);
        echoSocket.setSendBufferSize(1024);
        echoSocket.setTcpNoDelay(true);
        echoSocket.setSoTimeout(15*1000);
        
        LBQuery lbquery = new LBQuery((short) 2400);
        NetworkHeader networkPacket = new NetworkHeader(lbquery.getStruct());
        DataOutputStream outToServer = new DataOutputStream(echoSocket.getOutputStream());
    	outToServer.write(networkPacket.getStruct());
    	outToServer.flush();
        
        InputStream dIn = echoSocket.getInputStream();
        
        	short msg_length=0;
            int index = 0;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            long loopstarttime = System.currentTimeMillis();
            while ((System.currentTimeMillis()-loopstarttime)<300000) {
                
                byte[] fresh = new byte[10240];
                int size  = dIn.read( fresh );
                if(size<1)continue;
                baos.write(fresh, 0, size);
               // System.out.println(baos.size()+"\t"+index);
                while (baos.size()>index) 
                {
                if(baos.size()<2)
                {
                 
                    break;
                }    
                
                msg_length = Utils.getInt16(baos.toByteArray(), index);
                
                msg_length =(short) (msg_length+4);
                if(baos.size()-index<msg_length){
                  //System.out.println("More to come:"+(baos.size()-index)+"/"+msg_length);
                    break;
                }
               // System.out.println("Message length:"+msg_length+"\tStream:"+baos.size()+"\tIndex:"+index    );
                byte[] data = new byte[msg_length];
                System.arraycopy(baos.toByteArray(), index, data, 0, data.length);
                LoggerUtil.getLogger().info("Got some parsed data "+data);
                index +=msg_length;
                }
                
              //  System.out.println("Continuing to listen to socket at index:"+index);
                if(baos.size()>msg_length){//There are bytes left after
              //  System.out.println("Bytes are left to be carried forward:"+(baos.size()-index));
                byte[] remnant = new byte[baos.size()-index];
              // System.out.println("Remnant will receive bytes from:"+(baos.size()-index));
                System.arraycopy(baos.toByteArray(), index, remnant, 0, remnant.length);    
                baos.reset();
                baos.write(remnant);
                }
                else if(baos.size()==msg_length){
                    baos.reset();
                    
                }
                index = 0;
                LoggerUtil.getLogger().info("Reset index to zero");
            }
		}catch(Exception e){
			LoggerUtil.getLogger().log(Level.SEVERE, "Exception in main thread", e);
		}
	}

}

