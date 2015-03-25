package ufrr.editora.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "StringLimiterConverter")
public class StringLimiterConverter implements Converter {
    private static final String LIMIT_PARAMETER_NAME = "limit";
    private static final int DEFAULT_LIMIT = 5;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return limit(value, getLimitAttribute(component));
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (! (value instanceof String))
            return null;
        else {
            return limit(value.toString(), getLimitAttribute(component));
        }
    }

    private int getLimitAttribute(UIComponent component) {
        Object att = component.getAttributes().get(LIMIT_PARAMETER_NAME);
        if (att == null)
            return DEFAULT_LIMIT;
        else
            return Integer.parseInt((String)component.getAttributes().get(LIMIT_PARAMETER_NAME));
    }

    private String limit(String s, int limit) {
        String limited = s;
        if (!(s.length() <= limit)) {
            limited = s.substring(0, limit);
        }
        return limited;
    }

}
