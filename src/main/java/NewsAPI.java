import services.NewsAPIService;

//κλάση που χρησιμοποιεί το NewsAPIService
public class NewsAPI {
	public static NewsAPIService getNewsAPIService() {
		return new NewsAPIService("https://newsapi.org/v2/", "1a6714409f4e416eb1ba446037c34f42");
	}

}
