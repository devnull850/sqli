package sqli;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import static java.util.stream.Collectors.joining;

public class IndexHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		if (!exchange.getRequestMethod().equals("GET")) {
			return;
		}

		try (OutputStream out = exchange.getResponseBody()) {
			String contents = getFileContents();
			exchange.sendResponseHeaders(200, contents.length());
			out.write(contents.getBytes());
			out.flush();
		}
	}

	private String getFileContents() throws IOException {
		try (BufferedReader in = new BufferedReader(
				new FileReader("res/index.html"))) {
			return in.lines().collect(joining());
		}
	}

}
