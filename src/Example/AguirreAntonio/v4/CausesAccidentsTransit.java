package Example.AguirreAntonio.v4;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class CausesAccidentsTransit {

	String nombre;
	String diaSem;
	int hora;
	String causa;
	static ArrayList<Accidentes> accidentes= new ArrayList<>();


	private static void printArrayOfStrings(String [] aS){
		
		for(String s:aS){
			System.out.print(String.format("%15s",s));
		}
		System.out.println();
		
	}

	private static void printArrayOfStringsIncludingFieldIndex(String [] aS){
		
		int i=0;
		for(String s:aS){
			System.out.print(String.format("%2d %15s",(i++),s));
		}
		System.out.println();
		
	}
	

	private static int dayOfWeek(String dW){
		
		//System.out.println(dW);
		
		if(dW.equals("Dl")){
			return 1;
		}else if(dW.equals("Dm")){
			return 2;
		}else if(dW.equals("Dc")){
			return 3;
		}else if(dW.equals("Dj")){
			return 4;
		}else if(dW.equals("Dv")){
			return 5;
		}else if(dW.equals("Ds")){
			return 6;
		}else if(dW.equals("Dg")){
			return 7;
		}else{
			return -1;
		}
		
	}
	

	/**
	 * 
	 * @param inputFile				Example "data/in/f.csv"		might contain unsorted data
	 * @param outputDirectory		Directory where output files are written . Example "data/out"
	 * @param cause					Example "Excés de velocitat o inadequada"
	 * @param fieldSeparator
	 * @param charToSurroundField
	 * @return
	 */
	public static boolean csvFile(String inputFile, 
			String outputDirectory,   
			String cause,
			char fieldSeparator, char charToSurroundField) {

		String line;
		boolean oneLine = false;
		int numColum = 0;
		Accidentes accidente;

		boolean oneTime = false;
		boolean notDates = false;
		int countErr = 0;
		File logFile = null;
		int comillas=0;
		BufferedWriter writer = null;
		int[][] cont = new int[24][7];
		int[][] contA0 = new int[24][7];


		//Le damos el nombre del archivo y añadimos el contenido
		String oldName = "";
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			int n = 0;
			while ((line = br.readLine()) != null) {
				if (oneLine) {
					if (!line.isEmpty()) {
						//Le añado "" para que el char sea un String
						String [] lineArray;

//						Esto controla si existe  " en la linea
//						if(line.contains(""+charToSurroundField)){
//						}
						lineArray = line.split("" + fieldSeparator);
						if(lineArray.length==numColum){
							line.concat(lineArray[16]+lineArray[17]);
							line.concat(lineArray[14]+lineArray[15]);
							new Accidentes(lineArray);

						}else if(lineArray.length>=numColum){
							line.concat(lineArray[17]+lineArray[18]);
							line.concat(lineArray[15]+lineArray[16]);
							line.concat(lineArray[4]+lineArray[5]);
						}
						else{
								notDates = true;
								countErr++;
								//Si queremos observar los archivos desconocidos
//								System.err.println(""+lineArray[0]
//										+lineArray[1] +"\t"
//										+lineArray[2]+"\t"
//										+lineArray[3]+"\t"
//										+lineArray[4]+"\t"
//										+lineArray[5]+"\t"
//										+lineArray[6]+"\t"
//										+lineArray[7]+"\t"
//										+lineArray[8]+"\t"
//										+lineArray[9]+"\t"
//										+lineArray[10]+"\t"
//										+lineArray[11]+"\t"
//										+lineArray[12]+"\t"
//										+lineArray[13]+"\t"
//										+lineArray[14]+"\t"
//										+lineArray[15]+"\t");
						}
					}
					//Nos aseguramos que empieze a leer en la cuarta linea
				} else {
					++n;
					if (n >= 4) {
						String[] la = line.split("" + fieldSeparator);
						numColum = la.length+2;
						oneLine = true;
					}
				}
			}
			if(notDates)System.err.println("Existen "+countErr+" lineas de texto con datos desconocidos ");
			Collections.sort(Accidentes.accidentes);

			for(int i =0; i<Accidentes.accidentes.size();i++){
				if(!oldName.equals(Accidentes.accidentes.get(i).getNombre())){
					if(oneTime){
						for(int j = 0; j<cont.length ;j++){

							writer.write(""+'\n'+j+"h"+'\t');
							for (int h = 0; h <cont[j].length; h++) {
								writer.write(""+cont[j][h]+'\t');

							}
						}
					writer.close();
						//He creado otro array a 0 para cambiar el bucle por una asignación para que sea mas eficiente
						cont = contA0;
//						for(int j = 1; j<cont.length ;j++){
//							for (int h = 0; h <cont.length; h++) {
//								cont[j][h]=0;
//							}
//						}
					}else{
						oneTime=true;
					}
					oldName=Accidentes.accidentes.get(i).getNombre();
					logFile = new File(outputDirectory + "/" + Accidentes.accidentes.get(i).getNombre());
					writer = new BufferedWriter(new FileWriter(logFile));
					//Mostramos que se crean correctamente los archivos .csv
						writer.write(""+'\t'+"Dl"+'\t'+"Dm"+'\t'+"Dc"+'\t'+"Dj"+'\t'+"Dv"+'\t'+"Ds"+'\t'+"Dg");
					writer.write('\n');
					System.out.println(logFile.getCanonicalPath());
				}else{
					cont[Accidentes.accidentes.get(i).getHora()][dayOfWeek(Accidentes.accidentes.get(i).getDiaSem())-1]++;
				}

			}
			writer.close();
//			Collections.sort(AguirreAntonio.v4.Accidentes.accidentes);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}


}
