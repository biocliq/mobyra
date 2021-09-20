package com.zitlab.palmyra.http;

import com.zitlab.palmyra.auth.AuthClient;
import com.zitlab.palmyra.auth.BasicAuthClient;
import com.zitlab.palmyra.util.BaseDevice;

/**
 * The type Base client builder.
 */
public final class MobyraClientBuilder {

    private final String appName = "fluwiz";
    /**
     * The Host name.
     */
    protected String hostName;
    /**
     * The Scheme.
     */
    protected String scheme = "https";
    /**
     * The Connection timeout.
     */
    protected int connectionTimeout = 60;
    /**
     * The Write timeout.
     */
    protected int writeTimeout = 60;
    /**
     * The Read timeout.
     */
    protected int readTimeout = 60;
    /**
     * The Log level.
     */
    protected LogLevel logLevel = LogLevel.BASIC;
    private String userName;
    private String password;
    private AuthClient authClient;
    private BaseDevice device;
    private String apiVersion = "v1";
    private String context = "apidev";

    private MobyraClientBuilder() {
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    String getPassword() {
        return password;
    }

    /**
     * Gets auth client.
     *
     * @return the auth client
     */
    AuthClient getAuthClient() {
        if (null == this.authClient) {
            this.authClient = new BasicAuthClient();
        }
        return this.authClient;
    }

    /**
     * Gets app name.
     *
     * @return the app name
     */
    public String getAppName() {
        return appName;
    }

    /**
     * Gets device.
     *
     * @return the device
     */
    public BaseDevice getDevice() {
        return device;
    }

    /**
     * Gets context.
     *
     * @return the context
     */
    public String getContext() {
        return context;
    }

    /**
     * Gets api version.
     *
     * @return the api version
     */
    public String getApiVersion() {
        return apiVersion;
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
        BODY
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        /**
         * The Host name.
         */
        protected String hostName;
        /**
         * The Scheme.
         */
        protected String scheme = "http";
        /**
         * The Connection timeout.
         */
        protected int connectionTimeout;
        /**
         * The Write timeout.
         */
        protected int writeTimeout;
        /**
         * The Read timeout.
         */
        protected int readTimeout;
        /**
         * The Log level.
         */
        protected LogLevel logLevel;
        private String userName;
        private AuthClient authClient;
        private String password;
        private BaseDevice device;
        private String appName;
        private String apiVersion;
        private String context;

        /**
         * Instantiates a new Builder.
         *
         * @param hostName the host name
         */
        public Builder(String hostName) {
            this.hostName = hostName;
        }

        /**
         * With username password builder.
         *
         * @param userName the user name
         * @param password the password
         * @return the builder
         */
        public Builder withUsernamePassword(String userName, String password) {
            this.userName = userName;
            this.password = password;
            return this;
        }

        /**
         * With auth client builder.
         *
         * @param authClient the auth client
         * @return the builder
         */
        public Builder withAuthClient(final AuthClient authClient) {
            this.authClient = authClient;
            return this;
        }

        /**
         * With scheme builder.
         *
         * @param scheme the scheme
         * @return the builder
         */
        public Builder withScheme(String scheme) {
            this.scheme = scheme;
            return this;
        }

        /**
         * With api version builder.
         *
         * @param apiVersion the api version
         * @return the builder
         */
        public Builder withApiVersion(String apiVersion) {
            this.apiVersion = apiVersion;
            return this;
        }

        /**
         * With device builder.
         *
         * @param device the device
         * @return the builder
         */
        public Builder withDevice(BaseDevice device) {
            this.device = device;
            return this;
        }

        /**
         * With connection timeout builder.
         *
         * @param connectionTimeout the connection timeout
         * @return the builder
         */
        public Builder withConnectionTimeout(int connectionTimeout) {
            this.connectionTimeout = connectionTimeout;
            return this;
        }

        /**
         * With write timeout builder.
         *
         * @param writeTimeout the write timeout
         * @return the builder
         */
        public Builder withWriteTimeout(int writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        /**
         * With read timeout builder.
         *
         * @param readTimeout the read timeout
         * @return the builder
         */
        public Builder withReadTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        /**
         * With log level builder.
         *
         * @param logLevel the log level
         * @return the builder
         */
        public Builder withLogLevel(LogLevel logLevel) {
            this.logLevel = logLevel;
            return this;
        }

        /**
         * With app name builder.
         *
         * @param appName the app name
         * @return the builder
         */
        public Builder withAppName(String appName) {
            this.appName = appName;
            return this;
        }

        /**
         * With context builder.
         *
         * @param context the context
         * @return the builder
         */
        public Builder withContext(String context) {
            this.context = context;
            return this;
        }

        /**
         * Build mobyra client builder.
         *
         * @return the mobyra client builder
         */
        public MobyraClientBuilder build() {
            //Here we create the actual bank account object, which is always in a fully initialised state when it's returned.
            MobyraClientBuilder builder = new MobyraClientBuilder();  //Since the builder is in the BankAccount class, we can invoke its private constructor.
            builder.userName = this.userName;
            builder.password = this.password;
            builder.context = this.context;
            builder.hostName = this.hostName;
            builder.scheme = this.scheme;
            builder.connectionTimeout = this.connectionTimeout;
            builder.writeTimeout = this.writeTimeout;
            builder.readTimeout = this.readTimeout;
            builder.logLevel = this.logLevel;
            builder.apiVersion = this.apiVersion;
            builder.authClient = this.authClient;
            return builder;
        }

    }
}
