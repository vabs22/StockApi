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
            byte[] inNetworkPacket;
            int sizeIn = 0;
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
            //System.out.println(Arrays.toString(networkPacket.getStruct()));

            // sign on response
            inNetworkPacket = new byte[250];
            sizeIn = dIn.read(inNetworkPacket);
            MsSignonResponse packetSignOnResponse = new MsSignonResponse(inNetworkPacket);
            pckHdr = packetSignOnResponse.getMessageHeader();
            errorcode = pckHdr.getErrorCode();
            transcode = pckHdr.getTransactionCode();
            System.out.println("Error code is :" + errorcode + " , transaction code is : " + transcode + ", size of packet received :" + sizeIn);

            // system info request
            MsSystemInfoRequest packetInfoRequest = new MsSystemInfoRequest((short)1600 , (short) 44 , 7814);
            networkPacket = new NetworkHeader((packetInfoRequest.getStruct()));
            outToServer = new DataOutputStream(echoSocket.getOutputStream());
            outToServer.write(networkPacket.getStruct());
            outToServer.flush();
            //System.out.println(Arrays.toString(networkPacket.getStruct()));

            // system info response
            inNetworkPacket = new byte[140];
            sizeIn = dIn.read(inNetworkPacket);
            MsSystemInfoData packetInfoData = new MsSystemInfoData(inNetworkPacket);
            pckHdr = packetInfoData.getMessageHeader();
            errorcode = pckHdr.getErrorCode();
            transcode = pckHdr.getTransactionCode();
            System.out.println("Error code is :" + errorcode + " , transaction code is : " + transcode + ", size of packet received :" + sizeIn);

            // sign out request
            MessageHeader packetSignOff = new MessageHeader((short)2320 , (short) 40 , 7814);
            networkPacket = new NetworkHeader((packetSignOff.getStruct()));
            outToServer = new DataOutputStream(echoSocket.getOutputStream());
            outToServer.write(networkPacket.getStruct());
            outToServer.flush();
            //System.out.println(Arrays.toString(networkPacket.getStruct()));

            // sign off response
            inNetworkPacket = new byte[500];
            sizeIn = dIn.read(inNetworkPacket);
            SignOffOut packetSignOffOut = new SignOffOut(inNetworkPacket);
            pckHdr = packetSignOffOut.getMessageHeader();
            errorcode = pckHdr.getErrorCode();
            transcode = pckHdr.getTransactionCode();
            System.out.println("Error code is :" + errorcode + " , transaction code is : " + transcode + ", size of packet received :" + sizeIn);

        } catch (Exception e) {
            System.out.println("Exception : " +  e.getMessage());
            e.printStackTrace();
            //LoggerUtil.getLogger().log(Level.SEVERE, "Exception in main thread", e);
        }
    }
}
