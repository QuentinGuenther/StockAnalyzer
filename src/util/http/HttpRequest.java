package util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Provides a facade for creating HTTP-specific requests.
 * Each HttpRequest instance is used to create a single request
 *
 * @see HttpURLConnection
 * @author Quentin Guenther <qguenther@outlook.com>
 * @version 1.0
 */
public abstract class HttpRequest
{
	protected static final String USER_AGENT = "Mozilla/5.0";
	private HttpURLConnection connection;

	/**
	 * Constructor for the HttpRequest
	 *
	 * @param url the URL
	 */
	protected HttpRequest(URL url)
	{
		try
		{
			this.connection = (HttpURLConnection) url.openConnection();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Sends a HTTP request to the connection.
	 *
	 * @return The response from the server
	 * @throws IOException if an error occurred connecting to the server.
	 */
	public abstract StringBuilder sendRequest() throws IOException;

	/**
	 * Gets the status code from an HTTP response message
	 *
	 * @return the HTTP Status-Code, or -1
	 */
	public int getResponseCode()
	{
		int responseCode = -1;

		try
		{
			responseCode = connection.getResponseCode();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return responseCode;
	}

	protected void setRequestMethod(String requestMethod)
	{
		try
		{
			connection.setRequestMethod(requestMethod);
		} catch (ProtocolException e)
		{
			e.printStackTrace();
		}
	}

	protected void setRequestProperty(String key, String value)
	{
		connection.setRequestProperty(key, value);
	}

	protected void setDoOutput(boolean doOutput)
	{
		connection.setDoOutput(doOutput);
	}

	protected OutputStream getOutputStream()
	{
		OutputStream outputStream = null;

		try
		{
			outputStream =  connection.getOutputStream();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return outputStream;
	}

	protected StringBuilder readInputStreamFromConnection() throws IOException
	{
		BufferedReader inputStream = getInputStreamReader();
		StringBuilder response = new StringBuilder();

		String inputLine;
		while((inputLine = inputStream.readLine()) != null)
		{
			response.append(inputLine);
		}

		inputStream.close();

		return response;
	}

	private BufferedReader getInputStreamReader()
	{
		BufferedReader inputStream = null;

		try
		{
			inputStream = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		return inputStream;
	}


	@Override
	public String toString()
	{
		return "HttpRequest{" +
				"connection=" + connection +
				'}';
	}
}
