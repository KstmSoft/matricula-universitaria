package com.kstmsoft;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Login extends JFrame{
    JPanel panelLogin, panelBienvenida;
    JLabel info, bienvenida, imagen, confirmacion;
    JTextField codigo;
    JButton ingresar;
    Container contenedor;
    Eventos push;
    static Client client;

    public Login(){
        panelBienvenida = new JPanel();
        panelBienvenida.setLayout(new BoxLayout(panelBienvenida, BoxLayout.Y_AXIS));
        push = new Eventos();

        BufferedImage logo= null;
        try {
            logo = ImageIO.read(new File("src/com/kstmsoft/Resources/LogoU.png"));
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

        info = new JLabel("Por favor, digite su codigo (Ej. 1841369)");
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
        new Login();
        client = new Client();
        client.connect("localhost", 8080);
    }

    class Eventos implements ActionListener{
        public void actionPerformed(ActionEvent ae){
            if(ae.getSource() == ingresar){
                try{
                    ArrayList<Course> courseList = client.getCourses(codigo.getText());
                    if(!courseList.isEmpty()){
                        new Sira(client, codigo.getText());
                        setVisible(false);
                    }else{
                        confirmacion.setText("El usuario no tiene materias registradas o no existe.");
                        confirmacion.setVisible(true);
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