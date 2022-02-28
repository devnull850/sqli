package sqli;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

public class UserHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		if (!exchange.getRequestMethod().equals("POST")) {
			return;
		}

		try (OutputStream out = exchange.getResponseBody();
		     BufferedReader in = new BufferedReader(
			new InputStreamReader(exchange.getRequestBody()));
		     TestDB db = new TestDB()) {
			String body = in.lines().collect(joining());
			String id = getUserId(body);
			List<User> users = db.getUsers(id);
			String html = generateHtml(users);
			exchange.sendResponseHeaders(200,html.length());
			out.write(html.getBytes());
			out.flush();
		}
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	private String generateHtml(List<User> users) {
		if (users.isEmpty()) {
			return
				"<!DOCTYPE html>" +
				"<html>" +
				"<head>" +
				"<title>No User</title>" +
				"</head>" +
				"<body>" +
				"<h1>No user found</h1>" +
				"</body>" +
				"</html>";
		}

		StringBuilder builder = new StringBuilder();
		builder.append("<!DOCTYPE html><html><head><title>Users");
		builder.append("</title></head><body><table>");
		builder.append("<tr><th>Id</th><th>First Name</th>");
		builder.append("<th>Last Name</th><th>Age</th></tr>");

		for (User user : users) {
			builder.append(toTableRow(user));
		}
		
		builder.append("</table></body></html>");
		return builder.toString();
	}

	private String toTableRow(User user) {
		return String.format(Locale.US,
			"<tr><td>%d</td><td>%s</td><td>%s</td><td>%d</td>",
			user.getId(), user.getFirstName(), user.getLastName(),
			user.getAge());
	}

	private String getUserId(String body) {
		return Optional.ofNullable(body)
			.map(this::utf8Decode)
			.map(s -> s.replace("id=",""))
			.orElseGet(() -> "");
	}

	private String utf8Decode(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		}
		catch (IOException e) {
			return null;
		}
	}

}
