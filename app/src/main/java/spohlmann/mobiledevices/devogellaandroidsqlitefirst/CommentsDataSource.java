package spohlmann.mobiledevices.devogellaandroidsqlitefirst;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by spohlmann on 3/31/2017.
 * This class opens the connection to the database creates, deletes, and gets all the comments from the comments table
 */

public class CommentsDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COMMENT, MySQLiteHelper.COLUMN_RATING };

    /*
     * Creates the dbHelper object
     * @param context
     */
    public CommentsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    /*
     * This uses the dbHelper object to open the database connection
     * @throws SQLException
     */
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    /*
     * This method closes the database connection after everything else is run.
     */
    public void close() {
        dbHelper.close();
    }

    /*
     * It takes a new comment object and places it to the database moving it to the front of the list
     * @param comment
     * @returns newComment
     *
     *
     * INSERT INTO comment(comment) VALUES(values);
     */
    public Comment createComment(String comment, String rating) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COMMENT, comment);
        values.put(MySQLiteHelper.COLUMN_RATING, rating);
        long insertId = database.insert(MySQLiteHelper.TABLE_COMMENTS, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Comment newComment = cursorToComment(cursor);
        cursor.close();
        return newComment;
    }

    /*
     * This finds a comment associated with an id and deletes the comment from the database
     * @param comment
     *
     *
     * "DELETE FROM comment WHERE id = " + id;
     */
    public void deleteComment(Comment comment) {
        long id = comment.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_COMMENTS, MySQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    /*
     * This method creates an ArrayList object and navigates through the Comments table adding all of the entries to the list
     * @return comments - the ArrayList
     *
     *
     *
     * Select * FROM comments;
     */
    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<Comment>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMMENTS,
                null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    /*
     * This method is what is used when someone wants to find a specific comment.
     * It goes to a specific comment instead of looping through them all
     * @param cursor
     * @return comment
     *
     *
     * SELECT * FROM comment WHERE id = 0;
     */
    private Comment cursorToComment(Cursor cursor) {
        Comment comment = new Comment();
        comment.setId(cursor.getLong(0));
        comment.setComment(cursor.getString(1));
        comment.setRating(cursor.getString( cursor.getColumnIndex( MySQLiteHelper.COLUMN_RATING ) ));
        return comment;
    }
}