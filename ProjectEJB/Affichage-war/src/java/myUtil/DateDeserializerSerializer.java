package myUtil;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import java.lang.reflect.Type;
import java.util.Date;

public class DateDeserializerSerializer implements JsonSerializer<Date>, JsonDeserializer<Date> {

    @Override
    public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(date.getTime());
    }

    @Override
    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        String dateStr = jsonElement.getAsString();
        
        // Extrait le timestamp du format '/Date(1697922000000)/'
        String timestampStr = dateStr.replaceAll("/Date\\(", "").replaceAll("\\)/", "");
        
        try {
            long timestamp = Long.parseLong(timestampStr);
            return new Date(timestamp);
        } catch (NumberFormatException e) {
            throw new JsonParseException("Impossible de désérialiser la date.");
        }
    }
}

