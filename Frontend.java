/**
 * // --== CS400 File Header Information ==--
 * // Name: Alexander Dudin
 * // Email: dudin@wisc.edu
 * // Team: Red
 * // Group: GG
 * // TA: Surabhi
 * // Lecturer: Gary Dahl
 * // Notes to Grader: n/a
 */
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * this class is used as the interface to work with the back end and get the desired outputs for movies.
 * @author alexdudin
 */
public class Frontend  {
    private Backend backend ;// an instance of backend
    private String[] ratings = {"*0","*1","*2","*3","*4","*5","*6","*7","*8","*9","*10"};// a array of ratings
    private List<MovieInterface> topThreeMovies;// the top trhee movies
    private  static String[] genres;//the added genres
//apparently this was needed
public void main(Backend helper) {
   this.run(helper);
}
    /**
     * main method that runs all of the front end
     * @param helper an instance of the backend
     */
    public void run(Backend helper){//
        this.backend =helper;
        selectall();// priv helpe method to select all ratings
        this.topThreeMovies =  backend.getThreeMovies(0);// gets the top three
        List<String> genresTemp = backend.getAllGenres();// gets the genres
        this.genres = new String[genresTemp.size()];
        //makes it an array
        for(int i = 0 ; i < genresTemp.size();i++){
            genres[i] = genresTemp.get(i);
        }
        boolean exit = false;
        Scanner scan = new Scanner(System.in);
        while(!exit){// while it is not trigered it loops
            int count = 1;
            //prints a menu of the top three
            for (MovieInterface movie : topThreeMovies) {
                System.out.print(count + ": ");
                movieToString(movie);
                System.out.println();
                count++;
            }
         //   System.out.println();
            System.out.println("Select movie by pressing the corresponding number on the key pad or press r to go to ratings or g to go to genre mode or x to exit");
            if(scan.hasNextLine()){
                String res = scan.nextLine();
                //sees what they want to do
                if(res.toLowerCase().equals("r")){
                    ratingsMenu();// goes to the rating menu
                }else if (res.toLowerCase().equals("g")){
                    genreMenu();//goes to the genre menu
                } else if (res.toLowerCase().equals("x")) {
                    exit =true;// exits
                } else if((Integer.parseInt(res) > 0) && (Integer.parseInt(res) < 4)){
                    displayMovie(topThreeMovies.get(Integer.parseInt(res) - 1));// gets a close up of the top three
                }else{
                    System.out.println("respond with correct response");// prompts them to give a valid response
                }
            }

        }
    }

    /**
     * runs the main
     */
    public void ratingsMenu() {
        Scanner scan = new Scanner(System.in);
        //prompts the user
        System.out.println("Select ratings that you want to watch\nA star means it is selected\nPress the number you want to select or deselect\nEnter it in a line separated by spaces\nOr x to exit");
        for (String rating : ratings) {
            System.out.println(rating);
        }
        ArrayList<Integer> responses = new ArrayList<>();
        if(scan.hasNextLine()){
            String s = scan.nextLine();
            if(!s.toLowerCase().equals("x")){
                s = s.replaceAll(" ", "");
                //gets a list of all of the things they want to deselect or select
                if(s.length() !=0){
                    for(int i = 0 ; i<s.length()-1;i++){
                        responses.add(Integer.parseInt(s.substring(i,i+1)));
                    }
                    responses.add(Integer.parseInt(s.substring(s.length()-1)));
                }
                //does the deselecting or selecting
                for(int res: responses){
                    if(ratings[res].contains("*")){
                        removerating(Integer.toString(res));
                    }else{
                        addRating(Integer.toString(res));
                    }
                }
                //gets all of the things that match the given critiria
                ArrayList<MovieInterface> matches = new ArrayList<>();
                for(int i = 0 ; i < 4;i++) {
                    List<MovieInterface> three = backend.getThreeMovies(i*3);
                    for (int j = 0; j < three.size(); j++) {
                        if (!(three.size() == 0)) {
                            matches.add(three.get(j));
                        }
                    }
                }
                System.out.println(backend.getNumberOfMovies()+" is the amount of matches ");
                if(backend.getNumberOfMovies() == 0){
                    System.out.println(" Unfortunately there are no matching criteria");
                }
                displaylist(matches);
            }
        }

    }

