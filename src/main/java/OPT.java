import java.util.ArrayList;
import java.util.List;

public class OPT extends ReplacementAlgorithm {
    private List<Page> memory;
    private int inputIndex;

    public OPT(int num, int[] input) {
        super(num);
        this.referenceString = input;
        this.memory = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     * @param num
     */
    public void insert(int num) {
        if (this.memory.size() < this.pageFrameCount) {
            addToMemory(num);
            this.inputIndex++;
        } else {
            algorithm(num);
            this.inputIndex++;
        }
    }

    /**
     * returns a list representing the memory
     * @return a list representing the memory
     */
    public List<Page> getMemory() {
        return this.memory;
    }

    /**
     * returns the reference string currently being used by the algorithm
     * @return the reference string currently being used by the algorithm
     */
    public int[] getreferenceString() {
        return this.referenceString;
    }

    private void algorithm(int num) {
        if (!this.memory.contains(new Page(num))) {
            this.memory.remove(findFurthestNum());
            addToMemory(num);
        }
    }

    private Page findFurthestNum() {
        List<Page> tempList = new ArrayList<>(this.memory);
        for (int i = this.inputIndex + 1; i < this.referenceString.length; i++) {
            if (tempList.size() == 1) {
                return tempList.get(0);
            }
            Page currPage = new Page(this.referenceString[i]);
            tempList.remove(currPage);
        }
        return tempList.get(0);
    }

    private void addToMemory(int num) {
        this.memory.add(new Page(num));
        this.pageFaultCount++;
    }
}
