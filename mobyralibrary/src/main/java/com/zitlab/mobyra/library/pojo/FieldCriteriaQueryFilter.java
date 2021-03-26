package com.zitlab.mobyra.library.pojo;

import java.util.List;

/**
 * The type Field criteria query filter.
 */
public class FieldCriteriaQueryFilter extends Criteria {
    private List<String> fields;

    /**
     * Gets fields.
     *
     * @return the fields
     */
    public List<String> getFields() {
        return fields;
    }

    /**
     * Sets fields.
     *
     * @param fields the fields
     */
    public void setFields(List<String> fields) {
        if (null != fields && fields.size() > 0) {
            this.fields = fields;
        }
    }

}
