import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Login extends JFrame{
    JPanel panelLogin, panelBienvenida;
    JLabel info, bienvenida, imagen, confirmacion;
    JTextField codigo;
    JButton ingresar;
    Container contenedor;
    Eventos push;
    static Cliente student;

    public Login(){
        panelBienvenida = new JPanel();
        panelBienvenida.setLayout(new BoxLayout(panelBienvenida, BoxLayout.Y_AXIS));
        push = new Eventos();

        BufferedImage logo= null;
        try {
            logo = ImageIO.read(new File("Resources/LogoU.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        bienvenida = new JLabel("Ingreso al sistema");
        bienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);
        bienvenida.setFont(new Font(bienvenida.getFont().getFontName(), Font.PLAIN, 20));
        imagen = new JLabel(new ImageIcon(logo));
        imagen.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelBienvenida.add(imagen);
        panelBienvenida.add(bienvenida);

        panelLogin = new JPanel();
        panelLogin.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();
        config.weightx=0;
        config.weighty=0;

        info = new JLabel("Por favor, digite su codigo (Ej. 1842423)");
        config.fill = GridBagConstraints.NONE;
        config.gridx = 1;
        config.gridy=1;
        panelLogin.add(info, config);

        codigo = new JTextField(15);
        config.gridx = 1;
        config.gridy= 2;
        panelLogin.add(codigo,config);

        ingresar = new JButton("Ingresar");
        config.gridx= 1;
        config.gridy=3;
        config.insets = new Insets(8,0,0,0);
        ingresar.addActionListener(push);
        panelLogin.add(ingresar, config);

        confirmacion = new JLabel("");
        confirmacion.setVisible(false);
        config.gridx = 1;
        config.gridy = 4;
        config.insets = new Insets(8,0,0,0);
        panelLogin.add(confirmacion, config);

        contenedor = getContentPane();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.Y_AXIS));
        contenedor.add(panelBienvenida);
        contenedor.add(panelLogin);
        setSize(400,400);
        setResizable(false);
        setTitle("Login");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }


    public static void main (String[] args){
        Login ventana = new Login();
        student = new Cliente();
    }

    class Eventos implements ActionListener{

        public void actionPerformed(ActionEvent ae){
            if(ae.getSource() == ingresar){
                try{
                    int n= Integer.parseInt(codigo.getText());
                    switch(n){
                        case 2141121:   confirmacion.setText("Bienvenida Camila.");
                                        confirmacion.setVisible(true);
                                        student.conectar("127.0.0.1");
                                        break;

                        case 1932287:   confirmacion.setText("Bienvenido Omar.");
                                        confirmacion.setVisible(true);
                                        student.conectar("127.0.0.1");
                                        break;

                        case 2129929:   confirmacion.setText("Bienvenida Allison.");
                                        confirmacion.setVisible(true);
                                        student.conectar("127.0.0.1");
                                        break;

                        case 1946950:   confirmacion.setText("Bienvenido Santiago.");
                                        confirmacion.setVisible(true);
                                        student.conectar("127.0.0.1");
                                        break;

                        case 2032077:   confirmacion.setText("Bienvenido Lautaro.");
                                        confirmacion.setVisible(true);
                                        student.conectar("127.0.0.1");
                                        break;

                        default:        confirmacion.setText("Usted no pertenece a la universidad o escribió mal su código.");
                                        confirmacion.setVisible(true);
                                        break;

                            }
                    }catch (NumberFormatException a){
                        a.printStackTrace();
                        confirmacion.setText("Escriba numeros, no texto.");
                        confirmacion.setVisible(true);
                    }
            }
        }
    }
}