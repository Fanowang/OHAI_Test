package hello;

import java.util.*;

public class Result{
    private final String query_text;
    private final int number_of_occurrences;
    private final List<Occurrence> occurrences;

    public Result(String query_text,List<Occurrence>occurrences){
        this.query_text=query_text;
        this.number_of_occurrences=occurrences.size();
        this.occurrences=occurrences;
    }
    public String getQuery_text(){
        return query_text;
    }
    public int getNumber_of_occurrences(){
        return number_of_occurrences;
    }
    public List<Occurrence> getOccurrences(){
        return occurrences;
    }


}