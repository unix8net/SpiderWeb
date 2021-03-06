package com.webcollector.model;

import com.webcollector.model.Page;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.webcollector.net.HttpResponse;
import com.webcollector.util.CharsetDetector;

import java.io.UnsupportedEncodingException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Page是爬取过程中，内存中保存网页爬取信息的一个容器，与CrawlDatum不同，Page只在内存中存
 * 放，用于保存一些网页信息，方便用户进行自定义网页解析之类的操作。在广度遍历器中，用户覆盖 的visit(Page
 * page)方法，就是通过Page将网页爬取/解析信息传递给用户的。经过http请求、解 析这些流程之后，page内保存的内容会越来越多。
 */
public class Page {
	public static final Logger LOG = LoggerFactory.getLogger(Page.class);
	private HttpResponse response = null;
	private String url = null;
	private String html = null;
	private Document doc = null;

	// 返回网页/文件的内容
	public byte[] getContent() {
		if (response == null)
			return null;
		return response.getContent();
	}

	// 返回网页的源码字符串
	public String getHtml() {
		if (html != null) {
			return html;
		}
		if (getContent() == null) {
			return null;
		}
		String charset = CharsetDetector.guessEncoding(getContent());
		try {
			this.html = new String(getContent(), charset);
			return html;
		} catch (UnsupportedEncodingException e) {
			LOG.info("Exception", e);
			return null;
		}
	}

	// 返回网页解析后的DOM树(Jsoup的Document对象)
	public Document getDoc() {
		if (doc != null) {
			return doc;
		}
		try {
			this.doc = Jsoup.parse(getHtml(), url);
			return doc;
		} catch (Exception e) {
			LOG.info("Exception", e);
			return null;
		}
	}

	public HtmlUnitDriver getDriver() {
		HtmlUnitDriver driver = new HtmlUnitDriver();
		driver.setJavascriptEnabled(true);
		driver.get(url);
		return driver;
	}

	// 不使用代理
	public HtmlUnitDriver getDriver(BrowserVersion browserVersion) {
		HtmlUnitDriver driver = new HtmlUnitDriver(browserVersion);
		driver.setJavascriptEnabled(true);
		driver.get(url);
		return driver;
	}

	// 使用代理
	public HtmlUnitDriver getDriver(BrowserVersion browserVersion, String ip,
			int port) {
		Proxy proxy = new Proxy();
		// 设置代理服务器地址
		proxy.setHttpProxy(ip + ":" + port);
		DesiredCapabilities capabilities = DesiredCapabilities.htmlUnit();
		capabilities.setCapability(CapabilityType.PROXY, proxy);
		capabilities.setBrowserName(browserVersion.getNickname());
		HtmlUnitDriver driver = new HtmlUnitDriver(capabilities);
		driver.setJavascriptEnabled(true);
		driver.get(url);
		return driver;
	}

	// 返回网页的url
	public String getUrl() {
		return url;
	}

	// 设置网页的url
	public void setUrl(String url) {
		this.url = url;
	}

	// 设置网页的源码字符串
	public void setHtml(String html) {
		this.html = html;
	}

	// 设置网页解析后的DOM树(Jsoup的Document对象)
	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public HttpResponse getResponse() {
		return response;
	}

	public void setResponse(HttpResponse response) {
		this.response = response;
	}
}
