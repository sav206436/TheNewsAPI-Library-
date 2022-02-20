package model;

//����� ��� �� ����� �� �������� ��� ���� ��� top-headlines
public class TopHeadlinesRequest {
	private String country;
	private String category;

	// ��������� constructors
	public TopHeadlinesRequest(String country, String category) {
		super();
		this.country = country;
		this.category = category;
	}

	// ��������� getters-setters
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
