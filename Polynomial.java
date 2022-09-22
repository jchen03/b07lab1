public class Polynomial {
	double[] coefficients;
	public Polynomial() {
		coefficients = new double[1];
		coefficients[0] = 0;
	}
	public Polynomial(double[] array) {
		coefficients = new double[array.length];
		for(int i = 0; i < array.length; i++) {
			coefficients[i] = array[i];
		}
	}	
	public Polynomial add(Polynomial poly) {
		if(poly.coefficients.length < coefficients.length) {
			Polynomial sum = new Polynomial(coefficients);
			for(int i = 0; i < poly.coefficients.length; i++) {
				sum.coefficients[i] = sum.coefficients[i] + poly.coefficients[i];
			}
			return sum;
		}
		for(int i = 0; i < coefficients.length; i++) {
			poly.coefficients[i] = poly.coefficients[i] + coefficients[i];
		}
		return poly;
	}
	public double evaluate(double num) {
		double counter = coefficients[0];
		if(coefficients.length <= 1) {
			return coefficients[0];
		}
		for(int i = 1; i < coefficients.length; i++) {
				counter = counter + coefficients[i] * Math.pow(num, i);
		}
		return counter;
		
	}
	public boolean hasRoot(double num) {
		if(evaluate(num) == 0) {
			return true;
		}
		return false;
	}
	
}