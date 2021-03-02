import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// --== CS400 File Header Information ==--
// Author: CS400 Course Staff
// Email: heimerl@cs.wisc.edu / dahl@cs.wisc.edu
// Notes: This dummy class is part of the starter archive for Project One
//        in spring 2021. You can extend it to work on your Project One Final
//        App.
public class MovieDataReaderDummy implements MovieDataReaderInterface {

    /**
     * Method that reades movie data in CSV format from the Redaer provided. The dummy implementations
     * will always return the same 3 sets of movies.
     */
    @Override
    public List<MovieInterface> readDataSet(Reader inputFileReader) throws FileNotFoundException, IOException {
        ArrayList<MovieInterface> movies = new ArrayList<MovieInterface>();
        movies.add(new MovieInterface() {

            @Override
            public String getTitle() {
                return "Plan 9 from Outer Spacce";
            }

            @Override
            public Integer getYear() {
                return 1959;
            }

            @Override
            public List<String> getGenres() {
                return Arrays.asList(new String[] { "Action", "Comedy" });
            }

            @Override
            public String getDirector() {
                return "Ed Wood";
            }

            @Override
            public String getDescription() {
                return "Residents of California's San Fernando Valley are under attack by flying saucers from outer space.";
            }

            @Override
            public Float getAvgVote() {
                return 5.3f;
            }

            @Override
            public int compareTo(MovieInterface otherMovie) {
                if (this.getTitle().equals(otherMovie.getTitle())) {
                    return 0;
                    // sort by rating
                } else if (this.getAvgVote() < otherMovie.getAvgVote()) {
                    return +1;
                } else {
                    return -1;
                }
            }
            
        });
        
        movies.add(new MovieInterface() {

          @Override
          public String getTitle() {
              return "Star Wars: The Force Awakens";
          }

          @Override
          public Integer getYear() {
              return 2016;
          }

          @Override
          public List<String> getGenres() {
              return Arrays.asList(new String[] { "Action", "Adventure", "Sci-Fi" });
          }

          @Override
          public String getDirector() {
              return "J.J. Abrams";
          }

          @Override
          public String getDescription() {
              return "The Force Awakens follows Rey, Finn, Poe Dameron, and Han Solo's search for Luke Skywalker and their fight in the Resistance, led by General Leia Organa and veterans of the Rebel Alliance, against Kylo Ren and the First Order, a successor to the Galactic Empire.";
          }

          @Override
          public Float getAvgVote() {
              return 4.7f;
          }

          @Override
          public int compareTo(MovieInterface otherMovie) {
              if (this.getTitle().equals(otherMovie.getTitle())) {
                  return 0;
                  // sort by rating
              } else if (this.getAvgVote() < otherMovie.getAvgVote()) {
                  return +1;
              } else {
                  return -1;
              }
          }
          
      });
        
        movies.add(new MovieInterface() {

          @Override
          public String getTitle() {
              return "Black Panther";
          }

          @Override
          public Integer getYear() {
              return 2018;
          }

          @Override
          public List<String> getGenres() {
              return Arrays.asList(new String[] { "Sci-Fi", "Adventure" });
          }

          @Override
          public String getDirector() {
              return "Ryan Coogler";
          }

          @Override
          public String getDescription() {
              return "T'Challa is crowned king of Wakanda following his father's death, but he is challenged by Killmonger who plans to abandon the country's isolationist policies and begin a global revolution.";
          }

          @Override
          public Float getAvgVote() {
              return 4.8f;
          }

          @Override
          public int compareTo(MovieInterface otherMovie) {
              if (this.getTitle().equals(otherMovie.getTitle())) {
                  return 0;
                  // sort by rating
              } else if (this.getAvgVote() < otherMovie.getAvgVote()) {
                  return +1;
              } else {
                  return -1;
              }
          }
          
      });
        
        return movies;
    }
   
}

