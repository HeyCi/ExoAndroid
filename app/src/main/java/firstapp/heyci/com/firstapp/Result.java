package firstapp.heyci.com.firstapp;

import java.util.ArrayList;

public class Result {
    private ArrayList<City> results;
    private int nbr;

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    private Errors errors;

    public ArrayList<City> getResults() {
        return results;
    }

    public void setResults(ArrayList<City> results) {
        this.results = results;
    }

}
