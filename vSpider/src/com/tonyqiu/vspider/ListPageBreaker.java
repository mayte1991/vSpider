package com.tonyqiu.vspider;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ListPageBreaker implements Runnable{

	
	
	private Document d = null;
	private boolean recursive = true;
	
	public ListPageBreaker() {
		try {
			
			d = Jsoup.parse(new URL(JobConfig.listPageUrl),App.HTTP_TIME_OUT);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			App.STATUS.addListPageThreadRunning(Thread.currentThread());
			try {
				extractDetailUrlFromListPage(d, recursive);
			}catch(Exception e) {
				e.printStackTrace();
			}
			App.STATUS.removeListPageThreadRunning(Thread.currentThread());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void extractDetailUrlFromListPage(Document listPageDoc, boolean recursive) throws IOException{
		Elements els =listPageDoc.select(JobConfig.detailAnchorSelector);
		for(Element e : els) {
			String href =e.attr("href");
			if(!App.detailUrlQueue.contains(href)
					&& !App.historyUrls.contains(href)
					) {
				App.detailUrlQueue.add(new SpiderUrl(href));
				App.historyUrls.add(href);
			}
		}
		Elements nextPageEls = listPageDoc.select(JobConfig.nextPageSelector);
		if(recursive == false  
				|| nextPageEls.isEmpty()
				|| ! nextPageEls.first().attr("href").startsWith("http://")
				) {
			return;
		}else {
			Document nextD = Jsoup.parse(new URL(nextPageEls.first().attr("href")), 5000);
			extractDetailUrlFromListPage(nextD, recursive);
			return;
		}
	}
}
