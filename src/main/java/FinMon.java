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

    public FinMon() {
    }

    private static String parseFio(String fio) {
        String[] components = fio.replace("\"", "").split(" ");
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
        List<FinMon> terroristList = new ArrayList<>();
        File file = new File("C:\\fm_data\\fm_data.csv");
        if (!file.exists()) {
            System.out.printf("файла с именем %s не существует\n", file.getPath());
            System.exit(1);
        }
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        boolean firstLine = true;
        String fileData = reader.readLine();
        StringBuilder inputData = new StringBuilder(fileData);
        StringBuilder line;
        int count, index;
        while (fileData != null) {
            if (firstLine) {
                firstLine = false;
                fileData = reader.readLine();
                inputData = new StringBuilder(fileData);
                continue;
            }
            String[] dataItems = inputData.toString().split(";");
            if (dataItems.length < 4) {
                count = 6; //Количество попыток дочитать строку
                while (count > 0) {
                    fileData = reader.readLine();
                    line = new StringBuilder(fileData);
                    index = line.indexOf("\"");
                    if (index != -1) { //встретили закрывающуюся кавычку
                        inputData.append(line.substring(index + 1));
                        dataItems = inputData.toString().split(";");
                        break;
                    } else {
                        index = line.indexOf(";");
                        if (index != -1) { //пошли данные следующего лица
                            System.out.println("Неверный формат данных в строке: " + inputData);
                            inputData = new StringBuilder(line);
                            dataItems = inputData.toString().split(";");
                            break;
                        }
                    }
                    count--;
                }

                if (count == 0) { //не удалось найти логическое окончание данных лица
                    System.out.println("Неверное логическое окончание данных в строке: " + inputData);
                    fileData = reader.readLine();
                    inputData = new StringBuilder(fileData);
                    continue;
                }
            }
            String fio = parseFio(dataItems[1]);
            System.out.println(dataItems[0] + " fio = " + fio);
            fileData = reader.readLine();
            if (fileData == null) break;
            inputData = new StringBuilder(fileData);
        }
        fileReader.close();
        reader.close();
    }
}

//4085;"Тимченко Геннадій Миколайович
//(Тимченко Геннадий Николаевич, TYMCHENKO HENNADII).
//Народився 09 листопада 1952 р. у м. Гюмрі Вірменської Республіки.";;;09.11.1952;1952;
//4594;Бакальчук Владислав Сергійович                                 ;;;24 лютого ;1977;5,02707E+11
//6340 fio = Кір'янова/єгерева/горлач Тетяна Борисівна
//6119 fio = Вінокурова (дів. Лаврова) Катерина Сергіївна
//4854;"Лавренюк Наталія Миколаївна, вона ж Жучкова Світлана Петрівна, 26.02.1975 р.н., громадянка України та Російської Федерації. Зареєстрована за адресою: м. Львів, вул.Івана Франка, 115, кв. 3-А. ІПН – 2745001404. Паспорт громадянки РФ:
//серія 45 19 № 679496. Ідентифікаційний номер платника податків РФ – 402718407534.";;;;;