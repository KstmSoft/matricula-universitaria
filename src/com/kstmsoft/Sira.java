package com.kstmsoft;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Sira extends JFrame {
    Container contenedor;
    JPanel arriba, mitad, abajo;
    JButton matricular,cancelar;
    JLabel l1,l2, imageni,imagend, hora;
    JTable tabla;
    SiraEventos push;

    public Sira(){
        push = new SiraEventos();
    //JPanel arriba
        arriba= new JPanel();
        arriba.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();
        config.weightx=1; //Cuanto espacio coge el componente
        config.weighty=1;
        config.fill = GridBagConstraints.BOTH;

        BufferedImage logo= null;
        BufferedImage logo2= null;
        try {
            logo = ImageIO.read(new File("Resources/LogoU.png"));
            logo2= ImageIO.read(new File("Resources/vacio.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageni = new JLabel(new ImageIcon(logo2));
        config.gridx=0;
        config.gridy=0;
        arriba.add(imageni,config);

        l1 = new JLabel("Matrícula académica");
        Font font = new Font("Roboto",Font.PLAIN,24);
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        l1.setFont(font);
        config.gridx=1;
        config.gridy=0;
        arriba.add(l1,config);

        imagend = new JLabel(new ImageIcon(logo));
        config.gridx=2;
        config.gridy=0;
        arriba.add(imagend,config);

        l2=new JLabel("          Estas son tus materias matriculadas.");
        Font font2 = new Font("Roboto",Font.PLAIN,15);
        l2.setFont(font2);
        config.gridwidth=2;
        config.insets = new Insets(15,0,0,0);
        config.gridx=0;
        config.gridy=1;
        arriba.add(l2,config);
        config.gridwidth=1;

        String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        Font font3 = new Font("Roboto", Font.PLAIN,10);
        hora = new JLabel(timeStamp);
        hora.setFont(font3);
        hora.setHorizontalAlignment(SwingConstants.CENTER);
        config.gridx=2;
        config.gridy=1;
        arriba.add(hora,config);

    //JPanel mitad
        mitad = new JPanel();
        mitad.setLayout(new BorderLayout());
        mitad.setBorder(new EmptyBorder(0,40,0,40));
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Nombre");
        modelo.addColumn("Créditos");
        modelo.addRow(new Object[]{"v1", "v2","v3"});
        tabla = new JTable(modelo);
        tabla.setBorder(BorderFactory.createMatteBorder(0,1,1,1,Color.BLACK));
        tabla.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        tabla.setDefaultEditor(Object.class,null);
        tabla.getTableHeader().setReorderingAllowed(false);
        mitad.add(tabla.getTableHeader(),BorderLayout.PAGE_START);
        mitad.add(tabla, BorderLayout.CENTER);

    //JPanel abajo
        abajo = new JPanel();
        abajo.setLayout(new FlowLayout());
        matricular = new JButton("Matricular");
        matricular.setPreferredSize(new Dimension(200,40));
        matricular.addActionListener(push);
        cancelar = new JButton("Cancelar");
        cancelar.addActionListener(push);
        cancelar.setPreferredSize(new Dimension(200,40));
        abajo.add(matricular);
        abajo.add(cancelar);

    //Configuracion ventana
        contenedor = getContentPane();
        contenedor.setLayout(new BorderLayout());
        contenedor.add(arriba, BorderLayout.NORTH);
        contenedor.add(mitad, BorderLayout.CENTER);
        contenedor.add(abajo,BorderLayout.SOUTH);

        setSize(800,600);
        setResizable(true);
        setTitle("Matrícula Académica");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main (String[] args){
        Sira ventana = new Sira();
    }

    class SiraEventos implements ActionListener {

        public void actionPerformed(ActionEvent ae){
            if(ae.getSource()==matricular){
                JOptionPane.showConfirmDialog(null, "xd");
            }
            if(ae.getSource()==cancelar){
                JOptionPane.showConfirmDialog(null,"bruh");
            }

        }
    }
}











