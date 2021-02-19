package com.zitlab.mobyra.library.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Tuple filter.
 *
 * @param <T> the type parameter
 */
public class TupleFilter<T>{

    private T criteria;
    private FieldList fields;
    private List<String> orderBy = new ArrayList<String>();
    private List<String> groupBy = new ArrayList<String>();
    private Integer offset;
    private Integer limit;
    private boolean total;

    /**
     * Instantiates a new Tuple filter.
     */
    public TupleFilter() {

    }


}
