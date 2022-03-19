package zad2;

import zad2.exceptions.InvalidProgramException;
import zad2.exceptions.RuntimeProgramException;
import zad2.parser.Robson;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            if (args.length < 3)
                throw new IllegalArgumentException("Za mało argumentów.");

            Robson robson = new Robson();
            robson.fromJSON(args[0]);
            System.out.println(robson.execute());
            robson.toJSON(args[1]);
            robson.toJava(args[2]);
        } catch (IllegalArgumentException | IOException | RuntimeProgramException | InvalidProgramException e) {
            System.out.println(e.getMessage());
        }
    }
}
