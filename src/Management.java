
import Exceptions.ExistingFamilie;
import Exceptions.FamiliesAreEnough;
import Exceptions.InvalidID;
//import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Management {

    private int[] gift_card;
    private Family[] familias;
    private final int DEFAULT_SIZE = 100;
    private final int DEFAULT_SIZEFAMILY = 5000;
    private FileWriter f;
    private FileWriter fw;
    private Day[] day;
    private int id;
    private int idchoice;
    private int pos;

    public Management() throws IOException {
        this.gift_card = new int[]{0, 50, 75, 100, 200, 250, 300, 350, 400, 500};
        this.day = new Day[DEFAULT_SIZE];
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            day[i] = new Day();
        }
        this.f = createFileFamilyChoices();
        this.fw = createFileChoice();
        this.familias = new Family[DEFAULT_SIZEFAMILY];
        this.id = 0;
        this.pos = 0;
        this.idchoice = 0;

    }


    public void print() {
        for (int i = 0; i < familias.length && familias[i] != null; i++) {
            System.out.println(familias[i].getFinalchoice());
        }
    }

   /* public boolean selectChoice(Family f) throws IOException{

        int[] choice = f.getChoice();

        for(int i=0; i<choice.length ; ++i){
                if (day[choice[i]].getN_pessoas() >= 0 && day[choice[i]].getN_pessoas() + f.getN_members() <= 300){

                    f.setFinalchoice(choice[i]);



                    day[choice[i]].setN_pessoas(day[choice[i]].getN_pessoas() + f.getN_members());
                    day[choice[i]].setN_familias(day[choice[i]].getN_familias() + 1);

                    writeSelectDay(f);

                    return true;

                    /*switch(i){
                        case 0:
                            f.setCost(0);
                            break;
                        case 1:
                            f.setCost(50);
                            break;
                        case 2:
                            f.setCost(75);
                            break;
                        case 3:
                            f.setCost(100);
                            break;
                        case 4:
                            f.setCost(200);
                            break;
                        case 5:
                            f.setCost(250);
                            break;
                        case 6:
                            f.setCost(300);
                            break;
                        case 7:
                            f.setCost(350);
                            break;
                        case 8:
                            f.setCost(400);
                            break;
                        case 9:
                            f.setCost(500);
                            break;
                        default:
                            f.setCost(800);
                            break;

                    }*/
/*

            }





        }

        return false;
    }*/

   public boolean selectChoice(Family f) throws IOException, FamiliesAreEnough {
        //int[] choices = f.getChoice();

        for (int i = 0; i < day.length; i++) {
            if (day[i].getN_pessoas() >= 0 && day[i].getN_pessoas() + f.getN_members() <= 300) {

                f.setFinalchoice(i);
                familias[pos] = f;
                writeSelectDay(f);
                pos++;

                day[i].setN_pessoas(day[i].getN_pessoas() + f.getN_members());
                day[i].setN_familias(day[i].getN_familias() + 1);

                selectGiftCard(i,f);
                return true;
                }
            }
        return false;
        }


    private void selectGiftCard(int i, Family family) {
        int[] choices = family.getChoice();
       // familias[pos] = family;
       // pos++;

        for (int j = 0; j < choices.length; j++) {
            if (i == choices[j]) {
                family.setCost(gift_card[j]);
                break;
            }
            else{
               family.setCost(800);
           }

        }

    }
    private FileWriter createFileFamilyChoices() throws IOException {
        FileWriter csvWriter = new FileWriter("family_choices.csv");
        csvWriter.append("family_id");
        csvWriter.append(",");

        for (int i = 0; i < 10; i++) {
            csvWriter.append("choice_" + i);
            csvWriter.append(",");
        }
        csvWriter.append("n_people");
        csvWriter.append("\n");
        csvWriter.flush();
        csvWriter.close();
        return csvWriter;

    }

    private FileWriter createFileChoice() throws IOException {
        FileWriter csvWriter = new FileWriter("choice.csv");
        csvWriter.append("family_id");
        csvWriter.append(",");
        csvWriter.append("assigned_day");
        csvWriter.append(",");
        csvWriter.append("contabilidade_cost");
        csvWriter.append(",");
        csvWriter.append("preferences_cost");
        csvWriter.append("\n");
        csvWriter.flush();
        csvWriter.close();

        return csvWriter;

    }

    private void writeSelectDay(Family f) throws IOException {
        String str = null;
        BufferedWriter out = new BufferedWriter(new FileWriter("choice.csv", true));
        double contabilidade_cost = 30;//calculateContabilityCost();
        //for (int i = 0; i < familias.length && familias[i] != null; i++) {
        //str = idchoice + "," + familias[i].getFinalchoice() + "," + contabilidade_cost + "\n";
        str= idchoice+ "," + f.getFinalchoice() + "," + contabilidade_cost +"\n";
        idchoice++;
        //}

        out.write(str);
        out.close();
    } //calculateContabilityCost();


    public double calculateContabilityCost() throws ArrayIndexOutOfBoundsException{
        double penaltyTemp1 = 0;// penaltyTemp2, penalty = 0;

        for(int i=100; i<=day.length-1;i++){

            penaltyTemp1= ((day[i].getN_pessoas()/400)*(day[i].getN_pessoas()))*((1/2)+(Math.abs(day[i].getN_pessoas()- day[i+1].getN_pessoas())/50));
            //penaltyTemp2= ((1/2)+(Math.abs(day[i].getN_pessoas()- day[i+1].getN_pessoas())/50));

            //penalty= penaltyTemp1*penaltyTemp2;
        }
        System.out.println(penaltyTemp1);
        return penaltyTemp1;
    }


    public void printFile() {
        String csvFile = "family_choices.csv";
        String line = "";
        String cvsSplitBy = ",";
        String[] temp = null;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {
                temp = line.split(cvsSplitBy);
                System.out.println("ID: " + temp[0] + "Choice_1" + temp[1]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeFamilyCSVFile(Family f) throws IOException {

        int[] choices = f.getChoice();
        String str = null;
        String str2 = null;
        BufferedWriter out = new BufferedWriter(new FileWriter("family_choices.csv", true));


        str = id + ",";
        //str = f.getId() + ",";
        for (int i = 0; i < choices.length; i++) {
            str = str + choices[i] + ",";
        }
        str2 = str + f.getN_members() + "\n";

        id++;

        out.write(str2);

        out.close();

    }
}
