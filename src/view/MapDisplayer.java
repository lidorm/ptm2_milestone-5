package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

import javafx.scene.canvas.Canvas;
import javafx.stage.FileChooser;

public class MapDisplayer extends Canvas{
	
	static final int MAP_CSV_WIDETH = 16;
	static final int MAP_CSV_HEIGHT = 14;
	double[][] mapMatrix;
	
	public MapDisplayer() {
		
	}

	@SuppressWarnings("static-access")
	public void generateMap(File mapCsvFile) {
		Scanner scanner = null;
		RealMatrix matrix = new Array2DRowRealMatrix(this.MAP_CSV_HEIGHT,this.MAP_CSV_WIDETH);
		StringBuilder b = new StringBuilder();
		
		try {
			
			scanner = new Scanner(mapCsvFile);
			scanner.useDelimiter(",");
			 // Build one string of all the matrix
		      while (scanner.hasNext()) {
		        b.append(scanner.next() + " ");
		      }
		      // Build string array,every string is a vector of the matrix
		      String[] s = b.toString().split("\r\n");
		      int row = 0;
		      for (String string : s) {
		          String[] vectorToSet = string.split(" ");
		          double[] data = new double[vectorToSet.length];
		          int i = 0;
		          for (String val : vectorToSet) {
		            data[i] = (Double.parseDouble(val));
		            i++;
		          }
		          if(string.equals(" ") || string == null)
		        	  break;
		          RealVector v = MatrixUtils.createRealVector(data);
		          matrix.setRowVector(row, v);
		          row++;
		      }
		      this.mapMatrix = matrix.getData();
		      this.paintMap(mapMatrix);
			}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void paintMap(double[][] mapMatrix) {
		
	}
}
