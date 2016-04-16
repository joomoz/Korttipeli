/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package korttipeli;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 * GUI - graafinen käyttöliittymä
 * @author Joonas Moilanen, 2016
 */
public class Kayttoliittyma implements Runnable  {

    private JFrame frame;

    public Kayttoliittyma() {
    }

    @Override
    public void run() {
        frame = new JFrame("Blackjack");
        frame.setPreferredSize(new Dimension(900, 600));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.add(new JTextArea());
        container.add(luoNappulat(), BorderLayout.SOUTH);
    }

    private JPanel luoNappulat() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.add(new JButton("Hit"));
        panel.add(new JButton("Stand"));
        panel.add(new JButton("Quit"));
        return panel;
    }

    public JFrame getFrame() {
        return frame;
    }
}
