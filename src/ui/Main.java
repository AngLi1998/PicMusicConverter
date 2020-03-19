package ui;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import module.GetTheMainRGB;
import module.RGB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;


public class Main extends JFrame {
    private JPanel contentPane;
    private JPanel panel = new JPanel();
    private JLabel picture = new JLabel();
    private Music music;
    private String font = "Tempus Sans ITC";
    private int size = 20;

    public static void main(String[] args) {
        Main m = new Main();
        m.Player();

    }


    public void Player(){

        setTitle("Picture Player");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 852, 603);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblRecommendation = new JLabel("Recommendation");
        lblRecommendation.setHorizontalAlignment(SwingConstants.CENTER);

        lblRecommendation.setFont(new Font(font, Font.PLAIN, 20));
        lblRecommendation.setBounds(463, 58, 270, 53);
        contentPane.add(lblRecommendation);

        JButton btnUpLoad = new JButton("Upload");
        btnUpLoad.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileFilter(new FileNameExtensionFilter("image(*.jpg, *.png, *.gif)", "jpg", "png", "gif"));
                int i = jFileChooser.showOpenDialog(null);
                if(i== jFileChooser.APPROVE_OPTION){
                    String path = jFileChooser.getSelectedFile().getAbsolutePath();
                    String name = jFileChooser.getSelectedFile().getName();
                    RGB rgb = null;
                    try {
                        rgb = GetTheMainRGB.getMainRGB(path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(rgb);
                    System.out.println("File Path: "+path+";\nFile Name: "+name);
                    ImageIcon img = new ImageIcon(path);
                    picture.setIcon(upload(img));
                    if (rgb.Red >= rgb.Green && rgb.Red > rgb.Blue) {
                        lblRecommendation.setText("Recommendation: Action");
                        String pathM = getMusicPath("src/music/action");
                        music = new Music(pathM);
                        music.start();
                    } else if (rgb.Blue>rgb.Green && rgb.Green>rgb.Red) {
                        lblRecommendation.setText("Recommendation: Beauty & Style");
                        String pathM = getMusicPath("src/music/beauty & style");
                        music = new Music(pathM);
                        music.start();
                    } else if (rgb.Green>rgb.Blue && rgb.Blue>rgb.Red) {
                        lblRecommendation.setText("Recommendation: Education");
                        String pathM = getMusicPath("src/music/education");
                        music = new Music(pathM);
                        music.start();
                    } else if (rgb.Red>rgb.Blue && rgb.Blue>rgb.Green) {
                        lblRecommendation.setText("Recommendation: Entertainment");
                        String pathM = getMusicPath("src/music/entertainment");
                        music = new Music(pathM);
                        music.start();
                    } else if (rgb.Red > rgb.Green && rgb.Blue>rgb.Green) {
                        lblRecommendation.setText("Recommendation: News & Politics");
                        String pathM = getMusicPath("src/music/news & politics");
                        music = new Music(pathM);
                        music.start();
                    } else if (rgb.Red > rgb.Green && rgb.Blue>rgb.Red) {
                        lblRecommendation.setText("Recommendation: Tech & Science");
                        String pathM = getMusicPath("src/music/tech&science");
                        music = new Music(pathM);
                        music.start();
                    } else if (rgb.Green > rgb.Red && rgb.Red>rgb.Blue) {
                        lblRecommendation.setText("Recommendation: Travel");
                        String pathM = getMusicPath("src/music/travel");
                        music = new Music(pathM);
                        music.start();
                    } else {
                        lblRecommendation.setText("Recommendation: Others");
                    }
                }else{
                    System.out.println("No File Selected");
                }
            }
        });

        btnUpLoad.setFont(new Font(font, Font.PLAIN, size));
        btnUpLoad.setBounds(103, 439, 226, 53);
        contentPane.add(btnUpLoad);

        JButton btnTypeOne = new JButton("Action");
        btnTypeOne.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    music.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String path = getMusicPath("src/music/action");

                music = new Music(path);
                music.start();
                lblRecommendation.setText("Action");
            }

        });
        btnTypeOne.setFont(new Font(font, Font.PLAIN, size));
        btnTypeOne.setBounds(439, 124, 159, 53);
        contentPane.add(btnTypeOne);

        JButton btnTypeTwo = new JButton("Beauty & Style");
        btnTypeTwo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    music.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String path = getMusicPath("src/music/beauty & style");
                music = new Music(path);
                lblRecommendation.setText("Beauty & Style");
                music.start();
            }
        });
        btnTypeTwo.setFont(new Font(font, Font.PLAIN, size));
        btnTypeTwo.setBounds(623, 124, 159, 53);
        contentPane.add(btnTypeTwo);

        JButton btnTypeThree = new JButton("Education");
        btnTypeThree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    music.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String path = getMusicPath("src/music/education");
                music = new Music(path);
                lblRecommendation.setText("Education");
                music.start();
            }
        });
        btnTypeThree.setFont(new Font(font, Font.PLAIN, size));
        btnTypeThree.setBounds(439, 190, 159, 53);
        contentPane.add(btnTypeThree);

        JButton btnTypeFour = new JButton("Entertainment");
        btnTypeFour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    music.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String path = getMusicPath("src/music/entertaiment");
                music = new Music(path);
                lblRecommendation.setText("Entertainment");
                music.start();
            }
        });
        btnTypeFour.setFont(new Font(font, Font.PLAIN, size));
        btnTypeFour.setBounds(623, 190, 159, 53);
        contentPane.add(btnTypeFour);

        JButton btnTypeFive = new JButton("New & Politics");
        btnTypeFive.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    music.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String path = getMusicPath("src/music/news & politics");
                music = new Music(path);
                lblRecommendation.setText("New & Politics");
                music.start();
            }
        });
        btnTypeFive.setFont(new Font(font, Font.PLAIN, size));
        btnTypeFive.setBounds(439, 255, 159, 53);
        contentPane.add(btnTypeFive);

        JButton btnTypeSix = new JButton("Tech & Science");
        btnTypeSix.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    music.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String path = getMusicPath("src/music/tech & science");
                music = new Music(path);
                lblRecommendation.setText("Tech & Science");
                music.start();
            }
        });
        btnTypeSix.setFont(new Font(font, Font.PLAIN, size));
        btnTypeSix.setBounds(623, 256, 159, 53);
        contentPane.add(btnTypeSix);

        JButton btnTypeSeven = new JButton("Travel");
        btnTypeSeven.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    music.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String path = getMusicPath("src/music/travel");
                music = new Music(path);
                lblRecommendation.setText("Travel");
                music.start();
            }
        });
        btnTypeSeven.setFont(new Font(font, Font.PLAIN, size));
        btnTypeSeven.setBounds(439, 321, 159, 53);
        contentPane.add(btnTypeSeven);

        JButton btnLike = new JButton("Like");
        btnLike.setFont(new Font(font, Font.PLAIN, size));
        btnLike.setBounds(556, 439, 226, 53);
        contentPane.add(btnLike);


        panel.setBounds(22, 46, 400, 300);
        contentPane.add(panel);


        panel.add(picture);
        picture.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon img = new ImageIcon(getClass().getResource("../PlusSign.jpg"));
        ImageIcon icon = upload(img);
        picture.setIcon(icon);

        setVisible(true);
    }

    private double scaleW(double panelH, double panelW, double imgH, double imgW) {
        return Math.min(imgW / imgH * panelH, panelW);
    }

    private double scaleH(double panelH, double panelW, double imgH, double imgW) {
        return Math.min(imgH / imgW * panelW, panelH);
    }

    private ImageIcon upload(ImageIcon img){
        int sH = (int) scaleH(panel.getHeight(), panel.getWidth(), img.getIconHeight(), img.getIconWidth());
        int sW = (int) scaleW(panel.getHeight(), panel.getWidth(), img.getIconHeight(), img.getIconWidth());

        Image temp = img.getImage().getScaledInstance(sW, sH, img.getImage().SCALE_DEFAULT);
        ImageIcon icon = new ImageIcon(temp);

        return icon;
    }

    private String getMusicPath(String path) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        Random rand = new Random();
        return files[rand.nextInt(files.length)].getAbsolutePath();
    }

    class Music extends Thread{
        private String path;

        public Music(String path) {
            this.path = path;
        }

        public void run(){

            try {

                FileInputStream fileInputStream = new FileInputStream(path);
                Player player = new Player(fileInputStream);
                player.play();

            }catch(FileNotFoundException e) {
                e.printStackTrace();
            }catch(JavaLayerException e) {
                e.printStackTrace();
            }

        }




    }

}
