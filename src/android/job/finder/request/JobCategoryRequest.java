package android.job.finder.request;

import android.job.finder.R;
import android.content.Context;
import android.job.finder.model.JobCategoryItemList;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

/**
 * Job category robospice request object
 * 
 * @author sim
 * 
 */
public class JobCategoryRequest extends
		SpringAndroidSpiceRequest<JobCategoryItemList> {
	private final Context context;

	public JobCategoryRequest(Context context) {
		super(JobCategoryItemList.class);
		this.context = context;
	}

	@Override
	public JobCategoryItemList loadDataFromNetwork() throws Exception {
		String url = String.format(context.getString(R.string.api_base_url),
				context.getString(R.string.api_key),
				context.getString(R.string.api_job_category),
				context.getString(R.string.api_format_xml));

		return getRestTemplate().getForObject(url, JobCategoryItemList.class);
	}

	public String getCacheKey() {
		return "job.category";
	}
}