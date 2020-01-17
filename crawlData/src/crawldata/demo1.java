
package crawldata;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class demo1 extends JFrame{
    private JTextField setUrl, setId;

    private JLabel url;

    private JButton ok, setText;
    private JTextArea textarea;
    private JScrollPane sp;

    private final static String newline = "\n";
    public demo1() {
        this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        url = new JLabel("URL");
        ok = new JButton("OK");
        setId = new JTextField(100);
        setUrl = new JTextField(100);
        textarea = new JTextArea();
        setText = new JButton("Reset");
        JPanel p = new JPanel();
        p.setLayout(new MigLayout("", "[][grow]", "[][][]"));
        p.add(setUrl, "cell 1 0,growx");
        p.add(url, "cell 0 0,alignx trailing");
        p.add(ok, "cell 2 0,growx");
        p.add(setText, "cell 2 1,growx");
        this.add(p);
        this.setVisible(true);
    }
    public static void main(String []args){
        demo1 demo = new demo1();
    }
}
