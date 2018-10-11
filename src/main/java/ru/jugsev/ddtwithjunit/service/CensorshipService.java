package ru.jugsev.ddtwithjunit.service;

import org.springframework.stereotype.Service;

/**
 * Service provides censorship
 * Created by lconnected on 11/10/2018.
 */
@Service
public class CensorshipService {

    private static String[] BAD_WORDS = {"greedy", "ugly"};

    /**
     * returns false if censorship is broken
     * @param body
     * @return boolean
     */
    public boolean checkCensorship(String body) {
        String lowerCaseBody = body.toLowerCase();
        for (String badWord : BAD_WORDS) {
            if (lowerCaseBody.contains(badWord)) {
                return false;
            }
        }
        return true;
    }

}
