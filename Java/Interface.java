package Java;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Interface {
   private JFrame mainFrame;
   private JLabel statusLabel;
   protected JTextArea textArea;
   private JPanel controlPanel;

   private JFrame Frame1;
   private JPanel controlPanel1;
   protected JTextArea textArea1;
   protected JPanel text1;
   private JTextField textField;

   private JFrame Frame2;
   private JPanel controlPanel2;
   protected JTextArea textArea2;
   protected JPanel text2;
   private JTextField textField2;

   private JFrame Frame3;
   private JPanel controlPanel3;
   protected JTextArea textArea3;
   protected JPanel text3;
   private JTextField textField3;

   tripSearch ts = new tripSearch();
   String stops = "./TextFiles/stops.txt";
   String stopTimes = "./TextFiles/stop_times.txt";
   String transfers = "./TextFiles/transfers.txt";

   DijkstraAlgorithm sp = new DijkstraAlgorithm(stops, transfers, stopTimes);

   ternarySearchTree tst = new ternarySearchTree();

   final static String BUTTONPANEL = "Card with JButtons";
   final static String TEXTPANEL = "Card with JTextField";

   public Interface() {
      prepareGUI();

   }

   public static void main(String[] args) throws IOException {
      Interface swingLayoutDemo = new Interface();
      swingLayoutDemo.showCardLayoutDemo();

   }

   private void prepareGUI() {
      mainFrame = new JFrame("Vancouver Bus Planner");
      mainFrame.setSize(600, 400);
      mainFrame.setLayout(new GridLayout(3, 1));

      statusLabel = new JLabel("", JLabel.CENTER);

      textArea = new JTextArea(20, 5);
      textArea.setSize(600, 200);
      textArea.setEditable(false);
      JScrollPane scrollPane = new JScrollPane(textArea);

      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent) {
            System.exit(0);
         }
      });
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());
      mainFrame.add(statusLabel);
      mainFrame.add(textArea);
      mainFrame.add(controlPanel);
      mainFrame.setVisible(true);
      ///////////////////////////////////////
      ///////////////////////////////////////
      // Part1
      Frame1 = new JFrame("Vancouver Bus Planner");
      Frame1.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent) {
            System.exit(0);
         }
      });
      Frame1.setSize(600, 600);
      Frame1.setLayout(new GridLayout(3, 1));
      textArea1 = new JTextArea(20, 5);
      textArea1.setSize(300, 100);
      textArea1.setEditable(false);
      textArea1.setMargin(new Insets(10, 10, 10, 10));

      JScrollPane scrollPane1 = new JScrollPane(textArea1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      controlPanel1 = new JPanel();
      // controlPanel1.setLayout();
      // statusLabel1 = new JLabel("",JLabel.CENTER);

      text1 = new JPanel();
      Frame1.add(text1);
      Frame1.add(scrollPane1);
      Frame1.add(controlPanel1);
      // Frame1.add(scrollPane1);
      Frame1.setVisible(false);

      ///////////////////////////////////////
      ///////////////////////////////////////
      // PART2

      Frame2 = new JFrame("Vancouver Bus Planner");
      Frame2.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent) {
            System.exit(0);
         }
      });
      Frame2.setSize(600, 600);
      Frame2.setLayout(new GridLayout(3, 1));
      textArea2 = new JTextArea(20, 5);
      textArea2.setSize(300, 100);
      textArea2.setEditable(false);
      textArea2.setMargin(new Insets(10, 10, 10, 10));

      JScrollPane scrollPane2 = new JScrollPane(textArea2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      controlPanel2 = new JPanel();
      // controlPanel1.setLayout();
      // statusLabel2 = new JLabel("",JLabel.CENTER);

      text2 = new JPanel();
      Frame2.add(text2);
      Frame2.add(scrollPane2);
      Frame2.add(controlPanel2);
      // Frame1.add(scrollPane1);
      Frame2.setVisible(false);

      ///////////////////////////////////////
      ///////////////////////////////////////
      // PART3

      Frame3 = new JFrame("Vancouver Bus Planner");
      Frame3.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent) {
            System.exit(0);
         }
      });
      Frame3.setSize(600, 600);
      Frame3.setLayout(new GridLayout(3, 1));
      textArea3 = new JTextArea(20, 5);
      textArea3.setSize(300, 100);
      textArea3.setEditable(false);
      textArea3.setMargin(new Insets(10, 10, 10, 10));

      JScrollPane scrollPane3 = new JScrollPane(textArea3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      controlPanel3 = new JPanel();
      // controlPanel1.setLayout();
      // statusLabel2 = new JLabel("",JLabel.CENTER);
      // textArea.sc
      text3 = new JPanel();

      // Frame1.add(statusLabel2);

      Frame3.add(text3);
      Frame3.add(scrollPane3);
      Frame3.add(controlPanel3);

      // Frame1.add(scrollPane1);
      Frame3.setVisible(false);

   }

   private void showCardLayoutDemo() throws IOException {
      // MAINFRAME

      statusLabel.setText("----------------------------VANCOUVER BUS PLANNER----------------------------");

      textArea.setText("-Press Bus Route to find the shortest path between two routes" +
            "\n" + "-Press Search to search for a bus stop and get information" + "\n"
            + "-Press Bus times to search for what buses will be available at the entered time");
      textArea.setFont(new Font("Serif", Font.CENTER_BASELINE, 16));
      JButton a = new JButton("Bus Route");
      a.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // your actions
            mainFrame.setVisible(false);
            Frame1.setVisible(true);
         }
      });
      JButton b = new JButton("Search");
      b.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // your actions
            mainFrame.setVisible(false);
            Frame2.setVisible(true);
         }
      });
      JButton c = new JButton("Bus Times");
      c.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // your actions
            mainFrame.setVisible(false);
            Frame3.setVisible(true);
         }
      });
      controlPanel.add(a);
      controlPanel.add(b);
      controlPanel.add(c);
      ///////////////////////////////////////
      ///////////////////////////////////////
      // FRAME1

      textField = new JTextField(20);

      text1.add(textField, JTextField.CENTER);
      JButton submit = new JButton("Submit");
      submit.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // your actions
            String text = textField.getText();
            try {
               String result = "";
               result = sp.dijkstraMain(text);

               textArea1.setText(result);
            } catch (IOException a) {
            }
         }
      });
      text1.add(submit);
      JButton cp1 = new JButton("Return");
      ;
      cp1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // your actions
            mainFrame.setVisible(true);
            Frame1.setVisible(false);
         }
      });
      textArea1.setText("Enter 2 bus stop names into the text box and press submit to get the bus journey" + "\n"
            + "in the format stop1,stop2");
      textArea1.setFont(new Font("Serif", Font.CENTER_BASELINE, 15));
      // textArea1.setFont(new Font("Serif",Font.CENTER_BASELINE, 16));
      controlPanel1.add(cp1);

      ///////////////////////////////////////
      ///////////////////////////////////////
      // FRAME2

      textField2 = new JTextField(20);

      text2.add(textField2, JTextField.CENTER);
      JButton submit2 = new JButton("Submit");
      submit2.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // your actions
            String text = textField2.getText();
            text = text.toUpperCase();
            String result = "";
            try {
               result = tst.finalMain(text, stops);
            } catch (IOException e1) {
               // TODO Auto-generated catch block
            }

            // System.out.println(result);
            textArea2.setText(result);

         }
      });
      text2.add(submit2);
      JButton cp2 = new JButton("Return");
      ;
      cp2.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // your actions
            mainFrame.setVisible(true);
            Frame2.setVisible(false);
         }
      });
      textArea2.setText("Enter a bus id for information" + "\n" + "in the format: text");
      textArea2.setFont(new Font("Serif", Font.CENTER_BASELINE, 15));
      controlPanel2.add(cp2);

      ///////////////////////////////////////
      ///////////////////////////////////////
      // FRAME3

      textField3 = new JTextField(20);

      text3.add(textField3, JTextField.CENTER);
      JButton submit3 = new JButton("Submit");
      submit3.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // your actions
            String text = textField3.getText();
            try {
               String result = ts.finalMain(stops, stopTimes, text);

               System.out.println(result);
               textArea3.setText(result);
            } catch (IOException a) {
            }
         }
      });
      text3.add(submit3);
      JButton cp3 = new JButton("Return");
      ;
      cp3.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // your actions
            mainFrame.setVisible(true);
            Frame3.setVisible(false);
         }
      });
      textArea3.setText("Enter Time for Bus journeys" + "\n" + "in the format: hh:mm:ss");
      textArea3.setFont(new Font("Serif", Font.CENTER_BASELINE, 15));
      controlPanel3.add(cp3);

   }

}