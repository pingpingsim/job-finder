package android.job.finder.request;

import android.job.finder.R;
import android.content.Context;
import android.job.finder.model.CompanyItemList;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

/**
 * Company robospice request object
 * 
 * @author sim
 * 
 */
public class CompanyRequest extends SpringAndroidSpiceRequest<CompanyItemList> {
	private final Context context;

	public CompanyRequest(Context context) {
		super(CompanyItemList.class);
		this.context = context;
	}

	@Override
	public CompanyItemList loadDataFromNetwork() throws Exception {
		String url = String.format(context.getString(R.string.api_base_url),
				context.getString(R.string.api_key),
				context.getString(R.string.api_company),
				context.getString(R.string.api_format_xml));

		return getRestTemplate().getForObject(url, CompanyItemList.class);
	}

	public String createCacheKey() {
		return null;
	}
}