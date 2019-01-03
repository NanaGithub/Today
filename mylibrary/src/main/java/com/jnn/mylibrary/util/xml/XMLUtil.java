package com.jnn.mylibrary.util.xml;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * <pre>
 *     author : jnn
 *     e-mail : jnn_dream@126.com
 *     time   : 2018/7/31
 *     desc   :
 * </pre>
 */
public class XMLUtil {
    /**
     * @param context
     * @return 银行列表
     */
    public static List<BankBean> getXMLData(Context context, String fileName) {
        AssetManager asset = context.getAssets();
        InputStream input = null;
        List<BankBean> bankBeanList = null;
        try {
            input = asset.open(fileName);
            // 创建一个解析xml的工厂对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 解析xml
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();
            // 获取解析出来的数据
            bankBeanList = handler.getBankBeanList();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return bankBeanList;
    }
}
