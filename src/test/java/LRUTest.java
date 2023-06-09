import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LRUTest {
    private LRU lru;
    private int numOfFrames;

    @BeforeEach
    void setup() {
        PageGenerator generator = new PageGenerator();
        int[] sample = generator.getReferenceString();
        this.numOfFrames = 3;
        this.lru = new LRU(this.numOfFrames);
    }

    @Test
    void insertWhenNotFull() {
        for (int i = 0; i < this.numOfFrames; i++) {
            assertEquals(this.lru.pageFaultCount, i);
            this.lru.insert(i);
        }
        assertEquals(this.lru.pageFaultCount, this.numOfFrames);
    }

    @Test
    void insertDiffWhenFull() {
        for (int i = 0; i < this.numOfFrames; i++) {
            this.lru.insert(i);
        }
        this.lru.insert(3);
        assertEquals(this.lru.pageFaultCount, this.numOfFrames + 1);
    }

    @Test
    void removeCorrectValueWhenFull() {
        List<Page> correctOutput = new ArrayList<>();
        correctOutput.add(new Page(1));
        correctOutput.add(new Page(2));
        correctOutput.add(new Page(3));
        int[] sample = {0, 1, 2, 3, 0, 2, 1};
        for (int i = 0; i < sample.length; i++) {
            this.lru.insert(sample[i]);
            if (i == 3) {
                PriorityQueue<Page> currMemory = this.lru.getMemory();
                assertEquals(correctOutput, new ArrayList<Page>(currMemory));
            }
        }
        assertEquals(6, this.lru.pageFaultCount);
    }

    @Test
    void insertSameWhenFull() {
        int numOfFrames = 3;
        for (int i = 0; i < numOfFrames; i++) {
            this.lru.insert(i);
        }
        this.lru.insert(0);
        assertEquals(this.lru.pageFaultCount, numOfFrames);
    }

    @Test
    void fullSample() {
        PageGenerator generator = new PageGenerator();
        int[] sample = generator.getReferenceString();
        for (int i = 0; i < sample.length; i++) {
            this.lru.insert(sample[i]);
        }
        assertEquals(12, this.lru.pageFaultCount);
    }
}
