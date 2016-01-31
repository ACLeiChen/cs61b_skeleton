import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Planet {
	public double xxPos; //Its current x position
	public double yyPos; //Its current y position
	public double xxVel; //Its current velocity in the x direction
	public double yyVel; //Its current velocity in the y direction
	public double mass;  //Its mass
	public String imgFileName;//The name of an image in the images directory that depicts the planet

	//the first constructor
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	//the second constructor
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	//calculate the distance between two planets
	public double calcDistance(Planet p){
		double distance, dx, dy;
		dx = p.xxPos - xxPos;
		dy = p.yyPos - yyPos;
		distance = sqrt(dx*dx+dy*dy);

		return distance;
	}

	//return the force exerted on this planet by the given planet p
	public double calcForceExertedBy(Planet p){
		double fx, pairwise_force, distance, G_factor;
		G_factor = 6.67*pow(10,-11);
		distance = calcDistance(p);
		pairwise_force = G_factor*mass*p.mass/(distance*distance);

		return pairwise_force;
	}

	//return the x-component Fx of a force exerted on the planet
	public double calcForceExertedByX(Planet p){
		double fx, pairwise_force, distance, dx;	
		dx = p.xxPos - xxPos;
		distance = calcDistance(p);
		pairwise_force = calcForceExertedBy(p);
		fx = pairwise_force*dx/distance;

		return fx;
	}

	//return the y-component Fy of a force exerted on the planet
	public double calcForceExertedByY(Planet p){
		double fy, pairwise_force, distance, dy;
		dy = p.yyPos - yyPos;
		distance = calcDistance(p);
		pairwise_force = calcForceExertedBy(p);
		fy = pairwise_force*dy/distance;

		return fy;
	}

	//calculate the net X force exerted by all planets except itself
	public double calcNetForceExertedByX(Planet[] allplanets){
		int i;
		double fx = 0;
		for (i=0; i < allplanets.length; i++){
			if (! equals(allplanets[i])){
				fx += calcForceExertedByX(allplanets[i]);
				//fy += calcForceExertedByY(allplanets[i]);
			}
		}

		return fx;
	}

	//calculate the net Y force exerted by all planets except itself
	public double calcNetForceExertedByY(Planet[] allplanets){
		int i;
		double fy = 0;
		for (i=0; i < allplanets.length; i++){
			if (! equals(allplanets[i])){
				fy += calcForceExertedByY(allplanets[i]);
				//fy += calcForceExertedByY(allplanets[i]);
			}
		}

		return fy;
	}

	//update change in the planet's velocity and position in a small period of time
	public void update(double dt, double fx, double fy){
		double ax, ay;//the x- and y- acceleration
		ax = fx / mass;
		ay = fy / mass;
		xxVel = xxVel + ax * dt;
		yyVel = yyVel + ay * dt;//new velocity
		//new position, not accurate simulation, but required so
		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;	
	}

	//draw the Planet's img at the Planet's position, no return value, no parameters
	public void draw(){
		/*draw the Planet's img at the Planet's position*/
		 StdDraw.picture(xxPos, yyPos, imgFileName);

		 /* Shows the drawing to the screen, and turn off the pause mode*/	
		 //StdDraw.show();
	}
} 