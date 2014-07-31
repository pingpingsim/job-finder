package android.job.finder.model;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class JobCategoryItemList {
	@Attribute
	private String stat;
	@ElementList
	private List<JobCategoryItem> categories;

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public List<JobCategoryItem> getCategories() {
		return categories;
	}

	public void setCategories(List<JobCategoryItem> categories) {
		this.categories = categories;
	}
}
