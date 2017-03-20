package aubg.edu.londontourguide.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nikola on 18.03.17.
 */

public class NewsDBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 2;

    static final String DATABASE_NAME = "news.db";

    public NewsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create a table to hold locations.  A location consists of the string supplied in the
        // location setting, the city name, and the latitude and longitude
        final String SQL_CREATE_NEWS_TABLE = "CREATE TABLE " + NewsContract.NewsEntry.TABLE_NAME + " (" +
                NewsContract.NewsEntry._ID + " INTEGER PRIMARY KEY," +
                NewsContract.NewsEntry.COLUMN_TITLE + " TEXT UNIQUE NOT NULL, " +
                NewsContract.NewsEntry.COLUMN_URL + " TEXT UNIQUE NOT NULL " +
                " );";

        sqLiteDatabase.execSQL(SQL_CREATE_NEWS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        // Note that this only fires if you change the version number for your database.
        // It does NOT depend on the version number for your application.
        // If you want to update the schema without wiping data, commenting out the next 2 lines
        // should be your top priority before modifying this method.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NewsContract.NewsEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
