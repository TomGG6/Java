package manager;
import ex.api.AnalysisService;
import java.util.ServiceLoader;

public class AlgorithmsLoader {
    ServiceLoader<AnalysisService> serviceLoader;
    AnalysisService analysisMedian, analysisDeviation;

    public AlgorithmsLoader(){
        ServiceLoader<AnalysisService> serviceLoader = ServiceLoader.load(AnalysisService.class);
        for (AnalysisService service : serviceLoader) {
            if(service.getName().equals("Mediana")){
                this.analysisMedian = service;
            }
            else{this.analysisDeviation = service;}
        }
    }

    public AnalysisService getAnalysisMedian() {
        return analysisMedian;
    }

    public AnalysisService getAnalysisDeviation() {
        return analysisDeviation;
    }
}
