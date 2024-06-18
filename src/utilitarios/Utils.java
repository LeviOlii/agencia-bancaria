package utilitarios;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Utils {

	static NumberFormat formatandoValores = new DecimalFormat("R$ #,##0.00");
	static NumberFormat formatandoID = new DecimalFormat("0000");
	
	public static String doubleToString (Double valor) {
		return formatandoValores.format(valor);
	}
	
	public static String longToString(long valor) {
		return formatandoID.format(valor);
	}
}
