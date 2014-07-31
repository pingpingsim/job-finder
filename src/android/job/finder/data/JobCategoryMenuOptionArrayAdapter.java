package android.job.finder.data;

import java.util.List;

import android.content.Context;
import android.job.finder.model.JobCategoryItem;
import android.widget.ArrayAdapter;

public class JobCategoryMenuOptionArrayAdapter extends
		ArrayAdapter<JobCategoryItem> {

	public JobCategoryMenuOptionArrayAdapter(Context context, int layoutId,
			List<JobCategoryItem> jobCategoryItemList) {
		super(context, layoutId, jobCategoryItemList);
	}
}
