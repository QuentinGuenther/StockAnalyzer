package util.http;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A connection that uses GET request
 *
 * @author Quentin Guenther <qguenther@outlook.com>
 * @version 1.0
 */
public class GetHttpRequest extends HttpRequest
{
	/**
	 * Creates a connection that uses GET request
	 *
	 * @param url the url
	 * @throws MalformedURLException  if an unknown protocol is specified.
	 */
	public GetHttpRequest(String url) throws MalformedURLException
	{
		super(new URL(url));
		setRequestMethod("GET");
		setRequestProperty("User-Agent", USER_AGENT);
	}

	@Override
	public StringBuilder sendRequest() throws IOException
	{
		return readInputStreamFromConnection();
	}
}
