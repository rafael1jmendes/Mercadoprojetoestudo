package utility;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class Utilitys {
    static NumberFormat numberFormat =
            new DecimalFormat("R$ ##,###.##", new DecimalFormatSymbols(new Locale ("pt", "BR")));

    public static String doubleToString(Double value) {
        return numberFormat.format(value);
    }
}
