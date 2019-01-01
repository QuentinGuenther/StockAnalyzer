package stock_data_structure.data_point;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class OHLCV extends DataPoint<String, Object>
{

	public OHLCV(Long timestamp, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, int volume)
	{
		super(new HashMap<String, Object>());
		Map<String, Object> columns = new HashMap<>();
		columns.put("timestamp", timestamp);
		columns.put("open", open);
		columns.put("high", high);
		columns.put("low", low);
		columns.put("close", close);
		columns.put("volume", volume);
		this.putAll(columns);
	}

	@Override
	public int compareTo(DataPoint other)
	{
		return Long.compare((long)this.getColumns().get("timestamp"), (long)other.getColumns().get("timestamp"));
	}
}
