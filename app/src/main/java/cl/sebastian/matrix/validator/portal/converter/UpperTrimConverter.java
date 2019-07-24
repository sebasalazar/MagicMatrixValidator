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
@FacesConverter(value = "UpperTrimConverter")
public class UpperTrimConverter implements Converter, Serializable {

    private static final long serialVersionUID = 6139229856331968512L;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String texto) {
        return StringUtils.upperCase(StringUtils.trimToEmpty(texto));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            return StringUtils.upperCase(StringUtils.trimToEmpty(o.toString()));
        } else {
            return StringUtils.EMPTY;
        }
    }
}
