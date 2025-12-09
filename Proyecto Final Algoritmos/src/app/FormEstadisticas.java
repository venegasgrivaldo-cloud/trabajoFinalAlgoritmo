package app;

import javax.swing.*;
import java.awt.*;
import io.CSVReader;
import model.PruebaMolecular;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import structures.ListaEnlazada;
import structures.Nodo;
import utils.DataStore;

/**
 * FormEstadisticas corregido: genera totales por departamento sin usar
 * colecciones de Java; usa listas enlazadas propias.
 */
public class FormEstadisticas extends JPanel {

    private JButton btnCargar, btnGenerar;
    private JTable tabla;
    private ListaEnlazada<PruebaMolecular> datos;

    public FormEstadisticas() {

        setLayout(new BorderLayout());
        JPanel arriba = new JPanel();

        btnCargar = new JButton("Cargar CSV");
        btnGenerar = new JButton("Generar Estadísticas");

        arriba.add(btnCargar);
        arriba.add(btnGenerar);

        add(arriba, BorderLayout.NORTH);

        tabla = new JTable();
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnCargar.addActionListener(e -> cargarCSV());
        btnGenerar.addActionListener(e -> generar());
    }

    private void cargarCSV() {
        JFileChooser jf = new JFileChooser();
        if (jf.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            datos = CSVReader.cargarCSV(jf.getSelectedFile().getAbsolutePath());
            DataStore.getInstance().setPruebas(datos);
            JOptionPane.showMessageDialog(this, "Cargados " + datos.tamaño() + " registros");
        }
    }

    /**
     * Genera la estadística: Totales por Departamento usando solo estructuras
     * propias (ListaEnlazada).
     */
    private void generar() {
        if (datos == null || datos.tamaño() == 0) {
            JOptionPane.showMessageDialog(this, "Cargue primero un CSV con datos.");
            return;
        }

        // Nodo para acumulación (lista simple de pares)
        class NodoPar {

            String departamento;
            int contador;
            NodoPar siguiente;

            NodoPar(String d) {
                departamento = (d == null ? "DESCONOCIDO" : d);
                contador = 1;
                siguiente = null;
            }
        }

        NodoPar head = null;

        // recorrer datos y acumular conteos por departamento
        Nodo<PruebaMolecular> aux = datos.getCabeza();
        while (aux != null) {
            String dpto = aux.dato.getDepartamento();
            if (dpto == null || dpto.trim().isEmpty()) {
                dpto = "DESCONOCIDO";
            }

            // buscar en la lista de pares
            NodoPar t = head;
            boolean encontrado = false;
            while (t != null) {
                if (t.departamento.equalsIgnoreCase(dpto)) {
                    t.contador++;
                    encontrado = true;
                    break;
                }
                t = t.siguiente;
            }
            if (!encontrado) {
                NodoPar nuevo = new NodoPar(dpto);
                nuevo.siguiente = head;
                head = nuevo;
            }
            aux = aux.siguiente;
        }

        // contar número de filas
        int filas = 0;
        NodoPar tmp = head;
        while (tmp != null) {
            filas++;
            tmp = tmp.siguiente;
        }

        // preparar matriz para JTable
        String[][] m = new String[filas][2];
        tmp = head;
        int i = 0;
        while (tmp != null) {
            m[i][0] = tmp.departamento;
            m[i][1] = String.valueOf(tmp.contador);
            tmp = tmp.siguiente;
            i++;
        }

        //Usando jfreechard
        DefaultPieDataset datosTabla = new DefaultPieDataset();

        tmp = head;
        while (tmp != null) {
            datosTabla.setValue(tmp.departamento, tmp.contador);
            tmp = tmp.siguiente;
        }
        JFreeChart chart = org.jfree.chart.ChartFactory.createPieChart(
                "Distribución de Pruebas por Departamento", // título
                datosTabla, // dataset
                true, // leyenda
                true, // tooltips
                false // URLs
        );

        ChartPanel panelGrafico = new ChartPanel(chart);
        panelGrafico.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(this, panelGrafico, "Gráfico", JOptionPane.PLAIN_MESSAGE);

        // mostrar (sin ordenar): si quieres ordenarlo, podemos aplicar un ordenamiento sobre la matriz
        tabla.setModel(new javax.swing.table.DefaultTableModel(
                m,
                new String[]{"Departamento", "Total Pruebas"}
        ));

    }
}
