package club.easyutils.wepay.agency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JAXB XML Write 代理类
 * https://www.cnblogs.com/mumuxinfei/p/9121773.html
 * @author rainyblossom
 */
public class CustomXmlStreamWriter implements InvocationHandler {

    private static Logger logger = LoggerFactory.getLogger(CustomXmlStreamWriter.class);

    static {
        try {
            writeCharactersMethod = XMLStreamWriter.class.getDeclaredMethod("writeCharacters", String.class);
        } catch (NoSuchMethodException e) {
            logger.error("【CustomXmlStreamWriter】No Such Method {}", e);
        }
    }

    private static Method writeCharactersMethod = null;

    private static final String CDATA_PRE = "<![CDATA[";

    private static final String CDATA_END = "]]>";

    private XMLStreamWriter writer;

    public CustomXmlStreamWriter(XMLStreamWriter writer) {
        this.writer = writer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ( writeCharactersMethod.equals(method) ) {
            String text = (String)args[0];
            if ( text != null && text.startsWith(CDATA_PRE) && text.endsWith(CDATA_END) ) {
                writer.writeCData(text.substring(9, text.length() - 3));
                return null;
            }
        }
        return method.invoke(writer, args);
    }
}
