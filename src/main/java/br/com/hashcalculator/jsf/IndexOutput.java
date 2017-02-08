package br.com.hashcalculator.jsf;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class IndexOutput {

    private String algorithm;
    private byte[] hash;

    public IndexOutput(String algorithm, byte[] hash) {
        this.algorithm = algorithm;
        this.hash = hash;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getHash() {
        return new String(removeISOControl(hash));
    }

    public String getSize() {
        return hash.length + " byte(s) / " + 8 * hash.length + " bit(s)";
    }

    public String getHexa() {
        return new String(Hex.encodeHex(hash)).toUpperCase();
    }

    public String getBase64() {
        return new String(Base64.encodeBase64(hash));
    }

    public String getBase64InHexa() {
        return new String(Hex.encodeHex(Base64.encodeBase64(hash))).toUpperCase();
    }

    private static byte[] removeISOControl(byte[] buffer) {
        byte[] response = buffer.clone();

        for (int i = 0; i < response.length; i++) {
            if (Character.isISOControl(response[i])) {
                response[i] = '_';
            }
        }

        return response;
    }

}
