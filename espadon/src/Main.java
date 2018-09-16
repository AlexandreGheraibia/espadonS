import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static int fib(int n) {
        if(n<2){
            return n;
        }
        else{
            return fib(n-2)+fib(n-1);
        }
    }

    public static HashMap<Integer,String>  load(String uri,List<Integer> liste) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(uri));
        HashMap<Integer,String> dico=new HashMap<Integer, String>();
        String line = reader.readLine();
        int i=0;
        int j=0;
        String word;
        while(line != null) {
            StringTokenizer st = new StringTokenizer(line," ");
            while(st.hasMoreTokens()){
                word=st.nextToken();
                if(j<liste.size()&&i==liste.get(j)){
                    dico.put(i,word);
                    j++;
                }
                i++;
            }
            line = reader.readLine();

        }
        reader.close();
        return dico;
    }
    public static void main(String[] args) throws IOException {
        String filename = "miserables.txt";
        String suite="2 3 3 4 5 6 7 7 8 9 10 11 9 10 9 12 9 5 4 13 14 15 16 12 3 9 8 2 17 12 18 10";
        String [] arraySuite=suite.split(" ");
        List<String> strs = Arrays.asList(arraySuite);
        List<Integer> intRes;
        Stream<Integer> res;
        Stream<String> res2;
        res=strs.stream().map(x->Integer.parseInt(x)).map(x->fib(x)).map(x->String.valueOf(x)).distinct().map(x->Integer.parseInt(x));
        intRes=res.collect(Collectors.toList());
        System.out.println(intRes);
        HashMap<Integer,String> dico=load(filename,intRes);
        res2=strs.stream().map(x->Integer.parseInt(x)).map(x->dico.get(fib(x)));
        System.out.println(strs.stream().map(x->Integer.parseInt(x)).map(x->fib(x)).collect(Collectors.toList()));
        System.out.println(res2.collect(Collectors.reducing((o, o2)->o+o2)));
    }

}
