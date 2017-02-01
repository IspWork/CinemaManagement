package interfaces;

/**
 * An interface for a movie. Every movie should have a name, actors, rating,
 * genre, synopsis and a ticked price.
 */
public interface Movie {
	/**
	 * Returns the name of the movie.
	 * 
	 * @returns the name of the movie.
	 */
	String getName();

	/**
	 * Returns a string array of all the actors in the movie.
	 * 
	 * @returns a string array of all the actors in the movie.
	 */
	String[] getActors();

	/**
	 * Returns the imdb rating of the movie.
	 * 
	 * @returns a double representation of the imdb rating of the movie.
	 */
	double getImdbRating();

	/**
	 * Returns the genre of the movie.
	 * 
	 * @returns a string representation of the genre of the movie.
	 */
	String getGenre();

	/**
	 * Sets the movie synopsis.
	 * 
	 * @param synopsis
	 *            - the synopsis of the movie.
	 */
	void setSynopsis(String synopsis);

	/**
	 * Returns the synopsis of the movie.
	 * 
	 * @returns the synopsis of the movie.
	 */
	String getSynopsis();

	/**
	 * Returns the ticket price of the movie.
	 * 
	 * @returns a double representation of the ticket price of the movie.
	 */
	double getTicketPrice();
}
