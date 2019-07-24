package cl.sebastian.matrix.validator.portal.converter;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import java.io.Serializable;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Sebastián Salazar Molina
 */
@FacesConverter(value = "BaseBeanConverter")
public class BaseBeanConverter implements Converter, Serializable {

    private static final long serialVersionUID = 6802811478551171072L;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseBeanConverter.class);

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String text) {
        Object result = null;
        try {
            if (StringUtils.isNotBlank(text)) {
                byte[] serObj = Hex.decodeHex(text.toCharArray());
                result = SerializationUtils.deserialize(serObj);
            }
        } catch (Exception e) {
            LOGGER.error("Error al deserializar el objeto: {}", e.toString());
            throw new ConverterException(e);
        }
        return result;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object obj) {
        String result = StringUtils.EMPTY;
        try {
            if (obj != null) {
                byte[] serObj = SerializationUtils.serialize((Serializable) obj);
                result = new String(Hex.encodeHex(serObj));
            }
        } catch (Exception e) {
            LOGGER.error("Error al serializar objeto: {}", e.toString());
            throw new ConverterException(e);
        }
        return result;
    }
}
