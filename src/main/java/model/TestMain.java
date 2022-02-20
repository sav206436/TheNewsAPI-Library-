package model;

import java.util.List;

import exception.NewsAPIException;
import model.EverythingRequest;
import model.NewsInfo;
import model.TopHeadlinesRequest;
import model.thenewsdb.Article;
import model.thenewsdb.NewsResult;
import services.NewsAPIService;

//test πως λειτουργεί ο κώδικας και μου φέρνει αποτελέσματα:
public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// api-service
		NewsAPIService service = new NewsAPIService("https://newsapi.org/", "1a6714409f4e416eb1ba446037c34f42");

		// location service
		service.getUserLocation().getCountryCode().toLowerCase();

		// αποτελέσματα top-headlines
		try {
			// δοκιμάζω πως αφού μπεί αυτόματα η τοποθεσία μου φέρνει τα top-headlines
			List<NewsInfo> news = service.getTopHeadlinesNews(
					new TopHeadlinesRequest(service.getUserLocation().getCountryCode().toLowerCase(), null));
			System.out.println("==========RESULTS FOR TOP-HEADLINES OF THIS LOCATION:==========");
			for (NewsInfo info : news) {
				System.out.println(info.getTitle());
				System.out.println(info.getDescription());
				System.out.println(info.getUrl());
				System.out.println(info.getPublish_date());
			}

		} catch (NewsAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// αποτελέσματα everything-news
		try {
			List<NewsInfo> news = service
					// δοκιμάζω πως λειτουργεί η αναζήτηση όλων των ειδήσεων με αναζήτηση για
					// "mitsotakis"
					.getEverythingNews(new EverythingRequest("mitsotakis", null, null, null, null, null));
			System.out.println("==========RESULTS FOR SPECIFIC SEARCH OF ALL THE NEWS:===========");
			for (NewsInfo info : news) {
				System.out.println(info.getTitle());
				System.out.println(info.getDescription());
				System.out.println(info.getUrl());
				System.out.println(info.getPublish_date());
			}

		} catch (NewsAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}