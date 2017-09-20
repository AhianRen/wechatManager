package cn.chat.wechat.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import cn.chat.wechat.pojo.response.*;


/** 
 * 消息工具类 
 * 
 * @author yanghb
 * @email hai_bo_yang@126.com
 * @data 2016年5月24日14:33:22
 */  
public class MessageUtil {  
  
    /** 
     * 返回消息类型：文本 
     */  
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";  
  
    /** 
     * 返回消息类型：音乐 
     */  
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";  
  
    /** 
     * 返回消息类型：图文 
     */  
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";  
    /** 
     * 返回消息类型：图片
     */  
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";  
    /** 
     * 返回消息类型：图片
     */  
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";  
  
    /** 
     * 请求消息类型：文本 
     */  
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";  
  
    /** 
     * 请求消息类型：图片 
     */  
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";  
    /** 
     * 请求消息类型：图文 
     */  
    public static final String REQ_MESSAGE_TYPE_NEWS = "news";  
  
    /** 
     * 请求消息类型：链接 
     */  
    public static final String REQ_MESSAGE_TYPE_LINK = "link";  
  
    /** 
     * 请求消息类型：地理位置 
     */  
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";  
  
    /** 
     * 请求消息类型：音频 
     */  
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";  
  
    /** 
     * 请求消息类型：推送 
     */  
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";  
  
    /** 
     * 事件类型：subscribe(订阅) 
     */  
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";  
  
    /** 
     * 事件类型：unsubscribe(取消订阅) 
     */  
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";  
  
    /** 
     * 事件类型：CLICK(自定义菜单点击事件) 
     */  
    public static final String EVENT_TYPE_CLICK = "click";  
    /** 
     * 事件类型：VIEW(自定义跳转URL事件) 
     */  
    public static final String EVENT_TYPE_VIEW = "view";  
    /** 
     * 音乐搜索 按钮key值
     */  
    public static final String KEY_MUSIC_BTN = "KEY_MUSIC_BTN";  
    /** 
     * 人脸检测 按钮key值 
     */  
    public static final String KEY_FACE_BTN = "KEY_FACE_BTN";  
    /** 
     * 关于我们 按钮key值 
     */  
    public static final String KEY_ABOUT_BTN = "KEY_ABOUT_BTN";  
    /** 
     * 社区交流 按钮key值 
     */  
    public static final String KEY_COMMUNITY_BTN = "KEY_COMMUNITY_BTN";  
    
    
    
    /** 
     * 解析微信发来的请求（XML） 
     *  
     * @param request 
     * @return 
     * @throws Exception 
     */  
    @SuppressWarnings("unchecked")  
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {  
        // 将解析结果存储在HashMap中  
        Map<String, String> map = new HashMap<String, String>();  
  
        // 从request中取得输入流  
        InputStream inputStream = request.getInputStream();  
        // 读取输入流  
        SAXReader reader = new SAXReader();  
        Document document = reader.read(inputStream);  
        // 得到xml根元素  
        Element root = document.getRootElement();  
        // 得到根元素的所有子节点  
        List<Element> elementList = root.elements();  
  
        // 遍历所有子节点  
        for (Element e : elementList)  
            map.put(e.getName(), e.getText());  
  
        // 释放资源  
        inputStream.close();  
        inputStream = null;  
  
        return map;  
    }  
  
    /** 
     * 扩展xstream，使其支持CDATA块 
     *  
     */  
    private static XStream xstream = new XStream(new XppDriver() {  
        public HierarchicalStreamWriter createWriter(Writer out) {  
            return new PrettyPrintWriter(out) {  
                // 对所有xml节点的转换都增加CDATA标记  
                boolean cdata = true;  
  
                public void startNode(String name, Class clazz) {  
                    super.startNode(name, clazz);  
                }  
  
                protected void writeText(QuickWriter writer, String text) {  
                    if (cdata) {  
                        writer.write("<![CDATA[");  
                        writer.write(text);  
                        writer.write("]]>");  
                    } else {  
                        writer.write(text);  
                    }  
                }  
            };  
        }  
    });  
    
    /**
     * 将TextMessageResp转化成xml
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessageResp textMessage){
    	xstream.alias("xml", TextMessageResp.class);
    	return xstream.toXML(textMessage);
    }
    /**
     * 将ImageMessageResp转化成xml
     * @param ImageMessage
     * @return
     */
    public static String imageMessageToXml(ImageMessageResp imageMessage){
    	xstream.alias("xml", ImageMessageResp.class);
    	xstream.alias("Image", Image.class);
    	return xstream.toXML(imageMessage);
    }
    /**
     * 将VoiceMessageResp转化成xml
     * @param voiceMessage
     * @return
     */
    public static String voiceMessageToXml(VoiceMessageResp voiceMessage){
    	xstream.alias("xml", VoiceMessageResp.class);
    	return xstream.toXML(voiceMessage);
    }
    /**
     * 将NewsMessageResp转化成xml
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXml(NewsMessageResp newsMessage){
    	xstream.alias("xml", NewsMessageResp.class);
    	xstream.alias("item", Article.class);
    	return xstream.toXML(newsMessage);
    }

}
