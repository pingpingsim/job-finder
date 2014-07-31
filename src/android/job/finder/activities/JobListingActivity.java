package android.job.finder.activities;

import java.util.List;

import android.job.finder.R;
import android.content.Context;
import android.content.Intent;
import android.job.finder.data.JobListingArrayAdapter;
import android.job.finder.model.JobItem;
import android.job.finder.model.JobItemList;
import android.job.finder.model.Search;
import android.job.finder.request.JobRequest;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class JobListingActivity extends BaseActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.job_listing);
		setTitle(getString(R.string.job_listing_title));

		final Bundle bundle = getIntent().getExtras();
		if (bundle != null
				&& bundle.containsKey(getString(R.string.bundle_key_job_items))) {
			final String jobItemListJson = bundle
					.getString(getString(R.string.bundle_key_job_items));
			JobItemList jobItemList = new Gson().fromJson(jobItemListJson,
					JobItemList.class);
			populateJobItemsListView(jobItemList.getListings());
		} else {
			triggerJobRequest(this, new Search());
		}
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
			populateJobItemsListView(jobItemList.getListings());
		}
	}

	private void populateJobItemsListView(List<JobItem> jobItemList) {
		ListView jobListview = (ListView) findViewById(R.id.listview_job_listing);
		final JobListingArrayAdapter jobArrayAdapter = new JobListingArrayAdapter(
				JobListingActivity.this, R.layout.job_listing_row, jobItemList);
		jobListview.setAdapter(jobArrayAdapter);
		jobArrayAdapter.notifyDataSetChanged();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_search:
			final Intent intent = new Intent(this, JobSearchActivity.class);
			this.startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
