package br.com.hashcalculator;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class HashOutput {

    private String algorithm;
    private byte[] hash;

    public HashOutput(String algorithm, byte[] hash) {
        this.algorithm = algorithm;
        this.hash = hash;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getHash() {
        return hash == null ? "" : new String(hash);
    }

    public String getSize() {
        return hash == null ? "" : hash.length + " byte(s)";
    }

    public String getHexa() {
        return hash == null ? "" : new String(Hex.encodeHex(hash)).toUpperCase();
    }

    public String getBase64() {
        return hash == null ? "" : new String(Base64.encodeBase64(hash));
    }

    public String getBase64InHexa() {
        return hash == null ? "" : new String(Hex.encodeHex(Base64.encodeBase64(hash))).toUpperCase();
    }

}
