public class ThreadDemo extends Thread {

	String NomThread;
	int Pthread_ID;
	int priority;
	ThreadDemo entrant;

	/******************************************
	 * Lib Call
	 *********************************************************/

	static {
		System.loadLibrary("ThreadDemo");
	}

	/***** Native Method *************/

	public native void PrintPID(int prio); // Native Method

	public native int GetTID(); // Native Method

	

	//public native void ChangePrio2(int prio, int prioChanged);

	/*******************
	 * Getters & Setters
	 ******************************************/
	public Thread getEntrant() {
		return entrant;
	}

	public void setEntrant(ThreadDemo entrant) {
		this.entrant = entrant;
	}

	public int getPthread_ID() {
		return Pthread_ID;
	}

	public void setPthread_ID(int pthread_ID) {
		Pthread_ID = pthread_ID;
	}

	public String getNomThread() {
		return NomThread;
	}

	public void setNomThread(String nomThread) {
		NomThread = nomThread;
	}

	public String toString() {
		return "ThreadDemo [NomThread=" + NomThread + "]";
	}

	/*******************
	 * Getters & Setters
	 ******************************************/

	public ThreadDemo(String nomThread) {
		super();
		NomThread = nomThread;
	}
/*
	public void ChangeThreadPrio(int p, ThreadDemo t) {
		setEntrant(t);
		if (Thread.currentThread().isAlive() && entrant.isAlive()) {
		System.out.println("ThreadID >>> Changing ");			
		ChangePrio2(entrant.GetTID(), p);
		} else {
			System.out.println("Thread " + entrant.getNomThread() + " is not Alive");
		}
	}
*/
	/****************************************** Run *********************************************************/

	public void run() {
		while (true) {

	 System.out.println("Thread started:::" +Thread.currentThread().getName()+ "With ID :"+Thread.currentThread ().getId());
			Thread t = Thread.currentThread();
			//System.out.println("status of " + t.getName() + " this.nomThread " + this.NomThread + " = " + t.isAlive());
			// Native Methode call
			PrintPID(this.priority);
			System.out.println("Until JNI \n" + GetTID());
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread ended:::" + "With ID :" + Thread.currentThread().getId());
		}
	}

	public static void main(String args[]) throws Exception {

		ThreadDemo T1 = new ThreadDemo("T1");
		ThreadDemo T2 = new ThreadDemo("T2");
		//ThreadDemo T3 = new ThreadDemo("T3");

		T1.start();
		T2.start();
		
	
		T1.yield();

		//Change PRIORITY FROM ANOTHER THREAD
	//	T1.ChangeThreadPrio(14, T2);

		T2.yield();

//		System.out.println("status of " + T1.getNomThread() + " = " + T1.isAlive());
//		System.out.println("status of " + T2.getNomThread() + " = " + T2.isAlive());
	}
}
