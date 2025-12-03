package app;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import model.PruebaMolecular;
import structures.ListaEnlazada;
import utils.Buscador;
import utils.DataStore;
import utils.ExportCSV;
import utils.PruebaTableModel;
import io.CSVReader;

public class FormBusquedas extends JPanel {

    private JButton btnCargar, btnBuscar, btnOrdenarEdad, btnExportar;
    private JComboBox<String> cbCampo;
    private JTextField tfValor;
    private JTable tabla;
    private ListaEnlazada<PruebaMolecular> datos;

    public FormBusquedas() {

        setLayout(new BorderLayout());
        JPanel arriba = new JPanel();

        btnCargar = new JButton("Cargar CSV");
        cbCampo = new JComboBox<>(new String[]{"uuid", "fecha", "institucion", "ubigeo", "departamento", "resultado"});
        tfValor = new JTextField(12);
        btnBuscar = new JButton("Buscar");
        btnOrdenarEdad = new JButton("Ordenar por Edad");
        btnExportar = new JButton("Exportar CSV");

        arriba.add(btnCargar);
        arriba.add(new JLabel("Campo:"));
        arriba.add(cbCampo);
        arriba.add(new JLabel("Valor:"));
        arriba.add(tfValor);
        arriba.add(btnBuscar);
        arriba.add(btnOrdenarEdad);
        arriba.add(btnExportar);

        add(arriba, BorderLayout.NORTH);

        tabla = new JTable();
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        btnCargar.addActionListener(e -> cargarCSV());
        btnBuscar.addActionListener(e -> buscar());
        btnOrdenarEdad.addActionListener(e -> ordenarPorEdad());
        btnExportar.addActionListener(e -> exportar());
    }

    private void cargarCSV() {
        JFileChooser jfc = new JFileChooser();
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            datos = CSVReader.cargarCSV(jfc.getSelectedFile().getAbsolutePath());
            DataStore.getInstance().setPruebas(datos);
            actualizarTabla(datos);
            JOptionPane.showMessageDialog(this, "Cargados " + datos.tamaño() + " registros");
        }
    }

    private void buscar() {
        if (datos == null) {
            JOptionPane.showMessageDialog(this, "Primero cargue un archivo CSV");
            return;
        }
        String campo = (String) cbCampo.getSelectedItem();
        String valor = tfValor.getText();

        ListaEnlazada<PruebaMolecular> res = Buscador.buscarPorCampo(datos, campo, valor);
        actualizarTabla(res);
    }

    private void ordenarPorEdad() {
        if (datos == null) return;

        ListaEnlazada<PruebaMolecular> ord = utils.Ordenador.mergeSortByEdad(datos);
        actualizarTabla(ord);
    }

    private void exportar() {
        if (datos == null) return;

        JFileChooser jfc = new JFileChooser();
        if (jfc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            String ruta = jfc.getSelectedFile().getAbsolutePath();
            boolean ok = ExportCSV.exportar(datos, ruta);
            JOptionPane.showMessageDialog(this, ok ? "Exportado correctamente" : "Error exportando");
        }
    }

    private void actualizarTabla(ListaEnlazada<PruebaMolecular> lista) {
        tabla.setModel(new PruebaTableModel(lista));
        tabla.setRowSorter(new TableRowSorter<>(tabla.getModel()));
    }
}
