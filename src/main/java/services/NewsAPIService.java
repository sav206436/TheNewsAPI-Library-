package services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import exception.NewsAPIException;
import model.EverythingRequest;
import model.LocationResponse;
import model.NewsInfo;
import model.TopHeadlinesRequest;
import model.thenewsdb.Article;
import model.thenewsdb.ErrorResponse;
import model.thenewsdb.NewsResult;

//κλαση που θα μπουν οι μεθοδοι της βιβλιοθηκης μας
public class NewsAPIService {

	// μεταβλητες API_URL και API_KEY
	private final String API_URL;
	private final String API_KEY;

	private final String Location_URL = "http://ip-api.com/json/";

	// φτιαχνω constructor
	public NewsAPIService(String aPI_URL, String aPI_KEY) {
		super();
		API_URL = aPI_URL;
		API_KEY = aPI_KEY;
	}

	// μέθοδος που επιστρέφει την τοποθεσία
	public LocationResponse getUserLocation() {
		try {
			final HttpGet getRequest = new HttpGet(new URIBuilder(Location_URL).build());
			final CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = httpclient.execute(getRequest);
			final HttpEntity entity = response.getEntity();
			final ObjectMapper mapper = new ObjectMapper();

			return mapper.readValue(entity.getContent(), LocationResponse.class);
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new LocationResponse();
		}

	}

	// get top-headlines news
	public List<NewsInfo> getTopHeadlinesNews(TopHeadlinesRequest topheadlinesrequest) throws NewsAPIException {
		NewsResult result = getAPIData("top-headlines", topheadlinesrequest, null);
		List<NewsInfo> newsInfoList = new ArrayList<>(result.getArticles().size());
		for (Article theResult : result.getArticles()) {
			newsInfoList.add(new NewsInfo(theResult));
		}

		return newsInfoList;
	}

	// get everything-news
	public List<NewsInfo> getEverythingNews(EverythingRequest everythingrequest) throws NewsAPIException {
		NewsResult result = getAPIData("everything", null, everythingrequest);
		List<NewsInfo> newsInfoList = new ArrayList<>(result.getArticles().size());
		for (Article theResult : result.getArticles()) {
			newsInfoList.add(new NewsInfo(theResult));
		}

		return newsInfoList;
	}

	// get APIData
	private NewsResult getAPIData(String apiFunction, TopHeadlinesRequest topheadlinesrequest,
			EverythingRequest everythingrequest) throws NewsAPIException {

		// κανω build τα URI για τα ερωτηματα
		try {
			final URIBuilder uriBuilder = new URIBuilder(API_URL).setPathSegments("v2", apiFunction)
					.addParameter("apiKey", API_KEY);

			if (topheadlinesrequest != null || everythingrequest != null) {
				switch (apiFunction) {
				case "top-headlines":
					uriBuilder.addParameter("country", topheadlinesrequest.getCountry());
					uriBuilder.addParameter("category", topheadlinesrequest.getCategory());

					break;
				case "everything":
					uriBuilder.addParameter("q", everythingrequest.getQ());
					uriBuilder.addParameter("searchIn", everythingrequest.getSearchIn());
					uriBuilder.addParameter("sources", everythingrequest.getSources());
					uriBuilder.addParameter("language", everythingrequest.getLanguage());
					uriBuilder.addParameter("from", everythingrequest.getFrom());
					uriBuilder.addParameter("to", everythingrequest.getTo());
					break;
				}

				final URI uri = uriBuilder.build();
				System.out.println("==========" + uri + "==========");

				// για να στελνω το request και να παιρνω απαντηση
				final HttpGet getRequest = new HttpGet(uri);
				final CloseableHttpClient httpclient = HttpClients.createDefault();
				try (CloseableHttpResponse response = httpclient.execute(getRequest)) {
					final HttpEntity entity = response.getEntity();
					final ObjectMapper mapper = new ObjectMapper();

					// ελεγχω αν το response ειναι ok, αλλιως να βγαλει error
					if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
						ErrorResponse errorResponse = mapper.readValue(entity.getContent(), ErrorResponse.class);
						if (errorResponse.getMessage() != null)
							throw new NewsAPIException("Error occured on API call: " + errorResponse.getMessage());
					}

					return mapper.readValue(entity.getContent(), NewsResult.class);
				}

				catch (IOException e) {
					throw new NewsAPIException("Error requesting data from TheNewsDB API.", e);
				}

			}

			else
				throw new NewsAPIException("Unable to create request URI.");
		}

		catch (URISyntaxException e) {
			e.printStackTrace();
			throw new NewsAPIException("Unable to create request URI.", e);

		}
	}
}
