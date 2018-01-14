package buythecheapest.controller;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class WebsiteInfo {

	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public WebsiteInfo(String url) {
		setUrl(url);	
	}
	public WebsiteInfo() {	
	}
	
	public String chceckOutWebsite() {
		   Connection connect = Jsoup.connect(getUrl());
		   Document document = null;
		try {
			document = connect.get();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements alltr = document.select("strong");	 
		  return alltr.get(9).text();
			
	}
	
	
}
