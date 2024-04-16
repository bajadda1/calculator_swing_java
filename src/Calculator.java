import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.*;


public class Calculator extends JFrame implements ActionListener{

    JButton numBtns[]=new JButton[10];
    JButton funcBtns[]=new JButton[5];
    JButton delBtn=new JButton("Del");
    JButton clcBtn=new JButton("Clc");
    JButton dicBtn=new JButton(".");

    JPanel mainPanel;
    JPanel head;
    JPanel body;
    JPanel foot;
    JLabel label;

    Font fnt=new  Font("Ink Free",Font.BOLD,30);
    String[] op= {"+","-","*","/","="};

    double num1=0;
    double num2=0;
    double result=0;
    String operator;

    public Calculator() {
        this.setVisible(true);
        this.setSize(600, 600);
        this.setTitle("Calculator");
        this.setResizable(false);
        this.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));

        mainPanel=new JPanel();
        mainPanel.setPreferredSize(new Dimension(400,500));
//		mainPanel.setBackground(new Color(250,250,250));
        mainPanel.setLayout(new BorderLayout(20,20));

        head=new JPanel();
        head.setPreferredSize(new Dimension(400,80));
//		head.setBackground(new Color(250,10,10));
        Border border = BorderFactory.createLineBorder(new Color(0,0,255),1);
        head.setBorder(border);

        label=new JLabel();
        label.setPreferredSize(new Dimension(400,80));
        label.setText("");
        label.setFont(fnt);
        head.add(label);
        mainPanel.add(head,BorderLayout.NORTH);

        body=new JPanel();
        body.setPreferredSize(new Dimension(400,336));
        body.setLayout(new GridLayout(4,5,10,10));

        for(int i=0;i<10;i++) {
            numBtns[i]=new JButton(String.valueOf(i));
            numBtns[i].setFont(fnt);
            numBtns[i].setFocusable(false);
            numBtns[i].addActionListener(this);
        }
        for(int i=0;i<5;i++) {
            funcBtns[i]=new JButton(op[i]);
            funcBtns[i].setFont(fnt);
            funcBtns[i].setFocusable(false);
            funcBtns[i].addActionListener(this);
        }

        dicBtn.setFont(fnt);
        dicBtn.setFocusable(false);
        dicBtn.addActionListener(this);

        body.add(numBtns[1]);
        body.add(numBtns[2]);
        body.add(numBtns[3]);
        body.add(funcBtns[0]);
        body.add(numBtns[4]);
        body.add(numBtns[5]);
        body.add(numBtns[6]);
        body.add(funcBtns[1]);
        body.add(numBtns[7]);
        body.add(numBtns[8]);
        body.add(numBtns[9]);
        body.add(funcBtns[2]);
        body.add(dicBtn);
        body.add(numBtns[0]);
        body.add(funcBtns[4]);
        body.add(funcBtns[3]);

        mainPanel.add(body,BorderLayout.CENTER);

        foot=new JPanel();
        foot.setPreferredSize(new Dimension(400,70));
//		foot.setBackground(new Color(200,200,200));
        foot.setLayout(new GridLayout(1,2,10,10));

        delBtn.setFont(fnt);
        delBtn.setFocusable(false);
        delBtn.addActionListener(this);

        clcBtn.setFont(fnt);
        clcBtn.setFocusable(false);
        clcBtn.addActionListener(this);

        foot.add(delBtn);
        foot.add(clcBtn);

        mainPanel.add(foot,BorderLayout.SOUTH);

        this.add(mainPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        for(int i=0;i<10;i++) {
            if(e.getSource()==numBtns[i]) {
                String text=label.getText().concat(numBtns[i].getText());
                label.setText(text);
            }
        }
        for(int i=0;i<4;i++) {
            if(e.getSource()==funcBtns[i]){
                if(!label.getText().equals("")) {
                    operator=funcBtns[i].getText();
                    num1=Double.valueOf(label.getText());
                    label.setText("");

                }
                else {
                    if(funcBtns[i].getText().equals("/")||funcBtns[i].getText().equals("*")) {
                        if(e.getSource()==funcBtns[i]) {
                            label.setText("");
                        }

                    }
                    else {
                        label.setText(funcBtns[i].getText());
                    }
                }
            }



        }
        if(e.getSource()==funcBtns[4]) {  //==button

            if(operator!=null && !label.getText().equals("")) {
                num2=Double.valueOf(label.getText());
                switch (operator) {
                    case "+":
                        result=num1+num2;
                        label.setText(String.valueOf(result));
                        operator=null;
                        break;
                    case "-":
                        result=num1-num2;
                        label.setText(String.valueOf(result));
                        operator=null;
                        break;
                    case "*":
                        result=num1*num2;
                        label.setText(String.valueOf(result));
                        operator=null;
                        break;
                    case "/":
                        if(num2!=0) {
                            result=num1/num2;
                            label.setText(String.valueOf(result));
                            operator=null;
                        }
                        else {
                            label.setText("");
                            operator=null;
                        }

                        break;

                    default:
                        break;
                }

            }
            else if(operator!=null && label.getText().equals("")) {
                operator=null;
                result=num1;
                label.setText(String.valueOf(result));
            }

            else {
                result=Double.valueOf(label.getText());
                label.setText(String.valueOf(result));
            }
        }
        if(e.getSource()==clcBtn) {
            label.setText("");
            result=0;
            num1=0;
            num2=0;
        }
        if(e.getSource()==dicBtn) {
            if(label.getText().equals("")) {
                label.setText("0");
                String txt=label.getText().concat(".");
                label.setText(txt);

            }
            else {
                if(!label.getText().contains(".")) {
                    String txt=label.getText().concat(".");
                    label.setText(txt);
                }
            }

        }
        if(e.getSource()==delBtn) {
            if(!label.getText().equals("")) {
                String labelCurrentTxt=label.getText();
                int indexEnd=labelCurrentTxt.length();
                String labelNewText=labelCurrentTxt.substring(0,indexEnd-1);
                label.setText(labelNewText);
            }

        }

    }

}
