package cl.sebastian.matrix.validator.portal.converter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "TelefonoConverter")
public class TelefonoConverter implements Converter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelefonoConverter.class);

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Number numero = null;
        try {
            String phone = StringUtils.trimToEmpty(value);
            if (StringUtils.isNotBlank(phone)) {
                while (phone.contains(" ")) {
                    phone = phone.replace(" ", "");
                }
                while (phone.contains("-")) {
                    phone = phone.replace("-", "");
                }
                while (phone.contains("(")) {
                    phone = phone.replace("(", "");
                }
                while (phone.contains(")")) {
                    phone = phone.replace(")", "");
                }
                return Long.parseLong(phone);
            }
        } catch (Exception e) {
            numero = null;
            LOGGER.error("Error conviertiendo valor a telefono: {}", e.toString());
        }
        return numero;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        String result = StringUtils.EMPTY;
        Number num = (Number) value;
        if (num != null && num.longValue() > 0) {
            result = String.format("%d", num);
        }
        return result;
    }

}
