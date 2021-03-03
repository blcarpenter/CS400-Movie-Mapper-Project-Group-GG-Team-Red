// --== CS400 File Header Information ==--
// Name: Tate Riordan
// Email: triordan2@wisc.edu
// Team: Red
// Group: GG
// TA: Surabhi
// Lecturer: Gary Dahl
// Notes to Grader: n/a
import java.util.List;
public interface BackendInterface {

	
	public void addGenre(String genre);
	public void addAvgRating(String rating);
	public void removeGenre(String genre);
	public void removeAvgRating(String rating);
	public List<String> getGenres();
	public List<String> getAvgRatings();
	public int getNumberOfMovies();
	public List<MovieInterface> getThreeMovies(int startingIndex);
	public List<String> getAllGenres();
	
}
