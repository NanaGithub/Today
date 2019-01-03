package com.jnn.mylibrary.util.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     author : jnn
 *     e-mail : jnn_dream@126.com
 *     time   : 2018/7/31
 *     desc   : sax解析
 * </pre>
 */
public class XmlParserHandler extends DefaultHandler {
    private static final String TAG = "XmlParserHandler";
    private List<BankBean> bankBeanList;
    private BankBean bankBean;
    private StringBuffer buffer = new StringBuffer();

    public List<BankBean> getBankBeanList() {
        return bankBeanList;
    }

    @Override
    public void startDocument() throws SAXException {
        bankBeanList = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("bank"))
            bankBean = new BankBean();
        super.startElement(uri, localName, qName, attributes);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("bank")) {
            bankBeanList.add(bankBean);
        } else if (localName.equals("name")) {//银行名称
            bankBean.setBankName(buffer.toString().trim());
            buffer.setLength(0);
        } else if (localName.equals("once")) {//单笔
            bankBean.setBankOnceValue(buffer.toString().trim());
            buffer.setLength(0);
        } else if (localName.equals("day")) {//单日
            bankBean.setBankDayValue(buffer.toString().trim());
            buffer.setLength(0);
        }
        super.endElement(uri, localName, qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        buffer.append(ch, start, length);
        super.characters(ch, start, length);
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

}
