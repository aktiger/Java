
public class RUN_MAIN {
	public static void main(String args[])
	{
		CAR_FACTORY car_factory = new CAR_FACTORY_AUDI();
		CAR car_ref = car_factory.create_car();
		
		car_ref.start();
		car_ref.stop();
		
		car_factory = new CAR_FACTORY_3W();
		car_ref = car_factory.create_car();
		
		car_ref.start();
		car_ref.stop();
			
	}
}
