/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawldata;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Ct_fsp_2
 */
public class AddFiled{
    private JTextField txtField ;
    public AddFiled(){
        txtField = new JTextField(20);
    }
    public JTextField initAddField(){
        return txtField;
    }
}
