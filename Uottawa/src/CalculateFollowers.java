import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

public class CalculateFollowers {

	public static void main(String[] args) throws IOException, TwitterException {
		URL url1 = new URL("https://wipebook.org/vnps.csv");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(url1.openStream());
		// int count = 1;
		List<String> userNames = new ArrayList<>();
		while (input.hasNextLine()) {
			userNames.add(input.nextLine());
		}
		
		for(int i=1;i<userNames.size();i++) {
			
			
			TwitterFactory factory = new TwitterFactory();
			 Twitter twitter = factory.getInstance();
			 twitter.setOAuthConsumer("PudcTp1wZlwSwegOpSpTmqR9a", "cjidct5tI59AgOBhRxwAeEv7nx5RrzyC8DK0oklq8gEr5vfPp1");
			 AccessToken accessToken = new AccessToken("127131377-jDpbhlROsTL9DNFPvE2fsNm1kTvB95hfU63ZAT8R", "jmZFnPVVc4KxdE2iSugl499XEdMaRtHvJmasuGRyxfehk");
			 twitter.setOAuthAccessToken(accessToken);
			 
			 String name = userNames.get(i).replaceAll(",", "");
			 User user = twitter.showUser(name);
			 
			 
			 
			 try {

					URL url = new URL("https://api.twitter.com/1.1/users/show.json?screen_name="+user.getScreenName()+"&user_id=" + user.getId());
					HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("Accept", "application/json");
					

					if (conn.getResponseCode() != 200) {
						throw new RuntimeException("Failed : HTTP error code : "
								+ conn.getResponseCode());
					}

					BufferedReader br = new BufferedReader(new InputStreamReader(
						(conn.getInputStream())));

					String output;
					System.out.println("Output from Server .... \n");
					while ((output = br.readLine()) != null) {
						System.out.println(output);
					}

					conn.disconnect();

				  } catch (MalformedURLException e) {

					e.printStackTrace();

				  } catch (IOException e) {

					e.printStackTrace();

				  }
			 
			 System.out.println(user.getId());
		}
		
		
				 //String twitterScreenName = twitter.getScreenName()
				 /*IDs followerIDs = twitter.getFollowersIDs("jmacattak", -1);
				 
				// List<Integer> followerIdList = followerIDs.;
				 followersIDs.
				 long[] followerIdList = followerIDs.getIDs();
				 if (followerIdList.size() > 0) {
				     int numberOfFollowers = Math.min(followerIdList.size(), 20);
				     followerIdList = followerIdList.subList(0, numberOfFollowers);
				     followerIdList.each {
				         twitter4j.User user = twitter.showUser(it)
				           println("Name: ${user.screenName}")
				           println("Location: ${user.location}")
				     }*/
				 }

}
