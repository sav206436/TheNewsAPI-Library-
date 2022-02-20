package model;

//κλάση για να κρατα τα δεδομένα που θέλω για everything news
public class EverythingRequest {
	private String q;
	private String searchIn;
	private String sources;
	private String language;
	private String from;
	private String to;

	// φτιαχνω constructors
	public EverythingRequest(String q, String searchIn, String sources, String language, String from, String to) {
		super();
		this.q = q;
		this.searchIn = searchIn;
		this.sources = sources;
		this.language = language;
		this.from = from;
		this.to = to;
	}

	// φτιαχνω getters-setters
	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getSearchIn() {
		return searchIn;
	}

	public void setSearchIn(String searchIn) {
		this.searchIn = searchIn;
	}

	public String getSources() {
		return sources;
	}

	public void setSources(String sources) {
		this.sources = sources;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

}
