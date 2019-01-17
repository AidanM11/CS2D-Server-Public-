import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ConnectionSendingHandler extends Thread{
	private String remoteHost;
	private int port;
	private boolean connected;
	private ObjectOutputStream objOut;
	private ByteArrayOutputStream baOut;
	private InputHandle input;
	private InetAddress inet;
	private DatagramSocket socket;
	private ArrayList<SocketAddress> addresses;
	
	public ConnectionSendingHandler(ArrayList<SocketAddress> addresses) {
		super();
		this.addresses = addresses;
	}
	
	public void run() {
		baOut = new ByteArrayOutputStream();
		
		try {
			inet = InetAddress.getByName(remoteHost);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		
		try {
			objOut = new ObjectOutputStream(baOut);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		DatagramPacket packet;
		byte[] data;
		
		
		while(true) {
			for(int i = 0; i < addresses.size(); i ++) {
				try {
					baOut = new ByteArrayOutputStream();
					objOut = new ObjectOutputStream(baOut);
					InetSocketAddress addr = (InetSocketAddress) addresses.get(i);
					objOut.writeObject(Main.getGameState());
					data = baOut.toByteArray();
					packet = new DatagramPacket(data, data.length, addr.getAddress(), addr.getPort());
					socket.send(packet);
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	

}
