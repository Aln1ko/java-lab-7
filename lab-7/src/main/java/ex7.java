/*
Зміна написи у графічному вікні. У центрі верхньої панелі (JPanel)
"Виведення напису" у графічному контексті виводиться довільний напис.
У нижній панелі (JPanel) "Параметри напису" задаються наступні компоненти:
напис (JLabel) "Текст:", текстове поле (JTextField), напис (JLabel) "Колір:",
список (JSpinner), що розгортається, зі значеннями: "Чорний" ( колір за замовчуванням),
"Червоний", "Зелений" і "Синій", напис (JLabel) "Розмір:", список,
 що розгортається (JSpinner) зі значеннями "10pt", "12pt" (розмір за замовчуванням),
 "14pt" і "16pt", напис (JLabel) "Координата X:", текстове поле (JTextField),
 напис (JLabel) "Координата Y:", текстове поле (JTextField), а також кнопка (JButton)
 "Вивести рядок". Спочатку в текстових полях "Координата X:" та "Координата Y:"
 встановлюються координати початку базової лінії напису (текстові поля задані
 як нередаговані). Під час введення даних напис змінює текст, колір та/або розмір.
 При клацанні мишею в області верхньої панелі текстових полях виводяться координати
 точки клацання, і напис перемальовується в цій точці.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class demonstrates a graphical window with the ability to change text properties and coordinates dynamically.
 */
public class ex7 extends JFrame {
    private int x,y;
    private JTextField textFieldX,textFieldY;
    private JSpinner sizeSpinner,colorSpinner;
    private JLabel displayLabel;
    {
        x = 100;
        y = 100;
    }

    /**
     * Constructor to initialize the graphical components and set up the window.
     */
    public ex7() {
        super("Виведення напису");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280,720);
        setLocation(100,100);
        // Panel to display the text
        JPanel textPanel = new JPanel();
        textPanel.setLayout(null);
        displayLabel = new JLabel("Виведення напису");
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        displayLabel.setBounds(x,y,250,30);
        // Mouse listener to update coordinates on click
        textPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                textFieldX.setText(String.valueOf(e.getX()));
                System.out.println(e.getX());
                textFieldY.setText(String.valueOf(e.getY()));
                System.out.println(e.getY());
                updateLabel();
            }
        });
        textPanel.add(displayLabel);
        textPanel.setBackground(Color.white);
        add(textPanel,BorderLayout.CENTER);
        // Panel for controlling text properties
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(Color.white);
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        // Components for text properties
        JLabel text = new JLabel("Текст:");
        JTextField textField = new JTextField("Виведення напису",15);
        controlPanel.add(text);
        controlPanel.add(textField);

        JLabel color = new JLabel( "Колір:");
        String[] colors = {"Чорний ", "Червоний ", "Зелений ", "Синій "};
        SpinnerListModel spinnerModel = new SpinnerListModel(colors);
        colorSpinner = new JSpinner(spinnerModel);
        Dimension preferredSize = new Dimension(100, 30);
        colorSpinner.setPreferredSize(preferredSize);
        colorSpinner.setValue("Чорний ");
        controlPanel.add(color);
        controlPanel.add(colorSpinner);

        JLabel size = new JLabel("Розмір:");
        String[] sizes = {"10pt ", "12pt ", "14pt ", "16pt "};
        SpinnerListModel spinnerModelsize = new SpinnerListModel(sizes);
        sizeSpinner = new JSpinner(spinnerModelsize);
        Dimension preferredSize1 = new Dimension(100, 30);
        sizeSpinner.setPreferredSize(preferredSize1);
        sizeSpinner.setValue("12pt ");
        controlPanel.add(size);
        controlPanel.add(sizeSpinner);

        JLabel coorX = new JLabel( "Координата X:");
        textFieldX = new JTextField("100",10);
        textFieldX.setEditable(false);
        controlPanel.add(coorX);
        controlPanel.add(textFieldX);

        JLabel coorY = new JLabel( "Координата Y:");
        textFieldY = new JTextField("100",10);
        textFieldY.setEditable(false);
        controlPanel.add(coorY);
        controlPanel.add(textFieldY);

        // Button to update the label
        JButton button = new JButton("Вивести рядок");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateLabel();
            }
        });
        controlPanel.add(button);

        add(controlPanel,BorderLayout.SOUTH);
        setVisible(true);
    }
    /**
     * Updates the text properties based on user input.
     */
    private void updateLabel() {
        String text = ((JTextField) ((JPanel) getContentPane().getComponent(1)).getComponent(1)).getText();
        String selectedColor = (String) colorSpinner.getValue();
        String selectedSize = (String) sizeSpinner.getValue();
        x = Integer.parseInt(textFieldX.getText());
        y = Integer.parseInt(textFieldY.getText());
        System.out.println(selectedColor+"  "+selectedSize+"  "+x+"  "+y);
        String sizeWithoutSpaces = selectedSize.replaceAll("\\s", ""); // Удаление всех пробелов из строки
        String sizeWithoutUnit = sizeWithoutSpaces.replaceAll("[^\\d]", ""); // Удаление символов, не являющихся числами
        Font font = new Font("Arial", Font.PLAIN, Integer.parseInt(sizeWithoutUnit));
        Color color;
        switch (selectedColor) {
            case "Чорний ":
                color = Color.BLACK;
                break;
            case "Червоний ":
                color = Color.RED;
                break;
            case "Зелений ":
                color = Color.GREEN;
                break;
            case "Синій ":
                color = Color.BLUE;
                break;
            default:
                color = Color.BLACK;
        }

        displayLabel.setText(text);
        displayLabel.setFont(font);
        displayLabel.setForeground(color);
        displayLabel.setLocation(x, y);
        displayLabel.repaint();
    }
    /**
     * Main method to create the window.
     * @param args Command-line arguments (unused)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ex7();//створення вікна
            }
        });
    }
}
