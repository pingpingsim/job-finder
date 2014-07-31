package android.job.finder.request;

import android.job.finder.R;
import android.content.Context;
import android.job.finder.model.JobTypeItemList;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

/**
 * Job type robospice request object
 * 
 * @author sim
 * 
 */
public class JobTypeRequest extends SpringAndroidSpiceRequest<JobTypeItemList> {
	private final Context context;

	public JobTypeRequest(Context context) {
		super(JobTypeItemList.class);
		this.context = context;
	}

	@Override
	public JobTypeItemList loadDataFromNetwork() throws Exception {
		String url = String.format(context.getString(R.string.api_base_url),
				context.getString(R.string.api_key),
				context.getString(R.string.api_job_type),
				context.getString(R.string.api_format_xml));

		return getRestTemplate().getForObject(url, JobTypeItemList.class);
	}

	public String getCacheKey() {
		return "job.category";
	}
}
