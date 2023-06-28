package task4;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class task4Driver {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        GenericOptionsParser optionParser = new GenericOptionsParser(conf, args);
        String[] remainingArgs = optionParser.getRemainingArgs();
        if ((remainingArgs.length != 2)) {
            System.err.println("Usage: HPTask4 <in_path> <out_path>");
            System.exit(2);
        }
        Path inpath,outpath = null;
        for(int count=1;count<=30;count++){
            Job job = Job.getInstance(conf, "HPTask4");
            job.setJarByClass(task4Driver.class);
            job.setMapperClass(task4Mapper.class);
            job.setReducerClass(task4Reducer.class);
            //设置map输出的key类型
            job.setMapOutputKeyClass(Text.class);
            //设置map输出的value类型
            job.setMapOutputValueClass(Text.class);
            //设置输出的key类型
            job.setOutputKeyClass(Text.class);
            //设置输出的value类型
            job.setOutputValueClass(Text.class);

            if(count==1){
                inpath=new Path(args[0]);
            }else{
                inpath=outpath;
            }
            outpath=new Path(args[0]+"/iteration_1/iteration_"+count);
            FileInputFormat.addInputPath(job, inpath);
            FileOutputFormat.setOutputPath(job, outpath);
            job.waitForCompletion(true);
        }
        Job job = Job.getInstance(conf, "HPTask4");
        job.setJarByClass(task4Driver.class);
        job.setMapperClass(task4SortMapper.class);
        job.setReducerClass(task4SortReducer.class);
        //设置map输出的key类型
        job.setMapOutputKeyClass(StringFloat.class);
        //设置map输出的value类型
        job.setMapOutputValueClass(NullWritable.class);
        //设置输出的key类型
        job.setOutputKeyClass(Text.class);
        //设置输出的value类型
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, outpath);
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.waitForCompletion(true);
    }
}
