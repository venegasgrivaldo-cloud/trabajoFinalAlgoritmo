package app;

import javax.swing.*;
import java.awt.*;
import utils.AuthUtil;

/**
 * FormAutenticacion: permite registrar un usuario nuevo (se guarda en users.csv vía AuthUtil)
 */
public class FormAutenticacion extends JPanel {

    private JTextField tfNombre;
    private JTextField tfUsuario;
    private JPasswordField pfContrasena;
    private JButton btnRegistrar, btnVolver;
    private JPanel content;

    public FormAutenticacion() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        content = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(7,7,7,7);
        c.fill = GridBagConstraints.HORIZONTAL;

        tfNombre = new JTextField(20);
        tfUsuario = new JTextField(20);
        pfContrasena = new JPasswordField(20);
        btnRegistrar = new JButton("Registrar");
        btnVolver = new JButton("Volver a Login");

        c.gridx = 0; c.gridy = 0; content.add(new JLabel("Nombre completo:"), c);
        c.gridx = 1; content.add(tfNombre, c);

        c.gridx = 0; c.gridy = 1; content.add(new JLabel("Usuario:"), c);
        c.gridx = 1; content.add(tfUsuario, c);

        c.gridx = 0; c.gridy = 2; content.add(new JLabel("Contraseña:"), c);
        c.gridx = 1; content.add(pfContrasena, c);

        c.gridx = 0; c.gridy = 3; content.add(btnRegistrar, c);
        c.gridx = 1; content.add(btnVolver, c);

        add(content, BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> registrarUsuario());
        btnVolver.addActionListener(e -> {
            // cambiar al panel de login en la ventana principal
            java.awt.Container top = this.getTopLevelAncestor();
            if (top instanceof GUIPrincipal gui) {
                gui.mostrarPanel(new Login());
            }
        });
    }

    private void registrarUsuario() {
        String nombre = tfNombre.getText().trim();
        String usuario = tfUsuario.getText().trim();
        String contrasena = new String(pfContrasena.getPassword());

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete usuario y contraseña.");
            return;
        }

        boolean ok = AuthUtil.registrarUsuario(usuario, contrasena);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Usuario registrado correctamente.");
            
            java.awt.Container top = this.getTopLevelAncestor();
            if (top instanceof GUIPrincipal gui) {
                gui.mostrarPanel(new Login());
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo registrar (usuario existente o error).");
        }
    }
}
