package User;

import kong.unirest.json.JSONArray;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.util.Scanner;
import User.GUI1;


    public class googletranslate {
       public static String word;
       public static String input;

        public static void main(String args) throws Exception
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a word to translate: ");
            input = (scanner.nextLine());
            googletranslate http = new googletranslate();
            word = http.callUrlAndParseResult("en", "fr", input); //CHANGE THIS WORD

                   }


        private String callUrlAndParseResult(String langFrom, String langTo,
                                             String word) throws Exception
        {

            String url = "https://translate.googleapis.com/translate_a/single?"+
                    "client=gtx&"+
                    "sl=" + langFrom +
                    "&tl=" + langTo +
                    "&dt=t&q=" + URLEncoder.encode(word, "UTF-8");

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return parseResult(response.toString());
        }

        private String parseResult(String inputJson) throws Exception
        {

            JSONArray jsonArray = new JSONArray(inputJson);
            JSONArray jsonArray2 = (JSONArray) jsonArray.get(0);
            JSONArray jsonArray3 = (JSONArray) jsonArray2.get(0);

            return jsonArray3.get(0).toString();
        }

    }



/*
    public static void main(String[] args) throws IOException {


        String text = "Hello World";
        //Translated text: Hallo Welt!
        System.out.println("Translated text: " + translate("en", "de", text));


    }

    private static String translate(String langFrom, String langTo, String text) throws IOException {
        // INSERT YOU URL HERE
        HttpResponse<String> responses = Unirest.post("https://google-translate1.p.rapidapi.com/language/translate/v2/detect")
                .header("content-type", "application/x-www-form-urlencoded")
                .header("Accept-Encoding", "application/gzip")
                .header("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")

                .body("q=English%20is%20hard%2C%20but%20detectably%20so")
                .asString();

        String urlStr = "https://google-translate1.p.rapidapi.com/language/translate/v2/detect" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }



}
*/
