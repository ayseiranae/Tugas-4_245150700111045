import java.text.NumberFormat;
import java.util.Locale;

class Typography {

    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    
    public class Color {
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String RESET = "\u001B[0m";
    }

    class RpF {
        public static final Locale RUPIAH = new Locale.Builder().setLanguage("id").setRegion("ID").build();
        public static final NumberFormat IND = NumberFormat.getCurrencyInstance(RUPIAH);
    }
    
    public void justify(String label, String value) {
        int width = 60;
        int separator = 15;
        System.out.print(String.format("%-" + separator + "s: %" + (width - separator - 2) + "s\n", label, value));
    }

    public static void center(String text) {
        String plainText = text.replaceAll("\u001B\\[[;\\d]*m", "");
        int tengah = (60 - plainText.length()) / 2;
        for (int i = 0; i < tengah; i++) {
            System.out.print(" ");
        }
        System.out.println(text);
    }    
}