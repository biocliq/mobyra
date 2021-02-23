package com.zitlab.mobyra.library.builder;

/**
 * The type Base client builder.
 */
public final class MobyraClientBuilder extends BaseClientBuilder {
    private String userName;
    private String password;
    private String appName = "palmyra";
    private String apiVersion = "v1";
    private String context = "apidev";


    private MobyraClientBuilder() {
    }

    /**
     * The type Builder.
     */
    public static class Builder extends BaseClientBuilder.Builder {
        private String userName;
        private String password;
        private String appName;
        private String apiVersion;
        private String context;

        /**
         * Instantiates a new Builder.
         *
         * @param hostName the host name
         */
        public Builder(String hostName) {
            super(hostName);
        }

        /**
         * With username password builder.
         *
         * @param userName the user name
         * @param password the password
         * @return the builder
         */
        public Builder withUsernamePassword(String userName, String password){
            this.userName = userName;
            this.password = password;
            return this;
        }

        /**
         * With app name builder.
         *
         * @param appName the app name
         * @return the builder
         */
        public Builder withAppName(String appName){
            this.appName = appName;
            return this;
        }

        /**
         * With context builder.
         *
         * @param context the context
         * @return the builder
         */
        public Builder withContext(String context){
            this.context = context;
            return this;
        }

        public MobyraClientBuilder build(){
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
            return builder;
        }

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
    public String getPassword() {
        return password;
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
}
