package android.job.finder.model;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

public class LocationItemList {
	@Attribute
	private String stat;
	@ElementList
	private List<LocationItem> locations;

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public List<LocationItem> getLocations() {
		return locations;
	}

	public void setLocations(List<LocationItem> locations) {
		this.locations = locations;
	}
}
