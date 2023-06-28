package task4;


import java.util.HashMap;
import java.util.Map;

public class Node {
    private String nodeName;
    private Float pr;
    private final char split='\t';
    private Map<String, Float> neighbors;
    Node(String line){
        String[] parts=line.split("\t");
        //pr initiated as 1
        if(parts.length==2){
            pr= 1F;
        }
        else{
            pr=Float.parseFloat(parts[1].trim());
        }
        String[] neighbors_temp=line.substring(line.indexOf('[')+1,line.indexOf(']')).split("\\|");
        neighbors=new HashMap<>();
        nodeName=parts[0].trim();
        for(String neighbor:neighbors_temp){
            String name=neighbor.substring(0,neighbor.indexOf(','));
            Float weigh=Float.parseFloat(neighbor.substring(neighbor.indexOf(',')+1));
            neighbors.put(name,weigh);
        }
    }
    public String toString(boolean withNodeName,boolean withPr){
        StringBuilder out=new StringBuilder();
        //Harry pr [neighbors]
        if(withNodeName) out.append(nodeName).append(split);
        if(withPr) out.append(pr).append(split);
        out.append('[');
        for (Map.Entry<String, Float> entry : neighbors.entrySet()) {
            out.append(entry.getKey());
            out.append(',');
            out.append(entry.getValue());
            out.append("|");
        }
        out.deleteCharAt(out.length()-1);
        out.append(']');
        return out.toString();
    }
    public Map<String,Float> getNeighbors(){
        return neighbors;
    }
    public String getNodeName(){
        return nodeName;
    }
    public Float getPr(){
        return pr;
    }
}

