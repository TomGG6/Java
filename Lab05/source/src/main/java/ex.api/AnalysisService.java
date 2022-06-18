package ex.api;

public interface AnalysisService {
    void setOptions(String[] options) throws AnalysisException;
    String getName();
    void submit(DataSet ds) throws AnalysisException;
    DataSet retrieve(boolean clear) throws AnalysisException;
}
