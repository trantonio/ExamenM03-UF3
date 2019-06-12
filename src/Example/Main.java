package Example;

import Example.AguirreAntonio.v4.CausesAccidentsTransit;

public class Main {

	public static void main(String[] args) {

		CausesAccidentsTransit.csvFile(
				"data/in/2015_ACCIDENTS_CAUSES_GU_BCN_2015 v2.csv",
				"data/out",
				"Excés de velocitat o inadequada",
				',',
				'"');

		
	}

}
