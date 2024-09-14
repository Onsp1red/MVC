package mvc;

import java.util.ArrayList;
import java.util.List;

//Model is store all logical syntax and data
public class Model {
    private List<Song> songs = new ArrayList<>();

    //When click add song the songs will be add in Song List
    public void addSong(String title, int duration, String[] singers) {
        int score = calculateScore(duration, singers);
        songs.add(new Song(title, duration, singers, score));
    }

    //Calculate Score
    private int calculateScore(int duration, String[] singers) {
        /*----------- Model 1 -----------*/
        //requirements : ให้สร้างโมเดลแยกกันตามจํานวนคนร้อง หากคนร้องมีเพียงคนเดียว
        //Model 1 (When singer is one)
        //Make totalDuration to calculate duration because of 1 singer.
        if (singers.length == 1) {
            //Score calculation for a single singer
            int totalDuration = songs.stream().mapToInt(Song::getDuration).sum();
            //requirements : (จํานวนวินาทีที่ร้องเพลงมาก่อนหน้าเพลงนี้ทั้งหมดรวมกัน % 100)
            return totalDuration % 100;

        /*----------- Model 2 -----------*/
        //requirements : ให้สร้างโมเดลแยกกันตามจํานวนคนร้อง หากคนร้องมีสองคน
        //Model 2 (When singer is two)
        //Make totalChars to calculate sum of chars.
        } else if (singers.length == 2) {
            //Score calculation for two singers
            int totalChars = 0;
            //requirements : (ระยะเวลาของเพลงนี้ x จํานวนตัวอักษรของชื่อคนร้องทั้งคู่ในรอบนี้รวมกัน) % 100
            for (String singer : singers) {
                totalChars += singer.length();
            }
            return (duration * totalChars) % 100;

        /*----------- Model 3 -----------*/
        //requirements : ให้สร้างโมเดลแยกกันตามจํานวนคนร้อง หากคนร้องมีสามคน
        //Model 3 (When singer is three)
        //Make totalCharsPrevious to calculate sum of previous chars 
        //Make totalCharsCurrent  to calculate sum of present chars.
        } else if (singers.length == 3) {
            //Score calculation for three singers
            int totalCharsPrevious = songs.stream()
                .flatMap(song -> List.of(song.getSingers()).stream())
                .mapToInt(String::length).sum();
            int totalCharsCurrent = List.of(singers).stream().mapToInt(String::length).sum();
            //requirements : (จํานวนตัวอักษรของชื่อคนร้องก่อนหน้านี้ทั้งหมด x จํานวนตัวอักษรของชื่อคนร้องทั้งหมดในรอบนี้) % 100
            return (totalCharsPrevious * totalCharsCurrent) % 100;
        }
        //If it's not one ,two or three
        return 0;
    }

    //Getter Method
    public List<Song> getSongs() {
        return songs;
    }

    //Inner class to represent a Song
    public class Song {
        private String title;
        private int duration;
        private String[] singers;
        private int score;

        public Song(String title, int duration, String[] singers, int score) {
            this.title = title;
            this.duration = duration;
            this.singers = singers;
            this.score = score;
        }

        //Getter methods
        public String getTitle() {
            return title;
        }

        public int getDuration() {
            return duration;
        }

        public String[] getSingers() {
            return singers;
        }

        public int getScore() {
            return score;
        }
    }
}