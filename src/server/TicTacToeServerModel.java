package server;

import java.util.logging.Logger;

import common.TicTacToeMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TicTacToeServerModel {
	public final static int PORT = 8088;
	private final static Logger logger = Logger.getLogger("");
	
	public static void main(String[] args) {
		try(ServerSocket s = new ServerSocket(PORT, 10, null)){
			logger.info("Listening on Port: " + PORT);
			
			while(true) {
				try(Socket client = s.accept();
						BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
						PrintWriter out = new PrintWriter(client.getOutputStream());
						ObjectInputStream obj_in = new ObjectInputStream(client.getInputStream()))
				{
					logger.info("Request from client " + client.getInetAddress().toString() + " for server "
							+ client.getLocalAddress().toString());
					
					StringBuilder received = new StringBuilder();
					String inString;
					while((inString = in.readLine()) != null && inString.length() != 0) {
						System.out.println("received");
						TicTacToeMessage tttm = (TicTacToeMessage) obj_in.readObject();
						System.out.println("HELLO " + tttm);
						received.append(inString);
					}
					logger.info(inString);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
