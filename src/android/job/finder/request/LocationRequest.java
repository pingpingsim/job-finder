package android.job.finder.request;

import android.job.finder.R;
import android.content.Context;
import android.job.finder.model.LocationItemList;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

/**
 * Location robospice request object
 * 
 * @author sim
 * 
 */
public class LocationRequest extends
		SpringAndroidSpiceRequest<LocationItemList> {
	private final Context context;

	public LocationRequest(Context context) {
		super(LocationItemList.class);
		this.context = context;
	}

	@Override
	public LocationItemList loadDataFromNetwork() throws Exception {
		String url = String.format(context.getString(R.string.api_base_url),
				context.getString(R.string.api_key),
				context.getString(R.string.api_location),
				context.getString(R.string.api_format_xml));

		return getRestTemplate().getForObject(url, LocationItemList.class);
	}

	public String createCacheKey() {
		return null;
	}
}