import java.nio.file.Files;
import java.util.List;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        FST fst = new FST();

        Character[] AZ = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Character[] AZ_F = {'a', 'b', 'c', 'd', 'e', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Character[] AZ_CS = {'a', 'b', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Character[] BI = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'};
        Character[] AZ_EFHOSXYZ = {'a', 'b', 'c', 'd', 'g', 'i', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 't', 'u', 'v', 'w'};
        Character[] BA = {'a', 'e', 'i', 'o', 'u'};
        Character[] OSXZ = {'o', 's', 'x', 'z'};
        Character[] CS = {'c', 's'};

        fst.addState("0", true, false);
        fst.addState("1", false, false);
        fst.addState("2", false, false);
        fst.addState("3", false, false);
        fst.addState("4", false, true);
        fst.addState("5", false, false);
        fst.addState("6", false, false);
        fst.addState("7", false, false);
        fst.addState("8", false, false);
        fst.addState("9", false, false);

        State _0 = fst.getStates().get(0);
        State _1 = fst.getStates().get(1);
        State _2 = fst.getStates().get(2);
        State _3 = fst.getStates().get(3);
        State _4 = fst.getStates().get(4);
        State _5 = fst.getStates().get(5);
        State _6 = fst.getStates().get(6);
        State _7 = fst.getStates().get(7);
        State _8 = fst.getStates().get(8);
        State _9 = fst.getStates().get(9);

        fst.addSetTransition(_0, _3, AZ_EFHOSXYZ);
        fst.addSetTransition(_0, _7, AZ_CS);
        fst.addSetTransition(_0, _2, OSXZ);
        fst.addSetTransition(_0, _5, AZ_F);
        fst.addSetTransition(_0, _0, AZ);
        fst.addSetTransition(_0, _6, CS);
        fst.addSetTransition(_0, _9, BA);
        fst.addSetTransition(_0, _8, BI);

        fst.addTransition(_0, _1, 'f', ' ');
        fst.addTransition(_0, _2, 'f', 'v');
        fst.addTransition(_1, _2, 'e', 'v');
        fst.addTransition(_2, _3, ' ', 'e');
        fst.addTransition(_3, _4, ' ', 's');
        fst.addTransition(_5, _3, 'e', 'e');
        fst.addTransition(_6, _2, 'h', 'h');
        fst.addTransition(_7, _3, 'h', 'h');
        fst.addTransition(_8, _2, 'y', 'i');
        fst.addTransition(_9, _3, 'y', 'y');

        File test = new File("test.txt");
        List<String> vocabs = Files.readAllLines(test.toPath());
        for (String v : vocabs) {
            if (v.matches("[a-z]+")) {
                fst.parse(_0, v, "");
            } else {
                System.out.println("FAIL");
            }
        }
    }
}
