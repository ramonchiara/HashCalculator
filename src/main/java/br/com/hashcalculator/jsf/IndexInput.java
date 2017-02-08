package br.com.hashcalculator.jsf;

import br.com.hashcalculator.services.HashServiceException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.primefaces.util.Base64;

public class IndexInput {

    private String input;
    private String inputType;

    public IndexInput() {
        this("", "ascii");
    }

    public IndexInput(String input, String inputType) {
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
                case "ascii": {
                    transformedInput = input;
                    break;
                }
                case "hex": {
                    String trimmedInput = input.replaceAll("\\s", "");
                    if (trimmedInput.length() % 2 == 0) {
                        byte[] hexToText = Hex.decodeHex(trimmedInput.toCharArray());
                        transformedInput = new String(hexToText);
                    }
                    break;
                }
                case "base64": {
                    byte[] base64ToText = Base64.decode(input);
                    if (base64ToText != null) {
                        transformedInput = new String(base64ToText);
                    }
                    break;
                }
                case "base64hex": {
                    String trimmedInput = input.replaceAll("\\s", "");
                    if (trimmedInput.length() % 2 == 0) {
                        byte[] hexToText = Hex.decodeHex(trimmedInput.toCharArray());
                        byte[] base64ToText = Base64.decode(hexToText);
                        if (base64ToText != null) {
                            transformedInput = new String(base64ToText);
                        }
                    }
                    break;
                }
            }

            return transformedInput;
        } catch (DecoderException ex) {
            throw new HashServiceException(ex);
        }
    }

}
