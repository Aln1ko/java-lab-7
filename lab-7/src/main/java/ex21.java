/*
Виведення рядка заданим шрифтом та заданого кольору у графічному вікні.
У верхній панелі (JPanel) "Керування виводом" задається напис (JLabel)
"Текст:" та текстове поле (JTextField), напис (JLabel) "Гарнітура:",
список (JSpinner), що розгортається, зі значеннями "Times New Roman"
(шрифт по за замовчуванням), "Arial" і "Verdana", напис (JLabel) "Колір:"
і список, що розгортається (JSpinner) зі значеннями "Чорний" (за замовчуванням),
"Червоний", "Зелений" і "Синій", а також кнопка (JButton) "Вивести рядок".
У нижній панелі (JPanel) "Виведення рядка" виводиться у графічному контексті у
довільному місці порожній рядок. При заданні тексту рядка в текстовому полі,
параметрів рядка у списках верхньої панелі, що обертаються, і натисканні кнопки
"Виведення рядка" рядок заданого вмісту і кольору виводиться заданим шрифтом у
нижній панелі.

 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class demonstrates displaying a string with a specified font and color in a graphical window.
 */
public class ex21 {
    private JFrame frame;
    private JPanel outputPanel;
    private int x,y;
    private JSpinner fontSpinner,colorSpinner;
    private JLabel displayLabel;
    {
        x = 100;
        y = 100;
    }

    /**
     * Constructor to initialize the graphical window and components.
     */
    public ex21(){
        frame = new JFrame("Графічне вікно програми");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        JLabel text = new JLabel("Текст:");
        JTextField textField = new JTextField(10);
        controlPanel.add(text);
        controlPanel.add(textField);

        JLabel garnitura = new JLabel("Гарнітура:");
        String[] fonts = {"Times New Roman","Arial","Verdana"};
        SpinnerModel fontModel = new SpinnerListModel(fonts);
        fontSpinner = new JSpinner(fontModel);
        Dimension preferredSize = new Dimension(200, 30);
        fontSpinner.setPreferredSize(preferredSize);
        fontSpinner.setValue("Times New Roman");
        controlPanel.add(garnitura);
        controlPanel.add(fontSpinner);

        JLabel color = new JLabel("Колір:");
        String[] colors = { "Чорний ", "Червоний ", "Зелений ","Синій "};
        SpinnerModel colorModel = new SpinnerListModel(colors);
        colorSpinner = new JSpinner(colorModel);
        Dimension preferredSize1 = new Dimension(100, 30);
        colorSpinner.setPreferredSize(preferredSize1);
        colorSpinner.setValue("Чорний ");
        controlPanel.add(color);
        controlPanel.add(colorSpinner);

        JButton button = new JButton( "Вивести рядок");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                String selectedFont = (String) fontSpinner.getValue();
                String selectedColor = (String) colorSpinner.getValue();

                Font font = new Font(selectedFont, Font.PLAIN, 14);
                Color color = getColorByName(selectedColor);

                displayLabel.setFont(font);
                displayLabel.setForeground(color);
                displayLabel.setText(text);
            }
        });
        controlPanel.add(button);

        frame.add(controlPanel,BorderLayout.NORTH);

        outputPanel = new JPanel(new BorderLayout());
        outputPanel.setLayout(null);
        displayLabel = new JLabel(" ");
        displayLabel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        displayLabel.setBounds(x,y,250, 20);
        outputPanel.add(displayLabel);
        frame.add(outputPanel,BorderLayout.CENTER);


        frame.setSize(1000, 600);
        frame.setVisible(true);
    }

    /**
     * Retrieves the color object based on the color name.
     * @param colorName The name of the color.
     * @return The Color object corresponding to the color name.
     */
    private Color getColorByName(String colorName) {
        switch (colorName) {
            case "Чорний ":
                return Color.BLACK;
            case "Червоний ":
                return Color.RED;
            case "Зелений ":
                return Color.GREEN;
            case "Синій ":
                return Color.BLUE;
            default:
                return Color.BLACK;
        }
    }

    /**
     * Main method to start the graphical window.
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ex21();
            }
        });
    }

}
