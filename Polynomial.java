import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Polynomial {
	double[] coefficients;
	int [] degrees;
	public Polynomial() {
		coefficients = new double[1];
		coefficients[0] = 0;
	}
	public Polynomial(double[] array, int[] array2) {
		coefficients = new double[array.length];
		degrees = new int[array2.length];
		for(int i = 0; i < array.length; i++) {
			coefficients[i] = array[i];
		}
		for(int j = 0; j < array2.length; j++) {
			degrees[j] = array2[j];
		}
	}	
	public Polynomial(File file) {
		try {
			Scanner scanner = new Scanner(file);
			String poly = scanner.nextLine();
			scanner.close();
			String [] splitplus = poly.split("\\+");
			String [] splitminus;
			String [] splitx;
			int index = 0;
			boolean first = true;
			int [] temp_degrees = new int[poly.length()];
			double [] temp_coefficients = new double[poly.length()];
				
			for(String s : splitplus) {
				if(s.indexOf('-') != -1) {
					splitminus = s.split("-");
					for(int i = 0; i < splitminus.length; i++) {
						if(splitminus[i].equals("")) {
							i++;
							first = false;
						}
						if(splitminus[i].indexOf('x') == -1) { //degree is 0
							if(i != 0 || first == false) { //negative
								temp_degrees[index] = 0;
								temp_coefficients[index] = Double.parseDouble(splitminus[i]) * -1;
								index++;
							}else { //not negative
								temp_degrees[index] = 0;
								temp_coefficients[index] = Double.parseDouble(splitminus[i]);
								index++;
							}
						}else { //degree >= 1 
							if(i != 0 || first == false) { //negative
								if(splitminus[i].equals("x")) {
									temp_degrees[index] = 1;
									temp_coefficients[index] = -1;
									index++;
									continue;
								}
								splitx = splitminus[i].split("x");
								if(splitx.length == 1) { //degree of 1
									temp_degrees[index] = 1;
								}else {
									temp_degrees[index] = Integer.parseInt(splitx[1]);
								}
								if(splitx[0].equals("")) { //coefficient 1
									temp_coefficients[index] = -1;
								}else {
									temp_coefficients[index] = Double.parseDouble(splitx[0]) * -1 ;
								}
								index++;
							}else { //non negative
								
								if(splitminus[i].equals("x")) {
										temp_degrees[index] = 1;
										temp_coefficients[index] = 1;
										index++;
										continue;
								}
								splitx = splitminus[i].split("x");
								if(splitx.length == 1) { //degree of 1
									temp_degrees[index] = 1;
								}else {
									temp_degrees[index] = Integer.parseInt(splitx[1]);
								}
								if(splitx[0].equals("")) { //coefficient 1
									temp_coefficients[index] = 1;
								}else {
									temp_coefficients[index] = Double.parseDouble(splitx[0]);
								}
								index++;
							}
						}	
					}
				}else { //no -'s
					if(s.indexOf("x") == -1) { //degree is 0
						temp_degrees[index] = 0;
						temp_coefficients[index] = Double.parseDouble(s);
						index++;
					}else {
						splitx = s.split("x");
						if(splitx.length == 1) { //degree of 1
							temp_degrees[index] = 1;
						}else {
							temp_degrees[index] = Integer.parseInt(splitx[1]);
						}
						if(splitx[0].equals("")) {
							temp_coefficients[index] = 1;
						}else {
							temp_coefficients[index] = Double.parseDouble(splitx[0]);
						}
						index++;
					}
				}
				
			}
			coefficients = new double[index];
			degrees = new int [index];
			for(int i = 0; i < index; i++) {
				coefficients[i] = temp_coefficients[i];
				degrees[i] = temp_degrees[i];
			}
		}catch(FileNotFoundException e) {
			System.out.println("Error: File Not Found");
			e.printStackTrace();
		}
		
	}
	
	public Polynomial add(Polynomial poly) {
		//find length of new arrays
		int mutual = 0;
		for(int i = 0; i < degrees.length; i++) {
			for(int j = 0; j < poly.degrees.length; j++) {
				if(degrees[i] == poly.degrees[j]) {
					mutual++;
					break;
				}
			}
		}
		
		int index = 0;
		boolean track = true;
		int [] new_degrees = new int[poly.degrees.length + degrees.length - mutual];
		double [] new_coefficients = new double[poly.coefficients.length + coefficients.length - mutual];
		
		for(int i = 0; i < degrees.length; i++) { //insert this into new arrays
			new_degrees[index] = degrees[i];
			new_coefficients[index] = coefficients[i];
			index++;
		}
		
		for(int i = 0; i < poly.degrees.length; i++) { //insert poly.degrees into new_degrees
			track = true;
			for(int j = 0; j < degrees.length; j++) { //check if poly.degrees is in new_degrees
				if(degrees[j] == poly.degrees[i]) { //if it is in both arrays, then update value in new array
					new_coefficients[j] = new_coefficients[j] + poly.coefficients[i];
					track = false;
					break;
				}
			}
			if(track == true) {
				new_degrees[index] = poly.degrees[i];
				new_coefficients[index] = poly.coefficients[i];
				index++;
			}
		}
		Polynomial sum = new Polynomial(new_coefficients, new_degrees);
		return sum;

	}
	
	public double evaluate(double num) {
		double counter = 0;
		for(int i = 0; i < degrees.length; i++) {
			counter = counter + (coefficients[i] * Math.pow(num, degrees[i]));
		}
		return counter;
	}
	
	public boolean hasRoot(double num) {
		if(evaluate(num) == 0) {
			return true;
		}
		return false;
	}
	
	public Polynomial multiply(Polynomial poly) {
		int index = 0;
		boolean check = true;
		int [] new_degrees = new int[poly.degrees.length * degrees.length];
		double [] new_coefficients = new double[poly.coefficients.length * coefficients.length];
		
		for(int i = 0; i < degrees.length; i++) {
			for(int j = 0; j < poly.degrees.length; j++) {
				check = true;
				for(int k = 0; k < index; k++) { //checks duplicates
					if(degrees[i] + poly.degrees[j] == new_degrees[k]) {
						new_coefficients[k] = new_coefficients[k] + (coefficients[i] * poly.coefficients[j]);
						check = false;
						break;
					}
				}
				if(check == true) { //if no duplicates, add new index to both arrays
					new_degrees[index] = degrees[i] + poly.degrees[j];
					new_coefficients[index] = coefficients[i] * poly.coefficients[j];
					index++;
				}
			}
		}
		
		//get rid of trailing zeros
		int [] final_degrees = new int[index];
		double [] final_coefficients = new double[index];
		
		for(int i = 0; i < index; i++) {
			final_degrees[i] = new_degrees[i];
			final_coefficients[i] = new_coefficients[i];
		}
		Polynomial product = new Polynomial(final_coefficients, final_degrees);
		return product;
		
	}
	
	public void saveToFile(String file_name) {
		try {
			File file = new File(file_name);
			String str = "";
			for(int i = 0; i < coefficients.length; i++) {
				if(coefficients[i] < 0) {
					str = str + "-";
				}else if (coefficients[i] > 0 && i != 0) {
					str = str + "+";
				}
				str = str + coefficients[i];
				if(degrees[i] == 0) {
					continue;
				}
				str = str + "x";
				if(degrees[i] == 1) {
					continue;
				}
				str = str + degrees[i];
			}
			FileWriter writer = new FileWriter(file);
			writer.write(str);
			writer.close();
		}catch(IOException e) {
			System.out.println("Error: IOException");
			e.printStackTrace();
		}
		
		
	}
	
}