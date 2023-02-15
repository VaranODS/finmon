import java.util.Locale;

public class FinMon {
    private String fio;
    private int dayOfBirth;
    private int monthOfBirth;
    private int yearOfBirth;
    private String inn;
    public FinMon(String fio, int dayOfBirth, int monthOfBirth, int yearOfBirth, String inn) {
        this.fio = fio;
        this.dayOfBirth = dayOfBirth;
        this.monthOfBirth = monthOfBirth;
        this.yearOfBirth = yearOfBirth;
        this.inn = inn;
    }

    public FinMon() {}

    private String parseFio(String fio) {
        String[] components = fio.split(" ");
        StringBuilder result = new StringBuilder();
        for (String component : components) {
            if (component.equals("")) {
                continue;
            }
            result.append(toFirstUpperCase(component));
            result.append(" ");
        }
        return result.toString().trim();
    }

    private char[] toFirstUpperCase(String component) {
        String componentLowerCase = component.toLowerCase();
        char[] result = componentLowerCase.toCharArray();
        result[0] = String.valueOf(result[0]).toUpperCase().charAt(0);
        return result;
    }

    public static void main(String[] args) {
        String test = " Иванов  иван    ПЕТРович    ";
        FinMon terrorist = new FinMon();
        System.out.println("!"+terrorist.parseFio(test)+"!");
    }
}



