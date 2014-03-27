
public class CAR_CLASS_AUDI extends CAR_CLASS{
	public void start() {
		System.out.println("我是奥迪;" + this.getName() + "启动了，速度为" + this.getSpeed());
	}
	public void stop() {
		System.out.println("我是奥迪;" + this.getName() + "停止了");
	}
}
