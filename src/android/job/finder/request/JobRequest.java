package android.job.finder.request;

import android.job.finder.R;
import android.content.Context;
import android.job.finder.model.JobItemList;
import android.job.finder.model.Search;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

/**
 * Job robospice request object
 * 
 * @author sim
 * 
 */
public class JobRequest extends SpringAndroidSpiceRequest<JobItemList> {
	private final Context context;
	private final Search searchParameters;
	private final String jobId;

	/**
	 * @param context
	 *            Application context
	 * @param searchParameters
	 *            Provide search parameters to filter by optional search
	 *            parameters
	 * @param jobId
	 *            Provide job ID to filter by specific job ID
	 */
	public JobRequest(Context context, Search searchParameters, String jobId) {
		super(JobItemList.class);
		this.context = context;
		this.searchParameters = searchParameters;
		this.jobId = jobId;
	}

	@Override
	public JobItemList loadDataFromNetwork() throws Exception {
		String url = "";
		if (searchParameters != null) {
			url = String.format(context.getString(R.string.api_base_url),
					context.getString(R.string.api_key), String.format(
							context.getString(R.string.api_search),
							searchParameters.getCategory(),
							searchParameters.getType(),
							searchParameters.getSort(),
							searchParameters.getCompany(),
							searchParameters.getLocation(),
							searchParameters.getTelecommuting(),
							searchParameters.getKeywords(),
							searchParameters.getBegin_date(),
							searchParameters.getEnd_date(),
							searchParameters.getPage(),
							searchParameters.getPerpage()), context
							.getString(R.string.api_format_xml));
		} else {
			url = String.format(context.getString(R.string.api_base_url),
					context.getString(R.string.api_key),
					String.format(context.getString(R.string.api_job), jobId),
					context.getString(R.string.api_format_xml));
		}
		System.out.println(url);
		return getRestTemplate().getForObject(url, JobItemList.class);
	}

	public String createCacheKey() {
		return null;
	}
}
