package api;

import org.json.JSONObject;
import stock_data_structure.data_point.OHLCV;
import stock_data_structure.series.TimeSeries;
import util.http.GetHttpRequest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AlphaVantage
{
	public TimeSeries<OHLCV> getOHLC(String stockSymbol) throws ParseException
	{
		GetHttpRequest request=null;
		try
		{
			 request = new GetHttpRequest("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=MSFT&apikey=demo");
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		JSONObject jsonObject = null;
		try
		{
			jsonObject = new JSONObject(request.sendRequest().toString());
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		List<OHLCV> series = new ArrayList<>();

		Iterator<String> keys = jsonObject.getJSONObject("Time Series (Daily)").keys();
		while(keys.hasNext())
		{
			String key = keys.next();
			OHLCV ohlcv = new OHLCV(
					new SimpleDateFormat("yyyy-MM-dd").parse(key).getTime(),
					jsonObject.getJSONObject("Time Series (Daily)").getJSONObject(key).getBigDecimal("1. open"),
					jsonObject.getJSONObject("Time Series (Daily)").getJSONObject(key).getBigDecimal("2. high"),
					jsonObject.getJSONObject("Time Series (Daily)").getJSONObject(key).getBigDecimal("3. low"),
					jsonObject.getJSONObject("Time Series (Daily)").getJSONObject(key).getBigDecimal("4. close"),
					jsonObject.getJSONObject("Time Series (Daily)").getJSONObject(key).getInt("5. volume")
			);
			series.add(ohlcv);
		}

		Collections.sort(series);

		TimeSeries<OHLCV> timeSeries = new TimeSeries<>("MSFT", series);
		System.out.println(timeSeries.toString());

		return null;
	}
}
