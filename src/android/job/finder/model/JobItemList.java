package android.job.finder.model;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

public class JobItemList {
	@Attribute
	private String stat;
	@ElementList
	private List<JobItem> listings;

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public List<JobItem> getListings() {
		return listings;
	}

	public void setListings(List<JobItem> listings) {
		this.listings = listings;
	}
}
