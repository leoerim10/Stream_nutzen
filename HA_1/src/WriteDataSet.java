import java.io.*;

public class WriteDataSet {

    public static void main(String[] args) {

        try {
            String filename = "testFile.txt";
        OutputStream os = new FileOutputStream(filename);
        DataOutputStream dos = new DataOutputStream(os);

            String name = "MyGoodOldSensor";  // name for sensor
            byte[] textAsByte = name.getBytes();// string to bytes conversion
            int string_length = textAsByte.length;//length of string, to set the exact space for it to be stored

            //Given datasets
            float[][] values = new float[3][];
            // 1st measure .. just one value
            float[] valueSet = new float[1];
            values[0] = valueSet;
            valueSet[0] = (float) 1.5; // example value 1.5 degrees

            // 2nd measure .. just three values
            valueSet = new float[3];
            values[1] = valueSet;
            valueSet[0] = (float) 0.7;
            valueSet[1] = (float) 1.2;
            valueSet[2] = (float) 2.1;

            // 3rd measure .. two values
            valueSet = new float[2];
            values[2] = valueSet;
            valueSet[0] = (float) 0.7;
            valueSet[1] = (float) 1.2;




            float old_average = -500;// for real comparison of the temp
            dos.writeInt(values.length);
            // loop to write all 3 data sets in order including the 2d array for temp measures as mentioned in question
            for( int i = 0; i<values.length; i++){
                dos.writeInt(string_length);//1st the total space needed to write string
                dos.write(textAsByte); //sensor name

                dos.writeLong(System.currentTimeMillis() + i); //timestamp
                float new_average = calculateAverage(values[i]);

                if((new_average - old_average) > 1){// compares the 1st measures to the next one if it is changed by 1
                    dos.writeInt(values[i].length);
                    for( int j = 0; j<values[i].length; j++){
                        dos.writeFloat(values[i][j]); // every measures and its values
                    }
                }
            }


        } catch (FileNotFoundException ex) {
            System.err.println("couldn’t open file - fatal");
        System.exit(0);


        }catch (IOException ex) {
            System.err.println("couldn’t write data (fatal)");
            System.exit(0);
        }
    }

    //to calculte the average of the values for the measure
    public static float calculateAverage(float[] arr) {
        float sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = +arr[i];
        }
        float res = sum / ((float) arr.length);
        return res;

    }

}
