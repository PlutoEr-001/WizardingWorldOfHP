package task4;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

class StringFloat
        implements WritableComparable<StringFloat> {
    private String first = "";
    private float second = 0;
    StringFloat(){
        first="";
        second=0;
    }
    /**
     * Set the left and right values.
     */
    public void set(String left, float right) {
        first = left;
        second = right;
    }
    public String getFirst() {
        return first;
    }
    public float getSecond() {
        return second;
    }
    /**
     * Read the two integers.
     * Encoded as: MIN_VALUE -&gt; 0, 0 -&gt; -MIN_VALUE, MAX_VALUE-&gt; -1
     */
    @Override
    public void readFields(DataInput in) throws IOException {
        first = in.readUTF();
        second = in.readFloat();
    }
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(first);
        out.writeFloat(second);
    }
    @Override
    public boolean equals(Object right) {
        if (right instanceof StringFloat) {
            StringFloat r = (StringFloat) right;
            return r.first.equals(first) && r.second == second;
        } else {
            return false;
        }
    }
    @Override
    public int compareTo(StringFloat o) {
        if(second==o.second) return Integer.compare(first.compareTo(o.first),0);
        else return -Float.compare(second, o.second);
    }
}