package hello;

import java.util.*;

public class Occurrence{
    private final int start;
    private int line;
    private int end;
    private String in_sentence;

    public Occurrence(int start, int end, int line){
        this.start=start;
        this.end=end;
        this.line=line;
    }
    public int getStart(){
        return start;
    }
    public int getLine(){
        return line;
    }
    public int getEnd(){
        return end;
    }
    public String getIn_sentence(){
        return in_sentence;
    }
    public void setIn_sentence(String in_sentence){
        this.in_sentence=in_sentence;
    }
}