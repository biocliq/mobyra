package com.zitlab.mobyra.library.builder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Criteria builder.
 */
public class CriteriaBuilder {
    private Map<String, String> criteriaMap = new HashMap<>();

    private CriteriaBuilder() {
        super();
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

    /**
     * The type Builder.
     */
    public static class Builder {
        private final Map<String, String> criteriaMap = new HashMap<>();

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            super();
        }

        /**
         * Key value builder.
         *
         * @param key   the key
         * @param value the value
         * @return the builder
         */
        public Builder keyValue(final String key, final String value) {
            criteriaMap.put(key, value);
            return this;
        }

        /**
         * Key value builder.
         *
         * @param key   the key
         * @param value the value
         * @return the builder
         */
        public Builder keyValue(final String key, final Number value) {
            criteriaMap.put(key, String.valueOf(value));
            return this;
        }

        /**
         * Key value not builder.
         *
         * @param key   the key
         * @param value the value
         * @return the builder
         */
        public Builder keyValueNot(final String key, final String value) {
            String condition = String.format("!%s", value);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         * Key value not builder.
         *
         * @param key   the key
         * @param value the value
         * @return the builder
         */
        public Builder keyValueNot(final String key, final Number value) {
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
        public Builder keyValueGreaterThan(final String key, final String value) {
            String condition = String.format(">%s", value);
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
        public Builder keyValueGreaterThan(final String key, final Number value) {
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
        public Builder keyValueGreaterThanOrEqual(final String key, final String value) {
            String condition = String.format(">=%s", value);
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
        public Builder keyValueGreaterThanOrEqual(final String key, final Number value) {
            String condition = String.format(">=%s", value);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         * Key value contains builder.
         *
         * @param key   the key
         * @param value the value
         * @return builder
         */
        public Builder keyValueContains(final String key, final String value) {
            String condition = String.format("_%s*", value);
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
        public Builder keyValueLessThan(final String key, final String value) {
            String condition = String.format("<%s", value);
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
        public Builder keyValueLessThan(final String key, final Number value) {
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
        public Builder keyValueLessThanOrEqual(final String key, final String value) {
            String condition = String.format("<=%s", value);
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
        public Builder keyValueLessThanOrEqual(final String key, final Number value) {
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
        public Builder keyValueBetween(final String key, final String value1, final String value2) {
            String condition = String.format("%s...%s", value1, value2);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         * Key value between builder.
         *
         * @param key    the key
         * @param value1 the value 1
         * @param value2 the value 2
         * @return builder
         */
        public Builder keyValueBetween(final String key, final Number value1, final Number value2) {
            String condition = String.format("%s...%s", value1, value2);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         * Key value between builder.
         *
         * @param key   the key
         * @param date1 the date 1
         * @param date2 the date 2
         * @return builder
         */
        public Builder keyValueBetween(final String key, final Date date1, final Date date2) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String condition = String.format("%s...%s", formatter.format(date1), formatter.format(date2));
            criteriaMap.put(key, condition);
            return this;
        }


        /**
         * Key value between time builder.
         *
         * @param key   the key
         * @param date1 the date 1
         * @param date2 the date 2
         * @return builder
         */
        public Builder keyValueBetweenTime(final String key, final Date date1, final Date date2) {
            DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            String condition = String.format("%s...%s", formatter.format(date1), formatter.format(date2));
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         * Key value between date time builder.
         *
         * @param key   the key
         * @param date1 the date 1
         * @param date2 the date 2
         * @return builder
         */
        public Builder keyValueBetweenDateTime(final String key, final Date date1, final Date date2) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            String condition = String.format("%s...%s", formatter.format(date1), formatter.format(date2));
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         * Build criteria builder.
         *
         * @return the criteria builder
         */
        public CriteriaBuilder build() {
            //Here we create the actual bank account object, which is always in a fully initialised state when it's returned.
            CriteriaBuilder builder = new CriteriaBuilder();  //Since the builder is in the BankAccount class, we can invoke its private constructor.
            builder.criteriaMap = this.criteriaMap;
            return builder;
        }
    }
}
