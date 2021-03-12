package com.zitlab.mobyra.library.builder;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Criteria builder.
 */
public class CriteriaBuilder {
    private Map<String, String > criteriaMap = new HashMap<>();

    private CriteriaBuilder(){
        super();
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        private Map<String, String > criteriaMap = new HashMap<>();

        /**
         * Instantiates a new Builder.
         */
        public Builder(){
            super();
        }

        /**
         * Key value builder.
         *
         * @param key   the key
         * @param value the value
         * @return the builder
         */
        public Builder keyValue(final String key, final String value){
            criteriaMap.put(key, value);
            return this;
        }

        /**
         * Key value not builder.
         *
         * @param key   the key
         * @param value the value
         * @return the builder
         */
        public Builder keyValueNot(final String key, final String value){
            String condition = String.format("!%s", value);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         * Key value greater than builder.
         *
         * @param key   the key
         * @param value the value
         * @return the builder
         */
        public Builder keyValueGreaterThan(final String key, final String value){
            String condition = String.format(">%s", value);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         * Key value greater than or equal builder.
         *
         * @param key   the key
         * @param value the value
         * @return the builder
         */
        public Builder keyValueGreaterThanOrEqual(final String key, final String value){
            String condition = String.format(">=%s", value);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         * Key value less than builder.
         *
         * @param key   the key
         * @param value the value
         * @return the builder
         */
        public Builder keyValueLessThan(final String key, final String value){
            String condition = String.format("<%s", value);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         * Key value less than or equal builder.
         *
         * @param key   the key
         * @param value the value
         * @return the builder
         */
        public Builder keyValueLessThanOrEqual(final String key, final String value){
            String condition = String.format("<=%s", value);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         * Key value between builder.
         *
         * @param key    the key
         * @param value1 the value 1
         * @param value2 the value 2
         * @return the builder
         */
        public Builder keyValueBetween(final String key, final String value1, final String value2){
            String condition = String.format("%s...%s", value1, value2);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         * Build criteria builder.
         *
         * @return the criteria builder
         */
        public CriteriaBuilder build(){
            //Here we create the actual bank account object, which is always in a fully initialised state when it's returned.
            CriteriaBuilder builder = new CriteriaBuilder();  //Since the builder is in the BankAccount class, we can invoke its private constructor.
            builder.criteriaMap = this.criteriaMap;
            return builder;
        }
    }

    /**
     * Gets criteria map.
     *
     * @return the criteria map
     */
    public Map<String, String> getCriteriaMap() {
        return criteriaMap;
    }

    @Override
    public String toString() {
        return "CriteriaBuilder{" +
                "criteriaMap: " + criteriaMap +
                '}';
    }
}
