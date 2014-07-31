package android.job.finder.activities;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.job.finder.R;
import android.job.finder.data.JobCategoryMenuOptionArrayAdapter;
import android.job.finder.data.JobTypeMenuOptionArrayAdapter;
import android.job.finder.model.JobCategoryItem;
import android.job.finder.model.JobCategoryItemList;
import android.job.finder.model.JobItemList;
import android.job.finder.model.JobTypeItem;
import android.job.finder.model.JobTypeItemList;
import android.job.finder.model.Search;
import android.job.finder.request.JobCategoryRequest;
import android.job.finder.request.JobRequest;
import android.job.finder.request.JobTypeRequest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.octo.android.robospice.exception.NoNetworkException;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class JobSearchActivity extends BaseActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.job_search);
		setTitle(getString(R.string.job_search));
		// final ActionBar actionBar = getActionBar();
		// actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
		// | ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);
		// actionBar.setDisplayHomeAsUpEnabled(true);

		triggerJobTypeRequest(this);
		triggerJobCategoryRequest(this);
		initializeJobSearchBtn();
	}

	private void initializeJobSearchBtn() {
		Button jobSearchBtn = (Button) findViewById(R.id.button_submit_job_search);
		jobSearchBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// get field values and trigger job search request
				Spinner categorySpinner = (Spinner) JobSearchActivity.this
						.findViewById(R.id.job_categories_spinner);
				JobCategoryItem selectedJobCategory = (JobCategoryItem) categorySpinner
						.getSelectedItem();
				String categoryId = selectedJobCategory.getId();

				Spinner typeSpinner = (Spinner) JobSearchActivity.this
						.findViewById(R.id.job_types_spinner);
				JobTypeItem selectedJobType = (JobTypeItem) typeSpinner
						.getSelectedItem();
				String typeId = selectedJobType.getId();

				EditText companyEditText = (EditText) JobSearchActivity.this
						.findViewById(R.id.edit_text_company);
				String company = companyEditText.getText().toString();

				EditText locationEditText = (EditText) JobSearchActivity.this
						.findViewById(R.id.edit_text_location);
				String location = locationEditText.getText().toString();

				CheckBox telecommutingCheckbox = (CheckBox) findViewById(R.id.checkbox_telecommuting);
				boolean isTelcommuting = telecommutingCheckbox.isChecked();
				String telecommuting = ((isTelcommuting == true) ? "1" : "0");

				// data validation??
				Search searchParameters = new Search();
				searchParameters.setCategory(categoryId);
				searchParameters.setType(typeId);
				searchParameters.setCompany(company);
				searchParameters.setLocation(location);
				searchParameters.setTelecommuting(telecommuting);
				triggerJobRequest(JobSearchActivity.this, searchParameters);
			}
		});
	}

	private void triggerJobRequest(Context context, Search searchParameters) {
		final JobRequest request = new JobRequest(context, searchParameters,
				null);
		this.getSpiceManager().execute(request, request.createCacheKey(),
				DurationInMillis.ALWAYS_EXPIRED, new JobListener());
	}

	private class JobListener implements RequestListener<JobItemList> {
		@Override
		public void onRequestFailure(SpiceException e) {

		}

		@Override
		public void onRequestSuccess(JobItemList jobItemList) {
			showResultInJobListingActivity(jobItemList);
		}
	}

	private void showResultInJobListingActivity(JobItemList jobItemList) {
		final Intent intent = new Intent(JobSearchActivity.this,
				JobListingActivity.class);
		final Bundle bundle = new Bundle();
		bundle.putString(
				JobSearchActivity.this.getString(R.string.bundle_key_job_items),
				new Gson().toJson(jobItemList));
		intent.putExtras(bundle);
		JobSearchActivity.this.startActivity(intent);
	}

	@SuppressWarnings("deprecation")
	private void triggerJobTypeRequest(Context context) {
		final JobTypeRequest request = new JobTypeRequest(context);
		this.getSpiceManager().getFromCache(JobTypeItemList.class,
				request.getCacheKey(), DurationInMillis.ALWAYS_RETURNED,
				new JobTypeListener());

		this.getSpiceManager().execute(request, request.getCacheKey(),
				DurationInMillis.NEVER, new JobTypeListener());
	}

	private class JobTypeListener implements RequestListener<JobTypeItemList> {
		boolean dataInCache = false;

		@Override
		public void onRequestFailure(SpiceException exception) {
			if (exception instanceof NoNetworkException && dataInCache) {
				// Ignore network problems if there is some data in the cache.
				return;
			}
		}

		@Override
		public void onRequestSuccess(JobTypeItemList jobTypeItemList) {
			dataInCache = true;

			if (jobTypeItemList != null && jobTypeItemList.getTypes() != null) {
				final List<JobTypeItem> SpinnerArray = new ArrayList<JobTypeItem>();
				final JobTypeMenuOptionArrayAdapter adapter = new JobTypeMenuOptionArrayAdapter(
						JobSearchActivity.this, R.layout.spinner_item,
						SpinnerArray);
				for (JobTypeItem jobTypeItem : jobTypeItemList.getTypes()) {
					SpinnerArray.add(jobTypeItem);
				}
				Spinner spinner = (Spinner) JobSearchActivity.this
						.findViewById(R.id.job_types_spinner);
				spinner.setAdapter(adapter);
			}
		}
	}

	@SuppressWarnings("deprecation")
	private void triggerJobCategoryRequest(Context context) {
		final JobCategoryRequest request = new JobCategoryRequest(context);
		this.getSpiceManager().getFromCache(JobCategoryItemList.class,
				request.getCacheKey(), DurationInMillis.ALWAYS_RETURNED,
				new JobCategoryListener());

		this.getSpiceManager().execute(request, request.getCacheKey(),
				DurationInMillis.NEVER, new JobCategoryListener());
	}

	private class JobCategoryListener implements
			RequestListener<JobCategoryItemList> {
		boolean dataInCache = false;

		@Override
		public void onRequestFailure(SpiceException exception) {
			if (exception instanceof NoNetworkException && dataInCache) {
				// Ignore network problems if there is some data in the cache.
				return;
			}
		}

		@Override
		public void onRequestSuccess(JobCategoryItemList jobCategoryItemList) {
			dataInCache = true;
			if (jobCategoryItemList != null
					&& jobCategoryItemList.getCategories() != null) {
				final List<JobCategoryItem> SpinnerArray = new ArrayList<JobCategoryItem>();
				final JobCategoryMenuOptionArrayAdapter adapter = new JobCategoryMenuOptionArrayAdapter(
						JobSearchActivity.this, R.layout.spinner_item,
						SpinnerArray);
				for (JobCategoryItem jobCategoryItem : jobCategoryItemList
						.getCategories()) {
					SpinnerArray.add(jobCategoryItem);
				}
				Spinner spinner = (Spinner) JobSearchActivity.this
						.findViewById(R.id.job_categories_spinner);
				spinner.setAdapter(adapter);
			}
		}
	}
}
