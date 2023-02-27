import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Main {

	/*
	The following python code was used to test the connections.
 	# https://realpython.com/python-sockets/#handling-multiple-connections
 	
	import socket
	import selectors
	import types
	
	sel = selectors.DefaultSelector()
	
	def service_connection(key, mask):
		sock = key.fileobj
		data = key.data
		if mask & selectors.EVENT_READ:
			recv_data = sock.recv(1024)  # Should be ready to read
			if recv_data:
				data.outb += recv_data
			else:
				print(f"Closing connection to {data.addr}")
				sel.unregister(sock)
				sock.close()
		if mask & selectors.EVENT_WRITE:
			if data.outb:
				print(f"Echoing {data.outb!r} to {data.addr}")
				sent = sock.send(data.outb)  # Should be ready to write
				data.outb = data.outb[sent:]
	
	def accept_wrapper(sock):
		conn, addr = sock.accept()  # Should be ready to read
		print(f"Accepted connection from {addr}")
		conn.setblocking(False)
		data = types.SimpleNamespace(addr=addr, inb=b"", outb=b"")
		events = selectors.EVENT_READ | selectors.EVENT_WRITE
		sel.register(conn, events, data=data)
	
	def SetupSocketForResponse(IP : str, PORT : int) -> None:
		lsock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		lsock.bind((IP, PORT))
		lsock.listen()
		print(f"Listening on {(IP, PORT)}")
		lsock.setblocking(False)
		sel.register(lsock, selectors.EVENT_READ, data=None)
	
	IP_ADDR = "127.0.0.1"
	PORTS = [80, 443, 22, 25, 53]
	for PORT in PORTS:
		SetupSocketForResponse(IP_ADDR, PORT)
	try:
		while True:
			events = sel.select(timeout=None)
			for key, mask in events:
				if key.data is None:
					accept_wrapper(key.fileobj)
				else:
					service_connection(key, mask)
	except KeyboardInterrupt:
		print("Caught keyboard interrupt, exiting")
	finally:
		sel.close()
	 */
	
	private static List<Integer> VALID_PORTS = Arrays.asList(80, 443, 22, 25, 53);
	private static Integer defaultConnectionTimeout = 100; // Milliseconds
	
	// Try connect to the host given the IP and port number and set timeout
	private static boolean _TryConnectToIP(String IP, Integer Port, Integer timeout) {
		String IPString = String.format("%s:%d", IP, Port);
		try {
			System.out.println( "Attempting connection to: " + IPString + " with timeout: " + timeout );
			Socket connection = new Socket(IP, Port);
			connection.setSoTimeout(timeout);
			System.out.println( "Connected to: " + IPString + "!");
			connection.close();
			return true;
		} catch (SocketTimeoutException exception) {
			System.out.println( "Timeout reached on " + IPString + " [SocketTimeoutException]");
		} catch (ConnectException exception) {
			System.out.println( "Could not connect to " + IPString + " [ConnectException]");
		} catch (Exception exception) {
			/*
			  # https://stackoverflow.com/questions/7469316/why-is-exception-printstacktrace-considered-bad-practice
			  
			  You should generally not use exception.printStackTrace() because of several reasons;
			  - Sometimes developers do not handle exception.printStackTrace properly;
			  they ignore the exception and assume it may not affect the rest of the code.
			  - When used in multithreading scendarios, the exception(s) are not ordered by time,
			  they are appended to the logs based on whichever calls the stack trace first.
			  - The stack trace is written to System.err, which is hard to (re)route or filter elsewhere.
			  - 
			*/
			System.out.println( exception.toString() + " [Exception]" );
		}
		return false;
	}
	
	public static HashMap<String, Boolean> ParseIPAddress(String IP, Integer timeout) {
		HashMap<String, Boolean> IPToBoolean = new HashMap<String, Boolean>();
		for (Integer PORT_NUMBER : VALID_PORTS) {
			 Boolean available = _TryConnectToIP(IP, PORT_NUMBER, timeout);
			 IPToBoolean.put( String.format("%s:%d", IP, PORT_NUMBER), available );
		}
		return IPToBoolean;
	}
	
	// == MAIN == //
	public static void main(String[] args) {
		if (args.length > 0) {
			
			System.out.println("Got command line arguments!");
			for (Integer index = 0; index < args.length; index++) {
				System.out.println(index + " - " + args[index]);
			}
			System.out.println("\n\n");
			
			String command = args[0];
			if (command.equals("help")) {
				
				StringBuilder builder = new StringBuilder();
				builder.append("Welcome to the port scanning tool!");
				builder.append("\nThis will automatically scan the ports " + VALID_PORTS.toString() + " on the specified IP address using -ipaddr argument!");
				builder.append("\nExample usage: java Main.java -ipaddr 127.0.0.1");
				builder.append("\nThere is also a 'debug' argument which will port scan the localhost!");
				System.out.println(builder.toString());
				
			} else if (command.equals("debug")) {
				
				// Automatically scan localhost ports if no CLI arguments are passed
				String LOCAL_HOST_IP = "127.0.0.1";
				StringBuilder builder = new StringBuilder("DEBUG - SCAN LOCALHOST PORTS");
				builder.append("\n");
				builder.append(LOCAL_HOST_IP);
				builder.append("\n");
				builder.append( VALID_PORTS.toString() );
				builder.append("\n");
				builder.append("Timeout: ");
				builder.append(defaultConnectionTimeout);
				System.out.println(builder.toString());
				HashMap<String, Boolean> IPToBoolean = ParseIPAddress(LOCAL_HOST_IP, defaultConnectionTimeout);
				System.out.println("Result of Scan:\n" + IPToBoolean.toString());
				
			} else if (command.equals("-ipaddr")) {
				
				if (args.length == 1 || args[1].equals(null)) {
					System.out.println("When using the -ipaddr argument, make sure to pass a value for the IP address.");
				} else {
					StringBuilder builder = new StringBuilder();
					builder.append("Checking the given IP address on the following ports; ");
					builder.append( VALID_PORTS.toString() );
					System.out.println(builder.toString());
					HashMap<String, Boolean> IPToBoolean = ParseIPAddress(args[1], defaultConnectionTimeout);
					System.out.println("Result of Scan:\n" + IPToBoolean.toString());
				}
				
			} else {
				
				System.out.println("Could not parse the given CLI arguments. Use the 'help' argument.");
			
			}
		} else {
			System.out.println("To get the help information, use 'help' argument in the CLI.");
		}
	}

}
