import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OPTTest {
    private OPT opt;
    private int numOfFrames;

    @BeforeEach
    void setup() {
        PageGenerator generator = new PageGenerator();
        int[] sample = generator.getReferenceString();
        this.numOfFrames = 3;
        this.opt = new OPT(this.numOfFrames, sample);
    }

    @Test
    void insertWhenNotFull() {
        for (int i = 0; i < this.numOfFrames; i++) {
            assertEquals(this.opt.pageFaultCount, i);
            this.opt.insert(i);
        }
        assertEquals(this.opt.pageFaultCount, this.numOfFrames);
    }

    @Test
    void insertDiffWhenFull() {
        for (int i = 0; i < this.numOfFrames; i++) {
            this.opt.insert(i);
        }
        this.opt.insert(3);
        assertEquals(this.opt.pageFaultCount, this.numOfFrames + 1);
    }

    @Test
    void removeCorrectValueWhenFull() {
        List<Page> correctOutput = new ArrayList<>();
        correctOutput.add(new Page(0));
        correctOutput.add(new Page(2));
        correctOutput.add(new Page(3));
        int[] sample = {0, 1, 2, 3, 0, 2, 1};
        for (int i = 0; i < sample.length; i++) {
            this.opt.insert(sample[i]);
            if (i == 3) {
                List<Page> currMemory = this.opt.getMemory();
                assertEquals(currMemory, correctOutput);
            }
        }
        assertEquals(5, this.opt.pageFaultCount);
    }

    @Test
    void insertSameWhenFull() {
        int numOfFrames = 3;
        for (int i = 0; i < numOfFrames; i++) {
            this.opt.insert(i);
        }
        this.opt.insert(0);
        assertEquals(this.opt.pageFaultCount, numOfFrames);
    }

    @Test
    void fullSample() {
        int[] sample = this.opt.getreferenceString();
        for (int i = 0; i < sample.length; i++) {
            this.opt.insert(sample[i]);
        }
        assertEquals(9, this.opt.pageFaultCount);
    }
}
