import utils.Graph;

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

        AwesomeWizardGame awesomeWizardGame = new AwesomeWizardGame(nChallenges);
        for (int d = 0; d < nDecisions; d++) {
            line = in.readLine().split(" ");
            //C1 P/G V C2
            int previous_challenge = Integer.parseInt(line[0]);
            String type = line[1];
            int value = Integer.parseInt(line[2]);
            int next_challenge = Integer.parseInt(line[3]);

            //Add edge
            awesomeWizardGame.addPath(previous_challenge, type, value, next_challenge);
        }

        line = in.readLine().split(" ");
        int start_challenge = Integer.parseInt(line[0]);
        int wizard_challenge = Integer.parseInt(line[1]);
        int initial_energy = Integer.parseInt(line[2]);

        awesomeWizardGame.setOrigin(start_challenge, initial_energy, wizard_challenge);

        int maximum = 0;
        try {
            maximum = awesomeWizardGame.maximumEnergy();
            System.out.println(maximum);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        in.close();
    }
    /*public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addEdge(2,3);
        graph.addEdge(4,3);
        graph.addEdge(1,2);

        System.out.println(graph.findDescendants(2));
        System.out.println(graph.findAncestors(5));
    }*/
}