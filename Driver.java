import java.io.File;

public class Driver { 
	 public static void main(String [] args) { 
		 
		 double [] c1 = {1,2,1};
		 int [] d1 = {2, 1, 0};
		 Polynomial p1 = new Polynomial(c1, d1); 
		 for(double x : p1.coefficients) {
			 System.out.println(x); //should print out 1.0,2.0,1.0
		 }
		 System.out.println();
		 double [] c2 = {6,0,0,5};
		 int [] d2 = {3,2,1,0};
		 Polynomial p2 = new Polynomial(c2, d2);
		 Polynomial p3 = p2.add(p1);
		 for(double x : p3.coefficients) {
			 System.out.println(x); //should print out 6.0,1.0,2.0,6.0
		 }
		 System.out.println();
		 for(int x: p3.degrees) {
			 System.out.println(x); //should print out 3,2,1,0
		 }
		 System.out.println();
		 double [] c4 = {5, 3, 8};
		 int [] d4 = {4, 2, 0};
		 Polynomial p4 = new Polynomial(c4, d4);
		 Polynomial p5 = p4.add(p3);
		 
		 for(double x : p5.coefficients) {
			 System.out.println(x); //should print out 5.0, 4.0, 14.0, 6.0, 2.0
		 }
		 System.out.println();
		 for(int x: p5.degrees) {
			 System.out.println(x); //should print out 4, 2, 0, 3, 1
		 }
		 System.out.println();
		 
		 System.out.println(p1.evaluate(3)); //should print out 16.0
		 System.out.println(p1.hasRoot(3)); //should print out false
		 System.out.println(p1.hasRoot(-1)); //should print out true
		 System.out.println();
		 
		 Polynomial m1 = p4.multiply(p5);
		 Polynomial m2 = p5.multiply(p4);
		 
		 for(double x : m1.coefficients) {
			 System.out.println(x); //should print out 5.0, 4.0, 14.0, 6.0, 2.0
		 }
		 System.out.println();
		 for(int x: m1.degrees) {
			 System.out.println(x); //should print out 4, 2, 0, 3, 1
		 }
		 System.out.println();
		 for(double x : m2.coefficients) {
			 System.out.println(x); //should print out 5.0, 4.0, 14.0, 6.0, 2.0
		 }
		 System.out.println();
		 for(int x: m2.degrees) {
			 System.out.println(x); //should print out 4, 2, 0, 3, 1
		 }
		 System.out.println();
		 File f1 = new File("C:\\Jerry\\f1.txt");
		 p4.saveToFile("C:\\Jerry\\f1.txt");
		 Polynomial pf1 = new Polynomial(f1);
		 for(double x: pf1.coefficients) {
			 System.out.println(x);
		 }
		 System.out.println();
		 for(int x: pf1.degrees) {
			 System.out.println(x);
		 }
		 
		 
	 } 
}