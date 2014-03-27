package syncroNized;

public class Account {
	String name;
	float balance;
	public Account(String name, float balance)
	{
		this.name = name;
		this.balance = balance;
	}
	
	public synchronized void withDraw(float x){
		float tmp = this.balance;
		tmp = tmp + x;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {

		}
		this.balance = tmp;
	}
		
	public synchronized void deposite(float y) {
		float tmp = this.balance;
		tmp = tmp - y;
		try {
			Thread.sleep(100);
			
		} catch (InterruptedException e) {

		}
		this.balance = tmp;
	}
	
	public float getBalance(){
		return this.balance;
	}

}
