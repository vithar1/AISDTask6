package com.project.task.util;

import com.project.task.util.HashMaps.HashMap;
import com.project.task.util.HashMaps.impl.AlienHashMap;
import com.project.task.util.HashMaps.impl.MyHashMap;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Obfuscator {
    private ArrayList<String> listKeyWord;
    private HashMap<String, String> mapValues;

    public Obfuscator() {
        listKeyWord = new ArrayList<String>();
        //autowired ручной)
        mapValues = new MyHashMap<String, String>();
    }

    public String darken(String startCode, ArrayList<String> listKeyWord) {
        StringBuilder sb = new StringBuilder();
        sb.append(startCode);

        String startCode1 = sb.toString();
        this.listKeyWord = listKeyWord;
        fillMap(sb.toString());
        for (String word : mapValues.keySet()) {
            Pattern pattern = Pattern.compile(word);
            Matcher matcher = pattern.matcher(startCode1);
            StringBuilder str = new StringBuilder();
            int start = 0;
            while (matcher.find()) {
                if (!isCharNotSpecial(startCode1.charAt(matcher.start() - 1)) && (!isCharNotSpecial(startCode1.charAt(matcher.end())))) {
                    str.append(startCode1, start, matcher.start());
                    start = matcher.end();
                    str.append(mapValues.get(word));
                }
            }
            str.append(startCode1.substring(start));
            startCode1 = str.toString();
        }
        return startCode1;
    }

    private void fillMap(String str) {
        char[] charsStr = str.toCharArray();
        for (String typeStr : listKeyWord) {
            Pattern pattern = Pattern.compile(typeStr);
            Matcher matcher = pattern.matcher(str);
            while (matcher.find()) {
                StringBuilder wordForMap = new StringBuilder();
                int countInChars = matcher.end();
                if (!isCharNotSpecial(charsStr[matcher.start() - 1]) && !isCharNotSpecial(charsStr[matcher.end()])) {
                    if (charsStr[matcher.end()] == '<') {
                        countInChars++;
                        while (charsStr[countInChars] != '>') {
                            countInChars++;
                        }
                        countInChars++;
                    }
                    if (charsStr[matcher.end()] == '[') {
                        countInChars++;
                        while (charsStr[countInChars] != ']') {
                            countInChars++;
                        }
                        countInChars++;
                    }
                    while (charsStr[countInChars] == ' ') {
                        countInChars++;
                    }
                    while (isCharNotSpecial(charsStr[countInChars])) {
                        wordForMap.append(charsStr[countInChars]);
                        countInChars++;
                    }
                    if (!wordForMap.toString().equals("main")) {
                        if(!wordForMap.toString().equals("")) {
                            mapValues.put(wordForMap.toString(), generateCode(wordForMap.toString()));
                        }
                    }
                }
            }
        }
    }

    private boolean isCharNotSpecial(char c) {
        switch (c) {
            case ' ':
            case '.':
            case ';':
            case ')':
            case '(':
            case '[':
            case ']':
            case '{':
            case '}':
            case '>':
            case '<':
            case ':':
            case '=':
            case '!':
            case '|':
            case '&':
            case '+':
            case '-':
            case '\n':
            case '\t':
                return false;
        }
        return true;
    }

    private String generateCode(String value) {
        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("x").append(value.hashCode()).deleteCharAt(1).toString();
    }
}
