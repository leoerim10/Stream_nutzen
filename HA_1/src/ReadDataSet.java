import java.io.*;

public class ReadDataSet {
    public static void main(String[] args) {

        try {
            InputStream is = new FileInputStream("testFile.txt");
            DataInputStream dis = new DataInputStream(is);


            //reading the datasets in the same order as it was written before
            int ds_length = dis.readInt(); //total data sets to be read

            for( int i = 0; i<ds_length; i++){
                int read_stringlength = dis.readInt();//so that the space error can be handeld

                byte[] stringBuffer = new byte[read_stringlength];// bytes to string
                is.read(stringBuffer);
                String readString = new String(stringBuffer);
                System.out.println(readString);

                long time = dis.readLong();// bytes to long
                System.out.println("current time stamp: " + time);

                System.out.println("values of the measurement no." + i);
                int no_floats = dis.readInt();// total no of elements from 2d array written before
                for( int j = 0; j<no_floats; j++){

                    float res = dis.readFloat();// the complete measures in order
                    System.out.println(res);

                }
                System.out.println();
            }




        } catch (FileNotFoundException ex) {
            System.err.println("couldn’t open file - fatal");
            System.exit(0);

        } catch (IOException ex) {
            System.err.println("couldn’t read data (fatal)");
            System.exit(0);

        }


    }


}
