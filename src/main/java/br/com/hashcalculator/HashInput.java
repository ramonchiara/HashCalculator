package br.com.hashcalculator;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.primefaces.util.Base64;

public class HashInput {

    private String input;
    private String inputType;

    public HashInput() {
        this("", "ascii");
    }

    public HashInput(String input, String inputType) {
        this.input = input;
        this.inputType = inputType;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getTransformedInput() {
        try {
            String transformedInput = input;

            switch (inputType) {
                case "ascii":
                    transformedInput = input;
                    break;
                case "hex":
                    transformedInput = new String(Hex.decodeHex(input.toCharArray()));
                    break;
                case "base64":
                    transformedInput = new String(Base64.decode(input));
                    break;
                case "base64hex":
                    transformedInput = new String(Base64.decode(Hex.decodeHex(input.toCharArray())));
                    break;
            }

            return transformedInput;
        } catch (DecoderException ex) {
            throw new HashServiceException(ex);
        }
    }

}
