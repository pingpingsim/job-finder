package android.job.finder.model;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

public class JobTypeItemList {
	@Attribute
	private String stat;
	@ElementList
	private List<JobTypeItem> types;

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public List<JobTypeItem> getTypes() {
		return types;
	}

	public void setTypes(List<JobTypeItem> types) {
		this.types = types;
	}
}
