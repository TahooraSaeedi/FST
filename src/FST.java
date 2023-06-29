import java.util.ArrayList;

public class FST {

    private ArrayList<State> states;
    private ArrayList<Transition> transitions;

    public FST() {
        states = new ArrayList<State>();
        transitions = new ArrayList<Transition>();
    }

    public ArrayList<State> getStates() {
        return states;
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    public void addState(String name, Boolean isStart, Boolean isFinal) {
        this.getStates().add(new State(name, isStart, isFinal));
    }

    public Boolean addTransition(State start, State end, Character input, Character output) {
        Boolean foundStart = false;
        Boolean foundEnd = false;
        for (State state : this.getStates()) {
            if (state == start) foundStart = true;
            if (state == end) foundEnd = true;
        }
        if (foundStart && foundEnd) {
            this.getTransitions().add(new Transition(start, end, input, output));
            return true;
        }
        return false;
    }

    public Boolean addSetTransition(State start, State end, Character c[]) {
        Boolean foundStart = false;
        Boolean foundEnd = false;
        for (State state : this.getStates()) {
            if (state == start) foundStart = true;
            if (state == end) foundEnd = true;
        }
        if (foundStart && foundEnd) {
            for (Character ch : c) {
                this.getTransitions().add(new Transition(start, end, ch, ch));
            }
            return true;
        }
        return false;
    }

    public String parse(State start, String input, String output) {
        ArrayList<Transition> t = new ArrayList<Transition>();
        for (Transition transition : this.getTransitions()) {
            if (transition.getStart() == start && input.length() != 0 && transition.getInput() == input.charAt(0)) {
                t.add(transition);
            }
            if (transition.getStart() == start && transition.getInput() == ' ') {
                t.add(transition);
            }
        }
        if (start.getIsFinal() && input.length() == 0 && t.size() == 0) {
            output = output.replaceAll(" ", "");
            output = output + "\n";
            System.out.print(output);
            return "1";
        }
        if (!start.getIsFinal() && input.length() == 0 && t.size() == 0) {
            return "2";
        }
        if (input.length() != 0 && t.size() == 0) {
            return "3";
        }
        if (start.getIsFinal() && input.length() == 0 && t.size() != 0) {
            output = output.replaceAll(" ", "");
            output = output + "\n";
            System.out.print(output);
            for (Transition transition : t) {
                if (transition.getStart() == start && transition.getInput() == ' ') {
                    parse(transition.getEnd(), "", output + transition.getOutput().toString());
                }
            }
            return "4";
        }
        if (!start.getIsFinal() && input.length() == 0 && t.size() != 0) {
            for (Transition transition : t) {
                if (transition.getStart() == start && transition.getInput() == ' ') {
                    parse(transition.getEnd(), "", output + transition.getOutput().toString());
                }
            }
            return "5";
        }
        for (Transition transition : t) {
            if (transition.getInput() != ' ') {
                parse(transition.getEnd(), input.substring(1), output + transition.getOutput().toString());
            } else {
                parse(transition.getEnd(), input, output + transition.getOutput().toString());
            }
        }
        return "6";
    }
}