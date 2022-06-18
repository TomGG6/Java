package ex.api;

public class Impl02 implements AnalysisService {
    DataSet currentData;

    @Override
    public void setOptions(String[] options) throws AnalysisException {

    }

    @Override
    public String getName() {
        return "Odchylenie standardowe";
    }

    @Override
    public void submit(DataSet ds) throws AnalysisException {
        this.currentData = ds;
    }

    @Override
    public DataSet retrieve(boolean clear) throws AnalysisException {
        int dataSize = currentData.getSize();
        int[] dataAsInteger = new int[dataSize];
        for (int i = 0; i < dataSize; i++) {
            dataAsInteger[i] = Integer.parseInt(currentData.getData()[i][0]);
        }

        double average = 0, squareSum = 0, deviation = 0;
        for(int i = 0; i < dataSize; i++){
            average += Double.valueOf(dataAsInteger[i]);
        }

        average /= dataSize;
        for(int i = 0; i < dataSize; i++){
            squareSum += Math.pow((Double.valueOf(dataAsInteger[i]) - average), 2);
        }

        squareSum /= dataSize;

        deviation = Math.sqrt(squareSum);

        String[][] newData = new String[dataSize + 1][2];

        for (int i = 0; i < dataSize; i++) {
            newData[i][1] = Double.toString(deviation);
            newData[i][0] = currentData.getData()[i][0];
        }
        currentData.setData(newData);
        return currentData;
    }
}
