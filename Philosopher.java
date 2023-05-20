package philsopher;

public class Philosopher implements Runnable {

	private int id;
	private DiningPhilosophersMonitor monitor;

	public Philosopher(int id, DiningPhilosophersMonitor monitor) {
		this.id = id;
		this.monitor = monitor;

	}

	public void run() {

		boolean flag = true;
		while (flag) {
			think();
			monitor.takeForks(id);
			eat();
			monitor.returnForks(id);
			if (monitor.FinalCheck()) {
				System.out.println(" They are all done with eating");
				break;

			}
			flag = false;

		}

	}

	private void think() {

		try {
			System.out.println(id + ": Now I am thinking.");
			Thread.sleep((long) (Math.random() * 800));

		} catch (InterruptedException e) {
		}

	}

	private void eat() {

		try {
			System.out.println(id + ": Now I am eating.");
			Thread.sleep((long) (Math.random() * 50));

		} catch (InterruptedException e) {
		}

	}

}
