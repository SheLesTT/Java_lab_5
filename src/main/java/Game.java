/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import Fighters.Player;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * The Game class represents the game logic and functionality.*
 * @author Мария
 */
public class Game {

    CharacterAction action = new CharacterAction();
    ChangeTexts change = new ChangeTexts();
    Fight fight = new Fight();
    Location location = null;
    private ArrayList<Result> results = new ArrayList<>();
    /**
     * Sets the number of locations in the game.
     *
     * @param locations_count The number of locations.
     */
    public void setLocations_count(int locations_count) {
        this.locations_count = locations_count;
    }

    private int locations_count;

    /**
     * Creates a new game location.
     *
     * @param human_lvl The level of the human player.
     */
    public void NewLocation(int human_lvl){
        System.out.println("respawn enemies");
        location.ememies_left = location.count_num_enemies();
    }
    /**
     * Creates a new enemy for the player.
     *
     * @param L1  The first label for displaying enemy information.
     * @param L2  The second label for displaying enemy information.
     * @param L3  The third label for displaying enemy information.
     * @param L4  The fourth label for displaying enemy information.
     * @param pr2 The progress bar for enemy health.
     * @return The created enemy player.
     */
    public Player NewEnemy(JLabel L1, JLabel L2,
            JLabel L3, JLabel L4, JProgressBar pr2) {
        action.setEnemyes();
        Player enemy = action.ChooseEnemy(L1, L2, L3, L4);
        action.HP(enemy, pr2);
        pr2.setMaximum(enemy.getMaxHealth());
        return enemy;
    }
    /**
     * Creates a new human player.
     *
     * @param pr1 The progress bar for human player health.
     * @return The created human player.
     */
    public Human NewHuman(JProgressBar pr1){
        Human human = new Human (0,80,16,1);
        action.HP(human, pr1);
        pr1.setMaximum(human.getMaxHealth());
        return human;
    }
    /**
     * Ends the game and updates the top scores.
     *
     * @param human The human player.
     * @param text  The text field containing the player's name.
     * @param table The table for displaying the top scores.
     * @throws IOException If an I/O error occurs.
     */
    public void EndGameTop(Human human, JTextField text, JTable table) throws IOException {
        results.add(new Result(text.getText(), human.getPoints()));
        results.sort(Comparator.comparing(Result::getPoints).reversed());
        WriteToTable(table);
        WriteToExcel();
    }
    /**
     * Writes the top scores to an Excel file.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void WriteToExcel() throws IOException{
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet("Результаты ТОП 10");
        XSSFRow r = sheet.createRow(0);
        r.createCell(0).setCellValue("№");
        r.createCell(1).setCellValue("Имя");
        r.createCell(2).setCellValue("Количество баллов");
        for (int i=0; i<results.size();i++){
            if (i<10){
                XSSFRow r2 = sheet.createRow(i+1);
                r2.createCell(0).setCellValue(i+1);
                r2.createCell(1).setCellValue(results.get(i).getName());
                r2.createCell(2).setCellValue(results.get(i).getPoints());
            }
        }
        File f = new File(".\\Results.xlsx");
        book.write(new FileOutputStream(f));
        book.close();
    }
    
    public ArrayList<Result> getResults(){
        return this.results;
    }
    /**
     * Reads the top scores from an Excel file.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void ReadFromExcel() throws IOException{
        XSSFWorkbook book = new XSSFWorkbook(".\\Results.xlsx");
        XSSFSheet sh = book.getSheetAt(0);
        for (int i=1; i<=sh.getLastRowNum();i++) {
            results.add(new Result(sh.getRow(i).getCell(1).getStringCellValue(),(int)sh.getRow(i).getCell(2).getNumericCellValue()));
        }
    }
    /**
     * Writes the top scores to a table.
     *
     * @param table The table for displaying the top scores.
     */
    public void WriteToTable(JTable table){
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        for (int i=0; i<results.size();i++){
            if (i<10){
                model.setValueAt(results.get(i).getName(), i, 0);
                model.setValueAt(results.get(i).getPoints(), i, 1);
            }
        }
    }
}
