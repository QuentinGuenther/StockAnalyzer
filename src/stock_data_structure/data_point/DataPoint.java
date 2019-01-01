package stock_data_structure.data_point;

import java.util.Collections;
import java.util.Map;

public abstract class DataPoint<K, V> implements Comparable<DataPoint>
{
	private Map<K, V> columns;

	public DataPoint(Map<K, V> columns)
	{
		this.setColumns(columns);
	}

	public Map<K, V> getColumns()
	{
		return Collections.unmodifiableMap(columns);
	}

	protected void putAll(Map<K, V> columns)
	{
		this.columns.putAll(columns);
	}

	public void setColumns(Map<K, V> columns)
	{
		this.columns = columns;
	}

	@Override
	public String toString()
	{
		return "DataPoint{" +
				"columns=" + getColumns() +
				'}';
	}
}
