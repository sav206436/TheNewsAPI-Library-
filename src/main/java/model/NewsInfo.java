package model;

import javax.naming.spi.DirStateFactory.Result;

import model.thenewsdb.Article;

//����� ��� �� ����� �� �������� ��� ���� ��' �� �����
public class NewsInfo {
	private String title;
	private String description;
	private String url;
	private String publish_date;

	// ������� constructor
	public NewsInfo(String title, String description, String url, String publish_date) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
		this.publish_date = publish_date;
	}

	// ��� �� ������������ ��� ��� ��������
	public NewsInfo(Article theResult) {
		this.title = theResult.getTitle();
		this.description = theResult.getDescription();
		this.url = theResult.getUrl();
		this.publish_date = theResult.getPublishedAt();
		// TODO Auto-generated constructor stub
	}

	// ������� getters-setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPublish_date() {
		return publish_date;
	}

	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}

	// override ��� toString ��� �� ����� �� �������� ��������
	@Override
	public String toString() {
		return "News�nfo{" + "title='" + title + "'\n" + ", description='" + description + "'\n" + ", url='" + url
				+ "'\n" + ", publish_date='" + publish_date + "'\n" + '}';

	}

}
