import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Steph {
    private static FileWriter fw;
    private static int n;
    private static int k;
    private static int x;
    private static ArrayList<Peak> array = new ArrayList<>();
    private static ArrayList<Peak> result;

    static class Peak{
        private int nomer;
        private ArrayList<Integer> neighbors;

        public Peak(int nomer, ArrayList<Integer> b) {
            this.nomer = nomer;
            this.neighbors = b;
        }

        public int getNomer() {
            return nomer;
        }

        public void setNomer(int nomer) {
            this.nomer = nomer;
        }
    }



    public static void main(String[] args) {
        try{
            FileReader fr = new FileReader("input.in");
            Scanner in = new Scanner(fr);
            fw = new FileWriter("output.out");
            if (!in.hasNextLine())
                throw new Exception("File is empty");
            String s;
            s = in.nextLine();
            String[] str = s.split(" ");
            n = Integer.parseInt(str[0]);
            k = n/2;
            x = Integer.parseInt(str[2]);
            result = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                s = in.nextLine();
                str = s.split(" ");
                ArrayList<Integer> buf = new ArrayList<>();
                for (int j = 0; j < str.length; j++) {
                    buf.add(Integer.parseInt(str[j]));
                }
                if(i != x-1)
                    array.add(new Peak(i+1,buf));
                else result.add(new Peak(i+1,buf));
            }
            result.addAll(array);
            for (int i = 0; i < 2*n; i++) {
                if(result.get(0).neighbors.contains(result.get(1).nomer)) {
                    Peak peak = result.get(0);
                    result.remove(0);
                    result.add(peak);
                    continue;
                }
                int j = 1;
                int count = 0;
                while(count != 2){
                    count = 0;
                    j++;
                    if(result.get(0).neighbors.contains(result.get(j).nomer))
                        count++;
                    if(result.get(1).neighbors.contains(result.get(j+1).nomer))
                        count++;
                }
                int l = j;
                for (int k = 1; k <= j-k ; k++,l--) {
                    Peak temp = result.get(k);
                    result.set(k,result.get(l));
                    result.set(l,temp);
                }
                Peak peak = result.get(0);
                result.remove(0);
                result.add(peak);
            }
            while (result.get(0).nomer != x){
                Peak peak = result.get(0);
                result.remove(0);
                result.add(peak);
            }


            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < result.size(); i++) {
                sb.append(result.get(i).nomer + " ");
            }
            sb.append(result.get(0).nomer);
            System.out.println(sb.toString());
            fw.write(sb.toString());
            fw.close();

        }
        catch (Exception e){
            System.out.println(e.toString());
        }

    }
}