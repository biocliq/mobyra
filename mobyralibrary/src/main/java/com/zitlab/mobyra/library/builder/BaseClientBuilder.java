package com.zitlab.mobyra.library.builder;

/**
 * The type Base client builder.
 */
public class BaseClientBuilder {
    protected String hostName;
    protected String scheme = "https";
    protected int connectionTimeout = 60;
    protected int writeTimeout = 60;
    protected int readTimeout = 60;
    protected LogLevel logLevel = LogLevel.BASIC;


    /**
     * The enum Log level.
     */
    public enum LogLevel {
        /**
         * None log level.
         */
        NONE,
        /**
         * Basic log level.
         */
        BASIC,
        /**
         * Headers log level.
         */
        HEADERS,
        /**
         * Body log level.
         */
        BODY;
    }


    /**
     * The type Builder.
     */
    public static class Builder {

        protected String hostName;
        protected String scheme;
        protected int connectionTimeout;
        protected int writeTimeout;
        protected int readTimeout;
        protected LogLevel logLevel;

        /**
         * Instantiates a new Builder.
         *
         * @param hostName the base url
         */
        public Builder(String hostName) {
            this.hostName = hostName;
        }

        /**
         * With scheme builder.
         *
         * @param scheme the scheme
         * @return the builder
         */
        public Builder  withScheme(String scheme){
            this.scheme = scheme;
            return this;
        }

        /**
         * With connection timeout builder.
         *
         * @param connectionTimeout the connection timeout
         * @return the builder
         */
        public Builder withConnectionTimeout(int connectionTimeout){
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        /**
         * With write timeout builder.
         *
         * @param writeTimeout the write timeout
         * @return the builder
         */
        public Builder withWriteTimeout(int writeTimeout){
            this.writeTimeout = writeTimeout;
            return this;
        }

        /**
         * With read timeout builder.
         *
         * @param readTimeout the read timeout
         * @return the builder
         */
        public Builder withReadTimeout(int readTimeout){
            this.readTimeout = readTimeout;
            return this;
        }

        /**
         * With log level builder.
         *
         * @param logLevel the log level
         * @return the builder
         */
        public Builder withLogLevel(LogLevel logLevel){
            this.logLevel = logLevel;
            return this;
        }

        /**
         * Build base client builder.
         *
         * @return the base client builder
         */
        public BaseClientBuilder build(){
            //Here we create the actual bank account object, which is always in a fully initialised state when it's returned.
            BaseClientBuilder builder = new BaseClientBuilder();  //Since the builder is in the BankAccount class, we can invoke its private constructor.
            builder.hostName = this.hostName;
            builder.scheme = this.scheme;
            builder.connectionTimeout = this.connectionTimeout;
            builder.writeTimeout = this.writeTimeout;
            builder.readTimeout = this.readTimeout;
            builder.logLevel = this.logLevel;
            return builder;
        }
    }

    /**
     * Instantiates a new Base client builder.
     */
//Fields omitted for brevity.
    protected BaseClientBuilder() {
        //Constructor is now private.
    }

    /**
     * Gets host name.
     *
     * @return the host name
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Gets connection timeout.
     *
     * @return the connection timeout
     */
    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    /**
     * Gets write timeout.
     *
     * @return the write timeout
     */
    public int getWriteTimeout() {
        return writeTimeout;
    }

    /**
     * Gets read timeout.
     *
     * @return the read timeout
     */
    public int getReadTimeout() {
        return readTimeout;
    }

    /**
     * Gets log level.
     *
     * @return the log level
     */
    public LogLevel getLogLevel() {
        return logLevel;
    }

    /**
     * Gets scheme.
     *
     * @return the scheme
     */
    public String getScheme() {
        return scheme;
    }
}
