package com.neptune;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Neptune on 4/3/2017.
 */
public class SetupFrame extends JFrame {
    public SetupFrame() {
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        JButton first = new JButton("First");
        first.setFont(new Font("Arial", Font.BOLD, 45));
        JButton second = new JButton("Second");
        second.setFont(new Font("Arial", Font.BOLD, 45));
        panel.add(first);
        panel.add(second);
        this.add(panel);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(4, 1));
        JButton ez = new JButton("Easy");
        ez.setFont(new Font("Arial", Font.BOLD, 45));
        JButton medium = new JButton("Medium");
        medium.setFont(new Font("Arial", Font.BOLD, 45));
        JButton hard = new JButton("Hard");
        hard.setFont(new Font("Arial", Font.BOLD, 45));
        JButton back = new JButton("Back");
        back.setFont(new Font("Arial", Font.BOLD, 45));
        panel2.add(ez);
        panel2.add(medium);
        panel2.add(hard);
        panel2.add(back);

        first.addActionListener(e -> {
            Rule.PLAYER_FIRST = true;
            panel.setVisible(false);
            panel2.setVisible(true);
            this.add(panel2);
        });

        second.addActionListener(e -> {
            Rule.PLAYER_FIRST = false;
            panel.setVisible(false);
            panel2.setVisible(true);
            this.add(panel2);
        });

        ez.addActionListener(e -> {
            Rule.MAX_DEPTH = 1;
            initGomokuFrame();
        });

        medium.addActionListener(e -> {
            Rule.MAX_DEPTH = 2;
            initGomokuFrame();
        });

        hard.addActionListener(e -> {
            Rule.MAX_DEPTH = 3;
            initGomokuFrame();
        });

        back.addActionListener(e -> {
            panel2.setVisible(false);
            panel.setVisible(true);
            this.add(panel);
        });
    }

    private void initGomokuFrame() {
        GomokuFrame gomokuFrame = new GomokuFrame();
        gomokuFrame.setResizable(false);
        gomokuFrame.setSize(800, 800);
        gomokuFrame.setVisible(true);
    }
}
