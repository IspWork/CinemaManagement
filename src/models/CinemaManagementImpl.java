package models;

/**
 * An implementation of the CinemaManagement interface.
 */
import java.util.ArrayList;
import java.util.List;

import interfaces.Cinema;
import interfaces.CinemaManagement;

public class CinemaManagementImpl implements CinemaManagement {
	// A list of all the cinemas that are in the cinema management system.
	private List<Cinema> cinemas;

	public CinemaManagementImpl() {
		this.setCinemas(new ArrayList<>());
	}

	@Override
	public void addCinema(Cinema cinema) {
		if (checkCinema(cinema)) {
			ConsoleWriter.write("Cinema with this name and at this location already exists.");
			return;
		}
		cinemas.add(cinema);
	}

	@Override
	public void cinemaList() {
		if (isCinemaListEmpty()) {
			ConsoleWriter.write("Cinema list is empty.");
			return;
		}
		for (Cinema cinema : cinemas) {
			ConsoleWriter.write("" + cinema);
		}
	}

	@Override
	public void removeCinema(Cinema cinema) {

		if (!checkCinema(cinema)) {
			ConsoleWriter.write("No such cinema.");
			return;
		}

		for (int i = 0; i < cinemas.size(); i++) {
			if (cinemas.get(i).getName().equals(cinema.getName())
					&& cinemas.get(i).getLocation().equals(cinema.getLocation())) {
				cinemas.remove(i);
			}
		}

	}

	@Override
	public List<Cinema> searchCinemaBrand(String cinemaName) {
		List<Cinema> searchedCinema = new ArrayList<>();
		for (Cinema c : cinemas) {
			if (c.getName().equals(cinemaName)) {
				searchedCinema.add(c);
			}
		}
		if (searchedCinema.isEmpty()) {
			ConsoleWriter.write("Cinema not found.");
		}

		return searchedCinema;
	}

	@Override
	public Cinema getCinema(String cinemaName, String cinemaLocation) {
		for (Cinema c : cinemas) {
			if (c.getName().equals(cinemaName) && c.getLocation().equals(cinemaLocation)) {
				return c;
			}
		}
		ConsoleWriter.write("Cinema not found.");
		return null;
	}

	private void setCinemas(List<Cinema> cinemas) {
		this.cinemas = cinemas;
	}

	private boolean isCinemaListEmpty() {
		return cinemas.isEmpty();
	}

	/**
	 * Checks if a certain cinema exists in the cinema list.
	 * 
	 * @param cinema
	 *            - the cinema that is desired to be checked.
	 * @returns true if the cinema is in the cinema list and false if it is not.
	 */
	private boolean checkCinema(Cinema cinema) {
		for (Cinema c : cinemas) {
			if (c.getName().equals(cinema.getName()) && c.getLocation().equals(cinema.getLocation())) {
				return true;
			}
		}
		return false;
	}

}
