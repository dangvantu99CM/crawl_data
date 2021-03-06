/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawldata;

/**
 *
 * @author Ct_fsp_2
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class demo extends JPanel {

    private static final LayoutManager H = new GridLayout(1, 0);
    private static final LayoutManager V = new GridLayout(0, 1);

    public demo() {
        this.setLayout(H);
        this.setPreferredSize(new Dimension(320, 240));
        for (int i = 0; i < 3; i++) {
            this.add(new JLabel("Label " + String.valueOf(i), JLabel.CENTER));
        }
    }

    private void display() {
        JFrame f = new JFrame("DynamicLayout");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this);
        JPanel p = new JPanel();
        p.add(new JButton(new AbstractAction("Horizontal") {

            @Override
            public void actionPerformed(ActionEvent e) {
                demo.this.setLayout(H);
                demo.this.validate();
            }
        }));
        p.add(new JButton(new AbstractAction("Vertical") {

            @Override
            public void actionPerformed(ActionEvent e) {
                demo.this.setLayout(V);
                demo.this.validate();
            }
        }));
        f.add(p, BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new demo().display();
            }
        });
    }
}

