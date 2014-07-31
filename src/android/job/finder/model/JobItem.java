package android.job.finder.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Job model class
 */
public class JobItem {
	@Attribute(required = false)
	private String id;
	@Attribute(required = false)
	private String title;
	@Attribute(required = false)
	private String description;
	@Attribute(required = false)
	private String perks;
	@Attribute(required = false)
	private String howto_apply;
	@Attribute(required = false)
	private String post_date;
	@Element(required = false)
	private JobCategoryItem category;
	@Element(required = false)
	private JobTypeItem type;
	@Element(required = false)
	private CompanyItem company;
	@Attribute(required = false)
	private String relocation_assistance;
	@Attribute(required = false)
	private String telecommuting;
	@Attribute(required = false)
	private String keywords;
	@Attribute(required = false)
	private String apply_email;
	@Attribute(required = false)
	private String url;
	@Attribute(required = false)
	private String apply_url;
	private Search search;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPerks() {
		return perks;
	}

	public void setPerks(String perks) {
		this.perks = perks;
	}

	public String getHowto_apply() {
		return howto_apply;
	}

	public void setHowto_apply(String howto_apply) {
		this.howto_apply = howto_apply;
	}

	public String getPost_date() {
		return post_date;
	}

	public void setPost_date(String post_date) {
		this.post_date = post_date;
	}

	public JobCategoryItem getCategory() {
		return category;
	}

	public void setCategory(JobCategoryItem category) {
		this.category = category;
	}

	public JobTypeItem getType() {
		return type;
	}

	public void setType(JobTypeItem type) {
		this.type = type;
	}

	public CompanyItem getCompany() {
		return company;
	}

	public void setCompany(CompanyItem company) {
		this.company = company;
	}

	public String getRelocation_assistance() {
		return relocation_assistance;
	}

	public void setRelocation_assistance(String relocation_assistance) {
		this.relocation_assistance = relocation_assistance;
	}

	public String getTelecommuting() {
		return telecommuting;
	}

	public void setTelecommuting(String telecommuting) {
		this.telecommuting = telecommuting;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getApply_email() {
		return apply_email;
	}

	public void setApply_email(String apply_email) {
		this.apply_email = apply_email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getApply_url() {
		return apply_url;
	}

	public void setApply_url(String apply_url) {
		this.apply_url = apply_url;
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}
}
