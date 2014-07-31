package android.job.finder.data;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.job.finder.R;
import android.job.finder.activities.JobDetailsActivity;
import android.job.finder.model.JobItem;
import android.job.finder.util.StringUtils;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class JobListingArrayAdapter extends ArrayAdapter<JobItem> {
	private Context context;
	private List<JobItem> data;

	public JobListingArrayAdapter(Context context, int layoutResourceId,
			List<JobItem> data) {
		super(context, layoutResourceId, data);
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;

		if (view == null) {
			final LayoutInflater layoutInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.job_listing_row, null);
		}

		final JobItem jobItem = data.get(position);

		if (jobItem != null) {
			TextView jobTitleTextView = (TextView) view
					.findViewById(R.id.list_job_title);
			TextView jobCompanyTextView = (TextView) view
					.findViewById(R.id.list_job_company);
			TextView jobTypeTextView = (TextView) view
					.findViewById(R.id.list_job_type);
			TextView jobdatePostedTextView = (TextView) view
					.findViewById(R.id.list_job_date_posted);

			String jobTitle = "";
			String jobType = "";
			String jobCompany = "";
			String jobPostDate = "";
			String jobCategory = "";

			jobTitle = jobItem.getTitle();

			if (jobItem.getCompany() != null) {
				jobCompany = context.getString(R.string.job_company)
						+ context.getString(R.string.semicolon_separator) + " "
						+ jobItem.getCompany().getName();

				if (jobItem.getCompany().getLocation() != null) {
					jobCompany += " ("
							+ jobItem.getCompany().getLocation().getName()
							+ ")";
				}

				ImageView companyLogoImageView = (ImageView) view
						.findViewById(R.id.imageview_company_logo);
				Picasso.with(context).load(jobItem.getCompany().getLogo())
						.into(companyLogoImageView);
			}
			if (jobItem.getType() != null) {
				jobType = jobItem.getType().getName();

			}
			if (jobItem.getCategory() != null) {
				jobType += " | " + jobItem.getCategory().getName();
			}
			jobPostDate = context.getString(R.string.job_date_posted)
					+ context.getString(R.string.semicolon_separator) + " "
					+ jobItem.getPost_date();

			jobTitleTextView.setText(StringUtils.stripHtml(jobTitle));
			jobCompanyTextView.setText(StringUtils.stripHtml(jobCompany));
			jobTypeTextView.setText(StringUtils.stripHtml(jobType));
			jobdatePostedTextView.setText(StringUtils.stripHtml(jobPostDate));

			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					final ListView parent = (ListView) v.getParent();
					final int pos = parent.getPositionForView(v);
					final JobItem jobItem = (JobItem) parent
							.getItemAtPosition(pos);
					startJobDetailsActivity(jobItem);
				}
			});
		}
		return view;
	}

	private void startJobDetailsActivity(JobItem jobItem) {
		final Intent intent = new Intent(context, JobDetailsActivity.class);
		final Bundle bundle = new Bundle();
		bundle.putString(context.getString(R.string.bundle_key_job),
				new Gson().toJson(jobItem));
		intent.putExtras(bundle);
		context.startActivity(intent);
	}
}
