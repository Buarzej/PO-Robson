package zad2.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
import zad2.commands.Command;
import zad2.exceptions.InvalidProgramException;
import zad2.exceptions.RuntimeProgramException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Robson {

    private Program currentProgram;

    public void fromJSON(String filename) throws InvalidProgramException, FileNotFoundException {
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Command.class, new CommandAdapter());
            Gson gson = gsonBuilder.create();

            JsonReader reader = new JsonReader(new FileReader(filename));

            currentProgram = new Program(gson.fromJson(reader, Command.class));
        } catch (JsonParseException e) {
            throw new InvalidProgramException(e.getMessage());
        }
    }

    public void toJSON(String filename) throws IOException {
        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
        FileWriter writer = new FileWriter(filename);
        gson.toJson(currentProgram.getCode(), writer);
        writer.close();
    }

    public void toJava(String filename) throws IOException, RuntimeProgramException {
        // Jeśli program nie był nigdy uruchamiany, robimy to i wypełniamy listę wszystkich przypisywanych zmiennych.
        if (currentProgram.getAllVariables() == null)
            currentProgram.execute();

        FileWriter writer = new FileWriter(filename);
        writer.write("class Main {" + System.lineSeparator());
        writer.write("public static void main(String[] args) {" + System.lineSeparator());

        // Deklaracja wszystkich przypisywanych zmiennych.
        for (String variable : currentProgram.getAllVariables())
            writer.write("double " + variable + " = 0;" + System.lineSeparator());

        // Wygenerowanie kodu programu.
        writer.write("System.out.println(" + currentProgram.getCode().toJava(writer, "robson") + ");" + System.lineSeparator());

        writer.write("}" + System.lineSeparator() + "}");
        writer.close();
    }

    public double execute() throws RuntimeProgramException {
        return currentProgram.execute();
    }
}
