package com.letz.utils;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomUtil {
    private static Logger logger = LoggerFactory.getLogger(RandomUtil.class);
    
    static final char[] POSSIBLE_CHARS = 
        { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'
            //, 'I'
            , 'J', 'K', 'L', 'M', 'N'
            //, 'O'
            , 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
            //,'1'
            , '#'
            , '2', '3', '4', '5', '6', '7', '8', '9'
            //, '0'
            //,'1'
            , '#'
            , '2', '3', '4', '5', '6', '7', '8', '9'
            //, '0'
          };
    static final int POSSIBLE_CHARS_CNT = POSSIBLE_CHARS.length;
    
    
    /**
     * 
     * @param randomNoCnt - �깮�꽦�븷 �옖�뜡NO 嫄댁닔
     * @return �깮�꽦�맂 �옖�뜡NO SET
     */
    public static Set<String> getRandomNoList(int randomNoCnt) {
        HashMap<String, String> resultMap = new HashMap<String, String>();
        
        Random rnd = new Random();
        
        for (int i = 0; i < randomNoCnt; i++) {
            
            StringBuffer buf = new StringBuffer(9);
            for (int j = 0; j<9; j++) {
                buf.append(POSSIBLE_CHARS[rnd.nextInt(POSSIBLE_CHARS_CNT)]);
            }
            String randomNo = buf.toString();
            
            if (StringUtils.isEmpty(resultMap.get(randomNo))) {
                resultMap.put(randomNo, randomNo);
            } else {
                i--;
            }
            
            if (i%10000 == 0) {
                logger.debug("randomNo = {}", randomNo);
            }
        }
        
        return resultMap.keySet();
    }
    
    public static void main(String[] args) {
        long lstart = System.currentTimeMillis();
        Set<String> randomNoList = RandomUtil.getRandomNoList(1000000);
        for (String randomNo : randomNoList) {
            System.out.println("randomNo = " + randomNo);
        }
        logger.debug((System.currentTimeMillis()-lstart) + "(ms) elapsed.");
    }
}
