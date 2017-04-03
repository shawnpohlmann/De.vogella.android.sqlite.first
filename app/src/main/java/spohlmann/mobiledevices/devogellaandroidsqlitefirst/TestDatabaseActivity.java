package spohlmann.mobiledevices.devogellaandroidsqlitefirst;

import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by spohlmann on 3/31/2017.
 * Main interface for the app. Displays the information called in the app
 */
public class TestDatabaseActivity extends ListActivity {
    private CommentsDataSource datasource;
    EditText etRating;
    TextView tvRating;
    EditText etComment;
    TextView tvComment;

    /*
     * Runs when the app is first started
     * Sets the layout, opens the database connection,
     * runs the methods
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Had to change my layout name to activity_test_database from main
        setContentView(R.layout.activity_test_database);

        datasource = new CommentsDataSource(this);
        datasource.open();

        List<Comment> values = datasource.getAllComments();

        // use the SimpleCursorAdapter to show the
        // elements in a ListView
        ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);

        etRating = (EditText) findViewById(R.id.etRating);
        tvRating = (TextView) findViewById(R.id.tvRating);
        etComment = (EditText) findViewById(R.id.etComment);
        tvComment = (TextView) findViewById(R.id.tvComment);
    }

    /*
     * Creates and deletes the comments 
     */
    public void onClick(View view) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<Comment> adapter = (ArrayAdapter<Comment>) getListAdapter();
        Comment comment = null;
        switch (view.getId()) {
            case R.id.add:
                //Commented out for the challenge problems
                //String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
                String comments = etComment.getText().toString();
                String ratings  = etRating.getText().toString();
                //int nextInt = new Random().nextInt(3);
                // save the new comment to the database
                comment = datasource.createComment(comments, ratings);
                adapter.add(comment);
                break;
            case R.id.delete:
                if (getListAdapter().getCount() > 0) {
                    comment = (Comment) getListAdapter().getItem(0);
                    datasource.deleteComment(comment);
                    adapter.remove(comment);
                }

                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

}