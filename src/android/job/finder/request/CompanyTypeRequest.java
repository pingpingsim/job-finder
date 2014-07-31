package android.job.finder.request;

import android.job.finder.R;
import android.content.Context;
import android.job.finder.model.CompanyTypeItemList;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

/**
 * Company type robospice request object
 * 
 * @author sim
 * 
 */
public class CompanyTypeRequest extends
		SpringAndroidSpiceRequest<CompanyTypeItemList> {
	private final Context context;

	public CompanyTypeRequest(Context context) {
		super(CompanyTypeItemList.class);
		this.context = context;
	}

	@Override
	public CompanyTypeItemList loadDataFromNetwork() throws Exception {
		String url = String.format(context.getString(R.string.api_base_url),
				context.getString(R.string.api_key),
				context.getString(R.string.api_company_type),
				context.getString(R.string.api_format_xml));

		return getRestTemplate().getForObject(url, CompanyTypeItemList.class);
	}

	public String createCacheKey() {
		return null;
	}
}
