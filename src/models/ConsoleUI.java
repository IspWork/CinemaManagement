package models;

/**
 * This class represents an engine class that combines all the classes together.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import interfaces.Cinema;
import interfaces.CinemaManagement;
import interfaces.Movie;

public class ConsoleUI {
	private CinemaManagement cinemaM;
	private Cinema cinema;
	// a list in which he user commands are stored.
	private List<String> userMenuInput;
	private BufferedReader reader;

	public ConsoleUI() {
		this.setCinemaM(new CinemaManagementImpl());
		this.setUserInput(new ArrayList<>());
		this.setReader(new BufferedReader(new InputStreamReader(System.in)));
	}

	public void startLoop() throws IOException {
		ConsoleWriter.write("Welcome to the cinema management system! Please select one of the following options.");
		while (true) {
			menu();
			if (userMenuInput.get(0).equals("4")) {
				break;
			}
			switch (userMenuInput.get(0)) {
			case "1":
				magagementLoop();
				break;
			case "2":
				confirmCinema();
				break;
			case "3":
				askCinema();
				break;
			default:
				ConsoleWriter.write("Incorrect menu option.");
				addLine();
				break;
			}

		}
	}

	/**
	 * The main menu of the user interface.
	 * 
	 * @throws IOException
	 */
	private void menu() throws IOException {
		userMenuInput.clear();
		ConsoleWriter.write("");
		ConsoleWriter.write("Main Menu: ");
		ConsoleWriter.write("Press (1) to manage cinemas.");
		ConsoleWriter.write("Press (2) to manipulate a certain cinema");
		ConsoleWriter.write("Press (3) to manipulate a certain cinemas movie list.");
		ConsoleWriter.write("Press (4) to exit the system.");
		userMenuInput.add(reader.readLine());
		addLine();
	}

	/**
	 * The cinema management loop. Can add and remove cinemas. Can display a
	 * list of cinemas.
	 * 
	 * @throws IOException
	 */
	private void magagementLoop() throws IOException {
		while (true) {
			managementMenu();
			if (userMenuInput.get(0).equals("5")) {
				break;
			}
			switch (userMenuInput.get(0)) {
			case "1":
				addCinema();
				break;
			case "2":
				removeCinema();
				break;
			case "3":
				cinemaM.cinemaList();
				addLine();
				break;
			case "4":
				searchCinema();
				break;
			default:
				ConsoleWriter.write("Incorrect menu option.");
				addLine();
				break;

			}
		}
	}

	/**
	 * The cinema management menu.
	 * 
	 * @throws IOException
	 */
	private void managementMenu() throws IOException {
		userMenuInput.clear();
		ConsoleWriter.write("Cinema Management MenU: ");
		ConsoleWriter.write("Press (1) to add a cinema to the system.");
		ConsoleWriter.write("Press (2) to remove a cinema from the system.");
		ConsoleWriter.write("Press (3) to display a list of all cinemas in the system.");
		ConsoleWriter.write("Press (4) to search for a cinema.");
		ConsoleWriter.write("Press (5) to go back to the main menu.");
		userMenuInput.add(reader.readLine());
		addLine();

	}

	private void addCinema() throws IOException {
		askCinemaName();
		askCinemaLocation();
		cinema = new CinemaImpl(userMenuInput.get(1), userMenuInput.get(2));
		cinemaM.addCinema(cinema);
		addLine();
	}

	private void removeCinema() throws IOException {
		askCinemaName();
		askCinemaLocation();
		cinema = new CinemaImpl(userMenuInput.get(1), userMenuInput.get(2));
		cinemaM.removeCinema(cinema);
		addLine();
	}

	private void searchCinema() throws IOException {
		askCinemaName();
		for (Cinema c : cinemaM.searchCinemaBrand(userMenuInput.get(1))) {
			ConsoleWriter.write("" + c);
		}
		addLine();
	}

	/**
	 * Selects a cinema to be manipulated.
	 * 
	 * @throws IOException
	 */
	private void confirmCinema() throws IOException {
		askCinemaName();
		askCinemaLocation();
		if (cinemaM.getCinema(userMenuInput.get(1), userMenuInput.get(2)) != null) {
			
			ConsoleWriter.write("You have selected: " + cinemaM.getCinema(userMenuInput.get(1), userMenuInput.get(2))
					+ " for manipulation.");
			
			cinema = cinemaM.getCinema(userMenuInput.get(1), userMenuInput.get(2));
			addLine();
			manipulationLoop();
		}

	}

	/**
	 * The cinema manipulation loop. Operations: add projection, add movie at
	 * projection, remove movie from all projections, remove movie frome one
	 * projection, view movie at projection, view all available projections,
	 * view a list of movies that can be projected in a certain cinema.
	 * 
	 * @throws IOException
	 */
	private void manipulationLoop() throws IOException {
		while (true) {
			manipulationMenu();
			if (userMenuInput.get(0).equals("8")) {
				break;
			}
			try {
				switch (userMenuInput.get(0)) {
				case "1":
					addProjectionT();
					break;
				case "2":
					addMovie();
					break;
				case "3":
					removeFromAllP();
					break;
				case "4":
					removeAtP();
					break;
				case "5":
					viewMoviesAtP();
					break;
				case "6":
					cinema.allProjections();
					addLine();
					break;
				case "7":
					cinema.displayMovieList();
					addLine();
					break;
				default:
					ConsoleWriter.write("Incorrect menu option.");
					addLine();
					break;
				}
			} catch (IllegalArgumentException e) {
				ConsoleWriter.write("" + e.getMessage());
				addLine();
			}
		}
	}

	/**
	 * The cinema manipulation menu.
	 * 
	 * @throws IOException
	 */
	private void manipulationMenu() throws IOException {
		userMenuInput.clear();
		ConsoleWriter.write("" + cinema);
		ConsoleWriter.write("");
		ConsoleWriter.write("Cinema Manipulation Menu:");
		ConsoleWriter.write("Please select one of the following manipulation options: ");
		ConsoleWriter.write("Press (1) to add projection time to the cinema.");
		ConsoleWriter.write("Press (2) to add a movie at projection time.");
		ConsoleWriter.write("Press (3) to remove a movie from all projections in the cinema.");
		ConsoleWriter.write("Press (4) to remove a movie from a certain projection time.");
		ConsoleWriter.write("Press (5) to view all movies at a certain projection time. ");
		ConsoleWriter.write("Press (6) to view all projection times and movies in them.");
		ConsoleWriter.write("Press (7) to view all movies that can be projected in this cinema.");
		ConsoleWriter.write("Press (8) to stop manipulating this cinema.");
		userMenuInput.add(reader.readLine());
		addLine();

	}

	private void addProjectionT() throws IOException {
		askProjectionTime();
		cinema.addProjectionTime(userMenuInput.get(1));
		addLine();
	}

	private void addMovie() throws IOException {
		askProjectionTime();
		askMovieName();
		cinema.addMovieAtProjectionTime(userMenuInput.get(1), cinema.getMovie(userMenuInput.get(2)));
		addLine();
	}

	private void removeFromAllP() throws IOException {
		askMovieName();
		cinema.removeMovieFromProjections(userMenuInput.get(1));
		addLine();
	}

	private void removeAtP() throws IOException {
		askProjectionTime();
		askMovieName();

		cinema.removeMovieAtProjectionTime(userMenuInput.get(1), cinema.getMovie(userMenuInput.get(2)));
		addLine();
	}

	private void viewMoviesAtP() throws IOException {
		askProjectionTime();
		cinema.projectionsAtTime(userMenuInput.get(1));
		addLine();
	}

	/**
	 * The movie manipulation loop. Can manipulate movies of a certain cinema.
	 * 
	 * @throws IOException
	 */
	private void movieManipulationLoop() throws IOException {
		while (true) {
			movieManipulationMenu();
			if (userMenuInput.get(0).equals("4")) {
				break;
			}

			switch (userMenuInput.get(0)) {
			case "1":
				addMovieToList();
				break;
			case "2":
				removeMovieFromList();
				break;
			case "3":
				cinema.displayMovieList();
				addLine();
				break;
			default:
				ConsoleWriter.write("Incorrect menu option.");
				addLine();
				break;
			}

		}
	}

	/**
	 * Movie manipulation menu.
	 * 
	 * @throws IOException
	 */
	private void movieManipulationMenu() throws IOException {
		userMenuInput.clear();
		ConsoleWriter.write("" + cinema);
		ConsoleWriter.write("");
		ConsoleWriter.write("Cinema-Movie Manipulation Menu:");
		ConsoleWriter.write("Please select one of the following manipulation options: ");
		ConsoleWriter.write("Press (1) to add movie to the cinema movie list.");
		ConsoleWriter.write("Press (2) to remove a movie from the cinema movie list.");
		ConsoleWriter.write("Press (3) to view all movies in the cinema list.");
		ConsoleWriter.write("Press (4) to stop manipulating this cinema.");
		userMenuInput.add(reader.readLine());
		addLine();
	}

	private void addMovieToList() throws IOException {
		ConsoleWriter.write("Movie name: ");
		userMenuInput.add(reader.readLine());
		ConsoleWriter.write("Movie genre: ");
		userMenuInput.add(reader.readLine());
		ConsoleWriter.write("Movie imdb rating: ");
		userMenuInput.add(reader.readLine());
		ConsoleWriter.write("Movie ticket price: ");
		userMenuInput.add(reader.readLine());
		ConsoleWriter.write("Movie actors(write all actors on the same line with a ',' after each actor): ");
		userMenuInput.add(reader.readLine());
		ConsoleWriter.write("Add synopsis: ");
		userMenuInput.add(reader.readLine());

		try {
			Movie movie = new MovieImpl(userMenuInput.get(1), userMenuInput.get(2),
					Double.valueOf(userMenuInput.get(3)), Double.valueOf(userMenuInput.get(4)),
					userMenuInput.get(5).split(","));

			movie.setSynopsis(userMenuInput.get(6));

			cinema.addMovie(movie);
		} catch (NumberFormatException e) {
			ConsoleWriter.write("Incorrect imdb or price value.");
		}
		addLine();
	}

	private void removeMovieFromList() throws IOException {
		askMovieName();
		cinema.removeMovie(userMenuInput.get(1));
		addLine();
	}

	private void askCinema() throws IOException {
		askCinemaName();
		askCinemaLocation();
		if (cinemaM.getCinema(userMenuInput.get(1), userMenuInput.get(2)) != null) {
			ConsoleWriter.write("You have selected: " + cinemaM.getCinema(userMenuInput.get(1), userMenuInput.get(2)));
			cinema = cinemaM.getCinema(userMenuInput.get(1), userMenuInput.get(2));
			addLine();
			movieManipulationLoop();
		}
	}

	private void askMovieName() throws IOException {
		ConsoleWriter.write("Enter movie name: ");
		userMenuInput.add(reader.readLine());
	}

	private void askProjectionTime() throws IOException {
		ConsoleWriter.write("Enter projection time: ");
		userMenuInput.add(reader.readLine());
	}

	private void askCinemaLocation() throws IOException {
		ConsoleWriter.write("Please enter the location of the cinema: ");
		userMenuInput.add(reader.readLine());
	}

	private void askCinemaName() throws IOException {
		ConsoleWriter.write("Please enter the name of the cinema: ");
		userMenuInput.add(reader.readLine());
	}

	private void addLine() {
		ConsoleWriter.write("--------------------------------------------------------");
	}

	private void setCinemaM(CinemaManagement cinemaM) {
		this.cinemaM = cinemaM;
	}

	private void setUserInput(List<String> userInput) {
		this.userMenuInput = userInput;
	}

	private void setReader(BufferedReader reader) {
		this.reader = reader;
	}

}
