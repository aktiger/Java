
public class CAR_CLASS implements CAR{
	private String carname;
	int speed;
	
	public String getName() {
		return this.carname;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void setName(String carname) {
		this.carname = carname;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	@Override 
	public void start() {

	}
	
	@Override
	public void stop() {
		
	}
	
}
