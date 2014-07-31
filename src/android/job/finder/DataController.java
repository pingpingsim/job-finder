package android.job.finder;

import android.app.Application;
import android.content.Context;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.XmlSpringAndroidSpiceService;

public class DataController extends Application {
	private SpiceManager spiceManager;

	public SpiceManager getSpiceManager(Context context) {
		if (spiceManager == null) {
			spiceManager = new SpiceManager(XmlSpringAndroidSpiceService.class);
		}
		return spiceManager;
	}
}