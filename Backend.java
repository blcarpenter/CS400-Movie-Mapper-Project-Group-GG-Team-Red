// --== CS400 File Header Information ==--
// Name: Tate Riordan
// Email: triordan2@wisc.edu
// Team: Red
// Group: GG
// TA: Surabhi
// Lecturer: Gary Dahl
// Notes to Grader: n/a
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * 
 * @author Tate Riordan
 *
 */
public class Backend extends HashTableMap<String, LinkedList<MovieInterface>> implements BackendInterface {

	
//	public static void main(String[] args) {
//		addGenre("Western");
//		System.out.println();
//	}
	private MovieDataReader movieHelper;
	private List<MovieInterface> movieArray;
	private String[] commandLine;
	private HashTableMap<String, LinkedList<MovieInterface>> genreTable;
	private HashTableMap<String, LinkedList<MovieInterface>> ratingTable;
	private List<String> genreList;
//	private List<String> AvgRtgList;
	private List<String> genres;
	private List<String> ratings;
	
	/**
	 * This constructor takes the command line as an argument and creates a hashtable
	 * @param args, from command line
	 */
	public Backend(String[] args) {
		// this is for the command line from the search bar the user uses
		this.commandLine = args;
		this.movieHelper = new MovieDataReader();
		this.genreTable = new HashTableMap<String, LinkedList<MovieInterface>>();
		this.ratingTable = new HashTableMap<String, LinkedList<MovieInterface>>();
		this.genreList = new ArrayList<String>();
		this.genres = new ArrayList<>();
		this.ratings = new ArrayList<>();
		Reader helper = null;
		try {
			helper = new FileReader(commandLine[0]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// we create two hash tables here with keys for one as genres and ratings as the other
		try {
			try {
				this.movieArray = movieHelper.readDataSet(helper);
			} catch (DataFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// this for loop makes a hash map with the ratings as keys
			for (int j = 0; j < 11; j++) {
				String ratingHelper = j + ".0";
				ratingTable.put(ratingHelper, new LinkedList<MovieInterface>());
			}
			for (int i = 0; i < movieArray.size(); i++) {
				//this.addAvgRating(Double.toString(Math.floor(movieArray.get(i).getAvgVote())));
				String rating = Double.toString(Math.floor(movieArray.get(i).getAvgVote()));
				// adds it if key does not exist already
				// adds it
				this.ratingTable.get(rating).add(movieArray.get(i));
			}
			// This double for loop creates the hash map that has the genres as the keys
			for (int i = 0; i < movieArray.size(); i++) {
				for (int j = 0; j < movieArray.get(i).getGenres().size(); j++) {
					if (!genreTable.containsKey(movieArray.get(i).getGenres().get(j))) {
						genreTable.put(movieArray.get(i).getGenres().get(j), new LinkedList<MovieInterface>());
						// use this to help us with getAllGenres() later
						genreList.add(movieArray.get(i).getGenres().get(j));
					}
					//this.addGenre(movieArray.get(i).getGenres().get(j));
					this.genreList.add(movieArray.get(i).getGenres().get(j));
					this.genreTable.get(movieArray.get(i).getGenres().get(j)).add(movieArray.get(i));
				}
			} 
			
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * This is the constructor which takes in a parameter from the data wrangler for a file
	 * and initializes everything like the above constructor
	 * @param file, the file from the data wrangler to be read
	 * 
	 */
	public Backend(Reader file) {
		// initialize everything
		this.movieHelper = new MovieDataReader();
		this.genreTable = new HashTableMap<String, LinkedList<MovieInterface>>();
		this.ratingTable = new HashTableMap<String, LinkedList<MovieInterface>>();
		this.genreList = new ArrayList<String>();
		this.commandLine = null;
		this.genres = new ArrayList<>();
		this.ratings = new ArrayList<>();
		// This creates the hash map from the help of the data wrangler's readDataSet() method
		try {
			try {
				this.movieArray = movieHelper.readDataSet(file);
			} catch (DataFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// this for loop makes a hash map with the ratings as keys
			for (int j = 0; j < 11; j++) {
				String ratingHelper = j + ".0";
				ratingTable.put(ratingHelper, new LinkedList<MovieInterface>());
			}
			for (int i = 0; i < movieArray.size(); i++) {
				//this.addAvgRating(Double.toString(Math.floor(movieArray.get(i).getAvgVote())));
				String rating = Double.toString(Math.floor(movieArray.get(i).getAvgVote()));
				this.ratingTable.get(rating).add(movieArray.get(i));
			}
			// This double for loop creates the hash map that has the genres as the keys
			for (int i = 0; i < movieArray.size(); i++) {
				for (int j = 0; j < movieArray.get(i).getGenres().size(); j++) {
					if (!genreTable.containsKey(movieArray.get(i).getGenres().get(j))) {
						genreTable.put(movieArray.get(i).getGenres().get(j), new LinkedList<MovieInterface>());
						genreList.add(movieArray.get(i).getGenres().get(j));
					}
					//this.addGenre(movieArray.get(i).getGenres().get(j));
					this.genreList.add(movieArray.get(i).getGenres().get(j));
					this.genreTable.get(movieArray.get(i).getGenres().get(j)).add(movieArray.get(i));
				}
			} 
			
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		this.genreList = new ArrayList<String>();
//		this.AvgRtgList = new ArrayList<String>()
	}
	
	/**
	 * This method helps add genres to a list that is used in user interface
	 */
	@Override
	public void addGenre(String genre) {
		// TODO Auto-generated method stub
		// checks if key is already in hash map
		if (getAllGenres().contains(genre)) {
			genres.add(genre);
		}
	}

	/**
	 * This method helps add the average ratings from the user to a list
	 */
	@Override
	public void addAvgRating(String rating) {
		// TODO Auto-generated method stub
		// checks the first character
		String rating1 = Character.toString(rating.charAt(0));
		if (rating.length() > 1) {
			String rating2 = Character.toString(rating.charAt(1));
			if (rating1.equals("1") && rating2.equals("0")){
				rating1 = "10.0";
			} else {
				rating1 = rating1 +".0";
			}
		}
		// needs to see if rating is a 10
		
		// checks if it exists and adds it

			ratings.add(rating1);

	}

	/**
	 * This method will remove a specific genre from the list
	 */
	@Override
	public void removeGenre(String genre) {
		// TODO Auto-generated method stub
		if (genres != null) {
			genres.remove(genre);
		}

	}

	/**
	 * This method will help remove the average ratings from the list
	 */
	@Override
	public void removeAvgRating(String rating) {
		// TODO Auto-generated method stub
//		ratingTable.remove(rating);
		if (ratings != null) {
			ratings.remove(rating);
		}
	}

	/**
	 * This method will get the genres specified in the command line
	 */
	@Override
	public List<String> getGenres() {
		// TODO Auto-generated method stub

		return genres;
	}

	/**
	 * This method will get the ratings specified in the command line
	 */
	@Override
	public List<String> getAvgRatings() {
		// TODO Auto-generated method stub
		
		return ratings;
	}

	/**
	 * This method gets the number of movies in a certain rating or genre. 
	 * It checks both hash tables depending on the sizes of arrays
	 */
	@Override
	public int getNumberOfMovies() {
		// TODO Auto-generated method stub
		int size = 0;

		// first checks genres sizes to see how many genres are there, uses this to find number of movies
		// with all of those genres
		if (genres != null && genres.size() != 0) {
				// checks at size 1
			if (genres.size() == 1) {
				size += genreTable.get(genres.get(0)).size();
			// checks at size 2 for genres by scanning one linkedlist and checking if other genre is in the movie
			} else if (genres.size() == 2){
				for (int i = 0; i < genreTable.get(genres.get(0)).size(); i++) {
					if (genreTable.get(genres.get(0)).get(i).getGenres().contains(genres.get(1))) {
						size += 1;
					}
				}
			// similar to size 2 but checks for the third genre
			} else if (genres.size() == 3) {
				for (int i = 0; i < genreTable.get(genres.get(0)).size(); i++) {
					if (genreTable.get(genres.get(0)).get(i).getGenres().contains(genres.get(1))) {
						if (genreTable.get(genres.get(0)).get(i).getGenres().contains(genres.get(2))) {
							size += 1;
						}
				
					}
				}
				// similar to size 3 but checks for the fourth genre
			} else if (genres.size() == 4) {
				for (int i = 0; i < genreTable.get(genres.get(0)).size(); i++) {
					if (genreTable.get(genres.get(0)).get(i).getGenres().contains(genres.get(1))) {
						if (genreTable.get(genres.get(0)).get(i).getGenres().contains(genres.get(2))) {
							if (genreTable.get(genres.get(0)).get(i).getGenres().contains(genres.get(3))) {
								size += 1;
							}
						}
					}
				}
			}
			// now checks the ratings array

		} else if (ratings != null && ratings.size() != 0){
				// if size is 1, then return size of whole array
			if (ratings.size() >= 1) {
				for (int i = 0; i < ratings.size(); i++) {
					size += ratingTable.get(ratings.get(i)).size();
				}
				// movies cannot have more than one rating
			} else {
				return size;
			}
		}
		return size;
		
	}

	/**
	 * This returns a list of three movies in descending order of ratings from a certain index in a resulting
	 * set of a user's commands
	 */
	@Override
	public List<MovieInterface> getThreeMovies(int startingIndex) {
		// TODO Auto-generated method stub
		List<MovieInterface> endList = new ArrayList<>();
		List<MovieInterface> finalList = new ArrayList<>();
			// checks size of genres as different cases
		// uses similar process from getNumberOfMovies(), but instead adds only 3 values to array
		if (genres != null && genres.size() != 0) {
			if (genres.size() == 1) {
				for (int i = 0; i < genreTable.get(genres.get(0)).size(); i++) {
					if (genreTable.get(genres.get(0)).get(i) != null) {
						endList.add(genreTable.get(genres.get(0)).get(i));
					} 
				}
				// size 2
			} else if (genres.size() == 2) {
						for (int i = 0; i < genreTable.get(genres.get(0)).size(); i++) {
							if (genreTable.get(genres.get(0)).get(i).getGenres().contains(genres.get(1))) {
								endList.add(genreTable.get(genres.get(0)).get(i));
								
							}
						}
						// size 3
			} else if (genres.size() == 3) {
				for (int i = 0; i < genreTable.get(genres.get(0)).size(); i++) {
					if (genreTable.get(genres.get(0)).get(i).getGenres().contains(genres.get(1))) {
						if (genreTable.get(genres.get(0)).get(i).getGenres().contains(genres.get(2))) {
								endList.add(genreTable.get(genres.get(0)).get(i));
						}
					}
				}
				// size 4
			} else if (genres.size() == 4) {
				for (int i = 0; i < genreTable.get(genres.get(0)).size(); i++) {
					if (genreTable.get(genres.get(0)).get(i).getGenres().contains(genres.get(1))) {
						if (genreTable.get(genres.get(0)).get(i).getGenres().contains(genres.get(2))) {
							if (genreTable.get(genres.get(0)).get(i).getGenres().contains(genres.get(3))) {
								endList.add(genreTable.get(genres.get(0)).get(i));
							
							}
						}
					}
				}
			}
			// checks ratings similarly to getNumberOfMovies()
			
		} else if (ratings != null && ratings.size() != 0) {
			// this checks if we are dealing with the rating map
			// adds at size 1 in the same way the genre map does
			//System.out.println(ratings.size());
			if (ratings.size() >= 1) {
				for (int j = 0; j < ratings.size(); j++) {
					for (int i = 0; i < ratingTable.get(ratings.get(j)).size(); i++) {
						if (ratingTable.get(ratings.get(j)).get(i) != null) {
							endList.add(ratingTable.get(ratings.get(j)).get(i));
						} else {
							break;
						}
						
					}
				}
			}
				// if the input is multiple ratings, no movie can have two avg ratings
			
		}
		
		// this for loop will put the list in descending order by rating
		for (int i = 0; i < endList.size(); i++) {
			for (int j = 1; j < endList.size(); j++) {
				MovieInterface first = endList.get(i);
				MovieInterface second = endList.get(j);
				if (first.getAvgVote().compareTo(second.getAvgVote()) > 0) {
					continue;
					// switches if need be
				} else {
					MovieInterface helper = first;
					first = second;
					second = helper;
				}
			}
		}
		// adds the three movies from the resulting set to the final answer
		for (int i = startingIndex; i < endList.size(); i++) {
			if (endList.get(i) != null) {
				finalList.add(endList.get(i));
			}
		}

		return finalList;
			
	}

	/**
	 * This will return all of the genres that are in the genre hash map
	 */
	@Override
	public List<String> getAllGenres() {
		// TODO Auto-generated method stub
		List<String> helper = new ArrayList<>();
		if (genreTable == null) {
			return null;
		}
		if (genreList == null) {
			return null;
		}
		// uses the array list that tracks the number of genres in the constructor
		for (int i = 0; i < genreList.size(); i++) {
			if (helper.size() == 0) {
				// adds each one to new list so there are no duplicates
				helper.add(genreList.get(i));
			} else {
				for (int j = 0; j < helper.size(); j++) {
					if (genreList.get(i).equals(helper.get(j))) {
						break;
					} else if (j == helper.size()-1) {
						//System.out.println(genreList);
						helper.add(genreList.get(i));
						//System.out.println(helper);
					}
				}
			}
		}
		// returns the new list created of all the genres
		return helper;
	}
}

