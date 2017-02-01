package interfaces;

/**
 * Interface that enables the retrieving of a certain movie.
 *
 */
public interface MovieRetrievable {
	/**
	 * Returns a movie with a specified name.
	 * 
	 * @param movieName
	 *            - the name of the movie.
	 * @returns the movie with name that is equal to the movieName param.
	 * @returns null if the movie was not found.
	 * 
	 */
	Movie getMovie(String movieName);
}
