/**
 * <LICENSE/>
 */
package com.zitlab.mobyra.library.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Field list.
 */
public class FieldList {
	private List<String> attributes = new ArrayList<>();
	private Map<String, FieldList> reference;

    /**
     * Instantiates a new Field list.
     */
    public FieldList() {
		
	}

    /**
     * Gets attributes.
     *
     * @return the attributes
     */
    public List<String> getAttributes() {
		return attributes;
	}

    /**
     * Sets attributes.
     *
     * @param attributes the attributes
     */
    public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}

    /**
     * Add field.
     *
     * @param field the field
     */
    public void addField(String field) {
		int index = field.indexOf('.');
		if(index < 0) {
			this.attributes.add(field);	
		}else {
			String ref = field.substring(0, index);
			String _field = field.substring(index +1);
			FieldList list = getFieldList(ref);
			list.addField(_field);
		}		
	}
	
	private FieldList getFieldList(String key) {
		FieldList result = null;
		
		if(null != reference) {			
			result = reference.get(key);
		}else
			reference = new HashMap<String, FieldList>();
		
		if(null == result) {
			result = new FieldList();
			reference.put(key, result);
		}
		return result;
	}

    /**
     * Gets reference.
     *
     * @return the reference
     */
    public Map<String, FieldList> getReference() {
		return reference;
	}

    /**
     * Sets reference.
     *
     * @param reference the reference
     */
    public void setReference(Map<String, FieldList> reference) {
		this.reference = reference;
	}
}
