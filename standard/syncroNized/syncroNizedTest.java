package syncroNized;

public class syncroNizedTest {
	private static int NUMBER_THREAD = 1000;
	static Thread[] arrayThread = new Thread[NUMBER_THREAD];
	
	public static void main(String args[]) {
		final Account justin = new Account("justin",1000.0f);
		for(int i = 0; i < NUMBER_THREAD; i++) {
			arrayThread[i] = new Thread(new Runnable() {
				public void run() {
					
					justin.deposite(10.0f);
					justin.withDraw(10.0f);
					
				}
				
			});
			arrayThread[i].start();
		}
		
		for(int i = 0; i < NUMBER_THREAD; i++) {
			try {
				arrayThread[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("The balance of " + justin.name + " is " + justin.balance);
	}

}
