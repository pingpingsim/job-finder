package android.job.finder.model;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

public class CompanyTypeItemList {
	@Attribute
	private String stat;
	@ElementList
	private List<CompanyTypeItem> company_types;

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public List<CompanyTypeItem> getCompany_types() {
		return company_types;
	}

	public void setCompany_types(List<CompanyTypeItem> company_types) {
		this.company_types = company_types;
	}
}
