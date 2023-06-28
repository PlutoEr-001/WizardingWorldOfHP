package task4;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


public class task4Reducer
    extends Reducer<Text, Text, Text, Text>{
    private String neighbors="";
    private Float sum=0F;
    @Override
    public void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {
        try{
            for(Text val:values){
                String secondPart=val.toString();
                if(secondPart.charAt(0)=='#'){
                    //Graph
                    neighbors=secondPart.substring(secondPart.indexOf('#')+1);
                }else{
                    secondPart=secondPart.substring(secondPart.indexOf('@')+1).trim();
                    sum+=Float.parseFloat(secondPart);
                }
            }
            context.write(key,new Text(sum+ "\t" +neighbors));
            sum=0F;
            neighbors="";
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
