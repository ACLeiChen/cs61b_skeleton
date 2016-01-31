import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class NBody {

	public static void main(String[] args) {
		 double T, dt;
		 T = Double.parseDouble(args[0]);
		 dt = Double.parseDouble(args[1]);
		 String filename;
		 filename = args[2];

		 double radius;
		 radius = readRadius(filename);

		 Planet[] allplanets = readPlanets(filename);

		 StdDraw.setScale(-radius, radius);

		 /* Clears the drawing window. */
		 StdDraw.clear();

		 /* draw starfield.jpg as the backgound */
		 String backgound = "./images/starfield.jpg";
		 StdDraw.picture(0, 0, backgound);

		 /* Shows the drawing to the screen, and turn off the pause mode*/	
		 //StdDraw.show();

		 /*Draw all of the planets.*/
		 for (int i=0; i < allplanets.length; i++){
			allplanets[i].draw();
		 }
		 
		 /*Creating an Animation*/
		 double time_variable;
		 for (time_variable = 0; time_variable < T; time_variable += dt){
		 	double[] xForces = new double[allplanets.length];
		 	double[] yForces = new double[allplanets.length];
		 	for (int i=0; i < allplanets.length; i++){
				xForces[i] = allplanets[i].calcNetForceExertedByX(allplanets);
				yForces[i] = allplanets[i].calcNetForceExertedByY(allplanets);

		 	}
		 	for (int i=0; i < allplanets.length; i++){
		 		allplanets[i].update(dt, xForces[i], yForces[i]);
		 	}
			/* draw starfield.jpg as the backgound */
		 	StdDraw.picture(0, 0, backgound);
		 	//Draw all of the planets.
		 	for (int i=0; i < allplanets.length; i++){
				allplanets[i].draw();
		 	}
		 	//Pause the animation for 10 milliseconds 
		 	StdDraw.show(3);
		 }
		 

	}

	// static function. Read the second number planets the path of planets.txt
	public static double readRadius(String planets_path){
		In planets = new In(planets_path);
		int number_of_planets;
		double radius = 0.0;
		String line;
		while((planets.hasNextLine()) && (radius==0.0)){
			line = planets.readLine();
			if(line.length() == 0) {
				continue;
			}
			number_of_planets = planets.readInt();
			//number_of_planets = Integer.parseInt(line);
			line = planets.readLine();
			if(line.length() == 0) {
				continue;
			}
			//radius = planets.readDouble();
			radius = Double.parseDouble(line);
		}
		planets.close();

		return radius;
	}

	// static function. Return an array of five planets from planets.txt
	public static Planet[] readPlanets(String planets_path){
		In planets = new In(planets_path);
		int number_of_planets;
		double radius;
		number_of_planets = planets.readInt();
		radius = planets.readDouble();

		double xxPos, yyPos, xxVel, yyVel, mass;
		String planet_name;
		//class_name[] array_name = new class_name[array_length];
		Planet[] planet_array = new Planet[number_of_planets];
		for (int i = 0; i<number_of_planets; i++){
			
			xxPos = planets.readDouble();
			yyPos = planets.readDouble();
			xxVel = planets.readDouble();
			yyVel = planets.readDouble();
			mass = planets.readDouble();
			planet_name = planets.readString();
	
			planet_array[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, "./images/"+planet_name);
		}

		planets.close();

		return planet_array;
	}

}
