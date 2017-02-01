package interfaces;

import java.util.List;

/**
 * Interface that allows for certain cinema/s to be searched.
 *
 */
public interface CinemaSearchable {
	/**
	 * Method for searching a certain/s cinema.
	 * 
	 * @param cinemaName
	 *            - the name of the cinema/s.
	 * @returns a list of all the cinemas that are with the name provided by the
	 *          cinemaName param.
	 */
	List<Cinema> searchCinemaBrand(String cinemaName);

}
