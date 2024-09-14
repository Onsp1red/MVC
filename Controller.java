package mvc;

import java.util.List;

//Controller is connect between model and view
public class Controller {
    //New Model class
    private Model model;

    //Build model constructor
    Controller(Model model) {
        this.model = model;
    }

    //Add song function
    public void addSong(String title, int duration, String[] singers) {
        //model will be added song
        model.addSong(title, duration, singers);
    }

    //Get song from model then send to view
    public List<Model.Song> getSongs() {
        return model.getSongs();
    }
}