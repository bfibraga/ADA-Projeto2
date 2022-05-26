/**
 * @author Goncalo Prata 52912
 * @author Bruno Braga 57747
 */

import graph.Graph;
import graph.Pair;

import java.util.List;

public class AwesomeWizardGame {

    private static final String PAYS = "Pays";

    private Graph graph;
    private int start;
    private int end;
    private int energy;

    public AwesomeWizardGame(int nChallenges) {
        this.graph = new Graph(nChallenges);
    }

    public void addPath(int previous_challenge, String type, int value, int next_challenge) {
        int total_value = type.equalsIgnoreCase(PAYS) ? -1 * value : value;
        this.graph.addEdge(previous_challenge, next_challenge, total_value);
    }

    public void setOrigin(int start_challenge, int initial_energy, int wizard_challenge) {
        this.start = start_challenge;
        this.end = wizard_challenge;
        this.energy = initial_energy;
    }

    public int maximumEnergy() {
        int[][] length = new int[this.graph.nNodes()][2];
        boolean[] can_reach_end = new boolean[this.graph.nNodes()];

        //Initialize all values with maximum length possible
        for (int n = 0; n < this.graph.nNodes(); n++) {
            length[n][0] = Integer.MIN_VALUE;
            length[n][1] = Integer.MIN_VALUE;
            can_reach_end[n] = false;
        }
        length[this.start][0] = 0;
        length[this.start][1] = 0;
        can_reach_end[this.end] = true;

        boolean changes;
        for (int n = 1; n < this.graph.nNodes(); n++) {
            changes = update(length, can_reach_end);
            if (!changes)
                break;
        }

        for (int n = 0; n < this.graph.nNodes(); n++) {
            if (length[n][0] - length[n][1] > 0 && can_reach_end[n]) {
                return Integer.MAX_VALUE;
            }
        }

        return Math.max(energy + length[end][0], 0);
    }

    private boolean update(int[][] length, boolean[] can_reach_end) {
        boolean changes = false;
        for (int node1 = 0; node1 < this.graph.nNodes(); node1++) {
            length[node1][1] = length[node1][0];
            List<Pair<Integer>> successors = this.graph.findSuccessors(node1);
            for (Pair<Integer> pair : successors) {
                int node2 = pair.getValue1();
                int label = pair.getValue2();

                if (node2 == this.end || can_reach_end[node2]) {
                    can_reach_end[node1] = true;
                }

                if (length[node1][0] > Integer.MIN_VALUE) {
                    int new_length = length[node1][0] + label;

                    if (new_length > length[node2][0]) {
                        length[node2][0] = new_length;
                        changes = true;
                    }
                }
            }
        }
        return changes;
    }
}
