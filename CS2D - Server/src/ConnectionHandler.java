import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class ConnectionHandler extends Thread{
	private String remoteHost;
	private int port;
	private DatagramSocket socket;
	private ConnectionSendingHandler connSend;
	private ConnectionReceiveHandler connRec;
	private InputHandle input;
	
	
	
	public ConnectionHandler() {
		super();
		
	}
	
	public void run() {
		
		
		
		try {
			
			socket = new DatagramSocket(null);
			socket.bind(new InetSocketAddress(InetAddress.getByName(Main.getIp()), Integer.parseInt(Main.getPort())));
			System.out.println(socket.getInetAddress() + " " + socket.getLocalPort());
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connRec = new ConnectionReceiveHandler(socket);
		connSend = new ConnectionSendingHandler(socket, connRec);
		connRec.start();
		connSend.start();
		
	}
	
	public HashMap<SocketAddress, boolean[]> getAllKeys() {
		return this.connRec.getAllKeys();
	}
	
}
