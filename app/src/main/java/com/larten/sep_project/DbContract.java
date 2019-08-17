package com.larten.sep_project;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class DbContract {

    public static final String CONTENT_AUTHORITY = "com.larten.sep_project";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    private static final String PATH_ENTRIES = "entries";

    public static class Entry implements BaseColumns {

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/vnd.jsontosqlite.entries";

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/vnd.jsontosqlite.entry";

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ENTRIES).build();

        public static final String TABLE_NAME = "MonHoc";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "Name";
        public static final String COLUMN_STUDENTS = "Students";
        public static final String COLUMN_DAY = "Day";
        public static final String COLUMN_TIME = "Time";
        public static final String COLUMN_ROOM = "Room";

    }
}