import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String[] line = in.readLine().split(" ");

        //Vertices
        int nChallenges = Integer.parseInt(line[0]);
        //Edges
        int nDecisions = Integer.parseInt(line[1]);

        for (int d = 0; d < nDecisions; d++) {
            line = in.readLine().split(" ");
            //C1 P/G V C2
            int previous_challenge = Integer.parseInt(line[0]);
            String type = line[1];
            int value = Integer.parseInt(line[2]);
            int next_challenge = Integer.parseInt(line[3]);
        }

        line = in.readLine().split(" ");
        int start_challenge = Integer.parseInt(line[0]);
        int wizard_challenge = Integer.parseInt(line[1]);
        int initial_energy = Integer.parseInt(line[2]);

        in.close();
    }
}