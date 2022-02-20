package model;

import java.util.List;

import exception.NewsAPIException;
import model.EverythingRequest;
import model.NewsInfo;
import model.TopHeadlinesRequest;
import model.thenewsdb.Article;
import model.thenewsdb.NewsResult;
import services.NewsAPIService;

//test ��� ���������� � ������� ��� ��� ������ ������������:
public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// api-service
		NewsAPIService service = new NewsAPIService("https://newsapi.org/", "1a6714409f4e416eb1ba446037c34f42");

		// location service
		service.getUserLocation().getCountryCode().toLowerCase();

		// ������������ top-headlines
		try {
			// �������� ��� ���� ���� �������� � ��������� ��� ������ �� top-headlines
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

		// ������������ everything-news
		try {
			List<NewsInfo> news = service
					// �������� ��� ���������� � ��������� ���� ��� �������� �� ��������� ���
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