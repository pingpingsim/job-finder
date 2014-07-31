package android.job.finder.model;

import org.simpleframework.xml.Attribute;

/**
 * Base model class
 */
public class BaseModel {
	@Attribute
	private String id;
	@Attribute
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
