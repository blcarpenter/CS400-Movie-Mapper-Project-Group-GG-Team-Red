// --== CS400 File Header Information ==--
// Name: Junaid Ackroyd
// Email: jackroyd@wisc.edu
// Team: GG Red
// Role: Data Wrangler
// TA: Surabhi
// Lecturer: Gary Dahl
// Notes to Grader: None

import java.util.List;

/**
 * This class models reference type movie
 * @author Junaid
 */
public class Movie implements MovieInterface {
  
  // instance fields
  private String title;
  private int year;
  private List<String> genre;
  private String director;
  private String description;
  private float avgVote;
  
  /**
   * Creates a new Movie object and initializes its instance fields
   * @param title the title of the movie
   * @param year the year of the movie
   * @param genre the genre(s) of the movie
   * @param director the director(s) of the movie
   * @param description the description of the movie
   * @param avgVote the average vote of the movie
   */
  public Movie(String title, int year, List<String> genre, String director, String description, float avgVote) {
    this.title = title;
    this.year = year;
    this.genre = genre;
    this.director = director;
    this.description = description;
    this.avgVote = avgVote;
  }

  /**
   * Gets the title of the movie
   * @return title the title of the movie
   */
  @Override
  public String getTitle() {
    return title;
  }

  /**
   * Gets the year of the movie
   * @return year the year of the movie
   */
  @Override
  public Integer getYear() {
    return year;
  }

  /**
   * Gets the genre(s) of the movie
   * @return genre the genre(s) of the movie
   */
  @Override
  public List<String> getGenres() {
    return genre;
  }

  /**
   * Gets the director(s) of the movie
   * @return director the director(s) of the movie
   */
  @Override
  public String getDirector() {
    return director;
  }

  /**
   * Gets the description of the movie
   * @return description the description of the movie
   */
  @Override
  public String getDescription() {
    return description;
  }

  /**
   * Gets the average vote of the movie
   * @return avgVote the average vote of the movie
   */
  @Override
  public Float getAvgVote() {
    return avgVote;
  }

  /**
   * Compares two objects that implement the MovieInterface
   * 
   */
  @Override
  public int compareTo(MovieInterface otherMovie) {
    if (this.title.equals(otherMovie.getTitle())) {
      return 0;
    }
    else if (this.avgVote < otherMovie.getAvgVote()) {
      return 1;
    }
    else {
      return -1;
    }  
  }
}
