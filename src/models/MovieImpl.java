package models;

/**
 * Implementation of the Movie interface.
 */
import interfaces.Movie;

public class MovieImpl implements Movie {
	private String name;
	private String genre;
	private String synopsis;
	private String[] actors;
	private double imdbRating;
	private double ticketPrice;

	public MovieImpl(String name, String genre, double imdbRating, double ticketPrice, String... actors) {

		this.setName(name);
		this.setGenre(genre);
		this.setImdbRating(imdbRating);
		this.setTicketPrice(ticketPrice);
		this.setActors(actors);

	}

	/**
	 * Sets the name of the movie.
	 * 
	 * @param name
	 *            - the name of the movie.
	 */
	private void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	/**
	 * Sets the actors of the movie.
	 * 
	 * @param actors
	 *            - a string array representation of the actors in the movie.
	 */
	private void setActors(String[] actors) {
		this.actors = actors;
	}

	@Override
	public String[] getActors() {
		return actors;
	}

	/**
	 * Sets the genre of the movie.
	 * 
	 * @param genre
	 *            - the genre of the movie.
	 */
	private void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String getGenre() {
		return genre;
	}

	@Override
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	@Override
	public String getSynopsis() {
		return synopsis;
	}

	/**
	 * Sets the imdb rating of the movie.
	 * 
	 * @param imdbRating
	 *            - the imdb rating of the movie .
	 */
	private void setImdbRating(double imdbRating) {
		this.imdbRating = imdbRating;
	}

	@Override
	public double getImdbRating() {
		return imdbRating;
	}

	/**
	 * Sets the ticket price of the movie.
	 * 
	 * @param ticketPrice
	 *            - the ticket price of the movie.
	 */
	private void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	@Override
	public double getTicketPrice() {
		return ticketPrice;
	}

	@Override
	public String toString() {
		StringBuilder stringB = new StringBuilder();
		stringB.append("Movie name: ")
		       .append(this.name)
		       .append(System.getProperty("line.separator"))
		       .append("Genre: ")
			   .append(this.genre)
			   .append(System.getProperty("line.separator"))
			   .append("Imdb rating: ")
			   .append(this.imdbRating)
			   .append(System.getProperty("line.separator"))
			   .append("Ticket Price: ")
			   .append(this.ticketPrice)
			   .append(System.getProperty("line.separator"))
			   .append("Actors: ");
		for (String actor : actors) {
			stringB.append(actor.trim()).append(", ");
		}
		stringB.deleteCharAt(stringB.length() - 2)
		       .append(System.getProperty("line.separator"))
		       .append("Synopsis: ")
			   .append(this.synopsis);

		return stringB.toString();
	}
}
