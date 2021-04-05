package com.zitlab.mobyra.login.data;

import com.zitlab.mobyra.MobyraInstance;
import com.zitlab.mobyra.home.student.Student;
import com.zitlab.mobyra.library.MobyraClient;
import com.zitlab.mobyra.login.data.model.LoggedInUser;
import com.zitlab.palmyra.builder.PaginatedQueryFilter;

import java.io.IOException;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private final LoginDataSource dataSource;
    private final MobyraInstance mobyraInstance;
    Result result;
    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
        //Mobyra instance
        mobyraInstance = MobyraInstance.getInstance();
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public void login(String username, String password, LoginCallback callback) {
        MobyraClient client = mobyraInstance.clientWithUserNamePassword(username, password);
        PaginatedQueryFilter queryFilter = new PaginatedQueryFilter();
        queryFilter.setLimit(4);
        queryFilter.setTotal(true);
        client.query(queryFilter, Student.class, (status, response, exception) -> {
            LoggedInUser user = new LoggedInUser(username, "Raja K");
            setLoggedInUser(user);
            if (status.isStatus()) {
                mobyraInstance.setStudents(response);
                result = new Result.Success<>(user);
                callback.onLoginResponse(result);
            } else {
                result = new Result.Error(new IOException("Error logging in."));
                callback.onLoginResponse(result);
            }
        });
    }

    public interface LoginCallback {
        void onLoginResponse(Result<LoggedInUser> result);
    }
}