import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
/*from   w w w  .java  2 s  . c o m*/
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

public class Main {
  public static void main(String[] args) {
    JPanel panel = new JPanel();
    JTextField tf1 = new JTextField(10);
    panel.add(tf1);
    JTextField tf2 = new JTextField(10);
    panel.add(tf2);

    new TextPrompt("First Name", tf1);
    new TextPrompt("Last Name", tf2);

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}

class TextPrompt extends JLabel implements FocusListener, DocumentListener {
  JTextComponent component;
  Document document;

  public TextPrompt(String text, JTextComponent component) {
    this.component = component;
    document = component.getDocument();

    setText(text);
    setFont(component.getFont());
    setBorder(new EmptyBorder(component.getInsets()));

    component.addFocusListener(this);
    document.addDocumentListener(this);

    component.add(this);
  }

  public void checkForPrompt() {
    if (document.getLength() == 0)
      setSize(component.getSize());
    else
      setSize(0, 0);
  }

  public void focusGained(FocusEvent e) {
    checkForPrompt();
  }

  public void focusLost(FocusEvent e) {
    setSize(0, 0);
  }


  public void insertUpdate(DocumentEvent e) {
    checkForPrompt();
  }

  public void removeUpdate(DocumentEvent e) {
    checkForPrompt();
  }

  public void changedUpdate(DocumentEvent e) {
  }

}