package zad2.commands;

import zad2.exceptions.RuntimeProgramException;
import zad2.parser.Program;

import java.io.FileWriter;
import java.io.IOException;

public class Variable extends Command {
    private final String key;

    public Variable(String key) {
        super("Zmienna");
        this.key = key;
    }

    @Override
    public double execute(Program program) throws RuntimeProgramException {
        return program.getVariable(key);
    }

    @Override
    public String toJava(FileWriter writer, String currVariable) throws IOException {
        return key;
    }
}
