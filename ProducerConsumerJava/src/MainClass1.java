import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class MainClass1 {

	public static void main(String args[]) {
		ProducerConsumnerClass producerConsumnerClass = new ProducerConsumnerClass(
				4);
		Thread prducerThread = new Thread(new Runnable() {

			@Override
			public void run() {
				producerConsumnerClass.produce();
			}
		});

		Thread consumerThread = new Thread(new Runnable() {

			@Override
			public void run() {

				producerConsumnerClass.consume();
			}
		});
		prducerThread.start();
		consumerThread.start();

	}

}

class ProducerConsumnerClass {

	BlockingQueue<Integer> jobList;

	public ProducerConsumnerClass(int capacity) {

		this.jobList = new LinkedBlockingDeque<Integer>(capacity);
	}

	public void produce() {
		int jobNumber = 100;
		while (true) {
			try {
				jobList.put(jobNumber);
				System.out.println("addded job no " + jobNumber + " by thread "
						+ Thread.currentThread().getName());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jobNumber++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void consume() {

		while (true) {
			try {
				int removedJob = jobList.take();
				System.out.println("removed job no " + removedJob
						+ " by thread " + Thread.currentThread().getName());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}