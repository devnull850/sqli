package sqli;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

public class TestServer {

	public static void main(String[] args) throws IOException {
		HttpServer server = HttpServer.create(
			new InetSocketAddress("localhost",8080), 0);
		server.createContext("/", new IndexHandler());
		server.setExecutor(newSingleThreadExecutor());
		server.start();
	}

}
