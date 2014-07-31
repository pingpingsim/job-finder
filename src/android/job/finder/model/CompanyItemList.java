package android.job.finder.model;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

public class CompanyItemList {
	@Attribute
	private String stat;
	@ElementList
	private List<CompanyItem> companies;

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public List<CompanyItem> getCompanies() {
		return companies;
	}

	public void setCompanies(List<CompanyItem> companies) {
		this.companies = companies;
	}
}
