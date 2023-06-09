import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FIFOTest {
    private FIFO fifo;

    @BeforeEach
    void setup() {
        int numOfFrames = 3;
        this.fifo = new FIFO(numOfFrames);
    }

    @Test
    void insertWhenNotFull() {
        int numOfFrames = 3;
        for (int i = 0; i < numOfFrames; i++) {
            assertEquals(this.fifo.pageFaultCount, i);
            this.fifo.insert(i);
        }
        assertEquals(this.fifo.pageFaultCount, numOfFrames);
    }

    @Test
    void insertDiffWhenFull() {
        int numOfFrames = 3;
        for (int i = 0; i < numOfFrames; i++) {
            this.fifo.insert(i);
        }
        this.fifo.insert(3);
        assertEquals(this.fifo.pageFaultCount, numOfFrames + 1);

        //testing to see if it removed the correct value
        Queue<Page> memory = this.fifo.getMemory();
        int index = 0;
        int[] tempNums = new int[3];
        for (Page page : memory) {
            tempNums[index] = page.getPageNum();
            index++;
        }
        int[] newArray = {1, 2, 3};
        assertArrayEquals(newArray, tempNums);
    }

    @Test
    void insertSameWhenFull() {
        int numOfFrames = 3;
        for (int i = 0; i < numOfFrames; i++) {
            this.fifo.insert(i);
        }
        this.fifo.insert(0);
        assertEquals(fifo.pageFaultCount, numOfFrames);
    }

    @Test
    void fullSample() {
        int numOfFrames = 3;
        PageGenerator generator = new PageGenerator();
        int[] sample = generator.getReferenceString();
        for (int i = 0; i < sample.length; i++) {
            this.fifo.insert(sample[i]);
        }
        assertEquals(15, this.fifo.pageFaultCount);
    }

}
