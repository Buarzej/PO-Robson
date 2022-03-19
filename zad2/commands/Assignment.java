package zad2.commands;

import zad2.exceptions.RuntimeProgramException;
import zad2.parser.Program;

import java.io.FileWriter;
import java.io.IOException;

public class Assignment extends Command {
    private final String key;
    private final Command expression;

    public Assignment(String key, Command expression) {
        super("Przypisanie");
        this.key = key;
        this.expression = expression;
    }

    @Override
    public double execute(Program program) throws RuntimeProgramException {
        double value = expression.execute(program);
        program.setVariable(key, value);

        return value;
    }

    @Override
    public String toJava(FileWriter writer, String currVariable) throws IOException {
        String newVariable = currVariable + "v"; // wartość wynikowa
        String newVariableExpression = currVariable + "v1"; // wartość argumentu

        // Obliczenie wartości argumentu.
        writer.write("double " + newVariableExpression + " = " + expression.toJava(writer, newVariableExpression) + ";" + System.lineSeparator());

        // Obliczenie wartości wynikowej.
        writer.write("double " + newVariable + " = " + key + " = " + newVariableExpression + ";" + System.lineSeparator());

        return newVariable;
    }
}
