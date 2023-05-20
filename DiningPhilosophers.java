package philsopher;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

enum State {
	THINKING, HUNGRY, EATING, DONE
}

public class DiningPhilosophers {

	public static void main(String[] args) throws Exception {

		int numOfPhils = 5;
		Philosopher[] phoilosophers = new Philosopher[numOfPhils];
		DiningPhilosophersMonitor monitor = new DiningPhilosophersMonitor(numOfPhils);
		for (int i = 0; i < phoilosophers.length; i++) {

			new Thread(new Philosopher(i, monitor)).start();
		}

	}

}
