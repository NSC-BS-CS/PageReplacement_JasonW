import java.util.LinkedList;
import java.util.Queue;

public class FIFO extends ReplacementAlgorithm {

    private Queue<Page> memory;

    public FIFO(int pageFrameCount) {
        super(pageFrameCount);
        this.memory = new LinkedList<Page>();
    }

    /**
     * returns a queue representing the memory
     * @return a queue representing the memory
     */
    public Queue<Page> getMemory() {
        return this.memory;
    }

    /**
     * {@inheritDoc}
     */
    public void insert(int num) {
        if (memory.size() < this.pageFrameCount) {
            addToMemory(num);
        } else {
            algorithm(num);
        }
    }

    private void algorithm(int num) {
        if (!this.memory.contains(new Page(num))) {
            this.memory.poll();
            addToMemory(num);
        }
    }

    private void addToMemory(int num) {
        memory.add(new Page(num));
        this.pageFaultCount++;
    }
}
