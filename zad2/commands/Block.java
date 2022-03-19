package zad2.commands;

import zad2.exceptions.RuntimeProgramException;
import zad2.parser.Program;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Block extends Command {
    private final ArrayList<Command> commandList;

    public Block(ArrayList<Command> commandList) {
        super("Blok");
        this.commandList = commandList;
    }

    @Override
    public double execute(Program program) throws RuntimeProgramException {
        if (commandList.isEmpty())
            return 0;

        for (int i = 0; i < commandList.size() - 1; i++)
            commandList.get(i).execute(program);

        return commandList.get(commandList.size() - 1).execute(program);
    }

    @Override
    public String toJava(FileWriter writer, String currVariable) throws IOException {
        String newVariable = currVariable + "b"; // wartość wynikowa

        // Obliczenie wartości kolejnych instrukcji.
        int index = 0;
        for (Command command : commandList) {
            String indexVariable = newVariable + ++index; // wartość aktualnej instrukcji
            writer.write("double " + indexVariable + " = " + command.toJava(writer, indexVariable) + ";" + System.lineSeparator());
        }

        // Przypisanie wartości wynikowej jako wartość ostatniej instrukcji w bloku.
        writer.write("double " + newVariable + " = " + newVariable + index + ";" + System.lineSeparator());

        return newVariable;
    }
}
