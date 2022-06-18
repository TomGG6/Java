package manager;

import ex.api.DataSet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {

    public DataSet read(String fileName, DataSet data) throws IOException {
        String row;
        ArrayList<String[]> dataCSV = new ArrayList<>();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(fileName))) {
            if ((row = csvReader.readLine()) != null) {
                String[] newHeaders = row.split(";");
                data.setHeader(newHeaders);
            }

            int index = 0;
            while ((row = csvReader.readLine()) != null) {
                String[] record = row.split(";");
                dataCSV.add(record);
            }

            data.setSize(dataCSV.size());
            String[][] preparedData = new String[data.getSize()][];
            for(int i = 0; i < data.getSize(); i++){
                preparedData[i] = dataCSV.get(i);
            }

            data.setData(preparedData);
        }
        return data;
    }
}
