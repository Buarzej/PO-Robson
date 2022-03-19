package zad2.commands;

import zad2.exceptions.RuntimeProgramException;
import zad2.parser.Program;

import java.io.FileWriter;
import java.io.IOException;

public class If extends Command {
    private final Command condition;
    private final Command trueExpression;
    private final Command falseExpression;

    public If(Command condition, Command trueExpression) {
        super("If");
        this.condition = condition;
        this.trueExpression = trueExpression;
        this.falseExpression = null;
    }

    public If(Command condition, Command trueExpression, Command falseExpression) {
        super("If");
        this.condition = condition;
        this.trueExpression = trueExpression;
        this.falseExpression = falseExpression;
    }

    @Override
    public double execute(Program program) throws RuntimeProgramException {
        if (condition.execute(program) == 1)
            return trueExpression.execute(program);
        else
            if (falseExpression != null)
                return falseExpression.execute(program);
            else
                return 0;
    }

    @Override
    public String toJava(FileWriter writer, String currVariable) throws IOException {
        String newVariable = currVariable + "i"; // wartość wynikowa
        String newVariableCondition = currVariable + "ic"; // wartość logiczna warunku
        String newVariableTrue = currVariable + "it"; // wartość bloku "prawda"
        String newVariableFalse = currVariable + "if"; // wartość bloku "fałsz"

        // Deklaracja wartości wynikowej.
        writer.write("double " + newVariable + ";" + System.lineSeparator());

        // Obliczenie wartości logicznej warunku.
        writer.write("if (" + condition.toJava(writer, newVariableCondition) + " == 1) {" + System.lineSeparator());

        // Obliczenie wartości bloku "prawda" i przypisanie jej do wartości wynikowej.
        writer.write("double " + newVariableTrue + " = " + trueExpression.toJava(writer, newVariableTrue) + ";" + System.lineSeparator());
        writer.write(newVariable + " = " + newVariableTrue + ";" + System.lineSeparator());

        // Obliczenie wartości bloku "fałsz" i przypisanie jej do wartości wynikowej.
        if (falseExpression != null) {
            writer.write("} else {" + System.lineSeparator());
            writer.write("double " + newVariableFalse + " = " + falseExpression.toJava(writer, newVariableFalse) + ";" + System.lineSeparator());
            writer.write(newVariable + " = " + newVariableFalse + ";" + System.lineSeparator());
            writer.write("}" + System.lineSeparator());
        } else {
            writer.write("}" + System.lineSeparator());
        }

        return newVariable;
    }
}
