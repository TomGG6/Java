package ex.api;

import java.util.Arrays;

public class Impl01 implements AnalysisService {
    DataSet currentData;

    @Override
    public void setOptions(String[] options) throws AnalysisException {

    }

    @Override
    public String getName() {
        return "Mediana";
    }

    @Override
    public void submit(DataSet ds) {
        this.currentData = ds;
    }

    @Override
    public DataSet retrieve(boolean clear) {

            int dataSize = currentData.getSize();
            int[] dataAsInteger = new int[dataSize];
            for (int i = 1; i < dataSize; i++) {

                dataAsInteger[i] = Integer.parseInt(currentData.getData()[i][0]);
            }
            Arrays.sort(dataAsInteger);
            double result;
            dataSize--;

            if (dataSize % 2 == 1) {
                result = dataAsInteger[(dataSize + 1) / 2];
            } else {
                result = Double.valueOf(dataAsInteger[dataSize / 2] + dataAsInteger[(dataSize + 1) / 2]) / 2;
            }

            String[][] newData = new String[dataSize + 1][2];
            for (int i = 0; i < dataSize + 1; i++) {
                newData[i][1] = Double.toString(result);
                newData[i][0] = currentData.getData()[i][0];
            }
            currentData.setData(newData);
            return currentData;

    }
}
