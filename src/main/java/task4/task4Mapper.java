package task4;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.Map;

public class task4Mapper extends
        Mapper<Object, Text, Text, Text> {
    @Override
    public void map(Object key, Text value, Context context)
            throws IOException, InterruptedException {
        try{
            Node node=new Node(value.toString());
            System.out.println(node.toString(true,true));
            context.write(new Text(node.getNodeName()),new Text("#"+node.toString(false,false)));
            Map<String,Float> neighbors=node.getNeighbors();
            for (Map.Entry<String, Float> entry : neighbors.entrySet()) {
                Float res=entry.getValue()*node.getPr();
                context.write(new Text(entry.getKey()),new Text("&"+Float.toString(res)));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
