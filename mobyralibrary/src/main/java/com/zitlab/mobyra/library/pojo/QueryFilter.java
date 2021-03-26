package com.zitlab.mobyra.library.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Query builder.
 */
public class QueryFilter {

    private List<String> fields;
    private List<String> orderBy;
    private List<String> groupBy;
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
     * Gets order by.
     *
     * @return the order by
     */
    public List<String> getOrderBy() {
        return orderBy;
    }

    /**
     * Sets order by ascending.
     *
     * @param fields the fields
     */
    public void setOrderByAscending(List<String> fields) {
        if (null != fields && fields.size() > 0) {
            orderBy = new ArrayList<>();
            for (String field : fields) {
                if (null != field) {
                    String condition = String.format("+%s", field);
                    orderBy.add(condition);
                }
            }
        }
    }

    /**
     * Sets order by descending.
     *
     * @param orderBy the order by
     */
    public void setOrderByDescending(List<String> orderBy) {
        if (null != fields && fields.size() > 0) {
            orderBy = new ArrayList<>();
            for (String field : fields) {
                if (null != field) {
                    String condition = String.format("-%s", field);
                    orderBy.add(condition);
                }
            }
        }
    }

    /**
     * Gets group by.
     *
     * @return the group by
     */
    public List<String> getGroupBy() {
        return groupBy;
    }

    /**
     * Sets group by.
     *
     * @param groupBy the group by
     */
    public void setGroupBy(List<String> groupBy) {
        this.groupBy = groupBy;
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
     * @param criteria the criteria
     */
    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }
}
