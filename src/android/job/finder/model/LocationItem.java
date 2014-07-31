package android.job.finder.model;

import org.simpleframework.xml.Attribute;

/**
 * Location model class
 */
public class LocationItem extends BaseModel {
	@Attribute(required=false)
	private String city;
	@Attribute(required=false)
	private String country;
	@Attribute(required=false)
	private String lat;
	@Attribute(required=false)
	private String lng;
	@Attribute(required=false)
	private String state;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
