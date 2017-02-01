package interfaces;

/**
 * Interface that enables the removing of movies.
 *
 */
public interface MovieRemovable {
	/**
	 * Method for removing a movie.
	 * 
	 * @param movieName
	 *            - the name of the movie that will be removed
	 */
	void removeMovie(String movieName);
}
