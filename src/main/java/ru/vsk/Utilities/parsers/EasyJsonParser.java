package ru.vsk.Utilities.parsers;

import com.google.gson.*;

public class EasyJsonParser extends EasyParser {
    private String json;

    public EasyJsonParser(String json) throws EasyParserException {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(json);
            this.json = System.lineSeparator() + gson.toJson(je);
        } catch (JsonSyntaxException e) {
            throw new EasyParserException("Can't convert data");
        }
    }

    @Override
    public String toString() {
        return json;
    }
}
