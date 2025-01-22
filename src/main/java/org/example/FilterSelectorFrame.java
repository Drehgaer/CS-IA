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
    Dimension dimension = new Dimension(400,400);
    JPanel sidePanel;
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

        sidePanel = new JPanel();
        sidePanel.setBackground(Color.lightGray);
        sidePanel.setPreferredSize(new Dimension(150,150));

        okButton = new JButton("Confirm");
        okButton.addActionListener(this);

        //label = new JLabel();
        //label.setPreferredSize(dimension);

        checkBox1 = new JCheckBox();
        checkBox1.setText("test_category1");
        checkBox1.setFocusable(false);
        checkBox1.setBounds(10, 100, 150, 25);
        checkBox2 = new JCheckBox();
        checkBox2.setText("test_category2");
        checkBox2.setFocusable(false);
        checkBox2.setBounds(10, 125, 150, 25);
        checkBox3 = new JCheckBox();
        checkBox3.setBounds(10, 150, 150, 25);
        checkBox3.setText("test_category3");
        checkBox3.setFocusable(false);

        sidePanel.add(checkBox1);
        sidePanel.add(checkBox2);
        sidePanel.add(checkBox3);

        this.add(okButton, BorderLayout.PAGE_END);
        this.add(sidePanel, BorderLayout.WEST);
        //this.add(label);
        this.pack();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            setFilters();
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            t = 1;
        }
    }
    public void setFilters() {
        filters = new Boolean[3];
        if (checkBox1.isSelected()) {
            filters[0] = true;
        }else{
            filters[0] = false;
        }
        if (checkBox2.isSelected()) {
            filters[1] = true;
        }else{
            filters[1] = false;
        }
        if (checkBox3.isSelected()) {
            filters[2] = true;
        }else{
            filters[2] = false;
        }

    }
    public Boolean[] getFilters(){
        return filters;
    }
    public int getT() {return t;}
}
