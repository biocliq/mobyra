package com.zitlab.palmyra.builder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The Criteria builder class used to build the API request body with criteria object.
 * This class will generate the Map object with specified criteria's.
 */
public class CriteriaBuilder {
    private Map<String, String> criteriaMap = new HashMap<>();

    private CriteriaBuilder() {
        super();
    }

    /**
     * Gets criteria map with specified criteria which will be used in API request.
     *
     * @return the criteria map with specified criteria.
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
     * The Builder class uses to build Criteria builder.
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
         * This Key value builder create criteria to equal(=) operator.
         *
         * @param key   the key to check with the data is equal or not.
         * @param value the value to compare the key value is equals it or not.
         * @return the builder with key and value with equal.
         */
        public Builder keyValue(final String key, final String value) {
            criteriaMap.put(key, value);
            return this;
        }

        /**
         * This Key value builder create criteria with number value `=(Equals)` comparision.
         *
         * @param key   the key to check with the data is equal or not.
         * @param value the value to compare the key value is equals it or not.
         * @return the builder with key and value with `=` operator.
         */
        public Builder keyValue(final String key, final Number value) {
            criteriaMap.put(key, String.valueOf(value));
            return this;
        }

        /**
         * This method creates not equal(!=) string comparator of key values.
         *
         * @param key   the key
         * @param value the value
         * @return the builder with not equals string comparator.
         */
        public Builder keyValueNot(final String key, final String value) {
            String condition = String.format("!%s", value);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         * This method creates not equal(!=) number comparator of key values.
         *
         * @param key   the key
         * @param value the value
         * @return the builder with not equals number comparator.
         */
        public Builder keyValueNot(final String key, final Number value) {
            String condition = String.format("!%s", value);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         * This method creates greater than(>) comparator of key value.
         *
         * @param key   the key
         * @param value the value
         * @return the builder with greater than comparator.
         */
        public Builder keyValueGreaterThan(final String key, final String value) {
            String condition = String.format(">%s", value);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         * This method creates greater than (>) number comparator of key value.
         *
         * @param key   the key
         * @param value the value
         * @return the builder with greater than number comparator.
         */
        public Builder keyValueGreaterThan(final String key, final Number value) {
            String condition = String.format(">%s", value);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         * This method creates greater than equals (>=) string comparator of key value.
         *
         * @param key   the key
         * @param value the value
         * @return the builder with greater than equals string comparator.
         */
        public Builder keyValueGreaterThanOrEqual(final String key, final String value) {
            String condition = String.format(">=%s", value);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         * This method creates greater than equals (>=) number comparator of key value.
         *
         * @param key   the key
         * @param value the value
         * @return the builder with greater than equals number comparator.
         */
        public Builder keyValueGreaterThanOrEqual(final String key, final Number value) {
            String condition = String.format(">=%s", value);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         * This method creates criteria to check the key's value contains it.
         *
         * @param key   the key
         * @param value the value
         * @return the builder with greater than equals string comparator.
         */
        public Builder keyValueContains(final String key, final String value) {
            String condition = String.format("_%s*", value);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         *  This method creates less than (<) comparator of key value.
         *
         * @param key   the key
         * @param value the value
         * @return the builder with less than string comparator.
         */
        public Builder keyValueLessThan(final String key, final String value) {
            String condition = String.format("<%s", value);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         *  This method creates less than (<) number comparator of key value.
         *
         * @param key   the key
         * @param value the value
         * @return the builder with less than number comparator.
         */
        public Builder keyValueLessThan(final String key, final Number value) {
            String condition = String.format("<%s", value);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         *  This method creates less than or equals (<=) comparator of key value.
         *
         * @param key   the key
         * @param value the value
         * @return the builder with less than or equals string comparator.
         */
        public Builder keyValueLessThanOrEqual(final String key, final String value) {
            String condition = String.format("<=%s", value);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         *  This method creates less than or equals (<) comparator of key value.
         *
         * @param key   the key
         * @param value the value
         * @return the builder with less than or equals number comparator.
         */
        public Builder keyValueLessThanOrEqual(final String key, final Number value) {
            String condition = String.format("<=%s", value);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         * This method creates between criteria of key values.
         *
         * @param key    the key.
         * @param value1 the start string value of between criteria.
         * @param value2 the end string value of between criteria.
         * @return the builder with between criteria.
         */
        public Builder keyValueBetween(final String key, final String value1, final String value2) {
            String condition = String.format("%s...%s", value1, value2);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         * This method creates between criteria of key values.
         *
         * @param value1 the start number value of between criteria.
         * @param value2 the end number value of between criteria.
         * @return the builder with between criteria.
         */
        public Builder keyValueBetween(final String key, final Number value1, final Number value2) {
            String condition = String.format("%s...%s", value1, value2);
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         * This method creates between criteria of key values.
         *
         * @param date1 the start date value of between criteria.
         * @param date2 the end date value of between criteria.
         * @return the builder with between criteria.
         */
        public Builder keyValueBetween(final String key, final Date date1, final Date date2) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String condition = String.format("%s...%s", formatter.format(date1), formatter.format(date2));
            criteriaMap.put(key, condition);
            return this;
        }


        /**
         * This method creates between criteria of key values.
         *
         * @param date1 the start time value of between criteria.
         * @param date2 the end time value of between criteria.
         * @return the builder with between criteria.
         */
        public Builder keyValueBetweenTime(final String key, final Date date1, final Date date2) {
            DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            String condition = String.format("%s...%s", formatter.format(date1), formatter.format(date2));
            criteriaMap.put(key, condition);
            return this;
        }

        /**
         * This method creates between criteria of key values.
         *
         * @param date1 the start datetime value of between criteria.
         * @param date2 the end datetime value of between criteria.
         * @return the builder with between criteria.
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
