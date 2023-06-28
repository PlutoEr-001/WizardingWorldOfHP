package task4;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

class SortPartitioner extends Partitioner<StringFloat, NullWritable> {
    @Override
    public int getPartition(StringFloat key, NullWritable value, int numPartitions) {
        return (key.getFirst().hashCode())%numPartitions;
    }
}
