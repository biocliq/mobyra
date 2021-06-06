package com.zitlab.palmyra.pojo;

import com.zitlab.palmyra.builder.CriteriaBuilder;

import java.util.Map;

/**
 * The type Criteria.
 */
public class Criteria {
    private Map<String, String> criteria;

    /**
     * Gets criteria.
     *
     * @return the criteria
     */
    public Map<String, String> getCriteria() {
        return criteria;
    }

    /**
     * Sets criteria.
     *
     * @param criteriaBuilder the criteria builder
     */
    public void setCriteria(CriteriaBuilder criteriaBuilder) {
        if (null != criteriaBuilder) {
            this.criteria = criteriaBuilder.getCriteriaMap();
        }
    }
}
