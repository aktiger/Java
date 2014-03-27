
public class CAR_FACTORY_AUDI implements CAR_FACTORY {
	
	@Override
	public  CAR create_car() {
		CAR_CLASS_AUDI car_audi_imp = new CAR_CLASS_AUDI();
		car_audi_imp.setName("奥迪A6");
		car_audi_imp.setSpeed(200);
		return car_audi_imp;
	}
}
