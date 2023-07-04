package task4;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import task4.StringFloat;

import java.io.IOException;

class task4SortReducer
        extends Reducer<StringFloat, NullWritable, Text, Text> {
    @Override
    public void reduce(StringFloat key, Iterable<NullWritable> values,
                       Context context) throws IOException, InterruptedException {
        context.write(new Text(key.getFirst()),new Text(String.format("%.5f",key.getSecond())));

    }
}