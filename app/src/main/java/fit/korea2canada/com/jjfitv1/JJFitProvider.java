package fit.korea2canada.com.jjfitv1;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class JJFitProvider extends ContentProvider {
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final String AUTHORITY ="fit.korea2canada.com.jjfitv1.jjfitprovider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);


    private static final String JJFIT_PATH = "jjfit";
    private static final int JJFIT_DATA = 1;
    static {
        uriMatcher.addURI(AUTHORITY, JJFIT_PATH, JJFIT_DATA );
    }

    public static final  String ITEM_TYPE_NEW = "new data";
    public static final  String ITEM_TYPE_EDIT = "edit data";

    private SQLiteDatabase database;

    public JJFitProvider() {
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case JJFIT_DATA:
                return AUTHORITY + "." + JJFIT_PATH;
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    public int getUriType(Uri uri) {
        return uriMatcher.match(uri);
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri r = null;
        if( getUriType(uri) == JJFIT_DATA){
            long id = database.insert(JJDB.TABLE_FIT, null, values);
            r = Uri.parse(JJFIT_PATH + "/" + id);
        }
        return r;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return database.delete(JJDB.TABLE_FIT, selection, selectionArgs);
    }

    @Override
    public boolean onCreate() {
        JJDB db = new JJDB(getContext());
        database  = db.getWritableDatabase();
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        return database.query(JJDB.TABLE_FIT, JJDB.ALL_COLUMNS, selection, null,
                        null, null, JJDB.FIT_DATE + " DESC");

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        return database.update(JJDB.TABLE_FIT, values, selection, selectionArgs);
    }
}
