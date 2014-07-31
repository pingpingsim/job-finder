package android.job.finder.activities;

import java.util.ArrayList;

import android.app.ActionBar;
import android.job.finder.R;
import android.job.finder.data.ExpandListAdapter;
import android.job.finder.model.ExpandListChild;
import android.job.finder.model.ExpandListGroup;
import android.job.finder.model.JobItem;
import android.job.finder.util.StringUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class JobDetailsActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.job_details);
		setTitle(getString(R.string.job_details_title));
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
				| ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);
		actionBar.setDisplayHomeAsUpEnabled(true);
		final Bundle bundle = getIntent().getExtras();
		// deserialized bundle json format object string to job item object and
		// load the information
		if (bundle != null
				&& bundle.containsKey(getString(R.string.bundle_key_job))) {
			final String jobItemJson = bundle
					.getString(getString(R.string.bundle_key_job));
			JobItem jobItem = new Gson().fromJson(jobItemJson, JobItem.class);
			loadJobDetails(jobItem);
		}
	}

	private void loadJobDetails(JobItem jobItem) {
		TextView titleTextView = (TextView) findViewById(R.id.textview_title);
		titleTextView.setText(jobItem.getTitle());

		TextView companyTextView = (TextView) findViewById(R.id.textview_company);
		if (jobItem.getCompany() != null) {
			String companyStr = "";
			companyStr = jobItem.getCompany().getName();
			if (jobItem.getCompany().getLocation() != null) {
				companyStr += " ("
						+ jobItem.getCompany().getLocation().getName() + ")";
			}
			companyTextView.setText(companyStr);
		}

		TextView typeTextView = (TextView) findViewById(R.id.textview_type);
		String type = "";
		if (jobItem.getType() != null)
			type += jobItem.getType().getName();
		if (jobItem.getCategory() != null)
			type += " | " + jobItem.getCategory().getName();
		typeTextView.setText(type);

		ImageView checkTelecommutingImageView = (ImageView) findViewById(R.id.imageview_checked_telecommuting);
		ImageView uncheckTelecommutingImageView = (ImageView) findViewById(R.id.imageview_unchecked_telecommuting);
		if (jobItem.getTelecommuting().equals("1")) {
			checkTelecommutingImageView.setVisibility(View.VISIBLE);
			uncheckTelecommutingImageView.setVisibility(View.GONE);
		} else {
			checkTelecommutingImageView.setVisibility(View.GONE);
			uncheckTelecommutingImageView.setVisibility(View.VISIBLE);
		}

		ImageView checkRelocationAssistanceImageView = (ImageView) findViewById(R.id.imageview_checked_relocation_assistance);
		ImageView uncheckRelocationAssistanceImageView = (ImageView) findViewById(R.id.imageview_unchecked_relocation_assistance);
		if (jobItem.getRelocation_assistance().equals("1")) {
			checkRelocationAssistanceImageView.setVisibility(View.VISIBLE);
			uncheckRelocationAssistanceImageView.setVisibility(View.GONE);
		} else {
			checkRelocationAssistanceImageView.setVisibility(View.GONE);
			uncheckRelocationAssistanceImageView.setVisibility(View.VISIBLE);
		}

		TextView howToApplyTextView = (TextView) findViewById(R.id.textview_how_to_apply);
		howToApplyTextView.setText((StringUtils.isNullOrEmpty(jobItem
				.getHowto_apply()) ? "" : jobItem.getHowto_apply() + "\n")
				+ (StringUtils.isNullOrEmpty(jobItem.getApply_email()) ? ""
						: jobItem.getApply_email() + "\n")
				+ (StringUtils.isNullOrEmpty(jobItem.getApply_url()) ? ""
						: jobItem.getApply_url()));

		ImageView companyLogoImageView = (ImageView) findViewById(R.id.imageview_company_logo);
		Picasso.with(this).load(jobItem.getCompany().getLogo())
				.into(companyLogoImageView);

		ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.expandable_listview_job_info);
		ArrayList<ExpandListGroup> expandableListItems = getExpandableListViewRowData(jobItem);
		ExpandListAdapter expandableAdapter = new ExpandListAdapter(
				JobDetailsActivity.this, expandableListItems);
		expandableListView.setAdapter(expandableAdapter);
	}

	public ArrayList<ExpandListGroup> getExpandableListViewRowData(
			JobItem jobItem) {
		ArrayList<ExpandListGroup> groupList = new ArrayList<ExpandListGroup>();
		ArrayList<ExpandListChild> childList = new ArrayList<ExpandListChild>();

		ExpandListGroup groupDesc = new ExpandListGroup();
		groupDesc.setName(getString(R.string.job_details_desc));
		ExpandListChild childDesc = new ExpandListChild();
		childDesc.setName(jobItem.getDescription());
		childDesc.setTag(null);
		childList.add(childDesc);
		groupDesc.setItems(childList);
		groupList.add(groupDesc);

		childList = new ArrayList<ExpandListChild>();
		ExpandListGroup groupPerks = new ExpandListGroup();
		groupPerks.setName(getString(R.string.job_details_perks));
		ExpandListChild childPerks = new ExpandListChild();
		childPerks.setName(jobItem.getPerks());
		childPerks.setTag(null);
		childList.add(childPerks);
		groupPerks.setItems(childList);
		groupList.add(groupPerks);

		if (jobItem.getCompany() != null) {
			String companyProfileStr = "";
			childList = new ArrayList<ExpandListChild>();
			ExpandListGroup groupCompanyDesc = new ExpandListGroup();
			groupCompanyDesc
					.setName(getString(R.string.job_details_company_profile));
			ExpandListChild childJobDesc = new ExpandListChild();
			companyProfileStr += jobItem.getCompany().getName() + "</BR>"
					+ jobItem.getCompany().getType() + "</BR>"
					+ jobItem.getCompany().getTagline() + "</BR>"
					+ jobItem.getCompany().getUrl();
			childJobDesc.setName(companyProfileStr);
			childJobDesc.setTag(null);
			childList.add(childJobDesc);
			groupCompanyDesc.setItems(childList);
			groupList.add(groupCompanyDesc);
		}

		return groupList;
	}
}
