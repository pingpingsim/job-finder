package android.job.finder.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Search model class
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Search {
	/**
	 * The id of a job category to limit to. See aj.categories.getList
	 */
	private String category = "";
	/**
	 * The id of a job type to limit to. See aj.types.getList
	 */
	private String type = "";
	/**
	 * Accepted values are: date-posted-desc (the default) and date-posted-asc
	 */
	private String sort = "";
	/**
	 * Free-text matching against company names. Suggested values are the ids
	 * from aj.jobs.getCompanies
	 */
	private String company = "";
	/**
	 * Free-text matching against company location names. Suggested values are
	 * the ids from aj.jobs.getLocation
	 */
	private String location = "";
	/**
	 * Set to 1 if you only want telecommuting jobs
	 */
	private String telecommuting = "";
	/**
	 * Keywords to look for in the title or description of the job posting.
	 * Separate multiple keywords with commas. Multiple keywords will be treated
	 * as an OR
	 */
	private String keywords = "";
	/**
	 * Unix timestamp. Listings posted before this time will not be returned
	 */
	private String begin_date = "";
	/**
	 * Unix timestamp. Listings posted after this time will not be returned
	 */
	private String end_date = "";
	/**
	 * The page of listings to return. Defaults to 1
	 */
	private String page = "";
	/**
	 * The number of listings per page. The default value is 10. The maximum
	 * value is 100.
	 */
	private String perpage = "";

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPerpage() {
		return perpage;
	}

	public void setPerpage(String perpage) {
		this.perpage = perpage;
	}
}
