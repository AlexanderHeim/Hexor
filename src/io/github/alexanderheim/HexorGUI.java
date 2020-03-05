package io.github.alexanderheim;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;

public class HexorGUI implements ActionListener {
    File selectedF;
    JFrame f;
    JTextArea ta;
    HexorGUI() {
        f = new JFrame();
        //TEXT AREA
        this.ta = new JTextArea();
        this.ta.setLineWrap(true);

        //SCROLLBAR
        JScrollPane s = new JScrollPane(this.ta);
        f.add(s);

        //Open File Button
        JButton openFileButton = new JButton("Open File");
        openFileButton.addActionListener(this);

        //Save Button
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new GridLayout());
        buttonPanel.add(openFileButton);
        buttonPanel.add(saveButton);
        f.add(buttonPanel, BorderLayout.SOUTH);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 650);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton){
            JButton source = (JButton) e.getSource();
            if(source.getText().equals("Save")){
                System.out.println("Savebutton Pressed");

                ByteUtility.writeBytes(this.selectedF.getAbsolutePath(), ByteUtility.hexStringToByteArray(ByteUtility.removeSpaceFromHexString(this.ta.getText())));
            }
            if(source.getText().equals("Open File")){
                System.out.println("Open File Button Pressed");

                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                int returnValue = jfc.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    this.selectedF = jfc.getSelectedFile();
                    System.out.println(this.selectedF.getAbsolutePath());

                    this.ta.setText(ByteUtility.addSpaceToHexString(ByteUtility.bytesToHex(ByteUtility.readBytes(this.selectedF.getAbsolutePath()))));
                }
            }
        }
    }
}
