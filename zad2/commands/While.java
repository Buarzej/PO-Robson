package zad2.commands;

import zad2.exceptions.RuntimeProgramException;
import zad2.parser.Program;

import java.io.FileWriter;
import java.io.IOException;

public class While extends Command {
    private final Command condition;
    private final Command expression;

    public While(Command condition, Command expression) {
        super("While");
        this.condition = condition;
        this.expression = expression;
    }

    @Override
    public double execute(Program program) throws RuntimeProgramException {
        while (condition.execute(program) == 1)
            expression.execute(program);

        return 0;
    }

    @Override
    public String toJava(FileWriter writer, String currVariable) throws IOException {
        String newVariableCondition = currVariable + "w1"; // wartość logiczna warunku
        String newVariableExpression = currVariable + "w2"; // wartość bloku
        String newVariableCondition2 = currVariable + "w3"; // wartość logiczna ponownego obliczenia warunku

        // Obliczenie wartości warunku.
        String conditionReference = condition.toJava(writer, newVariableCondition);
        writer.write("while (" + conditionReference + " == 1) {" + System.lineSeparator());

        // Obliczenie wartości bloku.
        expression.toJava(writer, newVariableExpression);

        // Ponowne obliczenie wartości warunku i przepisanie jej do poprzedniej.
        writer.write(conditionReference + " = " + condition.toJava(writer, newVariableCondition2) + ";" + System.lineSeparator());
        writer.write("}" + System.lineSeparator());

        return "0";
    }
}
