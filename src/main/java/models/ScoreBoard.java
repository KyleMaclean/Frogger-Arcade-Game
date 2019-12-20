package models;

import controllers.ScoreController;
import javafx.beans.binding.StringBinding;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Uses an inner class to model the data for each person's score.
 * Reads and formats score data for use by the ScoreController.
 * Isolated and thus testable as all I/O is contained within this class.
 */
public class ScoreBoard {

    /**
     * Used in an ArrayList by the read method to store and sort each entry in the CSV file
     */
    private static class BoardEntry implements Comparable<BoardEntry> {
        String name;
        int score;
        Date date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yy");

        public BoardEntry(String[] columns) throws ParseException {
            if (columns[0].length() > 7)
                this.name = columns[0].substring(0,7);
            else
                this.name = columns[0];
            this.score = Integer.parseInt(columns[1]);
            this.date = new SimpleDateFormat("yyyy-MM-dd").parse(columns[2]);
        }

        /**
         * @param o the other BoardEntry to compare scores
         * @return integer indicating comparison result
         */
        @Override
        public int compareTo(BoardEntry o) {
            return Integer.compare(o.score, this.score);
        }

        @Override
        public String toString() {
            return '\n' + this.name + '\t' + this.score + '\t' + simpleDateFormat.format(this.date);
        }
    }

    private static ArrayList<BoardEntry> board;
    public static int getHighestScore() {
        return board.get(0).score;
    }

    public static String read(String scoresPath) throws ParseException {
        try (BufferedReader br = new BufferedReader((new FileReader(scoresPath)))) {
            String output = br.readLine().replace(',','\t') + '\n';
            board = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                board.add(new BoardEntry(line.split(",")));
            }
            Collections.sort(board);
            for (BoardEntry be : board) {
                output += be.toString();
            }

            return output;
        } catch (IOException e) {
            return "Reading failed: " + scoresPath + " not found";
        }
    }

    public static void write(String scoresPath) {
        try {
            FileWriter fw = new FileWriter(scoresPath, true);
            String line = '\n' + Player.getName() + ',' + ScoreController.getScore() + ',' + java.time.LocalDate.now();
            fw.write(line);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Writing failed: " + scoresPath + " not found");
        }
    }
}
