import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class HtmlRead implements ActionListener {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JTextArea statusLabel;
    private JPanel controlPanel;
    private JMenuBar mb;
    private JMenu file, edit, help;
    private JMenuItem cut, copy, paste, selectAll;
    private JTextArea ta;
    private JTextArea tb;
    private int WIDTH = 800;
    private int HEIGHT = 700;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane1;
    private JScrollPane scrollPane2;


    public void SwingControlDemo() {
        prepareGUI();
    }

    public static void main(String[] args) {
        //SwingControlDemo swingControlDemo = new SwingControlDemo();
       // swingControlDemo.showEventDemo();
        HtmlRead h = new HtmlRead();
        h.SwingControlDemo();
        h.showEventDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(3, 1));


        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);

        mb = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        mb.add(file);
        mb.add(edit);
        mb.add(help);

        ta = new JTextArea();
        ta.setBounds(50, 5, WIDTH - 100, HEIGHT - 50);
        tb = new JTextArea();
        tb.setBounds(50, 5, WIDTH - 100, HEIGHT - 50);
        mainFrame.add(mb);
        mainFrame.setJMenuBar(mb);

        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JTextArea();
        statusLabel.setSize(350, 100);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        scrollPane = new JScrollPane(statusLabel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane1 = new JScrollPane(ta);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane2 = new JScrollPane(tb);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // mainFrame.add(headerLabel);
        mainFrame.add(scrollPane1);
        mainFrame.add(scrollPane2);
        mainFrame.add(controlPanel);
        mainFrame.add(scrollPane);




        //mainFrame.add(statusLabel);
        mainFrame.setVisible(true);

    }

    private void showEventDemo() {
        headerLabel.setText("Control in action: Button");

        JButton okButton = new JButton("Search");


        okButton.setActionCommand("OK");


        okButton.addActionListener(new ButtonClickListener());


        controlPanel.add(okButton);


        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut)
            ta.cut();
        if (e.getSource() == paste)
            ta.paste();
        if (e.getSource() == copy)
            ta.copy();
        if (e.getSource() == selectAll)
            ta.selectAll();
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("OK")) {
                statusLabel.setText("Ok Button clicked.\n");
                try {
                    System.out.println();
                    System.out.print("hello \n");
                    URL url = new URL(ta.getText());
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(url.openStream())
                    );
                    String line;
                    while ( (line = reader.readLine()) != null ) {
                        if(line.contains("href")&&line.contains(tb.getText())&&line.contains("http")){
                            System.out.println( "og: "+line);

                            int index = line.indexOf("href");
                            line = line.substring(index + 6);
                            int endD = line.indexOf("\"");
                            int endS = line.indexOf("\'");
                            int end = 0;
                            if(endD==-1){
                                  end = endS;
                            }
                            if(endS==-1){
                                end = endD;
                            }
                            if(endD != -1 && endS != -1){
                                if(endD > endS){
                                    end = endS;
                                }
                                else{
                                    end = endD;

                                }
                            }

                            System.out.println(line.substring(0, end));
                            statusLabel.append(line.substring(0, end)+"\n");
                        }


                    }
                    reader.close();
                } catch(Exception ex) {
                    System.out.println(ex);
                }



        } else if (command.equals("Submit")) {
                statusLabel.setText("Submit Button clicked.\n");
            } else {
                statusLabel.setText("Cancel Button clicked.");
            }
        }
    }

    /*public HtmlRead() {

        try {
            System.out.println();
            System.out.print("hello \n");
            URL url = new URL("https://www.milton.edu/");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;
            while ( (line = reader.readLine()) != null ) {


                System.out.println(line);
            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }

    }*/

}


