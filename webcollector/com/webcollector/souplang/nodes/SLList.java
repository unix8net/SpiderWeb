package com.webcollector.souplang.nodes;

import com.webcollector.souplang.nodes.InputTypeErrorException;
import com.webcollector.souplang.nodes.SLList;

import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webcollector.souplang.Context;
import com.webcollector.souplang.LangNode;

public class SLList extends LangNode {
	public static final Logger LOG = LoggerFactory.getLogger(SLList.class);
	public String cssSelector = null;

	public void readCSSSelector(org.w3c.dom.Element xmlElement) {
		cssSelector = xmlElement.getAttribute("selector");
		if (cssSelector.isEmpty()) {
			cssSelector = null;
		}
	}

	@Override
	public Object process(Object input, Context context)
			throws InputTypeErrorException {
		ArrayList<Element> elementList = new ArrayList<Element>();
		Element jsoupElement = null;
		Elements jsoupElements = null;
		if (input instanceof Element) {
			jsoupElement = (Element) input;
		} else {
			jsoupElements = (Elements) input;
		}
		if (cssSelector != null && !cssSelector.isEmpty()) {
			Elements result;
			if (jsoupElement != null) {
				result = jsoupElement.select(cssSelector);
			} else {
				result = jsoupElements.select(cssSelector);
			}
			for (Element item : result) {
				elementList.add(item);
			}
			// System.out.println("this is element" + result);
			return elementList;
		} else {
			if (jsoupElement != null) {
				elementList.add(jsoupElement);
			} else {
				for (Element child : jsoupElements) {
					elementList.add(child);
				}
			}
			return elementList;
		}
	}

	@Override
	public boolean validate(Object input) throws Exception {
		if (!(input instanceof Element) && !(input instanceof Elements)) {
			return false;
		}
		return true;
	}
}
