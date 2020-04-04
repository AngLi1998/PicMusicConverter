package ui;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import module.GetTheMainRGB;
import module.RGB;
import module.Train;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Random;


public class Main extends JFrame {
    private JPanel contentPane;
    private JPanel panel = new JPanel();
    private JLabel picture = new JLabel();
    private Music music;
    private String font = "Tempus Sans ITC";
    private int size = 20;


//    private String typeOne = "Action";
//    private String typeTwo = "Beauty & Style";
//    private String typeThree = "Education";
//    private String typeFour = "Entertainment";
//    private String typeFive = "News & Politics";
//    private String typeSix = "Tech & Science";
//    private String typeSeven = "Travel";

    private String typeOne = "A";
    private String typeTwo = "B";
    private String typeThree = "C";
    private String typeFour = "D";
    private String typeFive = "E";
    private String typeSix = "F";
    private String typeSeven = "G";


    private RGB rgb = null;

    private Train train;


    public static void main(String[] args) throws IOException, ParseException, org.json.simple.parser.ParseException {
        Main m = new Main();
        m.Player();

    }


    public void Player() throws IOException, ParseException, org.json.simple.parser.ParseException {
        train = new Train();

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
                    try {
                        rgb = GetTheMainRGB.getMainRGB(path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(rgb);
                    System.out.println("File Path: "+path+";\nFile Name: "+name);
                    ImageIcon img = new ImageIcon(path);
                    picture.setIcon(upload(img));

                    try{
                        music.stop();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (train.type(rgb) == 1) {
                        lblRecommendation.setText("Recommendation: " + typeOne);
                        String pathM = getMusicPath("src/music/action");
                        music = new Music(pathM);
                        music.start();
                    } else if (train.type(rgb) == 2) {
                        lblRecommendation.setText("Recommendation: " + typeTwo);
                        String pathM = getMusicPath("src/music/beauty & style");
                        music = new Music(pathM);
                        music.start();
                    } else if (train.type(rgb) == 3) {
                        lblRecommendation.setText("Recommendation: " + typeThree);
                        String pathM = getMusicPath("src/music/education");
                        music = new Music(pathM);
                        music.start();
                    } else if (train.type(rgb) == 4) {
                        lblRecommendation.setText("Recommendation: " + typeFour);
                        String pathM = getMusicPath("src/music/entertainment");
                        music = new Music(pathM);
                        music.start();
                    } else if (train.type(rgb) == 5) {
                        lblRecommendation.setText("Recommendation: " + typeFive);
                        String pathM = getMusicPath("src/music/news & politics");
                        music = new Music(pathM);
                        music.start();
                    } else if (train.type(rgb) == 6) {
                        lblRecommendation.setText("Recommendation: " + typeSix);
                        String pathM = getMusicPath("src/music/tech&science");
                        music = new Music(pathM);
                        music.start();
                    } else if (train.type(rgb) == 7) {
                        lblRecommendation.setText("Recommendation: " + typeSeven);
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

        JButton btnTypeOne = new JButton(typeOne);
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
                lblRecommendation.setText(typeOne);
            }

        });
        btnTypeOne.setFont(new Font(font, Font.PLAIN, size));
        btnTypeOne.setBounds(439, 124, 159, 53);
        contentPane.add(btnTypeOne);

        JButton btnTypeTwo = new JButton(typeTwo);
        btnTypeTwo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    music.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String path = getMusicPath("src/music/beauty & style");
                music = new Music(path);
                lblRecommendation.setText(typeTwo);
                music.start();
            }
        });
        btnTypeTwo.setFont(new Font(font, Font.PLAIN, size));
        btnTypeTwo.setBounds(623, 124, 159, 53);
        contentPane.add(btnTypeTwo);

        JButton btnTypeThree = new JButton(typeThree);
        btnTypeThree.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    music.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String path = getMusicPath("src/music/education");
                music = new Music(path);
                lblRecommendation.setText(typeThree);
                music.start();
            }
        });
        btnTypeThree.setFont(new Font(font, Font.PLAIN, size));
        btnTypeThree.setBounds(439, 190, 159, 53);
        contentPane.add(btnTypeThree);

        JButton btnTypeFour = new JButton(typeFour);
        btnTypeFour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    music.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String path = getMusicPath("src/music/entertaiment");
                music = new Music(path);
                lblRecommendation.setText(typeFour);
                music.start();
            }
        });
        btnTypeFour.setFont(new Font(font, Font.PLAIN, size));
        btnTypeFour.setBounds(623, 190, 159, 53);
        contentPane.add(btnTypeFour);

        JButton btnTypeFive = new JButton(typeFive);
        btnTypeFive.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    music.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String path = getMusicPath("src/music/news & politics");
                music = new Music(path);
                lblRecommendation.setText(typeFive);
                music.start();
            }
        });
        btnTypeFive.setFont(new Font(font, Font.PLAIN, size));
        btnTypeFive.setBounds(439, 255, 159, 53);
        contentPane.add(btnTypeFive);

        JButton btnTypeSix = new JButton(typeSix);
        btnTypeSix.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    music.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String path = getMusicPath("src/music/tech & science");
                music = new Music(path);
                lblRecommendation.setText(typeSix);
                music.start();
            }
        });
        btnTypeSix.setFont(new Font(font, Font.PLAIN, size));
        btnTypeSix.setBounds(623, 256, 159, 53);
        contentPane.add(btnTypeSix);

        JButton btnTypeSeven = new JButton(typeSeven);
        btnTypeSeven.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try{
                    music.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String path = getMusicPath("src/music/travel");
                music = new Music(path);
                lblRecommendation.setText(typeSeven);
                music.start();
            }
        });
        btnTypeSeven.setFont(new Font(font, Font.PLAIN, size));
        btnTypeSeven.setBounds(439, 321, 159, 53);
        contentPane.add(btnTypeSeven);

        JButton btnLike = new JButton("Like");
        btnLike.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if(rgb != null) {
                    String recom = lblRecommendation.getText();

                    if (recom.contains(typeOne)) {
                        try {
                            train.addAndTrainOneDataPair(rgb, 1);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else if (recom.contains(typeTwo)) {
                        try {
                            train.addAndTrainOneDataPair(rgb, 2);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else if (recom.contains(typeThree)) {
                        try {
                            train.addAndTrainOneDataPair(rgb, 3);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else if (recom.contains(typeFour)) {
                        try {
                            train.addAndTrainOneDataPair(rgb, 4);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else if (recom.contains(typeFive)) {
                        try {
                            train.addAndTrainOneDataPair(rgb, 5);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else if (recom.contains(typeSix)) {
                        try {
                            train.addAndTrainOneDataPair(rgb, 6);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else if (recom.contains(typeSeven)) {
                        try {
                            train.addAndTrainOneDataPair(rgb, 7);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    rgb = null;
                }
            }
        });
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
