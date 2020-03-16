package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JPanel contentPane;
    private JPanel panel = new JPanel();
    private JLabel picture = new JLabel();


    public static void main(String[] args) {
        Main m = new Main();
        m.Player();
    }


    public void Player(){

        setTitle("picture Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 852, 603);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblRecommendation = new JLabel("Recommendation");
        lblRecommendation.setHorizontalAlignment(SwingConstants.CENTER);
        lblRecommendation.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 28));
        lblRecommendation.setBounds(463, 58, 270, 53);
        contentPane.add(lblRecommendation);

        JButton btnUpLoad = new JButton("Up Load");
        btnUpLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));
                int i = jFileChooser.showOpenDialog(null);
                if(i== jFileChooser.APPROVE_OPTION){
                    String path = jFileChooser.getSelectedFile().getAbsolutePath();
                    String name = jFileChooser.getSelectedFile().getName();
                    System.out.println("File Path："+path+";\nFile Name"+name);
                    picture.setIcon(upload(path));
                }else{
                    System.out.println("No File Selected");
                }
            }
        });
        btnUpLoad.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 23));
        btnUpLoad.setBounds(103, 439, 226, 53);
        contentPane.add(btnUpLoad);

        JButton btnTypeOne = new JButton("Type One");
        btnTypeOne.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 23));
        btnTypeOne.setBounds(439, 124, 159, 53);
        contentPane.add(btnTypeOne);

        JButton btnTypeTwo = new JButton("Type One");
        btnTypeTwo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        btnTypeTwo.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 23));
        btnTypeTwo.setBounds(623, 124, 159, 53);
        contentPane.add(btnTypeTwo);

        JButton btnTypeThree = new JButton("Type One");
        btnTypeThree.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 23));
        btnTypeThree.setBounds(439, 190, 159, 53);
        contentPane.add(btnTypeThree);

        JButton btnTypeFour = new JButton("Type One");
        btnTypeFour.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 23));
        btnTypeFour.setBounds(623, 190, 159, 53);
        contentPane.add(btnTypeFour);

        JButton btnTypeFive = new JButton("Type One");
        btnTypeFive.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 23));
        btnTypeFive.setBounds(439, 255, 159, 53);
        contentPane.add(btnTypeFive);

        JButton btnTypeSix = new JButton("Type One");
        btnTypeSix.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 23));
        btnTypeSix.setBounds(623, 256, 159, 53);
        contentPane.add(btnTypeSix);

        JButton btnTypeSeven = new JButton("Type One");
        btnTypeSeven.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 23));
        btnTypeSeven.setBounds(439, 321, 159, 53);
        contentPane.add(btnTypeSeven);

        JButton btnLilke = new JButton("Lilke");
        btnLilke.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 23));
        btnLilke.setBounds(556, 439, 226, 53);
        contentPane.add(btnLilke);


        panel.setBounds(22, 46, 400, 300);
        contentPane.add(panel);


        panel.add(picture);
        picture.setHorizontalAlignment(SwingConstants.CENTER);

//        ImageIcon img = new ImageIcon("C:\\Users\\Yifei Chen\\Desktop\\u=2835156641,1453869869&fm=26&gp=0.jpg");
//        int sH = (int) scaleH(panel.getHeight(), panel.getWidth(), img.getIconHeight(), img.getIconWidth());
//        int sW = (int) scaleW(panel.getHeight(), panel.getWidth(), img.getIconHeight(), img.getIconWidth());
//
//        Image temp = img.getImage().getScaledInstance(sW, sH, img.getImage().SCALE_DEFAULT);
//        ImageIcon icon = new ImageIcon(temp);
        picture.setIcon(upload("C:\\Users\\Yifei Chen\\Desktop\\u=2835156641,1453869869&fm=26&gp=0.jpg"));

        setVisible(true);
    }

    private double scaleW(double panelH, double panelW, double imgH, double imgW) {
        return Math.min(imgW / imgH * panelH, panelW);
    }

    private double scaleH(double panelH, double panelW, double imgH, double imgW) {
        return Math.min(imgH / imgW * panelW, panelH);
    }

    private ImageIcon upload(String filename){
        ImageIcon img = new ImageIcon(filename);
        int sH = (int) scaleH(panel.getHeight(), panel.getWidth(), img.getIconHeight(), img.getIconWidth());
        int sW = (int) scaleW(panel.getHeight(), panel.getWidth(), img.getIconHeight(), img.getIconWidth());

        Image temp = img.getImage().getScaledInstance(sW, sH, img.getImage().SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(temp);

        return icon;
    }




}
