package com.zitlab.mobyra.library.pojo;

import com.zitlab.mobyra.library.builder.CriteriaBuilder;

import java.util.List;

/**
 * The type Field criteria query filter.
 */
public class FieldCriteriaQueryFilter {
    private List<String> fields;
    private Criteria criteria;

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

    /**
     * Gets criteria.
     *
     * @return the criteria
     */
    public Criteria getCriteria() {
        return criteria;
    }

    /**
     * Sets criteria.
     *
     * @param criteriaBuilder the criteria builder
     */
    public void setCriteria(CriteriaBuilder criteriaBuilder) {
        if (null != criteriaBuilder) {
            Criteria criteria = new Criteria();
            criteria.setCriteria(criteriaBuilder.getCriteriaMap());
            this.criteria = criteria;
        }
    }

}
