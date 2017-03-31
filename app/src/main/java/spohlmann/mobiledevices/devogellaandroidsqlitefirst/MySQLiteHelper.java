package spohlmann.mobiledevices.devogellaandroidsqlitefirst;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by spohlmann on 3/31/2017.
 * This class sets up the database and opens a connection
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    // This sets a constant string value for the table of Comments which is used in the DATABASE_CREATE STRING to create the table
    public static final String TABLE_COMMENTS = "comments";
    // This sets a constant string value for id column which is used in the DATABASE_CREATE STRING to create the table
    public static final String COLUMN_ID = "_id";
    // This sets a constant string value for Comments column which is used in the DATABASE_CREATE STRING to create the table
    public static final String COLUMN_COMMENT = "comment";
    // This sets a constant string value for the database name which is used in the DATABASE_CREATE STRING to create the table

    private static final String DATABASE_NAME = "commments.db";
    // This sets a DATABASE_VERSION constant that is used when the MySQLiteHelper object is created
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement by taking in the constants defined above to form a string
    private static final String DATABASE_CREATE = "create table "
            + TABLE_COMMENTS + "( " + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_COMMENT
            + " text not null);";

    // Creates the MySQLiteHelper object pulling in the DATABASE_NAME and DATABASE_VERSION constants defined above
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /*
     * The onCreate is run when the program starts it pulls in a SQLiteDatabase object and opens a connection running the SQL command stored in the DATABASE_CREATE String
     * @param database
     */
    @Override
    public void onCreate(SQLiteDatabase database) {
        // Standard SQL command :
        // CREATE TABLE standardTable(Id integer primary key autoincrement, Name String);
        database.execSQL(DATABASE_CREATE);
    }

    /*
     * It checks if the tables exists and if it doesn it will replace it with the new table being created
     * @param db - the database object
     * @param oldVersion - the old version number of the table
     * @param newVersion - the new version number that the table will be set to
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        onCreate(db);
    }

}