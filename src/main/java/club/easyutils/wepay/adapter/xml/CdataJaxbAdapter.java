package club.easyutils.wepay.adapter.xml;

import cn.hutool.core.lang.Validator;

import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author rainyblossom
 */
public class CdataJaxbAdapter extends XmlAdapter<String, String> {


    /**
     * Convert a value type to a bound type.
     *
     * @param v The value to be converted. Can be null.
     * @throws Exception if there's an error during the conversion. The caller is responsible for
     *                   reporting the error to the user through {@link ValidationEventHandler}.
     */
    @Override
    public String unmarshal(String v) throws Exception {
        return v;
    }

    /**
     * Convert a bound type to a value type.
     *
     * @param v The value to be convereted. Can be null.
     * @throws Exception if there's an error during the conversion. The caller is responsible for
     *                   reporting the error to the user through {@link ValidationEventHandler}.
     */
    @Override
    public String marshal(String v) throws Exception {
        if(Validator.isNotNull(v)){
            return new StringBuilder("<![CDATA[").append(v).append("]]>").toString();
        }
        return v;
    }
}
