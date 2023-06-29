public class Transition {

    private final State start;
    private final State end;
    private final Character input;
    private final Character output;

    public Transition(State start, State end, Character input, Character output) {
        this.start = start;
        this.end = end;
        this.input = input;
        this.output = output;
    }

    public State getStart() {
        return start;
    }

    public State getEnd() {
        return end;
    }

    public Character getInput() {
        return input;
    }

    public Character getOutput() {
        return output;
    }

}
