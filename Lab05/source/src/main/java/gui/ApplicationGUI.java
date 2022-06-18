package gui;

import ex.api.AnalysisException;
import ex.api.AnalysisService;
import ex.api.DataSet;
import manager.AlgorithmsLoader;
import manager.CSVReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ApplicationGUI implements ActionListener {
    DataSet data;
    CSVReader dataReader;
    AlgorithmsLoader serviceLoader;
    AnalysisService algorithm;

    private final JFrame frame;
    private JPanel panel;
    private JTable table;
    private JScrollPane scroll;
    private JLabel fileNameLabel, algorithmsLabel;
    private JTextField fileNameField;
    private JButton loadFileButton, runAlgorithmButton;
    private JList algorithmsList;

    public ApplicationGUI() throws IOException {
        dataReader = new CSVReader();
        serviceLoader = new AlgorithmsLoader();

        frame = new JFrame("Lab05");
        frame.setSize(400, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        fileNameLabel = new JLabel("Nazwa pliku:");
        fileNameLabel.setBounds(10, 10, 100, 25);
        panel.add(fileNameLabel);

        fileNameField = new JTextField();
        fileNameField.setBounds(85, 10, 150, 25);
        panel.add(fileNameField);

        loadFileButton = new JButton("Załaduj");
        loadFileButton.setBounds(250, 10, 100, 25);
        loadFileButton.addActionListener(this);
        panel.add(loadFileButton);

        algorithmsLabel = new JLabel("Algorytmy analizy skupień");
        algorithmsLabel.setBounds(10, 50, 150, 25);
        panel.add(algorithmsLabel);

        algorithmsList = new JList(new String[]{"Mediana", "Odchylenie standardowe"});
        algorithmsList.setBounds(10, 80, 150, 45);
        algorithmsList.setSelectedIndex(0);
        panel.add(algorithmsList);

        runAlgorithmButton = new JButton("Wykonaj");
        runAlgorithmButton.setBounds(10, 130, 100, 25);
        runAlgorithmButton.addActionListener(this);
        panel.add(runAlgorithmButton);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == loadFileButton){
            try {
                loadFileAction();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if(source == runAlgorithmButton){
            try {
                runAlgorithmAction();
            } catch (AnalysisException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void loadFileAction() throws IOException {
        data = new DataSet();
        data = dataReader.read(fileNameField.getText(), data);
        fileNameField.setText("");
        if(scroll != null){
            panel.remove(scroll);
        }
        table = new JTable(data.getData(), data.getHeader());
        scroll = new JScrollPane(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scroll.setBounds(10, 180, 170, 550);
        panel.add(scroll);
        panel.repaint();
        panel.validate();
    }

    public void runAlgorithmAction() throws AnalysisException {
        if(algorithmsList.getSelectedValue().equals("Mediana")){
            algorithm = serviceLoader.getAnalysisMedian();
        }else if(algorithmsList.getSelectedValue().equals("Odchylenie standardowe")){
            algorithm = serviceLoader.getAnalysisDeviation();
        }

        algorithm.submit(data);
        data = algorithm.retrieve(false);

        panel.remove(scroll);
        table = new JTable(data.getData(), data.getHeader());
        scroll = new JScrollPane(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scroll.setBounds(10, 180, 170, 550);
        panel.add(scroll);
        panel.repaint();
        panel.validate();
    }

    public static void main(String[] args) throws IOException{
        new ApplicationGUI();
    }
}
