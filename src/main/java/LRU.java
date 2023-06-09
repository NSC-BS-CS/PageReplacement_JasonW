import java.util.PriorityQueue;

public class LRU extends ReplacementAlgorithm {

    //Ordered by the timeSinceAccessed field in Page
    private PriorityQueue<Page> memory;
    public LRU(int num) {
        super(num);
        this.memory = new PriorityQueue<>();
    }

    public void insert(int num) {
        tickAllPages();
        if (this.memory.size() < this.pageFrameCount) {
            addToMemory(num);
        } else {
            algorithm(num);
        }
    }

    /**
     * returns a priority queue representing the memory
     * @return a priority queue representing the memory
     */
    public PriorityQueue<Page> getMemory() {
        return this.memory;
    }

    /**
     * {@inheritDoc}
     * @return
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Page page : this.memory) {
            sb.append(page.toString() + "\n");
        }
        sb.append("....................\n");
        return sb.toString();
    }

    private void algorithm(int num) {
        if (this.memory.contains(new Page(num))) {
            resetPage(new Page(num));
        } else {
            this.memory.poll();
            addToMemory(num);
        }
    }

    private void addToMemory(int num) {
        this.memory.add(new Page(num));
        this.pageFaultCount++;
    }

    private void tickAllPages() {
        for (Page page : this.memory) {
            page.tickAccessTime();;
        }
    }

    private void resetPage(Page targetPage) {
        this.memory.remove(targetPage);
        this.memory.add(targetPage);
    }
}
