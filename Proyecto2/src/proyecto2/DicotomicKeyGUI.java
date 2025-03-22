/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author joseph.moreno
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DicotomicKeyGUI extends JFrame {
    private JTextArea questionArea;
    private JTextField searchField;
    private JButton loadButton, searchHashButton, searchTreeButton;
    private ArbolBinario arbol;
    private HashTable tabla;

    public DicotomicKeyGUI() {
        setTitle("Clave Dicotómica");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior para cargar el archivo
        JPanel topPanel = new JPanel();
        loadButton = new JButton("Cargar Clave Dicotómica");
        topPanel.add(loadButton);
        add(topPanel, BorderLayout.NORTH);

        // Área de texto para mostrar preguntas
        questionArea = new JTextArea();
        questionArea.setEditable(false);
        add(new JScrollPane(questionArea), BorderLayout.CENTER);

        // Panel inferior para búsqueda
        JPanel bottomPanel = new JPanel();
        searchField = new JTextField(20);
        searchHashButton = new JButton("Buscar por Hash");
        searchTreeButton = new JButton("Buscar por Árbol");
        bottomPanel.add(searchField);
        bottomPanel.add(searchHashButton);
        bottomPanel.add(searchTreeButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Inicializar estructuras de datos
        arbol = new ArbolBinario();
        tabla = new HashTable();

        // Listeners
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    cargarClaveDicotomica(selectedFile);
                }
            }
        });

        searchHashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombrePlanta = searchField.getText();
                String informacion = tabla.buscar(nombrePlanta);
                if (informacion != null) {
                    questionArea.setText("Información de la planta: " + informacion);
                } else {
                    questionArea.setText("Planta no encontrada.");
                }
            }
        });

        searchTreeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombrePlanta = searchField.getText();
                String resultado = buscarEnArbol(nombrePlanta);
                questionArea.setText(resultado);
            }
        });
    }

    private void cargarClaveDicotomica(File file) {
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file));
            for (Object key : jsonObject.keySet()) {
                String nombreClave = (String) key;
                JSONArray especies = (JSONArray) jsonObject.get(nombreClave);
                for (Object especie : especies) {
                    JSONObject especieObj = (JSONObject) especie;
                    for (Object nombreEspecie : especieObj.keySet()) {
                        String nombre = (String) nombreEspecie;
                        JSONArray preguntas = (JSONArray) especieObj.get(nombre);
                        for (Object pregunta : preguntas) {
                            JSONObject preguntaObj = (JSONObject) pregunta;
                            for (Object preguntaText : preguntaObj.keySet()) {
                                String preguntaStr = (String) preguntaText;
                                boolean respuesta = (Boolean) preguntaObj.get(preguntaText);
                                // Insertar en la tabla hash
                                tabla.insertar(nombre, preguntaStr);
                                // Insertar en el árbol binario
                                arbol.add(nombre.hashCode(), arbol.getRaiz());
                            }
                        }
                    }
                }
            }
            questionArea.setText("Clave dicotómica cargada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
            questionArea.setText("Error al cargar la clave dicotómica.");
        }
    }

    private String buscarEnArbol(String nombrePlanta) {
        NodoArbol nodo = arbol.buscarInorder(nombrePlanta.hashCode(), arbol.getRaiz());
        if (nodo != null) {
            return "Planta encontrada: " + nombrePlanta;
        } else {
            return "Planta no encontrada en el árbol.";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DicotomicKeyGUI().setVisible(true);
            }
        });
    }
}