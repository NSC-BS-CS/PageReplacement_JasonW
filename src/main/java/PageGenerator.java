import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PageGenerator
{

	public int[] getReferenceString() {

		int[] rStr1 = {7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1};
		//int[] fStr2 = {8,1,0,7,3,0,3,4,5,3,5,2,0,6,8,4,8,1,5,3};
		//int[] rStr3 = {4,6,4,8,6,3,6,0,5,9,2,1,0,4,6,3,0,6,8,4};
		return rStr1;
	}

	/**
	 * Returns a string of n size filled with random values from 0 - 9
	 * @param size the size of the string
	 * @return a string of n size filled with random values from 0 - 9
	 */
	public List<Integer> getRandomReferenceString(int size) {
		Random r = new Random();
		List<Integer> refString = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			refString.add(r.nextInt(10));
		}
		return refString;
	}


}
