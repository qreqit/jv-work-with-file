package core.basesyntax;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
    public void getStatistic(String fromFileName, String toFileName) {
        String[] arrayStringOfFile = getDataFromFile(fromFileName);
        String resultOfTable = new String();
        int supply = 0;
        int buy = 0;

        for (int i = 0; i < arrayStringOfFile.length; i++) {
            switch (arrayStringOfFile[i]) {
                case "supply": supply += Integer.parseInt(arrayStringOfFile[i + 1]);
                break;
                case "buy": buy += Integer.parseInt(arrayStringOfFile[i + 1]);
                break;
                default: break;
            }
        }

        resultOfTable = "supply," + supply + "\n"
                + "buy," + buy + "\n"
                + "result," + (supply - buy);

        writeToFile(toFileName, resultOfTable);
    }

    public String[] getDataFromFile(String fromFileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fromFileName))) {
            StringBuilder fileInStringBuilder = new StringBuilder();
            String value = bufferedReader.readLine();

            while (value != null) {
                fileInStringBuilder.append(" ").append(value);
                value = bufferedReader.readLine();
            }

            return fileInStringBuilder.toString().split(",|\\R|\\W+");
        } catch (IOException e) {
            throw new RuntimeException("Can not read the file:", e);
        }
    }

    public void writeToFile(String fileNameToWrite, String stringToWriteIntoFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileNameToWrite))) {
            bufferedWriter.write(stringToWriteIntoFile);
        } catch (IOException e) {
            throw new RuntimeException("Can't write data to file: ", e);
        }
    }
}
