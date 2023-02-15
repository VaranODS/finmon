import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

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

    private static String parseFio(String fio) {
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

    private static char[] toFirstUpperCase(String component) {
        String componentLowerCase = component.toLowerCase();
        char[] result = componentLowerCase.toCharArray();
        result[0] = String.valueOf(result[0]).toUpperCase().charAt(0);
        return result;
    }

    public static void main(String[] args) throws IOException {
        List<FinMon> terroristList = new ArrayList<FinMon>();
        File file = new File("C:\\fm_data\\fm_data.csv");
        if (!file.exists()) {
            System.out.printf("файла с именем %s не существует\n", file.getPath());
            System.exit(1);
        }
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        boolean firstLine = true;
        StringBuilder inputData = new StringBuilder(reader.readLine());
        while (inputData != null) {
            if (firstLine) {
                firstLine = false;
                inputData.delete(0, inputData.length() - 1).append(reader.readLine());
                continue;
            }
            String[] dataItems = inputData.toString().split(";");
            if (dataItems.length < 7) {
                inputData.append(reader.readLine());
                dataItems = inputData.toString().split(";");
                if (dataItems.length < 7) {
                    inputData.append(reader.readLine());
                    dataItems = inputData.toString().split(";");
                    if (dataItems.length < 7) {
                        System.out.println("Неверный формат данных в строке " + dataItems[0]);
                        System.exit(255);
                    }
                }

            }
            String fio = parseFio(dataItems[1]);
            System.out.println("fio = " + fio);
            inputData.delete(0, inputData.length() - 1).append(reader.readLine());
        }

    }
}



