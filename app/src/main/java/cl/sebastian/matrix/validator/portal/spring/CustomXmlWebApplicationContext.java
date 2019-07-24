package cl.sebastian.matrix.validator.portal.spring;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class CustomXmlWebApplicationContext extends XmlWebApplicationContext {

    private static final long serialVersionUID = 4172786998471163904L;

    @Override
    protected void initBeanDefinitionReader(XmlBeanDefinitionReader reader) {
        super.initBeanDefinitionReader(reader);
        reader.setValidationMode(XmlBeanDefinitionReader.VALIDATION_NONE);
        reader.setNamespaceAware(true);
    }
}
