package spohlmann.mobiledevices.devogellaandroidsqlitefirst;

import android.media.Rating;

/**
 * Created by spohlmann on 3/31/2017.
 * Class sets up the Comment object. Uses a get and set for the comment and Id
 */

public class Comment {
    // This sets the constant for the id of the comment object
    private long id;
    //This sets the constant for the comment column of the comment object
    private String comment;
    //This sets the constant for the rating column of the comment object
    private String rating;

    /*
     * This gets the Id
     * @return id
     */
    public long getId() {
        return id;
    }
    /*
     * This sets the id to the parameter passed in
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }
    /*
     * This gets the comment
     * @return comment
     */
    public String getComment() {
        return comment;
    }
    /*
     * This sets the comment to the parameter passed in
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
    /*
     * This gets the rating
     * @return rating
     */
    public String getRating() {
        return rating;
    }
    /*
     * This sets the rating to the parameter passed in
     * @param rating
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /*
     * This methods displays the comment as a String
     * @return comment
     */
    @Override
    public String toString() {
        return (comment + " - " + rating);
    }
}