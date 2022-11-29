package textproc;
import java.awt.*;
import javax.swing.*;

import java.util.Map;
import java.util.Map.Entry;
import java.awt.event.*;  



public class BookReaderController {

    JRadioButton b1, b2;
    JButton b;


    public BookReaderController(GeneralWordCounter counter) {
        SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 1000, 500));
    }

    private void createWindow(GeneralWordCounter counter, String title, int width, int height) {


        SortedListModel<Map.Entry<String, Integer>> listModel = new SortedListModel<Map.Entry<String, Integer>>(counter.getWordList());
        JList<Map.Entry<String, Integer>> myList = new JList<Map.Entry<String, Integer>>(listModel);


        //myList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        JFrame frame = new JFrame(title);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();

        JScrollPane scrollPane = new JScrollPane(myList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(width, height));

        //Knappar
        b1 = new JRadioButton("Alphabetic");
        b2 = new JRadioButton("Frequency");
        ButtonGroup group = new ButtonGroup();
        group.add(b1);
        group.add(b2);
        
        //Sortering
        b1.addActionListener(event -> listModel.sort( (x, y) -> { 
            return ((Entry<String, Integer>) x).getKey().compareTo(((Entry<String, Integer>) y).getKey());
        }));

        b2.addActionListener(event -> listModel
            .sort((x, y) -> -((x).getValue() - ( y).getValue()))); 


        JPanel panel = new JPanel();
        JButton Exit = new JButton("Exit");

        //Lägger till knappar i panelen
        panel.add(b1);
        panel.add(b2);

        b1.addActionListener( (n) -> { System.out.println("Alphabetical"); } );
        b2.addActionListener( (n) -> { System.out.println("Frequency"); } );

        //Sökfält och sökfältsknapp

        JPanel searchPanel = new JPanel();
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Find");
        //searchField.setPreferredSize(new Dimension(500, (int) searchButton.getPreferredSize().getHeight()));
        searchPanel.add(searchButton);
        searchPanel.add(searchField);
        frame.getRootPane().setDefaultButton(searchButton);


        searchButton.addActionListener(e -> {
            String searchedKey = searchField.getText().toLowerCase().trim();
            boolean found = false;

            for (int i = 0; i < listModel.getSize(); i++) {
                String currentKey = ((Entry<String, Integer>) listModel.getElementAt(i)).getKey();

                if (currentKey.equals(searchedKey)) {
                    myList.setSelectedIndex(i);
                    myList.ensureIndexIsVisible(i);
                    found = true;
                    break;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(frame, "Word not found");
            }
        });



        pane.add(scrollPane, BorderLayout.NORTH);
        pane.add(panel, BorderLayout.CENTER);
        pane.add(searchPanel, BorderLayout.SOUTH);

        //program exit-knapp
        panel.add(Exit);

        //Stänger systemet vid tryck av exitknapp
        Exit.addActionListener((n) -> { System.exit(0); });
 

        frame.pack();
        frame.setVisible(true);

    }

    /*public void actionPerformed(ActionEvent e){    
        if(b1.isSelected()){    
        System.out.println("Alphabetic");        
        }    
        if(b2.isSelected()){    
        System.out.println("Frequency");    
        }    
    }  */

}
