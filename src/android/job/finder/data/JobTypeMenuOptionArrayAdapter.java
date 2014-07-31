package android.job.finder.data;

import java.util.List;

import android.content.Context;
import android.job.finder.model.JobTypeItem;
import android.widget.ArrayAdapter;

public class JobTypeMenuOptionArrayAdapter extends ArrayAdapter<JobTypeItem> {

	public JobTypeMenuOptionArrayAdapter(Context context, int layoutId,
			List<JobTypeItem> jobTypeItemList) {
		super(context, layoutId, jobTypeItemList);
	}
}
