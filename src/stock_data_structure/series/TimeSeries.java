package stock_data_structure.series;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimeSeries<T>
{
	private String stockSymbol;
	private List<T> timeSeriesDataPoints;

	public TimeSeries(String stockSymbol)
	{
		this(stockSymbol, new ArrayList<T>());
	}

	public TimeSeries(String stockSymbol, List<T> timeSeriesDataPoints)
	{
		this.timeSeriesDataPoints = timeSeriesDataPoints;
		this.stockSymbol = stockSymbol;
	}

	public void addDataPoint(T dataPoint)
	{
		this.timeSeriesDataPoints.add(dataPoint);
	}

	public int length()
	{
		return this.timeSeriesDataPoints.size();
	}

	public String getStockSymbol()
	{
		return this.stockSymbol;
	}

	public List<T> getTimeSeries()
	{
		return Collections.unmodifiableList(this.timeSeriesDataPoints);
	}

	@Override
	public String toString()
	{
		return "TimeSeries{" +
				"stockSymbol='" + stockSymbol + '\'' +
				", timeSeriesDataPoints=" + timeSeriesDataPoints +
				'}';
	}
}
