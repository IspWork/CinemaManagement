package interfaces;

/**
 * This interface provides to the user precise control over a certain cinema and
 * its projections. Every cinema should have a name and location.
 */
public interface Cinema extends Projectable, MovieAddable, MovieRemovable, MovieListDisplayable, MovieRetrievable {
	/**
	 * A method for retrieving the name of the cinema.
	 * 
	 * @returns the name of the cinema.
	 */
	String getName();

	/**
	 * A method for retrieving the location of the cinema.
	 * 
	 * @return the location of the cinema.
	 */
	String getLocation();

}
