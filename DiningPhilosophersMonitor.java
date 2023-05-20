package philsopher;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophersMonitor implements DiningServer {
	private int numOfPhils;
	private State[] state;
	private Condition[] self;

	public State[] getState() {
		return state;
	}

	public void setState(State[] state) {
		this.state = state;
	}

	private Lock lock;

	public DiningPhilosophersMonitor(int num) {

		numOfPhils = num;
		state = new State[num];
		self = new Condition[num];
		lock = new ReentrantLock();
		for (int i = 0; i < num; i++) {
			state[i] = State.THINKING;
			self[i] = lock.newCondition();
		}

	}

	private int leftOf(int i) {
		return (i + numOfPhils - 1) % numOfPhils;

	}

	private int rightOf(int i) {
		return (i + 1) % numOfPhils;

	}

	private void test(int i) {
		if (state[i] == State.HUNGRY && state[leftOf(i)] != State.EATING && state[leftOf(i)] != State.EATING) {
			state[i] = State.EATING;
			self[i].signal();
		}

	}

	public boolean FinalCheck() {

		int i = 0;

		while (state[i] == State.DONE) {

			i++;
			if (i == numOfPhils - 1) {
				break;
			}

		}

		if (i == numOfPhils - 1) {

			return true;
		}

		else
			return false;

	}

	public void takeForks(int id) {
		lock.lock();
		try {
			state[id] = State.HUNGRY;
			test(id);
			if (state[id] != State.EATING) {
				self[id].await();
			}

		} catch (InterruptedException e) {
		} finally {
			lock.unlock();
		}
	}

	public void returnForks(int id) {
		lock.lock();
		try {
			state[id] = State.DONE;
			System.out.println(id + ": I am done with eating.");

			test(leftOf(id));
			test(rightOf(id));

		} finally {
			lock.unlock();
		}
	}

}
