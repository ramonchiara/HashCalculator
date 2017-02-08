package br.com.hashcalculator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Provider.Service;
import java.security.Security;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HashService {

    public List<String> getAlgorithms() {
        List<String> algorithms = new ArrayList<>();

        for (Provider provider : Arrays.asList(Security.getProviders())) {
            for (Service service : provider.getServices()) {
                if (service.getType().equals("MessageDigest")) {
                    algorithms.add(service.getAlgorithm());
                }
            }
        }

        return algorithms;
    }

    public byte[] getHash(String input, String algorithm) throws NoSuchAlgorithmException {
        byte[] hash = null;

        if (input != null) {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(input.getBytes());
            hash = md.digest();
        }

        return hash;
    }

}
