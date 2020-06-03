package allComands;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringsFormatter {

    public static boolean checkFloat(int i,String price) {
        if(price.contains(".")) {
            Pattern pattern = Pattern.compile("[0-9]{0,"+(i-1)+"}\\.?[0-9]{0,1}");
            Matcher matcher = pattern.matcher(price);
            return matcher.matches();
        } else {
            Pattern pattern = Pattern.compile("[0-9]{0,"+(i-1)+"}");
            Matcher matcher = pattern.matcher(price);
            return matcher.matches();
        }
    }

    public static void setFloatPattern(int length,JTextField jTextField) {
        jTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
            String x = "0123456789.";
            char c = e.getKeyChar();
            if (x.indexOf(c) == -1) {
                e.consume();
            } else if (!checkFloat(length,jTextField.getText())) {
                e.consume();
            }
            }
        });
    }

    public static boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile("^.+@.+\\..+$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean checkBirthDate(String date) {
        LocalDate birth = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return Period.between(birth,LocalDate.now()).getYears() >= 18;
    }

    public static void setTextFieldLength(int length, JTextField jTextField) {
        jTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if(jTextField.getText().length() >= length)
                        e.consume();
            }
        });
    }

    public static void setOnlyLetters(JTextField jTextField) {
        jTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c > '0') && (c < '9')) || (c == ' ')) {
                    e.consume();
                }
            }
        });
    }

    public static void setLettersWithSpaces(JTextField jTextField) {
        jTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c > '0') && (c < '9'))) {
                    e.consume();
                }
            }
        });
    }

    public static void setNoSpaces(JTextField jTextField) {
        jTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (c == ' ') {
                    e.consume();
                }
            }
        });
    }

    public static void setOnlyDigits(JTextField jTextField) {
        jTextField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if ( ((c < '0') || (c > '9'))) {
                    e.consume();
                }
            }
        });
    }

    public static boolean checkPostalCode(String postal) {
        Pattern pattern = Pattern.compile("\\d{2}-\\d{3}");
        Matcher matcher = pattern.matcher(postal);
        return matcher.matches();
    };



    public static JFormattedTextField getPostalCodeTextField() throws ParseException {
        MaskFormatter maskFormatter = new MaskFormatter("##-###");
        maskFormatter.setPlaceholderCharacter('_');
        return new JFormattedTextField(maskFormatter);
    }

    public static boolean isDateValid(String date) {
        LocalDate birth = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return birth.isBefore(LocalDate.now()) || birth.equals(LocalDate.now());
    }

    public static boolean isFlightDateValid(String date, String datearr) {
        LocalDate dep = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate arr = LocalDate.parse(datearr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return dep.isAfter(LocalDate.now()) && (dep.isBefore(arr) || dep.isEqual(arr));
    }

    public static boolean isDatesAreEqual(String date, String datearr) {
        LocalDate dep = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate arr = LocalDate.parse(datearr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return dep.isEqual(arr);
    }

    public static boolean isDepTimeIsBefore(String depTime, String arrTime) {
        return depTime.compareTo(arrTime) < 0;
    }
}
