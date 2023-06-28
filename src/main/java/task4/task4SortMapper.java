package task4;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class task4SortMapper extends Mapper<Object, Text, StringFloat, NullWritable> {
    private final StringFloat key = new StringFloat();
    //key:offset value:line
    @Override
    public void map(Object keyin, Text inValue,
                    Context context) throws IOException, InterruptedException {
        String[] temp=inValue.toString().split("\t");
        String name=temp[0];
        float pr=Float.parseFloat(temp[1].trim());
        key.set(name,pr);
        //value.set(average);
        context.write(key, NullWritable.get());
    }
}

