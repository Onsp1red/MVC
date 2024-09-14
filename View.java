package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

//View is a contact to user or we called "User Interfeace (UI)"
public class View {
    //New Controller class
    private Controller controller;

    //Build View constructor
    //Display UI from controller
    View(Controller controller) {
        this.controller = controller;
        initializeUI();
    }

    //Make UI
    private void initializeUI() {
        //Build frame
        JFrame frame = new JFrame("Song Score System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);

        //Build Panel
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel, controller);
        frame.setVisible(true);
    }

    private void placeComponents(JPanel panel, Controller controller) {
        //Set Panel layout type GridLayout with 5 rows and 2 collums
        panel.setLayout(new GridLayout(5, 2));

        //Build Title field
        panel.add(new JLabel("Song Title:"));
        JTextField titleField = new JTextField();
        panel.add(titleField);

        //Build Duration field
        panel.add(new JLabel("Duration (seconds):"));
        JTextField durationField = new JTextField();
        panel.add(durationField);

        //Build Singer field
        panel.add(new JLabel("Singers (comma-separated):"));
        JTextField singersField = new JTextField();
        panel.add(singersField);
        
        //Add button
        JButton addButton = new JButton("Add Song");
        panel.add(addButton);
        JButton displayButton = new JButton("Display Songs");
        panel.add(displayButton);

        //Make button clicks
        addButton.addActionListener((ActionEvent e) -> {
            String title = titleField.getText();
            int duration = Integer.parseInt(durationField.getText());
            String[] singers = singersField.getText().split(",");
            controller.addSong(title, duration, singers);
            JOptionPane.showMessageDialog(null, "Song Added!");
        });

        //When click button, Message dialog will show.
        displayButton.addActionListener((ActionEvent e) -> {
            List<Model.Song> songs = controller.getSongs();
            songs.forEach(song -> JOptionPane.showMessageDialog(null, song.getTitle() + " - Score: " + song.getScore()));
        });
    }
}