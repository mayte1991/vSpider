package demo.htmlparser;


import java.util.HashSet;
import java.util.Set;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.LinkStringFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import demo.jsoup.TimeWatch;

public class News163Cba {
	
	public static void main(String argv[]) throws ParserException {
		TimeWatch timeWatch = TimeWatch.start();
		Set<String> links = new HashSet<String>();
		String url = "http://sports.163.com/special/0005314V/moregdhy.html";
		int pageNum = 20;
		try {
//			String page = null;
//			for(int i = 1; i <= pageNum; i++){
//				System.out.println("µ⁄"+i+"“≥");
//				if(i == 1){
//					links.addAll(getPageLinks(url));
//				}
//				String s = null;
//				if(i<10){
//					s = "0"+i;
//				}else{
//					s = String.valueOf(i);
//				}
//				page = "http://sports.163.com/special/0005314V/moregdhy_"+s+".html";
//				System.out.println(page);
//				links.addAll(getPageLinks(page));
//			}
			links = getPageLinks(url);
			System.out.println(timeWatch.time());
			System.out.println(links.size());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Set<String> getPageLinks(String page) throws ParserException {
		
		Set<String> links = new HashSet<String>();
		
		Parser myParser = new Parser(page);
		// …Ë÷√±‡¬Î
		myParser.setEncoding("gb2312");
		NodeFilter filter2 = new CssSelectorNodeFilter("div.col2 ul.articleList span.articleTitle a");
		NodeList nodeList = myParser.extractAllNodesThatMatch(filter2);
		
		
		for (int i = 0; i < nodeList.size(); i++) {
			LinkTag linkTag = (LinkTag) nodeList.elementAt(i);
			String link = linkTag.getLink();
			String text = linkTag.getLinkText();
			
			System.out.println(link.toString()+"   "+text);
			links.add(link);
			
		}
		
		NodeFilter nextPageFilter = new CssSelectorNodeFilter("li.next a");
		nodeList = new Parser(page).extractAllNodesThatMatch(nextPageFilter);
		if(nodeList.size()==0
				|| !( (LinkTag)nodeList.elementAt(0)).getLink().startsWith("http://")
				) {
			return links;
		}else {
			String nextPageUrl = ((LinkTag)nodeList.elementAt(0)).getLink();
			Set<String> s2 = getPageLinks(nextPageUrl);
			links.addAll(s2);
			return links;
		}
			
		
	}
	
	private static int getPageNum(String page) throws ParserException{
		Parser myParser = new Parser(page);
		
		NodeFilter filter1 = new LinkStringFilter("http://sports.163.com/special/0005314V/moregdhy_");
		NodeFilter filter2 = new HasAttributeFilter("target");
		NodeFilter newAndFilter = new AndFilter(filter1,filter2);
		NodeList nodes = myParser.extractAllNodesThatMatch(newAndFilter);
		
		return nodes.size();
	}

}

