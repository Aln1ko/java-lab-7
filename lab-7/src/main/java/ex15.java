/*
Зміна розміру зображення у графічному вікні. У верхній панелі вікна (JPanel)
"Розмір зображення" задані наступні компоненти: напис (JLabel)
"Ширина:", текстове поле (JTextField), напис (JLabel) "Висота:",
текстове поле (JTextField) та кнопка (JButton): "Вивести зображення".
 У нижній панелі вікна (JPanel) вікна "Виведення зображення" в компоненті (JLabel)
 задається довільне зображення. При введенні розміру зображення за шириною та
 висотою та натисканням кнопки "Вивести зображення" в нижній панелі
 виводиться масштабоване зображення із заданими розмірами (у пікселях).
 Спочатку зображення має "природний" розмір. Перед виведенням зображення виконується
  перевірка, чи задані значення ширини та висоти та чи є вони цілими числами.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 * This class demonstrates resizing an image within a graphical window.
 */
public class ex15 extends JFrame {

    private JLabel widthLabel, heightLabel, displayImageLabel;
    private JTextField widthTextField, heightTextField;
    private JButton displayImageButton;
    private boolean is_nedded;
    {
        is_nedded = true;
    }

    /**
     * Constructor to initialize the window and its components.
     */
    public ex15(){
        setTitle("Масштабування зображення");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1280,720);

        // Top panel components for image size input
        JPanel topPanel = new JPanel();
        widthLabel = new JLabel("Ширина:");
        widthTextField = new JTextField(5);
        heightLabel = new JLabel("Висота:");
        heightTextField = new JTextField(5);
        displayImageButton = new JButton("Вивести зображення");

        // Action listener for displaying the image
        displayImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayImage(is_nedded);
            }
        });

        topPanel.add(widthLabel);
        topPanel.add(widthTextField);
        topPanel.add(heightLabel);
        topPanel.add(heightTextField);
        topPanel.add(displayImageButton);

        // Bottom panel to display the image
        JPanel bottomPanel = new JPanel();
        displayImageLabel = new JLabel();

        bottomPanel.add(displayImageLabel);

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
        displayImage(is_nedded);
        is_nedded = false;
    }

    /**
     * Displays the scaled image based on the given dimensions.
     * @param is_nedded Indicates whether the natural image size should be displayed initially.
     */
    private void displayImage(boolean is_nedded) {
        try {
            String imagePath = "./2.jpg";
            InputStream inputStream = getClass().getResourceAsStream(imagePath);
            BufferedImage originalImage = ImageIO.read(inputStream);
            if(is_nedded) {
                widthTextField.setText(String.valueOf(originalImage.getWidth()));
                heightTextField.setText(String.valueOf(originalImage.getHeight()));
            }

            try {
                int width = Integer.parseInt(widthTextField.getText());
                int height = Integer.parseInt(heightTextField.getText());
                // Scaling the image
                Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(scaledImage);
                displayImageLabel.setIcon(icon);

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Неправильний формат числа! Введіть ціле значення.");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Помилка завантаження зображення: " + ex.getMessage());
        }
    }
    /**
     * Main method to create the window.
     * @param args Command-line arguments (unused)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ex15();
            }
        });
    }
}

