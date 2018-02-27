package com.bdms.core.solr;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(solrCoreName = "label_manager")
public class LabelManager {

	public static final String FIELD_LABELMANAGER_ID = "id";

	public static final String FIELD_LABELMANAGER_SOURCE = "source";

	public static final String FIELD_LABELMANAGER_LABEL = "label";

	public static final String FIELD_LABELMANAGER_LINKPARMS = "linkParms";

	public static final String FIELD_LABELMANAGER_TITLE = "title";
	
	public static final String FIELD_LABELMANAGER_TIME = "time";
	
	@Id
	@Field(value=FIELD_LABELMANAGER_ID)
	private String id;
	
	@Field(value=FIELD_LABELMANAGER_SOURCE)
	private String source;
	
	@Field(value=FIELD_LABELMANAGER_LABEL)
	private String label;
	
	@Field(value=FIELD_LABELMANAGER_LINKPARMS)
	private String linkParms;
	
	@Field(value=FIELD_LABELMANAGER_TITLE)
	private String title;
	
	@Field(value=FIELD_LABELMANAGER_TIME)
	private String time;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLinkParms() {
		return linkParms;
	}

	public void setLinkParms(String linkParms) {
		this.linkParms = linkParms;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
}
