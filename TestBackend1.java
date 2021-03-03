import java.io.StringReader;

/**
 * This class contains a set of tests for the back end of the Movie Mapper project.
 */
public class TestBackend1 {

        public static void main(String[] args) {
                (new TestBackend1()).runTests();
        }


        public void runTests() {
                // add all tests to this method
                if (this.testInitialNumberOfMovies()) {
                        System.out.println("Test initial number of movies: PASSED");
                } else {
                        System.out.println("Test initial number of movies: FAILED");
                }
                if (this.testGetAllGenres()) {
                        System.out.println("Test get all genres: PASSED");
                } else {
                        System.out.println("Test get all genres: FAILED");
                }
                if (this.testGetThreeMoviesInitial()) {
                        System.out.println("Test get three movies sorted by rating: PASSED");
                } else {
                        System.out.println("Test get three movies sorted by rating: FAILED");
                }
                if (this.testAddGenre()){
                        System.out.println("Test if genre is added: PASSED");
                } else {
                        System.out.println("Test if genre is added: FAILED");
                }
                if (this.testRemoveGenre()){
                        System.out.println("Test if genre is removed: PASSED");
                } else {
                        System.out.println("Test if genre is removed: FAILED");
                }
                if (this.testGetGenres()){
                        System.out.println("Test if the list of genres is correct: PASSED");
                } else {
                        System.out.println("Test if the list of genres is correct: FAILED");
                }
        }
        /**
         * This test instantiates the back end with three movies and tests whether the
         * initial selection is empty (getNumberOfMovies() returns 0). It passes when
         * 0 is returned and fails in all other cases, including when an exception is
         * thrown.
         * @return true if the test passed, false if it failed
         */
        public boolean testInitialNumberOfMovies() {
                try {
                        // instantiate once BackendInterface is implemented
                        BackendInterface backendToTest = new BackendInterfaceHelper(new StringReader(
                                "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
                                + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
                                + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Par�, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
                                + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
                ));
                        backendToTest.addGenre("Action");
                        backendToTest.addGenre("Comedy");
//                      System.out.println(backendToTest.getThreeMovies(0));
                        if (backendToTest.getNumberOfMovies() == 1) {
                                // test passed
                                return true;
                        } else {
                                // test failed
                                return false;
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        // test failed
                        return false;
                }
        }
        
        /**
         * This test instantiates the back end with three movies and tests whether
         * the getAllGenres method return the correct set of genres for those three
         * movies.
         * @return true if the test passed, false if it failed
         */
        public boolean testGetAllGenres() {
                try {
                        // instantiate once BackendInterface is implemented
                        BackendInterface backendToTest = new BackendInterfaceHelper(new StringReader(
                                        "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
                                        + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
                                        + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Par�, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
                                        + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
                        ));
                        if (backendToTest.getAllGenres().size() == 3
                                       // && backendToTest.getAllGenres().contains("Horror")
                                        && backendToTest.getAllGenres().contains("Action")
                                        && backendToTest.getAllGenres().contains("Comedy")
                                        && backendToTest.getAllGenres().contains("Drama")) {
                                       // && backendToTest.getAllGenres().contains("Romance")) {
                                // test passed
                                return true;
                        } else {
                                // test failed
                                return false;
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        // test failed
                        return false;
                }
        }
                        
        /**
         * This test instantiates the back end with three movies and tests whether the
         * initial list returned by getThreeMovies starting with the first movie (0)
         * is empty. It passes when 0 is returned and fails in all other cases, including
         * when an exception is thrown.
         * @return true if the test passed, false if its failed
         */
        public boolean testGetThreeMoviesInitial() {
                try {
                        // instantiate once BackendInterface is implemented
                        BackendInterface backendToTest = new BackendInterfaceHelper(new StringReader(
                                        "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
                                        + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
                                        + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Par�, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
                                        + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
                        )); 
                        //backendToTest.addGenre("Drama");
//                         backendToTest.addAvgRating("0.0");
//                         backendToTest.addAvgRating("1.0");
//                         backendToTest.addAvgRating("2.0");
//                         backendToTest.addAvgRating("3.0");
//                         backendToTest.addAvgRating("4.0");
//                         backendToTest.addAvgRating("5.0");
//                         backendToTest.addAvgRating("6.0");
//                         backendToTest.addAvgRating("7.0");
//                         backendToTest.addAvgRating("8.0");
//                         backendToTest.addAvgRating("9.0");
//                         backendToTest.addAvgRating("10.0");
                         
                         
//                        System.out.println(backendToTest.getThreeMovies(0));
//                        System.out.println(backendToTest.getNumberOfMovies());
                        if (backendToTest.getThreeMovies(0).size() == 0) {
                                // test passed
                                return true;
                        } else {
                                // test failed
                                return false;
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        // test failed
                        return false;
                }
        }
        
     // TODO: Back End Developer, add at least 2 more tests
        /**
         * This test will check to see if a new genre is added to the list of
         * genres. It uses addGenre() and getAllGenres() to see if the genre
         * that was added is now contained in the new list. If it is successful,
         * true will be returned. If it fails, we return false.
         */
        public boolean testAddGenre(){
                try {
                        BackendInterface backendToTest = new BackendInterfaceHelper(new StringReader(
                                "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
                                + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
                                + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Par�, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
                                + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
                )); 
                        // tries adding a genre
                        backendToTest.addGenre("Comedy");
                        // checks if it adds it successfully
                        //System.out.println(backendToTest.getGenres());
                        if (backendToTest.getGenres().contains("Comedy")){
                                // test passes
                                return true;
                        }else{
                                // test fails
                                return false;
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        // test fails
                        return false;
                }
        }
                        
        /**
         * This method tests whether or not the removeGenre method works or
         * not.  If it does remove the genre needing removal, true will be
         * returned.  If it does not remove the genre, false will be 
         * returned.
         */
        public boolean testRemoveGenre(){
                try {
                        BackendInterface backendToTest = new BackendInterfaceHelper(new StringReader(
                                "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
                                + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
                                + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Par�, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
                                + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
                )); 
                        // adds western to it so we can then remove it
                        backendToTest.addGenre("Comedy");
                        // removes western
                        backendToTest.removeGenre("Comedy");
                        // checks to see if it is removed or not
                        if (!backendToTest.getGenres().contains("Comedy")){
                                // test passes
                                return true;
                        } else {
                                // test fails
                                return false;
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                        // test fails
                        return false;
                }
        }
        
        public boolean testGetGenres(){
            try {
                    BackendInterface backendToTest = new BackendInterfaceHelper(new StringReader(
                            "title,original_title,year,genre,duration,country,language,director,writer,production_company,actors,description,avg_vote\n"
                            + "The Source of Shadows,The Source of Shadows,2020,Horror,83,USA,English,\"Ryan Bury, Jennifer Bonior\",\"Jennifer Bonior, Trevor Botkin\",Four Thieves Productions,\"Ashleigh Allard, Tom Bonington, Eliane Gagnon, Marissa Kaye Grinestaff, Jenna Heffernan, Joshua Hummel, Janice Kingsley, Chris Labasbas, Jared Laufree, Dominic Lee, Vic May, Sienna Mazzone, Lizzie Mounter, Grace Mumm, Ashley Otis\",\"A series of stories woven together by one of our most primal fears, the fear of the unknown.\",3.5\n"
                            + "The Insurrection,The Insurrection,2020,Action,90,USA,English,Rene Perez,Rene Perez,,\"Michael Par�, Wilma Elles, Joseph Camilleri, Rebecca Tarabocchia, Jeanine Harrington, Malorie Glavan, Danner Boyd, Michael Cendejas, Woody Clendenen, Keely Dervin, Aaron Harvey, Tony Jackson, Michael Jarrod, Angelina Karo, Bernie Kelly\",The director of the largest media company wants to expose how left-wing powers use film to control populations.,2.9\n"
                            + "Valley Girl,Valley Girl,2020,\"Comedy, Musical, Romance\",102,USA,English,Rachel Lee Goldenberg,\"Amy Talkington, Andrew Lane\",Sneak Preview Productions,\"Jessica Rothe, Josh Whitehouse, Jessie Ennis, Ashleigh Murray, Chloe Bennet, Logan Paul, Mae Whitman, Mario Revolori, Rob Huebel, Judy Greer, Alex Lewis, Alex MacNicoll, Danny Ramirez, Andrew Kai, Allyn Rachel\",\"Set to a new wave '80s soundtrack, a pair of young lovers from different backgrounds defy their parents and friends to stay together. A musical adaptation of the 1983 film.\",5.4\n"
            )); 
                    backendToTest.addGenre("Comedy");
                    // checks if the correct list size is returned
                    if (backendToTest.getGenres().size() == 1) {
                            // test passes
                            return true;
                    } else {
                            // test fails
                            return false;
                    }
            } catch (Exception e){
                    e.printStackTrace();
                    // test fails
                    return false;
            }
    }
                        
}