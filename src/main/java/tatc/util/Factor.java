package tatc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Factor {
    private static final HashMap<Integer, List<Integer>> map = new HashMap();

    private Factor() {
        System.out.print("Loading divisors...");
        String fileName = "1-1000_divisors.txt";
        String resourcePath = System.getProperty("user.dir") + File.separator + "resources";

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(resourcePath, fileName)));
            Throwable var4 = null;

            try {
                for(String line = br.readLine(); line != null; line = br.readLine()) {
                    String[] args = line.split("\\s");
                    String[] divisors = args[1].split(",");
                    List<Integer> divisorList = new ArrayList(divisors.length);
                    String[] var9 = divisors;
                    int var10 = divisors.length;

                    for(int var11 = 0; var11 < var10; ++var11) {
                        String div = var9[var11];
                        divisorList.add(Integer.parseInt(div));
                    }

                    map.put(Integer.parseInt(args[0]), Collections.unmodifiableList(divisorList));
                }
            } catch (Throwable var21) {
                var4 = var21;
                throw var21;
            } finally {
                if (br != null) {
                    if (var4 != null) {
                        try {
                            br.close();
                        } catch (Throwable var20) {
                            var4.addSuppressed(var20);
                        }
                    } else {
                        br.close();
                    }
                }

            }
        } catch (IOException var23) {
            Logger.getLogger(Factor.class.getName()).log(Level.SEVERE, (String)null, var23);
        }

        System.out.println("done");
    }

    public static List<Integer> divisors(int n) {
        if (map.isEmpty()) {
            new Factor();
        }

        return (List)map.get(n);
    }
}