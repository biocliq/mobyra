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

    public static final List<Pair<String , String>> ITEMS_MARKS = new ArrayList<>();
    public static final List<Pair<String , String>> ITEMS_STUDENTS = new ArrayList<>();

    static {
        // Add some sample items.
        addMarksItem(Pair.create("All Student List", "{}"));
        addMarksItem(Pair.create("Student by Criteria 1","{ \"criteria\" : { \"english\": \">70.00\", \"maths\": \"<=90.00\", \"science\" : \"85...92\" } }"));
        addMarksItem(Pair.create("Student by Criteria 2","{ \"criteria\" : { \"english\": \">70.00\", \"maths\": \"<=90.00\", \"science\" : \"!90\" } }"));


        addStudentsItem(Pair.create("All Student List", "{}"));
        addStudentsItem(Pair.create("Student by Criteria 1","{ \"criteria\" : { \"english\": \">70.00\", \"maths\": \"<=90.00\", \"science\" : \"85...92\" } }"));
        addStudentsItem(Pair.create("Student by Criteria 2","{ \"criteria\" : { \"english\": \">70.00\", \"maths\": \"<=90.00\", \"science\" : \"!90\" } }"));
    }

    private static void addMarksItem(Pair<String, String> data) {
        ITEMS_MARKS.add(data);
    }

    private static void addStudentsItem(Pair<String, String> data) {
        ITEMS_STUDENTS.add(data);
    }

}