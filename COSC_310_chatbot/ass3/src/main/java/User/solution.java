package User;

//import User.Device;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import User.googletranslate;

public class solution {
    static Device d1;
    private static String keyword;
    static Device apple = new Apple();
    static Device android = new Android();
    static twitter tweet = new twitter();
   static Boolean dev;
    public solution() {
        d1 = apple;
        dev=true;


    }



    public static String Findsol(String Q) throws Exception {//takes in Sting
        Q = Q.toLowerCase(Locale.ROOT);//convert to lowercase
        Boolean ex = false;

        if(Q.equals("exit")||Q.equals("done"))//check if user entered an exit phrase
            return("exit");

        if(Q.equals("do understand ")){
            googletranslate gl = new googletranslate();
            gl.main("hi");
            keyword = googletranslate.word;
            return("This sentence is " + keyword +" in French");
        }

        if(Q.equals("has elon musk been saying lately ")) {
            return (tweet.getTweets("1512886651940491270", "### enter key here"));
        }

        for (int i = 0; i < d1.master.size(); i++) {//double for loop to search each list in master list for keywords
            if(ex)//break controler
                break;
            List<String> L1 = d1.master.get(i);
            for (int j = 0; j < L1.size(); j++) {
                if (Q.contains(L1.get(j))) {
                    keyword = (L1.get(j));
                    ex = true;
                    break;
                } else {
                    keyword = "not found";
                }
            }
        }
            Q = "";
        if (keyword.equals("not found")) {//match keyword to its list and find User.solution that acompanies key vale
            ArrayList<String> setResponses = new ArrayList<String>();
            setResponses.add("Chatbot: rephrase your problem. I couldn't understand that");
            setResponses.add("Chatbot: check your spelling. My programming is key-sensitive!");
            setResponses.add("Chatbot: answer in a one to two word manner. Example: data, power off, damage, etc..");
            setResponses.add("Chatbot: get better at spelling because I cannot follow.");
            setResponses.add("Chatbot: go back to school..\n maybe read a book or two, \n eventually learn how to spell, and return with good grammer. ");
            return (setResponses.get((int) (Math.random()*5)) );
        }
        if(keyword.equals("apple")&&!dev) {
            d1 = apple;
            dev=true;
            return"Chatbot:switching to apple";
        }

        if(keyword.equals("android")&&dev) {
            d1 = android;
            dev = false;
            return "Chatbot:switching to Android";
        }

        for (int i = 0; i < d1.master.size(); i++) {
            for (int j = 0; j < d1.master.get(i).size(); j++) {
                if (d1.master.get(i).get(j).equals(keyword)) {
                    //System.out.println(d1.problems.get(d1.master.get(i)));
                    return d1.problems.get(d1.master.get(i));
                }
            }
        }

        return ("an error has occurred");//if nothing is found an error has occured
    }
}

