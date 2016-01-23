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

            System.out.println(Arrays.toString(networkPacket.getStruct()));

            InputStream dIn = echoSocket.getInputStream();
            byte[] inNetworkPacket = new byte[1240];
            int sizeIn = 0;
            sizeIn =  dIn.read(inNetworkPacket);
                System.out.println(sizeIn);
                System.out.println(Arrays.toString(inNetworkPacket));
                System.out.println("");
                String newstr = new String(inNetworkPacket, "UTF-8");
                System.out.println(newstr);
            System.out.println("sizein : " + sizeIn);

            MsSystemInfoRequest infoPacket=new MsSystemInfoRequest((short)1600, (short)44,7814);
            networkPacket = new NetworkHeader(infoPacket.getStruct());
            outToServer = new DataOutputStream(echoSocket.getOutputStream());
            outToServer.write(networkPacket.getStruct());
            outToServer.flush();
            
            System.out.println(Arrays.toString(networkPacket.getStruct()));
            inNetworkPacket = new byte[1240];
            sizeIn = 0;
            sizeIn =  dIn.read(inNetworkPacket);
                System.out.println(sizeIn);
                System.out.println(Arrays.toString(inNetworkPacket));
                System.out.println("");
                newstr = new String(inNetworkPacket, "UTF-8");
                System.out.println(newstr);
            System.out.println("sizein : " + sizeIn);
            
            
            
            UpdateLocalDBIn dbPacket=new UpdateLocalDBIn((short)7300);
            networkPacket = new NetworkHeader(dbPacket.getStruct());
            outToServer = new DataOutputStream(echoSocket.getOutputStream());
            outToServer.write(networkPacket.getStruct());
            outToServer.flush();
            
            System.out.println(Arrays.toString(networkPacket.getStruct()));
            while(true){
            inNetworkPacket = new byte[1240];
            sizeIn = 0;
            sizeIn =  dIn.read(inNetworkPacket);
                System.out.println(sizeIn);
                System.out.println(Arrays.toString(inNetworkPacket));
                System.out.println("");
                newstr = new String(inNetworkPacket, "UTF-8");
                System.out.println(newstr);
            System.out.println("sizein : " + sizeIn);
            }
            
        } catch (Exception e) {
        	
        	
        	
            System.out.println("Exception : " +  e.getMessage());
            //LoggerUtil.getLogger().log(Level.SEVERE, "Exception in main thread", e);
        }
    }
}
