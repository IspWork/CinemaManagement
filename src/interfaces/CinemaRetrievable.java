package interfaces;

/**
 * Interface that allows for a certain cinema to be retrieved.
 *
 */
public interface CinemaRetrievable {
	/**
	 * Retrieves a cinema by name and location.
	 * 
	 * @param cinemaName
	 *            - the name of the cinema that is desired to be retrieved
	 * @param cinemaLocation
	 *            - the location of the cinema.
	 * @returns a cinema with the desired name and location if the cinema is
	 *          found.
	 * @returns null if cinema is not found.
	 */
	Cinema getCinema(String cinemaName, String cinemaLocation);
}
