/*
Введення тексту до графічного вікна програми. У вікні визначено рядок меню
(JMenuBar), у якому визначено два меню (JMenu) – "Шрифт" та "Стиль".
У меню "Шрифт" визначено три пункти меню (JRadioButtonMenuItem):
"Times New Roman" (шрифт за замовчуванням), "Arial" та "Verdana".
У меню "Стиль" визначено чотири пункти меню (JRadioButtonMenuItem): "Простий"
(шрифт за замовчуванням), "Жирний", "Курсів" та "Жирний курсив".
У текстовій панелі (JTextPane) "Введення тексту" вікна програми вводиться текст,
який набирається на клавіатурі. При виборі одного з пунктів меню текст у
 панелі відображається відповідним шрифтом та/або стилем.
 */

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class demonstrates text input with font and style selections using menus.
 */
public class ex17 {
    private JFrame frame;
    private JTextPane textPane;

    /**
     * Constructor to initialize the graphical window and components.
     */
    public ex17() {
        frame = new JFrame("Графічне вікно програми");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textPane = new JTextPane();
        frame.add(new JScrollPane(textPane), BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();

        // Font Menu
        JMenu fontMenu = new JMenu("Шрифт");
        ButtonGroup fontGroup = new ButtonGroup();
        String[] fonts = {"Times New Roman", "Arial", "Verdana"};
        for (String font : fonts) {
            JRadioButtonMenuItem fontItem = new JRadioButtonMenuItem(font);
            fontItem.addActionListener(new FontListener(font));
            fontGroup.add(fontItem);
            fontMenu.add(fontItem);

            if (font.equals("Times New Roman")) {
                fontItem.setSelected(true);
            }
        }

        menuBar.add(fontMenu);

        // Style Menu
        JMenu styleMenu = new JMenu("Стиль");
        ButtonGroup styleGroup = new ButtonGroup();
        String[] styles = {"Простий", "Жирний", "Курсів", "Жирний курсив"};
        for (String style : styles) {
            JRadioButtonMenuItem styleItem = new JRadioButtonMenuItem(style);
            styleItem.addActionListener(new StyleListener(style));
            styleGroup.add(styleItem);
            styleMenu.add(styleItem);

            if (style.equals("Простий")) {
                styleItem.setSelected(true);
            }
        }
        menuBar.add(styleMenu);

        frame.setJMenuBar(menuBar);
        frame.setSize(500, 400);
        frame.setVisible(true);
    }

    /**
     * ActionListener for font selection.
     */
    class FontListener implements ActionListener {
        private String fontName;

        public FontListener(String fontName) {
            this.fontName = fontName;
        }

        public void actionPerformed(ActionEvent e) {
            Font font = textPane.getFont();
            textPane.setFont(new Font(fontName, font.getStyle(), font.getSize()));
        }
    }

    /**
     * ActionListener for style selection.
     */
    class StyleListener implements ActionListener {
        private String styleName;

        public StyleListener(String styleName) {
            this.styleName = styleName;
        }

        public void actionPerformed(ActionEvent e) {
            Font font = textPane.getFont();
            int style = Font.PLAIN;

            if (styleName.equals("Жирний")) {
                style = Font.BOLD;
            }
            else if (styleName.equals("Курсів")) {
                style = Font.ITALIC;
            }
            else if (styleName.equals("Жирний курсив")) {
                style = Font.BOLD | Font.ITALIC;
            }

            textPane.setFont(new Font(font.getFamily(), style, font.getSize()));
        }
    }
    /**
     * Main method to start the graphical window.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ex17();
            }
        });
    }
}
