package club.easyutils.wepay.util;

import club.easyutils.wepay.util.xml.JaxbMarshallerFactory;
import club.easyutils.wepay.util.xml.JaxbUnmarshallerFactory;
import org.apache.commons.pool2.KeyedObjectPool;
import org.apache.commons.pool2.impl.GenericKeyedObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Proxy;

/**
 * XML 转换工具
 * @author RainyBlossom
 */
public class XmlUtil {

    private static Logger logger = LoggerFactory.getLogger(XmlUtil.class);

    static KeyedObjectPool MarshallerPool = new GenericKeyedObjectPool(new JaxbMarshallerFactory());

    static KeyedObjectPool UnmarshallerPool = new GenericKeyedObjectPool(new JaxbUnmarshallerFactory());

    public static String beanToXml(Object object, Boolean needFormat){
        Marshaller marshaller = null;
        try{
            marshaller = (Marshaller) MarshallerPool.borrowObject(object.getClass());
            StringWriter writer = new StringWriter();
            marshaller.marshal(object, writer);
            String output = "<?xml version='1.0' encoding='UTF-8'?>";
            if (needFormat){
                return output + xmlFormat(writer.toString());
            }
            return output + writer.toString();
        }catch (Exception e){
            logger.error("【Easy Express】bean to xml failed，error message is {}", e);
        }finally{
            if (object != null){
                try{
                    MarshallerPool.returnObject(object.getClass(), marshaller);
                }catch (Exception e){
                    logger.error("【Easy Express】return marshaller to pool failed，error message is {}", e);
                }
            }
        }
        return null;
    }

    public static <T> T xmlToBean(Class<T> clazz, String xml){
        Unmarshaller unmarshaller = null;
        try{
            unmarshaller = (Unmarshaller) UnmarshallerPool.borrowObject(clazz);
            Object object = unmarshaller.unmarshal(new StringReader(xml));
            return object instanceof JAXBElement ? (T)((JAXBElement<?>) object).getValue() : (T) unmarshaller.unmarshal(new StringReader(xml));
        }catch (Exception e){
            logger.error("【Easy Express】xml to bean failed，error message is {}", e);

        }finally{
            try{
                UnmarshallerPool.returnObject(clazz, unmarshaller);
            }catch (Exception e){
                logger.error("【Easy Express】return unmarshaller to pool failed，error message is {}", e);
            }
        }
        return null;
    }

    public static String xmlFormat(String xml) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        // 打开对齐开关
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        // 忽略掉 XML 声明头信息
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        StringWriter formattedStringWriter = new StringWriter();
        transformer.transform(new StreamSource(new StringReader(xml)), new StreamResult(formattedStringWriter));
        return formattedStringWriter.toString();
    }


}
