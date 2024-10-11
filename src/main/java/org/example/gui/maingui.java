package org.example.gui;

import org.example.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class maingui {
    private JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                maingui window = new maingui();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public maingui() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JButton btnCheckClient = new JButton("检测英雄联盟进程是否存在");
        btnCheckClient.setBounds(100, 100, 250, 50);
        frame.getContentPane().add(btnCheckClient);

        btnCheckClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.main(new String[]{});
                } catch (IOException | NoSuchAlgorithmException | KeyManagementException ex) {
                    JOptionPane.showMessageDialog(frame, "检测过程中发生错误: " + ex.getMessage());
                }
            }
        });
    }
}
