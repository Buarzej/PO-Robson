package zad2.parser;

import com.google.gson.*;
import zad2.commands.*;

import java.lang.reflect.Type;

public class CommandAdapter implements JsonDeserializer<Command> {
    @Override
    public Command deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        // Pobranie typu aktualnej instrukcji.
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        String objectType = jsonObject.get("type").getAsString();

        // Konwersja do odpowiedniej klasy konkretnej.
        return switch (objectType) {
            case "Blok" -> jsonDeserializationContext.deserialize(jsonElement, Block.class);
            case "If" -> jsonDeserializationContext.deserialize(jsonElement, If.class);
            case "While" -> jsonDeserializationContext.deserialize(jsonElement, While.class);
            case "Przypisanie" -> jsonDeserializationContext.deserialize(jsonElement, Assignment.class);
            case "Plus", "Minus", "Razy", "Dzielenie" -> jsonDeserializationContext.deserialize(jsonElement, ArithmeticExpression.class);
            case "And", "Or" -> jsonDeserializationContext.deserialize(jsonElement, LogicExpression.class);
            case ">", ">=", "==", "<", "<=" -> jsonDeserializationContext.deserialize(jsonElement, Comparison.class);
            case "Not" -> jsonDeserializationContext.deserialize(jsonElement, Not.class);
            case "Liczba" -> jsonDeserializationContext.deserialize(jsonElement, NumberValue.class);
            case "True", "False" -> jsonDeserializationContext.deserialize(jsonElement, LogicValue.class);
            case "Zmienna" -> jsonDeserializationContext.deserialize(jsonElement, Variable.class);
            default -> throw new JsonParseException("Nieznana instrukcja.");
        };
    }
}
