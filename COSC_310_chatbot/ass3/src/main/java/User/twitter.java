package User;


import kong.unirest.Unirest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;


    public class twitter {

        // To set your enviornment variables in your terminal run the following line:
        // export 'BEARER_TOKEN'='<your_bearer_token>'

      /*  public static void main(String args[]) throws IOException, URISyntaxException {

            if (null != bearerToken) {
                //Replace comma separated ids with Tweets Ids of your choice
                String response = getTweets("1512886651940491270", bearerToken);
                System.out.println(response);
            } else {
                System.out.println("There was a problem getting you bearer token. Please make sure you set the BEARER_TOKEN environment variable");
            }
        }
*/
        /*
         * This method calls the v2 Tweets endpoint with ids as query parameter
         * */
        public String getTweets(String ids, String bearerToken) throws IOException, URISyntaxException {
            String tweetResponse = null;
            String act_tweet = null;

            HttpClient httpClient = HttpClients.custom()
                    .setDefaultRequestConfig(RequestConfig.custom()
                            .setCookieSpec(CookieSpecs.STANDARD).build())
                    .build();

            URIBuilder uriBuilder = new URIBuilder("https://api.twitter.com/2/tweets");
            ArrayList<NameValuePair> queryParameters;
            queryParameters = new ArrayList<>();
            queryParameters.add(new BasicNameValuePair("ids", ids));
            queryParameters.add(new BasicNameValuePair("tweet.fields", "created_at"));
            uriBuilder.addParameters(queryParameters);

            HttpGet httpGet = new HttpGet(uriBuilder.build());
            httpGet.setHeader("Authorization", String.format("Bearer %s", bearerToken));
            httpGet.setHeader("Content-Type", "application/json");

            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                tweetResponse = EntityUtils.toString(entity, "UTF-8");
                act_tweet = tweetResponse.substring(85,116);
            }
            return act_tweet;
        }
    }




