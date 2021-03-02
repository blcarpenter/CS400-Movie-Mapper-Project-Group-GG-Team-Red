
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
	
//	public static void main(String[] args) {
//		System.out.println(readDataSet());
//	}
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
                return "Good Will Hunting";
            }
            @Override
            public Integer getYear() {
                return 1997;
            }
            @Override
            public List<String> getGenres() {
                return Arrays.asList(new String[] { "Drama", "Comedy" });
            }
            @Override
            public String getDirector() {
                return "Gus Van Sant";
            }
            @Override
            public String getDescription() {
            	return "Will Hunting, a Boston native, has to navigate his love life with his wicked smart personality.";
            }
            @Override
            public Float getAvgVote() {
            	return 9.9f;
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
                return "Whiplash";
            }
            @Override
            public Integer getYear() {
                return 2014;
            }
            @Override
            public List<String> getGenres() {
                return Arrays.asList(new String[] { "Drama" , "Comedy" });
            }
            @Override
            public String getDirector() {
                return "Damien Chazelle";
            }
            @Override
            public String getDescription() {
            	return "Andrew Niemann is a college drummer seeking stardom.  Terence Fletcher, his coach, tries to help him achieve this but in very unorthodox ways.";
            }
            @Override
            public Float getAvgVote() {
            	return 9.8f;
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
     // TODO: Fixme! Add two more example movies to the list before returning it (could be
     //       ficticious ones).
     return movies;
    
   }
    

}