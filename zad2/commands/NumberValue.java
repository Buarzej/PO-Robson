package zad2.commands;

import zad2.parser.Program;

import java.io.FileWriter;
import java.io.IOException;

public class NumberValue extends Command {
    private final double value;

    public NumberValue(double value) {
        super("Liczba");
        this.value = value;
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
