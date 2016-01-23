package com.pelf.nse.listener;

import com.pelf.nse.requeststructure.*;
import com.pelf.nse.requeststructure.MsSignonRequest;
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

            // variables
            InputStream dIn = echoSocket.getInputStream();
            byte[] inNetworkPacket = new byte[500];
            int sizeIn = 0;
            short errorcode = 0 , transcode = 0;
            NetworkHeader networkPacket;
            DataOutputStream outToServer = new DataOutputStream(echoSocket.getOutputStream());


            // sign on request
            MsSignonRequest packetSignOn  = new MsSignonRequest((short)2300 , (short) 214 , 7814 );
            networkPacket = new NetworkHeader(packetSignOn.getStruct());
            outToServer.write(networkPacket.getStruct());
            outToServer.flush();
            System.out.println(Arrays.toString(networkPacket.getStruct()));

            // sign on response
            sizeIn = dIn.read(inNetworkPacket);
            System.out.println(sizeIn);
            System.out.println(Arrays.toString(inNetworkPacket));
            errorcode = Utils.getInt16(inNetworkPacket, 34);
            transcode = Utils.getInt16(inNetworkPacket, 22);
            System.out.println("Error code is :" + errorcode + " , transaction code is : " + transcode);

            // system info request
            MsSystemInfoRequest packetInfoRequest = new MsSystemInfoRequest((short)1600 , (short) 214 , 7814);
            networkPacket = new NetworkHeader((packetInfoRequest.getStruct()));
            outToServer.write(networkPacket.getStruct());
            outToServer.flush();

            // system info response
            sizeIn = dIn.read(inNetworkPacket);
            System.out.println(sizeIn);
            System.out.println(Arrays.toString(inNetworkPacket));
            errorcode = Utils.getInt16(inNetworkPacket , 34);
            transcode = Utils.getInt16(inNetworkPacket , 22);
            System.out.println("Error code is :" + errorcode + " , transaction code is : " + transcode);

            // sign out request
            MessageHeader packetSignOff = new MessageHeader((short)2320 , (short) 214 , 7814);
            networkPacket = new NetworkHeader((packetSignOff.getStruct()));
            outToServer.write(networkPacket.getStruct());
            outToServer.flush();

            // sign off response
            sizeIn = dIn.read(inNetworkPacket);
            System.out.println(sizeIn);
            System.out.println(Arrays.toString(inNetworkPacket));
            errorcode = Utils.getInt16(inNetworkPacket , 34);
            transcode = Utils.getInt16(inNetworkPacket , 22);
            System.out.println("Error code is :" + errorcode + " , transaction code is : " + transcode);

        } catch (Exception e) {
            System.out.println("Exception : " +  e.getMessage());
            //LoggerUtil.getLogger().log(Level.SEVERE, "Exception in main thread", e);
        }
    }
}
