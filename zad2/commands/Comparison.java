package zad2.commands;

import zad2.exceptions.RuntimeProgramException;
import zad2.parser.Program;

import java.io.FileWriter;
import java.io.IOException;

public class Comparison extends TwoArgumentCommand {

    public Comparison(String operationType, Command argument1, Command argument2) {
        super(operationType, argument1, argument2);
    }

    @Override
    public double execute(Program program) throws RuntimeProgramException {
        double value1 = argument1.execute(program);
        double value2 = argument2.execute(program);

        return switch (getType()) {
            case ">" -> value1 > value2 ? 1 : 0;
            case ">=" -> value1 >= value2 ? 1 : 0;
            case "==" -> value1 == value2 ? 1 : 0;
            case "<" -> value1 < value2 ? 1 : 0;
            case "<=" -> value1 <= value2 ? 1 : 0;
            default -> throw new RuntimeProgramException("Niepoprawna instrukcja porównania.");
        };
    }

    @Override
    public String toJava(FileWriter writer, String currVariable) throws IOException {
        String newVariable = currVariable + "c"; // wartość wynikowa
        String newVariable1 = currVariable + "c1"; // wartość pierwszego argumentu
        String newVariable2 = currVariable + "c2"; // wartość drugiego argumentu

        // Obliczenie wartości argumentów.
        writer.write("double " + newVariable1 + " = " + argument1.toJava(writer, newVariable1) + ";" + System.lineSeparator());
        writer.write("double " + newVariable2 + " = " + argument2.toJava(writer, newVariable2) + ";" + System.lineSeparator());

        // Obliczenie wartości wynikowej.
        writer.write("double " + newVariable + " = " + newVariable1 + " " + getType() + " " + newVariable2 + "? 1 : 0;" + System.lineSeparator());

        return newVariable;
    }
}
