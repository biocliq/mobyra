package com.zitlab.mobyra.library.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Query builder.
 */
public class QueryFilter extends FieldCriteriaQueryFilter {

    private List<String> orderBy;
    private List<String> groupBy;

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
     * @param fields the order by
     */
    public void setOrderByDescending(List<String> fields) {
        if (null != fields && fields.size() > 0) {
            this.orderBy = new ArrayList<>();
            for (String field : fields) {
                if (null != field) {
                    String condition = String.format("-%s", field);
                    this.orderBy.add(condition);
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

}
