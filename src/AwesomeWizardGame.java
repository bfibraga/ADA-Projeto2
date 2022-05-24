import graph.Graph;
import graph.Pair;

import java.util.List;

public class AwesomeWizardGame {

    private static final String GETS = "Gets";
    private static final String PAYS = "Pays";

    private Graph graph;
    private int start;
    private int end;
    private int energy;
    
    public AwesomeWizardGame(int nChallenges){
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
        int[] previous_length = new int[this.graph.nNodes()];
        int[] length = new int[this.graph.nNodes()];
        boolean[] can_reach_end = new boolean[this.graph.nNodes()];

        //Initialize all values with maximum length possible
        for (int n = 0; n < this.graph.nNodes(); n++) {
            length[n] = Integer.MIN_VALUE;
            previous_length[n] = Integer.MIN_VALUE;
            can_reach_end[n] = false;
        }
        length[this.start] = 0;
        previous_length[this.start] = 0;
        can_reach_end[this.end] = true;

        boolean changes = false;
        for (int n = 1; n < this.graph.nNodes(); n++) {
            changes = update(length, previous_length, can_reach_end);

            //TODO Delete this later
            System.out.println("\nIteration " + n);
            for (int node = 0; node < length.length; node++) {
                System.out.println("Length: " + node + ": " + length[node]);
                System.out.println("Previous_length: " + node + ": " + previous_length[node]);
                System.out.println("Can reach wizard? " + can_reach_end[node]);
                int delta = length[node] + previous_length[node];
                System.out.println("Delta: " + node + ": " + delta);
                System.out.println(Integer.compare(length[node], previous_length[node]));
            }

            /*if (!changes){
                break;
            }*/
        }

        return Math.max(energy + length[end], 0);
    }

    private boolean update(int[] length, int[] previous_length, boolean[] can_reach_end) {
        boolean changes = false;

        for (int node1 = 0; node1 < this.graph.nNodes(); node1++) {
            previous_length[node1] = length[node1];
            List<Pair<Integer>> successors = this.graph.findSuccessors(node1);
            for (Pair<Integer> pair: successors) {
                int node2 = pair.getValue1();
                int label = pair.getValue2();

                if (node2 == this.end || can_reach_end[node2]){
                    can_reach_end[node1] = true;
                }

                if (length[node1] > Integer.MIN_VALUE){
                    int new_length = length[node1] + label;

                    if (new_length > length[node2]){

                        length[node2] = new_length;
                        changes = true;
                    }
                }
            }

        }

        return changes;
    }
}
