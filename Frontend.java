import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Frontend  {
    BackendInterfaceHelper backend ;
    static String[] ratings = {"*0","*1","*2","*3","*4","*5","*6","*7","*8","*9","*10"};
    List<MovieInterface> topThreeMovies;
    private  static String[] genres;

    public Frontend(String[] input) {
        this.backend = new BackendInterfaceHelper(input);
        this.topThreeMovies =  backend.getThreeMovies(10);
        List<String> foo = backend.getGenres();
        this.genres = new String[foo.size()];
        for(int i = 0 ; i < foo.size();i++){
            genres[i] = foo.get(i);
        }

    }
    public Frontend(Reader r) {
        this.backend = new BackendInterfaceHelper(r);
    }
    public void run(){
        backend.addAvgRating("10");
        sortTopThree();
        boolean exit = false;
        while(!exit){
            int count = 1;
            for (MovieInterface movie : topThreeMovies) {
                System.out.print(count + ": ");
                movieToString(movie);
                count++;
            }
            Scanner scan = new Scanner(System.in);
            System.out.println("Select movie by pressing the corresponding number on the key pad or press r to go to ratings or g to go to genre mode or x to exit");
            Object res = scan.next();
            if(res.toString().toLowerCase().equals("r")){
                ratingsMenu();
            }else if (res.toString().toLowerCase().equals("g")){
                genreMenu();
            } else if (res.toString().toLowerCase().equals("x")) {
                exit =true;
            } else if(((int)res > 0) && ((int)res < 4)){
                displayMovie(topThreeMovies.get((int)res - 1));
            }else{
                System.out.println("respond with correct response");
            }
        }
    }
    public boolean ratingsMenu() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Select ratings that you want to watch\n A star means it is selected\nPress the number you want to select or deselect\n Enter it in a line separated by spaces");
        for (String rating : ratings) {
            System.out.println(rating);
        }
        ArrayList<Integer> responses = new ArrayList<>();
        String s = scan.nextLine();
        s = s.replaceAll(" ", "");
        for(int i = 0 ; i<s.length()-1;i++){
        responses.add(Integer.parseInt(s.substring(i,i+1)));
        }
        responses.add(Integer.parseInt(s.substring(s.length()-1)));
        for(int res: responses){
            if(ratings[res].contains("*")){
                removerating(Integer.toString(res));
            }else{
                addRating(Integer.toString(res));
            }
        }
        ArrayList<MovieInterface> matches = new ArrayList<>();
        for(int i = 0 ; i < 11;i++){
            List<MovieInterface> three = backend.getThreeMovies(i);
            for(int j =0;i<3;i++){
                if(!(three.size() ==0)){
                    matches.add(three.get(j));
                }
            }
        }
        System.out.println(backend.getNumberOfMovies()+" is the amount of matches ");
        if(backend.getNumberOfMovies() == 0){
            System.out.println(" Unfortunately there are no matching criteria");
            return false;
        }
        return displaygenre(matches);
    }

    private boolean displayrat(ArrayList<MovieInterface> foo){
        boolean exist = false;
        int start = 0;
        int pg = 0;
        Scanner scan = new Scanner(System.in);
        while (!exist){
            displayListofRatings(foo,start);
            System.out.println("page: "+pg);
            System.out.println("press the page you want to go on or x to exit");
            Object page = scan.next();
            if(page.equals("x")){
                exist= true;
            }
            start = 3*(int)page;
            pg = (int) page;
        }
        return false;
    }
    public boolean genreMenu() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Select genre that you want to watch\n A star means it is selected\nPress the number you want to select or deselect\n Enter it in a line separated by spaces");
        for (String genre : genres) {
            System.out.print(genre);
        }
        ArrayList<Integer> responses = new ArrayList<>();
        String s = scan.nextLine();
        s = s.replaceAll(" ", "");
        for(int i = 0 ; i<s.length()-1;i++){
            responses.add(Integer.parseInt(s.substring(i,i+1)));
        }
        responses.add(Integer.parseInt(s.substring(s.length()-1)));
        for(int res: responses){
            if(ratings[res].contains("*")){
                removerating(Integer.toString(res));
            }else{
                addRating(Integer.toString(res));
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
        for(int i = 0 ; i < genres.length;i++){
            List<MovieInterface> three = backend.getThreeMovies(i);
            for(int j =0;i<3;i++){
                matches.add(three.get(j));
            }
        }
        System.out.println(backend.getNumberOfMovies()+" is the amount of matches ");
        if(backend.getNumberOfMovies() == 0){
            System.out.println(" Unfortunately there are no matching criteria");
            return false;
        }
       return displaygenre(matches);
    }
    private boolean displaygenre(ArrayList<MovieInterface> foo){
        boolean exist = false;
        int start = 0;
        int pg = 0;
        Scanner scan = new Scanner(System.in);
        while (!exist){
            displayListofRatings(foo,start);
            System.out.println("page: "+pg);
            System.out.println("press the page you want to go on or x to exit");
            Object page = scan.next();
            if(page.equals("x")){
                exist= true;
            }
            start = 3*(int)page;
            pg = (int) page;
        }
        return false;
    }
    private void displayListofRatings(ArrayList<MovieInterface> foo,int start){
        String res = "";
        res +="|"+ foo.get(start).getTitle()+"|"+foo.get(start+1).getTitle()+"|"+foo.get(start+2).getTitle()+"|\n";
        res +="|"+ foo.get(start).getDirector()+"|"+foo.get(start+1).getDirector()+"|"+foo.get(start+2).getDirector()+"|\n";
        res +="|"+ foo.get(start).getAvgVote()+"|"+foo.get(start+1).getAvgVote()+"|"+foo.get(start+2).getAvgVote()+"|\n";
        res +="|"+ foo.get(start).getGenres()+"|"+foo.get(start+1).getGenres()+"|"+foo.get(start+2).getGenres()+"|\n";
        System.out.println(res);
    }

    private void displayMovie(MovieInterface movie){
        System.out.println(movie.getTitle()+" Directed by " +movie.getDirector());
        System.out.println("Rating: "+movie.getAvgVote());
        System.out.println("Genre: "+movie.getGenres());
        System.out.println("Year: "+movie.getYear());
        System.out.println(movie.getDescription());
    }
   private void movieToString(MovieInterface movie){
        System.out.print(movie.getTitle());
    }
    private void addRating(String rating) {
        backend.addAvgRating(rating);
        if(!ratings[Integer.parseInt(rating)].contains("*")) {
            ratings[Integer.parseInt(rating)] = "*" +ratings[Integer.parseInt(rating)];
        }

    }
    private void removerating(String rating) {
        backend.removeAvgRating(rating);
        if (ratings[Integer.parseInt(rating)].contains("*")) {
            ratings[Integer.parseInt(rating)] = ratings[Integer.parseInt(rating)];
        }
    }
        private void addGenre(String gen) {
            backend.addGenre(genres[Integer.parseInt(gen)]);
            if(!genres[Integer.parseInt(gen)].contains("*")) {
                genres[Integer.parseInt(gen)] = "*" + genres[Integer.parseInt(gen)];
            }

        }
        private void removeGenre(String gen) {
            backend.removeGenre(genres[Integer.parseInt(gen)]);
            if(genres[Integer.parseInt(gen)].contains("*")) {
                genres[Integer.parseInt(gen)] = genres[Integer.parseInt(gen)];
            }

    }

    private void sortTopThree(){
       for(int i = 0 ; i < topThreeMovies.size();i++){
           for(int j = 0 ; j<topThreeMovies.size();j++){
               if(topThreeMovies.get(i).compareTo(topThreeMovies.get(j))==-1){
                   MovieInterface movie = topThreeMovies.get(i);
                   topThreeMovies.remove(i);
                   topThreeMovies.add(i, topThreeMovies.get(j));
                   topThreeMovies.remove(j);
                   topThreeMovies.add(j, topThreeMovies.get(i));
               }
           }
       }
    }

}
