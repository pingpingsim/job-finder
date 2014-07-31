package android.job.finder.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Company model class
 */
public class CompanyItem extends BaseModel {
	@Attribute(required = false)
	private String url;
	@Attribute(required = false)
	private String logo;
	@Attribute(required = false)
	private String tagline;
	@Attribute(required = false)
	private String type;
	@Element(required = false)
	private LocationItem location;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getTagline() {
		return tagline;
	}

	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocationItem getLocation() {
		return location;
	}

	public void setLocation(LocationItem location) {
		this.location = location;
	}
}
