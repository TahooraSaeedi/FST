public class State implements Comparable<State> {

    private final String name;
    private final Boolean isStart;
    private final Boolean isFinal;

    public State(String name, Boolean isStart, Boolean isFinal) {
        this.name = name;
        this.isStart = isStart;
        this.isFinal = isFinal;
    }

    public String getName() {
        return name;
    }

    public Boolean getIsStart() {
        return isStart;
    }

    public Boolean getIsFinal() {
        return isFinal;
    }

    @Override
    public int compareTo(State o) {
        if (this.getName() == o.getName() && this.getIsStart() == o.getIsStart() && this.getIsFinal() == o.getIsFinal()) return 1;
        return 0;
    }

}
