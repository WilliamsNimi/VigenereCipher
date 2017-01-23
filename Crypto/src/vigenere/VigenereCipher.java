package vigenere;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class VigenereCipher extends JFrame {
    public static JLabel text, key, encryptedTextLabel;
    public static JTextField keyWord;
    public static JTextArea plainText;
    public static JTextArea encryptedTextArea;
    public static int width = 500, height = 700;
    public static Button decrypt, encrypt;
    Container container;
    GridBagConstraints object;
    encryptButtonHandler eButtonHandler;
    decryptButtonHandler dButtonHandler;
    

    static encryption newCipher = new encryption();
    
    public VigenereCipher ()
    {
        container = getContentPane();
        container.setBackground(Color.LIGHT_GRAY);
        container.setLayout(new GridBagLayout());
        object = new GridBagConstraints();
        object.insets = new Insets(5,5,0,0);
        
        text = new JLabel("Plain text");
        object.gridx = 1;
        object.gridy = 1;
        container.add(text, object);
        
        key = new JLabel("Enter KeyWord");
        object.gridx = 1;
        object.gridy = 2;
        container.add(key, object);
        
        plainText = new JTextArea();
        object.gridx = 2;
        object.gridy = 1;
        container.add(plainText, object);
        plainText.setRows(10);
        plainText.setColumns(20);
        plainText.setLineWrap(true);
        
        keyWord = new JTextField(10);
        object.gridx = 2;
        object.gridy = 2;
        container.add(keyWord, object);
        
        encrypt = new Button("ENCRYPT");
        eButtonHandler = new encryptButtonHandler();
        encrypt.addActionListener(eButtonHandler);
        object.gridx = 2;
        object.gridy = 3;
        container.add(encrypt, object);
        
        decrypt = new Button("DECRYPT");
        dButtonHandler = new decryptButtonHandler();
        decrypt.addActionListener(dButtonHandler);
        object.gridx = 3;
        object.gridy = 3;
        container.add(decrypt, object);
        
        encryptedTextLabel = new JLabel("Encrypted Text");
        object.gridx = 1;
        object.gridy = 4;
        container.add(encryptedTextLabel, object);
        
        encryptedTextArea = new JTextArea();
        object.gridx = 2;
        object.gridy = 4;
        container.add(encryptedTextArea, object);
        encryptedTextArea.setRows(10);
        encryptedTextArea.setColumns(20);
        encryptedTextArea.setLineWrap(true);
        
        setTitle("Vigenere Cipher");
        setSize(width,height);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
    }
    
    public class encryptButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
           String plainTextString = plainText.getText();
           String keyString = keyWord.getText();
           String encryptedText = newCipher.encrypt(plainTextString, keyString);
           encryptedTextArea.setText(encryptedText);
           plainText.setText("");
        }
    }
    
    public class decryptButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String encryptedTextString = encryptedTextArea.getText();
            String keyString = keyWord.getText();
            String decryptedText = newCipher.decrypt(encryptedTextString, keyString);
            plainText.setText(decryptedText);
            encryptedTextArea.setText("");
        }
    }
    
    public static void main(String[] args) {
        VigenereCipher obj = new VigenereCipher();

     }
}
