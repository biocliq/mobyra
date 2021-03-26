package com.zitlab.mobyra.library.builder;

import com.zitlab.mobyra.library.pojo.QueryFilter;

/**
 * The type Pagination query builder.
 */
public final class PaginatedQueryFilter extends QueryFilter {
    private int offset;
    private int limit;
    private boolean total;

    /**
     * Gets offset.
     *
     * @return the offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Sets offset.
     *
     * @param offset the offset
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * Gets limit.
     *
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Sets limit.
     *
     * @param limit the limit
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * Is total boolean.
     *
     * @return the boolean
     */
    public boolean isTotal() {
        return total;
    }

    /**
     * Sets total.
     *
     * @param total the total
     */
    public void setTotal(boolean total) {
        this.total = total;
    }
}
