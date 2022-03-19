package zad2.commands;

import zad2.exceptions.RuntimeProgramException;
import zad2.parser.Program;

import java.io.FileWriter;
import java.io.IOException;

public class LogicExpression extends TwoArgumentCommand {

    public LogicExpression(String operationType, Command argument1, Command argument2) {
        super(operationType, argument1, argument2);
    }

    @Override
    public double execute(Program program) throws RuntimeProgramException {
        boolean value1 = argument1.execute(program) != 0;
        boolean value2 = argument2.execute(program) != 0;

        return switch (getType()) {
            case "And" -> value1 && value2 ? 1 : 0;
            case "Or" -> value1 || value2 ? 1 : 0;
            default -> throw new RuntimeProgramException("Niepoprawna instrukcja logiczna.");
        };
    }

    @Override
    public String toJava(FileWriter writer, String currVariable) throws IOException {
        String newVariable = currVariable + "l"; // wartość wynikowa
        String newVariable1 = currVariable + "l1"; // wartość logiczna pierwszego argumentu
        String newVariable2 = currVariable + "l2"; // wartość logiczna drugiego argumentu

        // Obliczenie wartości logicznych argumentów.
        writer.write("boolean " + newVariable1 + " = " + argument1.toJava(writer, newVariable1) + "!= 0;" + System.lineSeparator());
        writer.write("boolean " + newVariable2 + " = " + argument2.toJava(writer, newVariable2) + "!= 0;" + System.lineSeparator());

        // Obliczenie wartości wynikowej.
        writer.write("double " + newVariable + " = " + newVariable1);
        switch (getType()) {
            case "And" -> writer.write(" && ");
            case "Or" -> writer.write(" || ");
        }
        writer.write(newVariable2 + "? 1 : 0;" + System.lineSeparator());

        return newVariable;
    }
}
