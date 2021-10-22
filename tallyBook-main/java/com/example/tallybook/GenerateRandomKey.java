package com.example.tallybook;

import androidx.annotation.RequiresApi;

import android.os.Build;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GenerateRandomKey {

    /**
     * 生成指定长度随机秘钥.
     * @param length 指定长度.
     * @return 返回秘钥.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public String randomKey(int length) {
        Random random = new Random();
        List<String> key = new ArrayList<>();
        List<Integer> x = new ArrayList<>();
        while (key.size() < length) {
            int n = random.nextInt(3) + 1;
            if (x.size() < 3) {
                if (x.size() != 0) {
                    for (Integer i : x) {
                        if (i != 0) {
                            if (indexOf(x,i) == x.size() - 1 && x.get(0) != n) {
                                x.add(n);
                                key.add(String.valueOf(n == 1 ? ((char) (random.nextInt(9) + 49)) : n == 2 ? ((char) (random.nextInt(26) + 65)) : ((char) (random.nextInt(26) + 97))));
                                break;
                            }
                        }
                    }
                    continue;
                } else {
                    x.add(n);
                }
            }
            key.add(String.valueOf(n == 1 ? ((char) (random.nextInt(9) + 49)) : n == 2 ? ((char) (random.nextInt(26) + 65)) : ((char) (random.nextInt(26) + 97))));
        }
        StringBuilder ice = new StringBuilder();
        key.forEach(ice::append);
        return ice.toString();
    }

    private int indexOf(List<Integer> x,Integer y) {
        for (int j = 0; j < x.size(); j++) {
            if (Objects.equals(x.get(j), y))
                return j;
        }
        return -1;
    }
}
