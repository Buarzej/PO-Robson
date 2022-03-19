package zad2.commands;

import zad2.exceptions.RuntimeProgramException;
import zad2.parser.Program;

import java.io.FileWriter;
import java.io.IOException;

public abstract class Command {

    // Typ instrukcji.
    private final String type;

    public Command(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    // Wykonuje instrukcję i zwraca jej wartość.
    public abstract double execute(Program program) throws RuntimeProgramException;

    // Konwertuje instrukcję na kod Javy i zwraca nazwę zmiennej,
    // której przypisana została zwrócona przez instrukcję wartość.
    public abstract String toJava(FileWriter writer, String currVariable) throws IOException;
}
