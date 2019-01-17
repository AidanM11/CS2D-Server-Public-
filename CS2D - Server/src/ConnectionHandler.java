import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.HashMap;

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
			socket = new DatagramSocket();
			System.out.println(socket.getInetAddress() + " " + socket.getLocalPort());
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connRec = new ConnectionReceiveHandler(socket);
		connSend = new ConnectionSendingHandler(connRec.getAddresses());
		connRec.start();
		connSend.start();
		
	}
	
	public HashMap<SocketAddress, boolean[]> getAllKeys() {
		return this.connRec.getAllKeys();
	}
	
}
