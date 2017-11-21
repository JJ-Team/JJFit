package fit.korea2canada.com.jjfitv1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jun
 */

public class JJDB extends SQLiteOpenHelper {

    //Constants for db name and version
    private static final String DATABASE_NAME = "jjfit.db";
    private static final int DATABASE_VERSION = 1;

    //Constants for identifying table and columns
    public static final String TABLE_FIT = "tbjjfit";
    public static final String FIT_ID = "_id";
    public static final String FIT_WALKING = "walking";
    public static final String FIT_WEIGHT = "weight";
    public static final String FIT_DATE = "date";

    public static final String[] ALL_COLUMNS =
            {FIT_ID, FIT_WALKING, FIT_WEIGHT, FIT_DATE};



    //SQL to create table
    private static final String TABLE_CREATE_FIT =
            "CREATE TABLE " + TABLE_FIT + " (" +
                    FIT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FIT_WALKING + " INTEGER, " +
                    FIT_WEIGHT + " FLOAT, " +
                    FIT_DATE + " TEXT default CURRENT_TIMESTAMP" +
                    ")";



    public static final String DROP_TABLE_FIT = "DROP TABLE " + TABLE_FIT;

    public JJDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_FIT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FIT);
        onCreate(db);
    }


}
