package zad2.commands;

import zad2.exceptions.RuntimeProgramException;
import zad2.parser.Program;

import java.io.FileWriter;
import java.io.IOException;

public class Not extends Command {
    private final Command argument;

    public Not(Command argument) {
        super("Not");
        this.argument = argument;
    }

    @Override
    public double execute(Program program) throws RuntimeProgramException {
        return argument.execute(program) == 0 ? 1 : 0;
    }

    @Override
    public String toJava(FileWriter writer, String currVariable) throws IOException {
        String newVariable = currVariable + "n"; // wartość wynikowa
        String newVariableArgument = currVariable + "na"; // wartość argumentu

        // Obliczenie wartości argumentu.
        writer.write("double " + newVariableArgument + " = " + argument.toJava(writer, newVariableArgument) + ";" + System.lineSeparator());

        // Obliczenie wartości wynikowej.
        writer.write("double " + newVariable + " = " + newVariableArgument + "== 0 ? 1 : 0;" + System.lineSeparator());

        return newVariable;
    }
}
