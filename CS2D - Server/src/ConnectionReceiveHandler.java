import java.io.ByteArrayInputStream;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ConnectionReceiveHandler extends Thread{
	private DatagramSocket socket;
	private ObjectInputStream objIn;
	private ByteArrayInputStream baIn;
	private HashMap<SocketAddress, boolean[]> allKeys;
	private ArrayList<SocketAddress> addresses;

	public ConnectionReceiveHandler(DatagramSocket socket) {
		super();
		this.socket = socket;
		this.allKeys = new HashMap<SocketAddress, boolean[]>();
		this.addresses = new ArrayList<SocketAddress>();
	}
	
	public void run() {
		DatagramPacket packet;
		boolean[] recKeys = new boolean[8];
		byte[] bytes;
		while(true) {
			bytes = new byte[1024];
			packet = new DatagramPacket(bytes, bytes.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			baIn = new ByteArrayInputStream(packet.getData());
			try {
				objIn = new ObjectInputStream(baIn);
				recKeys = (boolean[]) objIn.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.allKeys.put(packet.getSocketAddress(), recKeys);
			if(addresses.contains(packet.getSocketAddress()) == false) {
				addresses.add(packet.getSocketAddress());
			}
		}
	}
	public HashMap<SocketAddress, boolean[]> getAllKeys() {
		return this.allKeys;
	}

	public ArrayList<SocketAddress> getAddresses() {
		return addresses;
	}
	
}
