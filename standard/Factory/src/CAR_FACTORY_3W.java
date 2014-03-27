
public class CAR_FACTORY_3W  implements CAR_FACTORY{
	@Override
	public CAR create_car() {
		CAR_CLASS_3W car_3w_imp = new CAR_CLASS_3W();
		car_3w_imp.setName("上海大众passt");
		car_3w_imp.setSpeed(200);
		return car_3w_imp;
	}
}
