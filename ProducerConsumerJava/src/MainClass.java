import java.util.LinkedList;
import java.util.List;

public class MainClass {
	public static void main(String args[]) {
		ProducerConsumerClass pc = new ProducerConsumerClass(3);

		Thread prducerThread = new Thread(new Runnable() {

			@Override
			public void run() {
				pc.produce();
			}
		});

		Thread consumerThread = new Thread(new Runnable() {

			@Override
			public void run() {
			
				pc.consume();
			}
		});

		prducerThread.start();
		consumerThread.start();

	}

	static class ProducerConsumerClass {

		List<Integer> jobList = new LinkedList<Integer>();
		int capacity;

		public ProducerConsumerClass(int capacity) {
			this.capacity = capacity;
		}

		public void produce() {
			int jobNumber = 100;
			while (true) {
				synchronized (this) {
					if (jobList.size() == capacity) {
						System.out
								.println("========job list is full, producer is waiting======");
						try {
							wait();
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					jobList.add(jobNumber);
					System.out.println("addded job no " + jobNumber
							+ " by thread " + Thread.currentThread().getName());
					notify();
					jobNumber++;

				}
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

				synchronized (this) {
					if (jobList.isEmpty()) {
						System.out
								.println("=======job list is empty, consumer is waiting======");
						try {
							wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					int removedJob = jobList.remove(0);
					System.out.println("removed job no " + removedJob
							+ " by thread " + Thread.currentThread().getName());
					notify();
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
}