    public void genreMenu() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Select genre that you want to watch\nA star means it is selected\nPress the number you want to select or deselect\nEnter it in a line separated by spaces.\n Or x to leave");
        int count = 0;
        for (String genre : genres) {
            System.out.println(count +": "+genre);
            count++;
        }
        ArrayList<Integer> responses = new ArrayList<>();
        if(scan.hasNextLine()){
            String s = scan.nextLine();
            if(!(s.toLowerCase().equals("x"))){
                s = s.replaceAll(" ", "");
                for(int i = 0 ; i<s.length()-1;i++){
                    responses.add(Integer.parseInt(s.substring(i,i+1)));
                }
                responses.add(Integer.parseInt(s.substring(s.length()-1)));
                for(int res: responses){
                    if(ratings[res].contains("*")){
                        removeGenre(Integer.toString(res));
                    }else{
                        addGenre(Integer.toString(res));
                    }
                }
                for(int res: responses){
                    if(genres[res].contains("*")){
                        removeGenre(Integer.toString(res));
                    }else{
                        addGenre(Integer.toString(res));
                    }
                }
                ArrayList<MovieInterface> matches = new ArrayList<>();
                for(int i = 0 ; i < genres.length/3;i++){
                    List<MovieInterface> three = backend.getThreeMovies(i*3);
                    for(int j =0;j<three.size();j++){
                        matches.add(three.get(j));
                    }
                }
                System.out.println(backend.getNumberOfMovies()+" is the amount of matches ");
                if(backend.getNumberOfMovies() == 0){
                    System.out.println(" Unfortunately there are no matching criteria");
                }
                displaylist(matches);
        }
        }
    }

    /**
     *
     * @param movieList an arraylist of movieInterfaces
     * @return false when it exits
     */
    private boolean displaylist(ArrayList<MovieInterface> movieList){
        boolean exit = false;
        int start = 0;
        int pg = 0;
        Scanner scan = new Scanner(System.in);
        while (!exit){
            displayListofMovies(movieList,start);// call to private method
            System.out.println("page: "+pg);
            System.out.println("press the page you want to go on or x to exit");
            String page = scan.next();
            if(page.equals("x")){
                exit= true;
                break;
            }
            if( 3*Integer.parseInt(page)<movieList.size()){
                start = 3*Integer.parseInt(page);
                pg = Integer.parseInt(page);
            }else{
                System.out.println("not a valid page");
                continue;
            }
        }
        return false;
    }

    /**
     * displays the movies in a nice way
     * @param movieList the array list of movies
     * @param start the begging index
     */
    private void displayListofMovies(ArrayList<MovieInterface> movieList,int start){
        String res = "";
        if(start+1 >= movieList.size()){
            res +="|"+ movieList.get(start).getTitle()+prints(movieList.get(start).getTitle())+"|\n";
            res +="|"+ movieList.get(start).getDirector()+prints(movieList.get(start).getDirector())+"|\n";
            res +="|"+ movieList.get(start).getAvgVote()+prints(Float.toString(movieList.get(start).getAvgVote()))+"|\n";
            res +="|"+ movieList.get(start).getGenres()+prints(movieList.get(start).getGenres())+"|\n";
        }else if(start +2>= movieList.size()){
            res +="|"+ movieList.get(start).getTitle()+prints(movieList.get(start).getTitle())+"|"+movieList.get(start+1).getTitle()+prints(movieList.get(start+1).getTitle())+"|\n";
            res +="|"+ movieList.get(start).getDirector()+prints(movieList.get(start).getDirector())+"|"+movieList.get(start+1).getDirector()+prints(movieList.get(start+1).getDirector())+"|\n";
            res +="|"+ movieList.get(start).getAvgVote()+prints(Float.toString(movieList.get(start).getAvgVote()))+"|"+movieList.get(start+1).getAvgVote()+prints(Float.toString(movieList.get(start+1).getAvgVote()))+"|\n";
            res +="|"+ movieList.get(start).getGenres()+prints(movieList.get(start).getGenres())+"|"+movieList.get(start+1).getGenres()+prints(movieList.get(start+1).getGenres())+"|\n";
            System.out.println(res);
        }else{
            res +="|"+ movieList.get(start).getTitle()+prints(movieList.get(start).getTitle())+"|"+movieList.get(start+1).getTitle()+prints(movieList.get(start+1).getTitle())+"|"+movieList.get(start+2).getTitle()+prints(movieList.get(start+2).getTitle())+"|\n";
            res +="|"+ movieList.get(start).getDirector()+prints(movieList.get(start).getDirector())+"|"+movieList.get(start+1).getDirector()+prints(movieList.get(start+1).getDirector())+"|"+movieList.get(start+2).getDirector()+prints(movieList.get(start+2).getDirector())+"|\n";
            res +="|"+ movieList.get(start).getAvgVote()+prints(Float.toString(movieList.get(start).getAvgVote()))+"|"+movieList.get(start+1).getAvgVote()+prints(Float.toString(movieList.get(start+1).getAvgVote()))+"|"+movieList.get(start+2).getAvgVote()+prints(Float.toString(movieList.get(start+2).getAvgVote()))+"|\n";
            res +="|"+ movieList.get(start).getGenres()+prints(movieList.get(start).getGenres())+"|"+movieList.get(start+1).getGenres()+prints(movieList.get(start+1).getGenres())+"|"+movieList.get(start+2).getGenres()+prints(movieList.get(start+2).getGenres())+"|\n";
        }
        System.out.println(res);
    }

    /**
     * displays the movie nicly
     * @param movie a instance of a MovieInterface
     */
    private void displayMovie(MovieInterface movie){
        System.out.println(movie.getTitle()+" Directed by " +movie.getDirector());
        System.out.println("Rating: "+movie.getAvgVote());
        System.out.println("Genre: "+movie.getGenres());
        System.out.println("Year: "+movie.getYear());
        System.out.println(movie.getDescription());
    }

    /**
     * helper method to get the title
     * @param movie a instance of a MovieInterface
     */
   private void movieToString(MovieInterface movie){
        System.out.print(movie.getTitle());
    }

    /**
     * adds the rating
     * @param rating a string representation of a rating
     */
    private void addRating(String rating) {
        backend.addAvgRating(rating);
        if(!ratings[Integer.parseInt(rating)].contains("*")) {
            ratings[Integer.parseInt(rating)] = "*" +ratings[Integer.parseInt(rating)];
        }

    }
    /**
     * removes the rating
     * @param rating a string representation of a rating
     */
    private void removerating(String rating) {
        backend.removeAvgRating(rating);
        if (ratings[Integer.parseInt(rating)].contains("*")) {
            ratings[Integer.parseInt(rating)] = ratings[Integer.parseInt(rating)];
        }
    }
    /**
     * adds the rating
     * @param gen a string representation of a Genre
     */
        private void addGenre(String gen) {
            backend.addGenre(genres[Integer.parseInt(gen)]);
            if(!genres[Integer.parseInt(gen)].contains("*")) {
                genres[Integer.parseInt(gen)] = "*" + genres[Integer.parseInt(gen)];
            }
        }
    /**
     * removes the rating
     * @param gen a string representation of a Genre
     */
    private void removeGenre(String gen) {
        backend.removeGenre(genres[Integer.parseInt(gen)]);
        if(genres[Integer.parseInt(gen)].contains("*")) {
            genres[Integer.parseInt(gen)] = genres[Integer.parseInt(gen)];
        }
    }

    /**
     * selects all ratings
     */
    private void selectall(){
        for(int i  = 0 ; i <11;i++){
            backend.addAvgRating(Double.toString(i));
        }
    }

    /**
     * helps with formating
     * @param s a string to input into the format
     * @return a string with the spaces needed to make it even
     */
    private String prints(String s){
        String st ="";
         for(int i  = 0 ; i <30-s.length();i++){
             st+=" ";
         }
         return st;
    }
    /**
     * helps with formating
     * @param s a string  list to input into the format
     * @return a string with the spaces needed to make it even
     */
    private String prints(List<String> s){
        String st ="";
        int count =26;
        for (int j = 0;j<s.size();j++){
           count -= s.get(j).length();
        }
        for(int i  = 0 ; i <count;i++){
            st+=" ";
        }

        return st;
    }
}
