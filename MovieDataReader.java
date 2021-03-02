// --== CS400 File Header Information ==--
// Name: Junaid Ackroyd
// Email: jackroyd@wisc.edu
// Team: GG Red
// Role: Data Wrangler
// TA: Surabhi
// Lecturer: Gary Dahl
// Notes to Grader: None

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.zip.DataFormatException;

/**
 * This class implements type MovieDataReader
 * @author Junaid
 *
 */
public class MovieDataReader implements MovieDataReaderInterface {

  /**
   * Creates a new MovieDataReader object
   */
  public MovieDataReader() {
  }

  /**
   * Processes the data and returns it as a List of Movie objects sorted in descending order
   * @return a list of Movie objects sorted in descending order from the provided dataset. 
   */
  @Override
  public List<MovieInterface> readDataSet(Reader inputFileReader) throws FileNotFoundException, IOException, DataFormatException {
    String temp = "";
    List<MovieInterface> arrlist = new ArrayList<MovieInterface>();
    try {
      BufferedReader reader = new BufferedReader(inputFileReader);
      reader.readLine();
      while((temp = reader.readLine()) != null) {
        String[] row = temp.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        String title = row[0];
        title = title.replace("\"", "");
        int year = Integer.parseInt(row[2]);
        String genre = row[3];
        genre = genre.replace("\"", "");
        genre = genre.replace(" ","");
        List<String> items = Arrays.asList(genre.split("\\s*,\\s*"));
        String dir = row[7];
        dir = dir.replace("\"", "");
        String desc = row[11];
        desc = desc.replace("\"", "");
        float avgvote = Float.parseFloat(row[12]);
        Movie mov = new Movie(title, year, items, dir, desc, avgvote);
        arrlist.add(mov);
      }
      Collections.sort(arrlist);
      return arrlist; 
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return arrlist;
  }  
}
