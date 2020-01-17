
package crawldata.ActionButon;

import javax.swing.JButton;

public class Button {
    private String text="";
    private Action action=null;
    public Button(String text,Action clickMe){
        this.text=text;
        this.action = clickMe;
    }
    
}
