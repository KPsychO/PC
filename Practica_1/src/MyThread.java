class MyThread extends Thread {

	private int id;
	private int T;

	public MyThread (int id, int T) {
		this.id = id;
		this.T = T;
	}

	public void run() {
		System.out.println("[CREATE] My id is: " + this.id);
		try {
			sleep(T);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("[END] My id is: " + this.id);

	}

}