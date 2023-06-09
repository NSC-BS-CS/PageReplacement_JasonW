public class Page implements Comparable<Page> {
    private int pageNum;
    private int timeSinceAccessed;

    public Page(int pageNum) {
        this.pageNum = pageNum;
        this.timeSinceAccessed = 0;
    }
    /**
     * Increments the time since the page was last accessed.
     */
    public void tickAccessTime() {
        this.timeSinceAccessed++;
    }

    /**
     * returns the number that the page represents
     * @return the number that the page represents
     */
    public int getPageNum() {
        return this.pageNum;
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return this.pageNum + " --> " + this.timeSinceAccessed;
    }

    /**
     * {@inheritDoc}
     */
    public int compareTo(Page other) {
        if (this.timeSinceAccessed > other.timeSinceAccessed) {
            return -1;
        } else if (this.timeSinceAccessed < other.timeSinceAccessed) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object other) {
        if (other instanceof Page) {
            return this.pageNum == ((Page)other).pageNum;
        }
        return false;
    }
}
