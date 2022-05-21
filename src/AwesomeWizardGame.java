import utils.Graph;
import utils.Pair;

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
        int total_value = value;
        if (type.equalsIgnoreCase(PAYS)){
            total_value *= -1;
        }
        this.graph.addEdge(previous_challenge, next_challenge, total_value);
    }

    public void setOrigin(int start_challenge, int initial_energy, int wizard_challenge) {
        this.start = start_challenge;
        this.end = wizard_challenge;
        this.energy = initial_energy;
    }

    public int maximumEnergy() throws Exception {
        int[] length = new int[this.graph.nNodes()];

        //Initialize all values with maximum length possible
        for (int n = 0; n < this.graph.nNodes(); n++) {
            length[n] = Integer.MAX_VALUE;
        }
        length[this.start] = 0;

        boolean changes = false;
        for (int n = 1; n < this.graph.nNodes(); n++) {
            changes = updateLength(length);

            if (!changes){
                break;
            }
        }

        if (changes && updateLength(length)){
            throw new Exception("Negative cycle");
        } else {
            return energy + length[end];
        }
    }

    private boolean updateLength(int[] length) {
        boolean changes = false;

        for (int node1 = 0; node1 < this.graph.nNodes(); node1++) {
            List<Pair<Integer>> successors = this.graph.findSuccessors(node1);

            for (Pair<Integer> pair: successors) {
                int node2 = pair.getValue1();
                int label = pair.getValue2();
                if (length[node1] < Integer.MAX_VALUE){
                    int new_length = length[node1] + label;

                    if (new_length < length[node2]){
                        length[node2] = new_length;
                        changes = true;
                    }
                }
            }

        }

        return changes;
    }
}
