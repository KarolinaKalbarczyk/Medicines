package com.example.medicines;

import android.net.Uri;
import android.content.ContentResolver;
import android.provider.BaseColumns;

public final class MedicineContract {

    private MedicineContract() {}

    public static final String CONTENT_AUTHORITY = "com.example.android.medicines";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_BOOKS = "medicines";

    public static final class MedicineEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_BOOKS);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BOOKS;

        public final static String TABLE_NAME = "medicines";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_NAME ="name";

        public final static String COLUMN_TIMES = "times";

        public final static String COLUMN_QUANTITY = "quantity";

        public final static String COLUMN_ONE_DOSE = "one_dose";

    }

}


