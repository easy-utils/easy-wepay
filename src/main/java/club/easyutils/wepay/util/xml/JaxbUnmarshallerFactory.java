package club.easyutils.wepay.util.xml;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class JaxbUnmarshallerFactory extends JaxbMarshallerFactory{

    @Override
    public PooledObject makeObject(Object key) throws Exception {
        Class<?> clazz = (Class<?>)key;
        if(context.containsKey(key)){
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            context.put(clazz, jaxbContext);
        }
        Unmarshaller unmarshaller = context.get(clazz).createUnmarshaller();
        return new DefaultPooledObject(unmarshaller);
    }
}
