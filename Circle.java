package lab4;

public class Circle implements Shape{
	private double radius;
	public Circle(double radius) {
		this.radius = radius;
	}
	public double calculateArea() {
		return 3.1415*radius*radius;
	};
	public double getRadius() {
		return radius;
	}
}
