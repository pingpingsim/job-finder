package android.job.finder.activities;

import android.app.Activity;
import android.job.finder.DataController;
import android.os.Bundle;

import com.octo.android.robospice.SpiceManager;

public class BaseActivity extends Activity {
	private SpiceManager spiceManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		spiceManager = ((DataController) this.getApplication())
				.getSpiceManager(this);
		if (!spiceManager.isStarted()) {
			spiceManager.start(this);
		}
	}

	public SpiceManager getSpiceManager() {
		return spiceManager;
	}

	public void setSpiceManager(SpiceManager spiceManager) {
		this.spiceManager = spiceManager;
	}
}
