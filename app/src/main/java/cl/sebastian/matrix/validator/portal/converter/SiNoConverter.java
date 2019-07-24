package cl.sebastian.matrix.validator.portal.converter;

import org.apache.commons.lang3.StringUtils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.io.Serializable;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Sebastián Salazar Molina.
 */
@FacesConverter(value = "SiNoConverter")
public class SiNoConverter implements Converter, Serializable {

    private static final String SI = "Sí";
    private static final String NO = "No";
    private static final long serialVersionUID = 1107690757415885696L;

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        String formatedCheck = NO;
        if (o instanceof Boolean) {
            boolean sino = (Boolean) o;
            if (sino) {
                formatedCheck = SI;
            } else {
                formatedCheck = NO;
            }
        }

        if (o instanceof Number) {
            Number sino = (Number) o;
            if (sino.intValue() > 0) {
                formatedCheck = SI;
            } else {
                formatedCheck = NO;
            }
        }
        return formatedCheck;
    }

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        boolean result = false;
        if (StringUtils.isNotBlank(value)) {
            String text = StringUtils.trimToEmpty(value);
            result = StringUtils.equalsIgnoreCase(text, SI);
        }
        return result;
    }
}
