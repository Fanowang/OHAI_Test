package hello;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchingController {

    private static final String FILENAME="./king-i.txt";
    private static final String symbols=".?!]";


    @RequestMapping("/searching")
    public Result searching(@RequestParam(value="searchtxt") String searchtxt) {
        List<Occurrence> list=new ArrayList<Occurrence>();
        if(searchtxt==""){
            return new Result(searchtxt,list);
        }   

        int fileLength=0;
        int lineNum=1;
        int stLength=searchtxt.length();
                
        Queue<Occurrence> tempQueue=new LinkedList<>();
        int[] sentenceRange=new int[]{0,0};

        try{

            String wholetext=new String(Files.readAllBytes(Paths.get(FILENAME)));
            fileLength=wholetext.length();            
                for(int i=0;i<fileLength-stLength-1;i++){
                    char currentChar=wholetext.charAt(i);
                    boolean sentenceEndSymbol=symbols.indexOf(currentChar)==-1?false:true;

                    if(Character.isUpperCase(currentChar) 
                    && sentenceRange[1]!=0){                        
                        sentenceRange=new int[]{i,0};
                    }

                    if(wholetext.substring(i,i+1).equals("\n")){
                        lineNum++;
                    }
                    
                    if(wholetext.substring(i,i+stLength).toUpperCase().equals(searchtxt.toUpperCase())){                        
                        tempQueue.add(new Occurrence(i,i+stLength,lineNum));
                    }

                    if((sentenceEndSymbol&& Character.isWhitespace(wholetext.charAt(i+1))
                    && !Character.isLowerCase(wholetext.charAt(i+2)))
                    || (sentenceEndSymbol && wholetext.charAt(i+1)=='"')|| (lineNum==10&&wholetext.substring(i,i+1).equals("\n"))){
                        sentenceRange[1]=i+1;                        
                        while(!tempQueue.isEmpty()){                            
                            Occurrence oc=tempQueue.poll();                                                       
                            oc.setIn_sentence(wholetext.substring(sentenceRange[0],sentenceRange[1]).replace("\n"," "));
                            list.add(oc);
                        }
                    }
                }

        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        return new Result(searchtxt,list);
    }
}
