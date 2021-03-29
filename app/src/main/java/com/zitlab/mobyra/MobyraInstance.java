package com.zitlab.mobyra;

import com.zitlab.mobyra.home.detail.marks.pojo.Marks;
import com.zitlab.mobyra.home.student.Student;
import com.zitlab.mobyra.library.MobyraClient;
import com.zitlab.palmyra.http.MobyraClientBuilder;
import com.zitlab.palmyra.pojo.QueryResultSet;

public class MobyraInstance {

    private static MobyraInstance instance;
    private QueryResultSet<Student> students;
    private QueryResultSet<Marks> marks;

    private MobyraInstance() {
        super();
    }

    public static MobyraInstance getInstance() {
        if (instance == null) {
            instance = new MobyraInstance();
        }
        return instance;
    }

    public MobyraClient client() {
        MobyraClientBuilder builder = new MobyraClientBuilder.Builder("api.fluwiz.com")
                .withUsernamePassword("admin", "ad")
                .withAppName("palmyra")
                .withContext("apidev")
                .withApiVersion("v1")
                .withLogLevel(MobyraClientBuilder.LogLevel.BASIC)
                .build();

        return new MobyraClient(builder);
    }

    public MobyraClient clientWithUserNamePassword(final String username, final String password) {
        MobyraClientBuilder builder = new MobyraClientBuilder.Builder("api.fluwiz.com")
                .withUsernamePassword(username, password)
                .withAppName("palmyra")
                .withContext("apidev")
                .withApiVersion("v1")
                .withLogLevel(MobyraClientBuilder.LogLevel.BASIC)
                .build();
        return new MobyraClient(builder);
    }

    public QueryResultSet<Student> getStudents() {
        return this.students;
    }

    public void setStudents(QueryResultSet<Student> students) {
        this.students = students;
    }

    public QueryResultSet<Marks> getMarks() {
        return this.marks;
    }

    public void setMarks(QueryResultSet<Marks> marks) {
        this.marks = marks;
    }

}
