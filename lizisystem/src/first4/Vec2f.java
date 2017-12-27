package first4;

public class Vec2f {
	public double x,y;
	public Vec2f(double x,double y)
	{
	this.x=x;this.y=y;
	}
	//向量加
	public Vec2f add(Vec2f v) 
	{
	return new Vec2f(this.x + v.x, this.y + v.y);
	}
	//向量乘
	public Vec2f multiply(double f) 
	{
	return new Vec2f(this.x * f, this.y * f);
	}
}