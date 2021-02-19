package com.zitlab.mobyra.library.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Tuple meta info.
 */
public class TupleMetaInfo {
	
	private String type;
	private Integer action;
	private String label;
	private String error;
	private String labelFormat;
	private Map<String, Object> modAttributes;

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
		return type;
	}

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
		this.type = type;
	}

    /**
     * Gets action.
     *
     * @return the action
     */
    public Integer getAction() {
		return action;
	}

    /**
     * Sets action.
     *
     * @param action the action
     */
    public void setAction(Integer action) {
		this.action = action;
	}

    /**
     * Sets action.
     *
     * @param actn the actn
     */
    public void setAction(String actn) {
		if (null == actn) {
			this.action = null;
			return;
		}

		this.action = Action.getInt(actn);
		if(this.action == Action.UPDATE && null == modAttributes)
			modAttributes = new HashMap<String, Object>();
	}

    /**
     * Gets label format.
     *
     * @return the label format
     */
    public String getLabelFormat() {
		return labelFormat;
	}

    /**
     * Sets label format.
     *
     * @param labelFormat the label format
     */
    public void setLabelFormat(String labelFormat) {
		this.labelFormat = labelFormat;
	}

    /**
     * Gets mod attributes.
     *
     * @return the mod attributes
     */
    public Map<String, Object> getModAttributes() {
		return modAttributes;
	}

    /**
     * Sets mod attributes.
     *
     * @param modAttributes the mod attributes
     */
    public void setModAttributes(Map<String, Object> modAttributes) {
		this.modAttributes = modAttributes;
	}

    /**
     * Gets label.
     *
     * @return the label
     */
    public String getLabel() {
		return label;
	}

    /**
     * Sets label.
     *
     * @param label the label
     */
    public void setLabel(String label) {
		this.label = label;
	}

    /**
     * For create boolean.
     *
     * @return the boolean
     */
    public boolean forCreate() {
		return null == this.action ? false : this.action == Action.CREATE;
	}

    /**
     * For delete boolean.
     *
     * @return the boolean
     */
    public boolean forDelete() {
		return null == this.action ? false : this.action == Action.DELETE;
	}

    /**
     * For update boolean.
     *
     * @return the boolean
     */
    public boolean forUpdate() {
		return null == this.action ? false : this.action == Action.UPDATE;
	}

    /**
     * Gets action code.
     *
     * @return the action code
     */
    public Integer getActionCode() {
		return action;
	}

    /**
     * Sets action code.
     *
     * @param action the action
     */
    public void setActionCode(Integer action) {
		this.action = action;
		if (Action.UPDATE == action && null == modAttributes)
			modAttributes = new HashMap<String, Object>();
	}

    /**
     * Gets error.
     *
     * @return the error
     */
    public String getError() {
		return error;
	}

    /**
     * Sets error.
     *
     * @param error the error
     */
    public void setError(String error) {
		this.error = error;
	}

}
