package neerajm.cli;

import neerajm.cli.model.Arg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class CLI {
    private ArrayList<Arg> optionsList = new ArrayList<>();

    public CLI(){}

    public void parseArgs(String[] args) throws IllegalArgumentException {
        for(String arg : args){
            arg = arg.trim();
            if(arg.isEmpty())continue;
            if(arg.startsWith("--")){
                optionsList.add(new Arg(arg.substring(2),null));
            }
            else if(arg.startsWith("-")){
                for(int i=1; i<arg.length() ; i++){
                    String subArg = arg.substring(i,i+1);
                    optionsList.add(new Arg(subArg,null));
                }
            }
            else if(!optionsList.isEmpty()){
                optionsList.get(optionsList.size()-1).values.add(arg);
            }else {
                throw new IllegalArgumentException(Arrays.toString(args));
            }
        }
        for(Arg a : optionsList) {
//            System.out.println(a);
            if(!optsWrapper(a.key,a.values)){
                help();
                throw new IllegalArgumentException(Arrays.toString(args));
            }

        }

    }

    private boolean optsWrapper(String key, ArrayList<String> values) {
        key = key.toLowerCase();
        if(!opts(key,values)){
            switch (key){
                case "help":
                case "h":
                case "man":
                case "manual":
                case "guide":
                    help();
                    return true;
                default:
                    return false;
            }
        }
        return true;
    }

    public abstract boolean opts(String arg, ArrayList<String> value);
    public static void help(){
        try {
            InputStream in = CLI.class.getClassLoader().getResourceAsStream("res/help.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Arg> getOpts(){
        return optionsList;
    }
}
