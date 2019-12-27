import Exceptions.*;

public class Family {
    private final int DEFAULT_CAPACITY = 10;
    private int n_members;
    private int[] choice;
    private double cost;
    private int finalchoice;

    public Family(int n_members, int[] choice) throws InvalidDayException, SizeOfFamily, SameDayInArray, InvalidID {
        this.n_members = setN_members(n_members);
        this.choice = setChoice(choice);
        this.cost = 0;
        this.finalchoice = 0;

    }

    public Family() {
    }



    public int getN_members() {
        return n_members;
    }

    public int setN_members(int n_members) throws SizeOfFamily {
        if (n_members < 1) {
            throw new SizeOfFamily("numero de pessoas invalida!!");
        }
        this.n_members = n_members;
        return n_members;
    }

    public int[] getChoice() {
        return choice;
    }

    public int[] setChoice(int[] choice) throws InvalidDayException, SameDayInArray {
        for (int i = 0; i < choice.length; i++) {
            if (choice[i] > 99 || choice[i] < 0) {
                throw new InvalidDayException();
            }
        }
        for (int k = 0; k < choice.length; k++) {
            for (int j = 0; j < choice.length - 1; j++) {
                if (j == k) {
                    if (choice[k] == choice[j+1] && j + 1 < 10) {
                        throw new SameDayInArray("Mais que uma vez o mesmo dia escolhido");
                    }
                } else {
                    if (choice[k] == choice[j]) {
                        throw new SameDayInArray("Mais que uma vez o mesmo dia escolhido");
                    }
                }

            }
        }
        return choice;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getFinalchoice() {
        return finalchoice;
    }

    public void setFinalchoice(int finalchoice) {
        this.finalchoice = finalchoice;
    }

    @Override
    public String toString() {
        return "Family: " + this.getFinalchoice() + "\n";
    }

}
