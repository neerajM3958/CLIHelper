package neerajm.cli.model;

import java.util.ArrayList;

public class Arg implements Comparable<Arg>{
    public String key;
    public ArrayList<String> values = new ArrayList<>();
    public Arg(String key, ArrayList<String> values) {
        this.key = key;
        if(values!=null)
            this.values = values;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(key);
        for(String s : values){
            sb.append(" ").append(s);
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Arg && ((Arg)obj).key.equals(this.key);
    }

    @Override
    public int compareTo(Arg o) {
        return key.compareTo(o.key);
    }
}
