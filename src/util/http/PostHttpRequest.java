package util.http;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A connection that uses POST request
 *
 * @author Quentin Guenther <qguenther@outlook.com>
 * @version 1.0
 */
public class PostHttpRequest extends HttpRequest
{
	private String urlParameters;

	/**
	 * Creates a connection that uses POST request
	 *
	 * @param url the URL
	 * @param urlParameters parameters to send with the request
	 * @throws MalformedURLException  if an unknown protocol is specified.
	 */
	public PostHttpRequest(String url, String urlParameters) throws MalformedURLException
	{
		super(new URL(url));
		this.urlParameters = urlParameters;

		setRequestMethod("POST");
		setRequestProperty("User-Agent", USER_AGENT);
		setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	}

	@Override
	public StringBuilder sendRequest() throws IOException
	{
		setDoOutput(true);
		DataOutputStream writeStream = new DataOutputStream(getOutputStream());
		writeStream.writeBytes(urlParameters);
		writeStream.flush();
		writeStream.close();

		return readInputStreamFromConnection();
	}

	/**
	 * @return parameters to be sent
	 */
	public String getUrlParameters()
	{
		return urlParameters;
	}

	/**
	 * @param urlParameters parameters to be sent
	 */
	public void setUrlParameters(String urlParameters)
	{
		this.urlParameters = urlParameters;
	}

	@Override
	public String toString()
	{
		return "PostHttpRequest{" +
				"urlParameters='" + urlParameters + '\'' +
				'}';
	}
}
