import Exceptions.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class PaiNatal_Manager {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InvalidDayException, SizeOfFamily, SameDayInArray, FamiliesAreEnough, InvalidID, ExistingFamilie {
        // TODO code application logic here
        Management m = new Management();

      /*
       int[] f1a = {0,3,6,10,15,30,56,78,98,99};
        Family f1 = new Family(4,f1a);
        int[] f2a = {0,2,5,7,10,33,55,77,88,99};
        Family f2 = new Family(3,f2a);*/
        int[] f3a = {0, 1, 5, 7, 6, 33, 55, 77, 88, 99};
        // Family f3 = new Family(4,f3a);
        /*Family f4 = new Family(10,f3a);
        Family f5 = new Family(10,f3a);
        Family f6 = new Family(10,f3a);
        Family f7 = new Family(10,f3a);
        Family f8 = new Family(10,f3a);
        Family f9 = new Family(10,f3a);
        Family f10 = new Family(290,f3a);
        Family f11 = new Family(300,f3a);
        */
        Family[] families = new Family[5000];


        for (int i = 0; i < families.length; i++) {

            if (i == 0) {
                families[i] = new Family(6, f3a);
            } else {
                families[i] = new Family(6, f3a);
            }
            m.writeFamilyCSVFile(families[i]);

        }


        for (int i = 0; i < families.length; i++) {
            m.selectChoice(families[i]);

        }


       // m.calculateContabilityCost();
        System.out.println(families[255].getCost());
        System.out.println(families[436].getCost());
        System.out.println(families[1].getCost());
        System.out.println(families[115].getCost());
        /*
        m.selectChoice(f1);
        m.selectChoice(f2);
        m.selectChoice(f3);
        m.selectChoice(f4);
        m.selectChoice(f11);
        m.selectChoice(f10);

        m.writeSelectDay();

        m.print();
        */


    }

}
