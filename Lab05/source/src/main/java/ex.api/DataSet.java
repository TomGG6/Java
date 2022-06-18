package ex.api;

public class DataSet {
    private String[] header = {};
    private String[][] data = {{}};
    private int size;

    private <T> T[][] deepCopy(T[][] matrix) {
        return java.util.Arrays.stream(matrix).map(el -> el.clone()).toArray(i -> matrix.clone());
    }

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header.clone();
    }

    public String[][] getData() {
        return data;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int s){
        this.size = s;
    }

    public void setData(String[][] data) {
        this.data = deepCopy(data);
    }
}
