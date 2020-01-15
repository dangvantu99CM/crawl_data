/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crawldata;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import net.miginfocom.swing.MigLayout;
import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

/**
 *
 * @author Ct_fsp_2
 */
public class CrawlData{

    JFrame frame = new JFrame();
    JPanel panel = new JPanel();

    JButton btnImport = new JButton("Import");
    JButton btnExport = new JButton("Export");
    JButton btnFilter = new JButton("Lọc theo");
    
    JTextField txtFilter = new JTextField();
    
    JLabel lblDsc = new JLabel("Result:");
    JTextArea txaDsc = new JTextArea(10, 10);
    
    JFileChooser chooser;
    String choosertitle;
    
    File selectFolder=null;
    ArrayList<File> listFile = new ArrayList<>();
    ArrayList<String> listResultLinkFile = new ArrayList<>();
    ArrayList<String> listIframe = new ArrayList<>();
    ArrayList<String> listScript = new ArrayList<>();

    public CrawlData() {
            panel.setLayout(new MigLayout());
            panel.add(btnImport, "skip, split2");
            panel.add(btnExport, "wrap");
            panel.add(lblDsc, "top");
            panel.add(new JScrollPane(txaDsc), "push, grow");
            frame.add(panel);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setSize(600,600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
    }
    
    public File actionListener(){
            btnImport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    int result;
                    chooser = new JFileChooser(); 
                    chooser.setCurrentDirectory(new java.io.File("."));
                    chooser.setDialogTitle(choosertitle);
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    chooser.setAcceptAllFileFilterUsed(false);
                    if (chooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) { 
                        System.out.println("getCurrentDirectory(): " 
                           +  chooser.getCurrentDirectory());
                        System.out.println("getSelectedFile() : " 
                           +  chooser.getSelectedFile());
                        selectFolder=chooser.getSelectedFile();
                        try {
                            listFilesForFolder(chooser.getSelectedFile());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(CrawlData.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else {
                        System.out.println("No Selection ");
                    }
                }
            });
            
            return selectFolder;
    }
    
    public void listFilesForFolder(final File folder) throws FileNotFoundException {
        for (final File file : folder.listFiles()) {
            if (file.isDirectory()) {
                listFilesForFolder(file);
            } else {
                String fileName=file.getName();                
                String pattern = "(^.*[.html]$)";
                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(fileName);
                if (m.find()) {
                    listFile.add(file);
                    ReadFile.readFile(file,listIframe,""); 
                }
            }
        }
    }
 
    public static void main(String[] args) throws FileNotFoundException {
        CrawlData cd = new CrawlData();
        cd.actionListener();
    }

}
