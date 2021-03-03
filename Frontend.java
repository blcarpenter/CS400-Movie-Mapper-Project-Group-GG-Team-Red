import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Frontend  {
    BackendInterfaceHelper backend ;
    static String[] ratings = {"0","1","2","3","4","5","6","7","8","9","10"};
    List<MovieInterface> topThreeMovies;
    private  static String[] genres;

    public Frontend() {
    }
    public Frontend(Reader r) {
        this.backend = new BackendInterfaceHelper(r);
        selectall();
        this.topThreeMovies =  backend.getThreeMovies(0);
        selectall();
        List<String> foo = backend.getGenres();
        this.genres = new String[foo.size()];
        for(int i = 0 ; i < foo.size();i++){
            genres[i] = foo.get(i);
        }
    }
    public void run(BackendInterfaceHelper helper){
        this.backend =helper;
        selectall();
        this.topThreeMovies =  backend.getThreeMovies(0);
        List<String> foo = backend.getAllGenres();
        this.genres = new String[foo.size()];
        for(int i = 0 ; i < foo.size();i++){
            genres[i] = foo.get(i);
        }
        backend.addAvgRating("10");
        boolean exit = false;
        while(!exit){
            int count = 1;
            for (MovieInterface movie : topThreeMovies) {
                System.out.print(count + ": ");
                movieToString(movie);
                System.out.println();
                count++;
            }
            System.out.println();
            Scanner scan = new Scanner(System.in);
            System.out.println("Select movie by pressing the corresponding number on the key pad or press r to go to ratings or g to go to genre mode or x to exit");
            String res = scan.next();
            if(res.toLowerCase().equals("r")){
                ratingsMenu();
            }else if (res.toLowerCase().equals("g")){
                genreMenu();
            } else if (res.toLowerCase().equals("x")) {
                exit =true;
            } else if((Integer.parseInt(res) > 0) && (Integer.parseInt(res) < 4)){
                displayMovie(topThreeMovies.get(Integer.parseInt(res) - 1));
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
        if(s.length() ==0){

        }else {
            for(int i = 0 ; i<s.length()-1;i++){
                responses.add(Integer.parseInt(s.substring(i,i+1)));
            }
            responses.add(Integer.parseInt(s.substring(s.length()-1)));
        }

        for(int res: responses){
            if(ratings[res].contains("*")){
                removerating(Integer.toString(res));
            }else{
                addRating(Integer.toString(res));
            }
        }
        ArrayList<MovieInterface> matches = new ArrayList<>();
        for(int i = 0 ; i < 11;i++) {
            List<MovieInterface> three = backend.getThreeMovies(0);
            for (int j = 0; j < three.size(); j++) {//rremebr to change back
                if (!(three.size() == 0)) {
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

        System.out.println("Select genre that you want to watch\nA star means it is selected\nPress the number you want to select or deselect\nEnter it in a line separated by spaces");
        int count = 0;
        for (String genre : genres) {
            System.out.println(count +": "+genre);
            count++;
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
            List<MovieInterface> three = backend.getThreeMovies(0);
            for(int j =0;j<three.size();j++){
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
            String page = scan.next();
            if(page.equals("x")){
                exist= true;
                break;
            }
            if( 3*Integer.parseInt(page)<foo.size()){
                start = 3*Integer.parseInt(page);
                pg = Integer.parseInt(page);
            }else{
                System.out.println("not a valid page");
                continue;
            }
        }
        return false;
    }
    private void displayListofRatings(ArrayList<MovieInterface> foo,int start){
        String res = "";
        if(start+1 >= foo.size()){
            res +="|"+ foo.get(start).getTitle()+prints(foo.get(start).getTitle())+"|\n";
            res +="|"+ foo.get(start).getDirector()+prints(foo.get(start).getDirector())+"|\n";
            res +="|"+ foo.get(start).getAvgVote()+prints(Float.toString(foo.get(start).getAvgVote()))+"|\n";
            res +="|"+ foo.get(start).getGenres()+prints(foo.get(start).getGenres())+"|\n";
        }else if(start +2>= foo.size()){
            res +="|"+ foo.get(start).getTitle()+prints(foo.get(start).getTitle())+"|"+foo.get(start+1).getTitle()+prints(foo.get(start+1).getTitle())+"|\n";
            res +="|"+ foo.get(start).getDirector()+prints(foo.get(start).getDirector())+"|"+foo.get(start+1).getDirector()+prints(foo.get(start+1).getDirector())+"|\n";
            res +="|"+ foo.get(start).getAvgVote()+prints(Float.toString(foo.get(start).getAvgVote()))+"|"+foo.get(start+1).getAvgVote()+prints(Float.toString(foo.get(start+1).getAvgVote()))+"|\n";
            res +="|"+ foo.get(start).getGenres()+prints(foo.get(start).getGenres())+"|"+foo.get(start+1).getGenres()+prints(foo.get(start+1).getGenres())+"|\n";
            System.out.println(res);
        }else{
            res +="|"+ foo.get(start).getTitle()+prints(foo.get(start).getTitle())+"|"+foo.get(start+1).getTitle()+prints(foo.get(start+1).getTitle())+"|"+foo.get(start+2).getTitle()+prints(foo.get(start+2).getTitle())+"|\n";
            res +="|"+ foo.get(start).getDirector()+prints(foo.get(start).getDirector())+"|"+foo.get(start+1).getDirector()+prints(foo.get(start+1).getDirector())+"|"+foo.get(start+2).getDirector()+prints(foo.get(start+2).getDirector())+"|\n";
            res +="|"+ foo.get(start).getAvgVote()+prints(Float.toString(foo.get(start).getAvgVote()))+"|"+foo.get(start+1).getAvgVote()+prints(Float.toString(foo.get(start+1).getAvgVote()))+"|"+foo.get(start+2).getAvgVote()+prints(Float.toString(foo.get(start+2).getAvgVote()))+"|\n";
            res +="|"+ foo.get(start).getGenres()+prints(foo.get(start).getGenres())+"|"+foo.get(start+1).getGenres()+prints(foo.get(start+1).getGenres())+"|"+foo.get(start+2).getGenres()+prints(foo.get(start+2).getGenres())+"|\n";
        }
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
    private void selectall(){
        for(int i  = 0 ; i <11;i++){
            backend.addAvgRating(Integer.toString(i));
        }
    }
    private String prints(String s){
        String st ="";
         for(int i  = 0 ; i <30-s.length();i++){
             st+=" ";
         }
         return st;
    }

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
