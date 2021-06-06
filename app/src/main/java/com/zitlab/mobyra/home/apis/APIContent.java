package com.zitlab.mobyra.home.apis;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class APIContent {

    public static final List<Pair<String, String>> ITEMS_MARKS = new ArrayList<>();
    public static final List<Pair<String, String>> ITEMS_STUDENTS = new ArrayList<>();

    static {
        // Add some sample items.
        addMarksItem(Pair.create("All Marks List", "{}"));
        addMarksItem(Pair.create("Marks by Query Greater/Less Criteria", "{ \"criteria\" : { \"english\": \">70.00\", \"maths\": \"<=90.00\", \"science\" : \"85...92\" } }"));
        addMarksItem(Pair.create("Marks by Query Not Condition Criteria", "{ \"criteria\" : { \"english\": \">70.00\", \"maths\": \"<=90.00\", \"science\" : \"!90\" } }"));


        addStudentsItem(Pair.create("All Student Query List", "{}"));
        addStudentsItem(Pair.create("Student List", "{}"));
        addStudentsItem(Pair.create("Student Query by Unique", "{ \"criteria\" : { \"studentCode\" : \"SD9000\" }}"));
        addStudentsItem(Pair.create("Student Query by Date Filter1", "{ \"criteria\" : { \"dob\": \">2010-03-01\" } }"));
        addStudentsItem(Pair.create("Student Query by Date Filter2", "{ \"criteria\" : { \"dob\": \"2010-02-01...2013-02-01\" } }"));
        addStudentsItem(Pair.create("Student Query by String Filter1", "{ \"criteria\" : { \"studentName\" : \"JOHN\" } }"));
        addStudentsItem(Pair.create("Student Query by String Filter2", "{ \"criteria\" : { \"studentName\" : \"_O*\" } }"));
        addStudentsItem(Pair.create("Student Save Data", "{ \"studentName\": \"JOHN\", \"dob\": \"2010-01-02\", \"studentCode\": \"SD9000\", \"studentClass\": \"5\" }"));
        addStudentsItem(Pair.create("Student with Marks", "{ \"studentName\": \"JOHN\", \"dob\": \"2010-01-02\", \"studentCode\": \"SD9000\", \"studentClass\": \"5\", \"marks\": [ { \"exam\": \"EX03\", \"english\": 90.00, \"maths\": 85.00, \"science\": 90.00, \"tamil\" : 86, \"history\" : 75 } ] }"));
        addStudentsItem(Pair.create("Student with return Saved Data with Headers", "{ \"studentName\": \"JOHN\", \"dob\": \"2010-01-02\", \"studentCode\": \"SD9000\", \"studentClass\": \"5\" }"));
        addStudentsItem(Pair.create("Student return Generated ID", "{ \"studentName\": \"JOHN\", \"dob\": \"2010-01-02\", \"studentCode\": \"SD9000\", \"studentClass\": \"5\" }"));
    }

    private static void addMarksItem(Pair<String, String> data) {
        ITEMS_MARKS.add(data);
    }

    private static void addStudentsItem(Pair<String, String> data) {
        ITEMS_STUDENTS.add(data);
    }

}