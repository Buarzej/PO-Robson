package zad2.commands;

public abstract class TwoArgumentCommand extends Command {
    protected final Command argument1;
    protected final Command argument2;

    public TwoArgumentCommand(String operationType, Command argument1, Command argument2) {
        super(operationType);
        this.argument1 = argument1;
        this.argument2 = argument2;
    }
}
