import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Main {

    public static String foodname(ArrayList<String[]> foodlist, String id) {

        String name = null;
        for (int i = 0; i < foodlist.size(); i++) {

            if (id.equals((foodlist.get(i))[0])) {
                name = (String) (foodlist.get(i))[1];
            }

        }
        return name;
    }

    public static String foodcalori(ArrayList<String[]> foodlist, String id){
        String calori = null;

        for( int i = 0 ; i < foodlist.size() ; i++) {

            if (id.equals((foodlist.get(i))[0])) {
                calori = (String) (foodlist.get(i))[2];
            }
        }
        return calori;
    }

    public static String sportname(ArrayList<String[]> sportlist, String id){

        String name = null;

        for( int i = 0 ; i < sportlist.size() ; i++) {

            if (id.equals((sportlist.get(i))[0])) {
                name = (String) (sportlist.get(i))[1];
            }
        }
        return name;
    }

    public static String sportcalori(ArrayList<String[]> sportlist, String id) {

        String calori = null;

        for (int i = 0; i < sportlist.size(); i++) {

            if (id.equals((sportlist.get(i))[0])) {
                calori = (String) (sportlist.get(i))[2];
            }
        }
        return calori;
    }

    public static String tamsayi(int tamsayi){

        String x = "+" + tamsayi;
        if (tamsayi > 0){
            return x;
        }
        else {
            return String.valueOf(tamsayi);
        }
    }



    public static void main(String[] args) throws IOException {

        ArrayList<People> objectlist = new ArrayList<>();
        ArrayList<String[]> sportslist = new ArrayList<>();
        ArrayList<String[]> foodlist = new ArrayList<>();
        ArrayList<People> personlistfood = new ArrayList<>();
        ArrayList<People> personlistsport = new ArrayList<>();


        String filesport = "sport.txt";
        File file1 = new File(filesport);
        Scanner scanner1 = null;
        try {
            scanner1 = new Scanner(file1);
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner1.hasNext()) {
            String[] line = scanner1.nextLine().split("\t");
            sportslist.add((line));
        }

        String filefood = "food.txt";
        File file2 = new File(filefood);
        Scanner scanner2 = null;
        try {
            scanner2 = new Scanner(file2);
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner2.hasNext()) {
            String[] line = scanner2.nextLine().split("\t");
            foodlist.add(line);
        }

        String filename = "people.txt";
        File file = new File(filename);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scanner.hasNext()) {
            String[] plist = scanner.nextLine().split("\t");
            People person = new People(plist[0],plist[1],plist[2],Double.parseDouble(plist[3]),Double.parseDouble(plist[4]),2022 - parseInt(plist[5]));
            objectlist.add(person);
        }

        try {
            FileWriter out = new FileWriter("monitoring.txt");
            String filecommand = args[0];
            File file3 = new File(filecommand);
            Scanner scanner3 = null;
            try {
                scanner3 = new Scanner(file3);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            while (scanner3.hasNext()) {
                String[] line = scanner3.nextLine().split("\t");
                if(line[0].substring(0,1).equals("1")){
                    if (line[1].substring(0,1).equals("1")){
                        for (People person : objectlist){
                            if(person.getId().equals(line[0])){
                                out.write(person.getId() + "\thas   taken\t" + parseInt(line[2])*parseInt(foodcalori(foodlist, line[1])) + "\tfrom\t" + foodname(foodlist,line[1]) + "\n***************\n");

                                if (!(personlistfood.contains(person))){
                                    person.setCaloritaken(parseInt(line[2])*parseInt(foodcalori(foodlist, line[1])));
                                    personlistfood.add(person);
                                }
                                else{
                                    person.setCaloritaken(person.getCaloritaken() + parseInt(line[2])*parseInt(foodcalori(foodlist, line[1])));
                                }
                            }
                        }
                    }
                    if (line[1].substring(0,1).equals("2")){
                        for (People person : objectlist){
                            if(person.getId().equals(line[0])){
                                out.write(person.getId() + "\thas   burned\t" + (parseInt(line[2])/60) * parseInt(sportcalori(sportslist,line[1])) + "\tkcal   thanks    to\t" + sportname(sportslist,line[1]) + "\n***************\n");


                                if(!(personlistsport.contains(person))){
                                    person.setCaloriburned((parseInt(line[2])/60) * parseInt(sportcalori(sportslist,line[1])));
                                    personlistsport.add(person);

                                }
                                else{
                                    person.setCaloriburned(person.getCaloriburned() + (parseInt(line[2])/60) * parseInt(sportcalori(sportslist,line[1])) );
                                }
                            }

                        }
                    }
                }
                if(line[0].startsWith("print(")) {
                    for (People person : objectlist){
                        if(person.getId().equals(line[0].substring(6,11))){
                            out.write(person.getName()+ "\t" + person.getAge()+ "\t" + person.dailyneeds()+ "kcal" +"\t" + person.getCaloritaken()+ "kcal"+ "\t" + person.getCaloriburned()+ "kcal" + "\t" + tamsayi(person.getCaloritaken() - person.getCaloriburned() - person.dailyneeds())+ "kcal"+ "\n***************\n");
                        }
                    }
                }
                if(line[0].startsWith("printList")) {
                    for (People person : personlistfood) {
                        out.write(person.getName() + "\t" + person.getAge() + "\t" + person.dailyneeds() + "kcal" + "\t" + person.getCaloritaken() + "kcal" + "\t" + person.getCaloriburned() + "kcal" + "\t" + tamsayi(person.getCaloritaken() - person.getCaloriburned() - person.dailyneeds()) + "kcal\n");
                    }
                    out.write("***************\n");
                }
                if(line[0].startsWith("printWarn")){
                    int warn = 0;
                    for (People person : personlistfood){
                        if(person.getCaloritaken() - person.getCaloriburned() - person.dailyneeds() > 0) {
                            out.write(person.getName() + "\t" + person.getAge() + "\t" + person.dailyneeds() + "kcal" + "\t" + person.getCaloritaken() + "kcal" + "\t" + person.getCaloriburned() + "kcal" + "\t" + tamsayi(person.getCaloritaken() - person.getCaloriburned() - person.dailyneeds()) + "kcal\n");
                            warn+=1;
                        }
                    }
                    if ( warn == 0 ) {
                        out.write("there\tis\tno\tsuch\tperson\n***************\n");
                    }
                    else{
                        out.write("***************\n");
                    }
                }
            }
        out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        RandomAccessFile f = null;
        try {
            f = new RandomAccessFile("monitoring.txt", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long length = f.length();
        f.setLength(length - 17);
        f.close();
    }
}
