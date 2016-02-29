package com.pelf.server.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class FileUploader {
	public static void uploadFile(String fileName, String ip, Socket socket) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());
		String str;
		while((str = br.readLine()) != null) {
			osw.write(str);
		}
		br.close();
		osw.close();
	}
}
