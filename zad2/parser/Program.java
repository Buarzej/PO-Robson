package zad2.parser;

import zad2.commands.Command;
import zad2.exceptions.RuntimeProgramException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Program {
    private final Command code;
    private final Map<String, Double> variables = new HashMap<>();
    private Set<String> allVariables;

    public Program(Command code) {
        this.code = code;
    }

    public double execute() throws RuntimeProgramException {
        variables.clear();
        double result = code.execute(this);

        // Zapisanie wszystkich przypisanych zmiennych w celu ewentualnej konwersji do Javy.
        allVariables = variables.keySet();

        return result;
    }

    public Command getCode() {
        return code;
    }

    public Set<String> getAllVariables() {
        return allVariables;
    }

    public void setVariable(String key, double value) {
        variables.put(key, value);
    }

    public double getVariable(String key) throws RuntimeProgramException {
        if (!variables.containsKey(key))
            throw new RuntimeProgramException("Odwołanie do nieistniejącej zmiennej.");

        return variables.get(key);
    }

}
