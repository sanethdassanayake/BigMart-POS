package com.seagile.bigmart.component;

import javax.swing.*;
import java.text.*;
import java.util.Locale;
import javax.swing.event.DocumentListener;

public class CurrencyTextField extends JTextField {
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));

    public CurrencyTextField() {
        super();
        setHorizontalAlignment(SwingConstants.RIGHT);
        setText(formatCurrency(0.00));

        // Listen to document changes instead of key events
        getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                formatAndUpdate();
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                formatAndUpdate();
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
    }

    private void formatAndUpdate() {
        SwingUtilities.invokeLater(() -> {
            String text = getText().replaceAll("[^\\d]", "");
            if (!text.isEmpty()) {
                try {
                    double value = Double.parseDouble(text) / 100.0;
                    setText(formatCurrency(value));
                } catch (NumberFormatException ex) {
                    // Handle any potential number format errors
                    setText("");
                }
            } else {
                setText(formatCurrency(0.00));
            }
            setCaretPosition(getText().length());
        });
    }

    private String formatCurrency(double value) {
        return currencyFormat.format(value);
    }

    public double getCurrencyValue() {
        String text = getText().replaceAll("[^\\d.]", "");
        return text.isEmpty() ? 0.00 : Double.parseDouble(text) / 100.0;
    }
}
