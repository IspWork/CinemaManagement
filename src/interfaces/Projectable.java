package interfaces;

/**
 * An interface that allows for a certain class to have projections.
 *
 */
public interface Projectable {

	/**
	 * Displays all available projections.
	 */
	void allProjections();

	/**
	 * Displays projections at a certain time.
	 * 
	 * @param projectionTime
	 *            - the desired projection time.
	 */
	void projectionsAtTime(String projectionTime);

	/**
	 * Adds a projection time to the system.
	 * 
	 * @param projectionTime
	 *            - the desired projection time.
	 */

	void addProjectionTime(String projectionTime);

	/**
	 * Adds a movie at a certain projection time.
	 * 
	 * @param projectionTime
	 *            - the desired projection time.
	 * @param movie
	 *            - the movie that will be added to the desired projection time.
	 */
	void addMovieAtProjectionTime(String projectionTime, Movie movie);

	/**
	 * Removes a movie from all projection times.
	 * 
	 * @param movieName
	 *            - the name of the movie that will be removed.
	 */
	void removeMovieFromProjections(String movieName);

	/**
	 * Removes a movie at a certain projection time.
	 * 
	 * @param projectionTime
	 *            - the desired projection time.
	 * @param movie
	 *            - the movie that will be removed
	 */
	void removeMovieAtProjectionTime(String projectionTime, Movie movie);

}
