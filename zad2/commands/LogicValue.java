package zad2.commands;

import zad2.exceptions.RuntimeProgramException;
import zad2.parser.Program;

import java.io.FileWriter;
import java.io.IOException;

public class LogicValue extends Command {
    private final double value;

    public LogicValue(String value) throws RuntimeProgramException {
        super(value);
        switch (value) {
            case "True" -> this.value = 1;
            case "False" -> this.value = 0;
            default -> throw new RuntimeProgramException("Niepoprawna wartość logiczna.");
        }
    }

    @Override
    public double execute(Program program) {
        return value;
    }

    @Override
    public String toJava(FileWriter writer, String currVariable) throws IOException {
        return Double.toString(value);
    }
}
