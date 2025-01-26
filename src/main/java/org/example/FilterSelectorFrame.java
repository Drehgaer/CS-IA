package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class FilterSelectorFrame extends JFrame implements ActionListener {
    JCheckBox checkBox1;
    JCheckBox checkBox2;
    JCheckBox checkBox3;
    JCheckBox checkBox4;
    JCheckBox checkBox5;
    JCheckBox checkBox6;
    JCheckBox checkBox7;
    JCheckBox checkBox8;
    JCheckBox checkBox9;
    JCheckBox checkBox10;
    JCheckBox checkBox11;
    JCheckBox checkBox12;
    JCheckBox checkBox13;
    JCheckBox checkBox14;
    JCheckBox checkBox15;
    JCheckBox checkBox16;
    JCheckBox checkBox17;
    JCheckBox checkBox18;
    JCheckBox checkBox19;
    JCheckBox checkBox20;
    JCheckBox checkBox21;
    JCheckBox checkBox22;
    JCheckBox checkBox23;
    JCheckBox checkBox24;
    JCheckBox checkBox25;
    JCheckBox checkBox26;
    JCheckBox checkBox27;
    JCheckBox checkBox28;
    JCheckBox checkBox29;
    JCheckBox checkBox30;
    JCheckBox [] checkBoxes ={checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9, checkBox10, checkBox11, checkBox12, checkBox13, checkBox14, checkBox15, checkBox16,checkBox17,checkBox18,checkBox19,checkBox20, checkBox21, checkBox22, checkBox23, checkBox24, checkBox25, checkBox26, checkBox27, checkBox28, checkBox29, checkBox30};
    Dimension dimension = new Dimension(1000,1000);
    JPanel leftPanel;
    JPanel centerPanel;
    JPanel rightPanel;
    //JLabel label;
    JButton okButton;
    Boolean [] filters;
    int t = 0;
    FilterSelectorFrame() {
        this.setSize(dimension);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setTitle("Choose Filters");
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        //this.setResizable(false);

        leftPanel = new JPanel();
        leftPanel.setBackground(Color.lightGray);
        leftPanel.setPreferredSize(new Dimension(350,350));
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.gray);
        centerPanel.setPreferredSize(new Dimension(350,350));
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.darkGray);
        rightPanel.setPreferredSize(new Dimension(350,350));


        okButton = new JButton("Confirm");
        okButton.addActionListener(this);

        //label = new JLabel();
        //label.setPreferredSize(dimension);
        int i = 0;
        while (i < checkBoxes.length) {
            checkBoxes[i] = new JCheckBox();
            checkBoxes[i].setText(Product.getCategoryAt(i).toString());
            checkBoxes[i].setSelected(true);
            checkBoxes[i].setFocusable(false);
            checkBoxes[i].setBounds(10, i*20 + 20, 150, 20);
            checkBoxes[i].addActionListener(this);
            if (i<10) {
                leftPanel.add(checkBoxes[i]);
            } else if (i<20) {
                centerPanel.add(checkBoxes[i]);
            } else {
                rightPanel.add(checkBoxes[i]);
            }
            i++;
        }
        /*checkBox1 = new JCheckBox();
        checkBox1.setText("akcesoria/plastikowe/kostki do gry ekstra");
        checkBox1.setFocusable(false);
        checkBox1.setBounds(10, 20, 150, 20);
        checkBox2 = new JCheckBox();
        checkBox2.setText("akcesoria/plastikowe/kostki do gry zwykłe");
        checkBox2.setFocusable(false);
        checkBox2.setBounds(10, 40, 150, 20);
        checkBox3 = new JCheckBox();
        checkBox3.setBounds(10, 60, 150, 20);
        checkBox3.setText("gamebook");
        checkBox3.setFocusable(false);
        checkBox4 = new JCheckBox();
        checkBox4.setBounds(10, 80, 150, 20);
        checkBox4.setText("gamebook");
        checkBox4.setFocusable(false);
        checkBox5 = new JCheckBox();
        checkBox5.setBounds(10, 100, 150, 20);
        checkBox5.setText("gry dla dzieci/edukacyjne");

        leftPanel.add(checkBox1);
        leftPanel.add(checkBox2);
        leftPanel.add(checkBox3);
*/
        this.add(okButton, BorderLayout.PAGE_END);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(centerPanel, BorderLayout.CENTER);
        //this.add(label);
        this.pack();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()  == okButton) {
            setFilters();
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            t = 1;
        }
    }
    public void setFilters() {
        filters = new Boolean[checkBoxes.length];
        for (int i = 0; i < checkBoxes.length; i++) {
            filters[i] = checkBoxes[i].isSelected();
        }
    }
    public Boolean[] getFilters(){
        return filters;
    }
    public int getT() {return t;}
}
