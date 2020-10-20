package club.easyutils.wepay.util.xml;

import org.apache.commons.pool2.KeyedPooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.util.HashMap;
import java.util.Map;

public class JaxbMarshallerFactory implements KeyedPooledObjectFactory{

    protected Map<Class<?>,JAXBContext> context = new HashMap<>();

    @Override
    public PooledObject makeObject(Object key) throws Exception {
        Class<?> clazz = (Class<?>)key;
        if(!context.containsKey(clazz)){
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            context.put(clazz, jaxbContext);
        }
        Marshaller marshaller = context.get(clazz).createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        return new DefaultPooledObject(marshaller);
    }

    @Override
    public void destroyObject(Object key, PooledObject pooledObject) throws Exception {
        context.remove(key);
    }

    @Override
    public boolean validateObject(Object o, PooledObject pooledObject) {
        return true;
    }

    @Override
    public void activateObject(Object o, PooledObject pooledObject) throws Exception {}

    @Override
    public void passivateObject(Object o, PooledObject pooledObject) throws Exception {}
}
