import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxTemperatureMapper
  extends Mapper<LongWritable, Text, Text, IntWritable> {

  private static final int MISSING = 9999;
  
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    
    
    String line = value.toString();

    //Extracts characters from position 15 to 18 (since substring's second parameter is exclusive), which represent the year.
    String year = line.substring(15, 19);
    
    //The temperature is stored in specific positions.  If the character at position 87 is '+', the temperature is in characters 88-91. Otherwise, it's in characters 87-91.
    int airTemperature;
    if (line.charAt(87) == '+') { // parseInt doesn't like leading plus signs
      airTemperature = Integer.parseInt(line.substring(88, 92));
    } else {
      airTemperature = Integer.parseInt(line.substring(87, 92));
    }

    //Extracts a single character at position 92, which indicates the quality of the temperature reading. Extracts a single character at position 92, which indicates the quality of the temperature reading.
    String quality = line.substring(92, 93);
    if (airTemperature != MISSING && quality.matches("[01459]")) {
      context.write(new Text(year), new IntWritable(airTemperature));
    }
  }
}
