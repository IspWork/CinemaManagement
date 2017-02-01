package models;
/**
 * An implementation of the Cinema interface.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import interfaces.Cinema;
import interfaces.Movie;

public class CinemaImpl implements Cinema {
	private static final String REGEX_NUMBERS = "[0-9]+";
	private String name;
	private String location;
	// I store my projections in a TreeMap, where the keys are the time of the
	// projections and the values are the movies at the certain projection time.
	private Map<Integer, List<Movie>> projections;
	// A list with all the movies that are available for projections in the
	// cinema.
	private List<Movie> movieList;

	public CinemaImpl(String name, String location) {
		this.setName(name);
		this.setLocation(location);
		this.setProjections(new TreeMap<>());
		this.setMovieList(new ArrayList<>());
	}

	@Override
	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

	@Override
	public String getLocation() {
		return location;
	}

	private void setLocation(String location) {
		this.location = location;
	}

	@Override
	public void allProjections() {
		// checking if the projections map has any key-values mappings.
		if (projections.isEmpty()) {
			ConsoleWriter.write("No projections at the moment.");
			return;
		}
		for (Entry<Integer, List<Movie>> proj : projections.entrySet()) {

			ConsoleWriter.write("");
			ConsoleWriter.write("Projection time: " + convertToNormalTime(proj.getKey()));
			ConsoleWriter.write("Movies: ");
			ConsoleWriter.write("");
			// checking if the value behind a certain key is empty
			if (proj.getValue().isEmpty()) {
				ConsoleWriter.write("At the moment there are no movies for this projection time.");
				continue;
			}
			for (Movie movies : proj.getValue()) {
				ConsoleWriter.write("" + movies);
				ConsoleWriter.write("");
			}
		}
	}

	@Override
	public void projectionsAtTime(String projectionTime) {
		//converting normal time to military.
		int militaryTime = convertToMilitaryTime(projectionTime);
		if (!projections.containsKey(militaryTime)) {
			ConsoleWriter.write("No such projection time.");
			return;
		}
		ConsoleWriter.write("");
		ConsoleWriter.write("Projection time: " + projectionTime);
		ConsoleWriter.write("Movies: ");
		ConsoleWriter.write("");

		for (int i = 0; i < projections.get(militaryTime).size(); i++) {
			ConsoleWriter.write("" + projections.get(militaryTime).get(i));
			ConsoleWriter.write("");
		}
		ConsoleWriter.write("");
	}

	@Override
	public void addProjectionTime(String projectionTime) {
		if (checkProjectionTime(convertToMilitaryTime(projectionTime))) {
			ConsoleWriter.write("Projection time already exists");
			return;
		}
		projections.put(convertToMilitaryTime(projectionTime), new ArrayList<>());

	}

	@Override
	public void removeMovieFromProjections(String movieName) {
		// go through every key-value pair and remove the movies that match the movieName parameter.
		for (Entry<Integer, List<Movie>> proj : projections.entrySet()) {
			for (int i = proj.getValue().size() - 1; i >= 0; i--) {
				if (proj.getValue().get(i).getName().equals(movieName)) {
					proj.getValue().remove(i);
				}
			}
		}
	}

	@Override
	public void addMovieAtProjectionTime(String projectionTime, Movie movie) {
		int militaryTime = convertToMilitaryTime(projectionTime);
		if (movie == null) {
			return;
		}
		if (!checkProjectionTime(militaryTime)) {
			ConsoleWriter.write("No such projection time");
			return;
		}
		projections.get(militaryTime).add(movie);
	}

	@Override
	public void removeMovieAtProjectionTime(String projectionTime, Movie movie) {
		int militaryTime = convertToMilitaryTime(projectionTime);

		if (movie == null || !checkProjectionTime(militaryTime)) {
			ConsoleWriter.write("No such projection time");
			return;
		}
		for (int i = projections.get(militaryTime).size() - 1; i >= 0; i--) {
			if (projections.get(militaryTime).get(i).getName().equals(movie.getName())) {
				projections.get(militaryTime).remove(i);
			}
		}
	}

	@Override
	public void addMovie(Movie movie) {
		if (checkMovieInList(movie.getName()) != -1) {
			ConsoleWriter.write("Movie is already in the movie list");
			return;
		}

		movieList.add(movie);

	}

	@Override
	public void removeMovie(String movieName) {
		int movieIndex = checkMovieInList(movieName);
		if (movieIndex == -1) {
			ConsoleWriter.write("No such movie to be removed.");
			return;
		}
		movieList.remove(movieIndex);
	}

	@Override
	public void displayMovieList() {
		ConsoleWriter.write("Movie List:");
		ConsoleWriter.write("");

		if (movieList.isEmpty()) {
			ConsoleWriter.write("Movie list is empty.");
			return;
		}
		for (Movie m : movieList) {
			ConsoleWriter.write("" + m);
			ConsoleWriter.write("");
		}
	}

	@Override
	public Movie getMovie(String movieName) {
		for (Movie m : movieList) {
			if (m.getName().equals(movieName)) {
				return m;
			}
		}
		ConsoleWriter.write("Movie not found.");
		return null;
	}

	@Override
	public String toString() {
		StringBuilder stringB = new StringBuilder();
		stringB.append("Cinema name: ").append(this.name).append(" -> Address: ").append(this.location);
		return stringB.toString();

	}

	/**
	 * Checks if a certain movie exits in the cinema movie list.
	 * 
	 * @param movieName
	 *            - the name of the movie.
	 * @returns the index of the movie in the list if the movie exits and -1 if
	 *          the movie does not exits
	 */
	private int checkMovieInList(String movieName) {
		for (int i = 0; i < movieList.size(); i++) {
			if (movieList.get(i).getName().equals(movieName)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Method for validating if a certain projection time exists.
	 * 
	 * @param projectionTime
	 *            - the projection time in military time.
	 * @return true if the time exits and false if it does not.
	 */
	private boolean checkProjectionTime(int projectionTime) {
		for (Entry<Integer, List<Movie>> proj : projections.entrySet()) {
			if (proj.getKey() == projectionTime) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Method for checking if a certain time is in the right time format.
	 * 
	 * @param time
	 *            - the time that will be validated.
	 * @returns true if the format of the time is correct and false if it is
	 *          not.
	 */
	private boolean checkTimeFormat(String time) {
		String timeFormat = time.replace(":", "");
		int hours, minutes;

		if (!timeFormat.matches(REGEX_NUMBERS)) {
			return false;
		}

		switch (timeFormat.length()) {
		case 3:
			minutes = Integer.valueOf(timeFormat.substring(1, 3));
			if (minutes > 59) {
				return false;
			}
			break;
		case 4:
			hours = Integer.valueOf(timeFormat.substring(0, 2));
			minutes = Integer.valueOf(timeFormat.substring(2, 4));
			if (hours > 23 || minutes > 59) {
				return false;
			}
			break;
		default:
			return false;
		}

		return true;
	}

	/**
	 * Method for converting normal time to military time.
	 * 
	 * @param time
	 *            - the desired time.
	 * @returns an integer(military) representation of the normal time
	 */
	private int convertToMilitaryTime(String time) {
		if (!checkTimeFormat(time)) {
			throw new IllegalArgumentException("Illegal time format.");
		}

		int militaryTime = Integer.valueOf(time.replace(":", ""));
		return militaryTime;
	}

	/**
	 * Method for converting from military time to normal time.
	 * 
	 * @param militaryTime
	 *            - the military time that will be converted
	 * @returns the converted to normal time military time
	 */
	private String convertToNormalTime(int militaryTime) {
		StringBuilder stringTime = new StringBuilder();
		// append militaryTime to the stringBuilder
		stringTime.append(militaryTime);
		// checking if the time is before or after 1000, the military
		// representation of 10:00
		switch (stringTime.length()) {
		// for cases 1 and 2 i check if the time is before 100
		case 1:
			stringTime.append("0:0").append(stringTime.substring(0, 1));
			stringTime.delete(0, 1);
			break;
		case 2:
			stringTime.append("0:").append(stringTime.substring(0, 2));
			stringTime.delete(0, 2);
			break;
		case 3:
			// if the time is before 1000, then we append to the existing string
			// the same string, but with ':' between the first and second
			// character.
			stringTime.append(stringTime.substring(0, 1) + ":" + stringTime.substring(1, 3));
			// delete the military time from the strinBuilder
			stringTime.delete(0, 3);
			// if the military time was 900 afther the above operation the
			// content of the stringBuilder will be 9:00
			break;
		case 4:
			// if the time is after 1000, then we append to the existing string
			// the same string, but with ':' between the second and third
			// character.
			stringTime.append(stringTime.substring(0, 2) + ":" + stringTime.substring(2, 4));
			// delete the military time from the stringBuilder
			stringTime.delete(0, 4);
			break;
		}
		// the normal time.
		return stringTime.toString();
	}

	private void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}

	private void setProjections(Map<Integer, List<Movie>> projections) {
		this.projections = projections;
	}
}
