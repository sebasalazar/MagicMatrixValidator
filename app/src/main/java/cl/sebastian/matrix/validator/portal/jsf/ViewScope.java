package cl.sebastian.matrix.validator.portal.jsf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author Sebastián Salazar Molina
 */
public class ViewScope implements Scope, Serializable {

    private static final long serialVersionUID = 2065484188494809856L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ViewScope.class);

    @Override
    public Object get(String name, ObjectFactory objectFactory) {
        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();

        if (viewMap.containsKey(name)) {
            return viewMap.get(name);
        } else {
            Object object;
            try {
                object = objectFactory.getObject();
                viewMap.put(name, object);
            } catch (Exception e) {
                object = null;
                LOGGER.error("Error al obtener valor desde la vista: {}", e.toString());
                LOGGER.debug("Error al obtener valor desde la vista: {}", e.toString(), e);
            }

            return object;
        }
    }

    @Override
    public Object remove(String name) {
        return FacesContext.getCurrentInstance().getViewRoot().getViewMap().remove(name);
    }

    @Override
    public String getConversationId() {
        return null;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        //Not supported
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }
}
