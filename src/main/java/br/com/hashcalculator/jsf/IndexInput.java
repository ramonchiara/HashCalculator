package br.com.hashcalculator.jsf;

import br.com.hashcalculator.services.HashServiceException;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class IndexInput {

    private String input = "";
    private String inputType = "ascii";
    private String viewAs = "ascii";

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

    public String getViewAs() {
        return viewAs;
    }

    public void setViewAs(String viewAs) {
        this.viewAs = viewAs;
    }

    public byte[] getTransformedInput() {
        byte[] transformedInput = input.getBytes(); // ascii as default

        try {
            switch (inputType) {
                case "ascii": {
                    break;
                }
                case "hex": {
                    String trimmedInput = input.replaceAll("\\s", "");
                    if (trimmedInput.length() % 2 == 0) {
                        transformedInput = Hex.decodeHex(trimmedInput.toCharArray());
                    }
                    break;
                }
                case "base64": {
                    byte[] base64ToText = Base64.decodeBase64(transformedInput);
                    if (base64ToText != null) {
                        transformedInput = base64ToText;
                    }
                    break;
                }
                case "base64hex": {
                    String trimmedInput = input.replaceAll("\\s", "");
                    if (trimmedInput.length() % 2 == 0) {
                        byte[] hexToText = Hex.decodeHex(trimmedInput.toCharArray());
                        byte[] base64ToText = Base64.decodeBase64(hexToText);
                        if (base64ToText != null) {
                            transformedInput = base64ToText;
                        }
                    }
                    break;
                }
            }
        } catch (DecoderException ex) {
            throw new HashServiceException(ex);
        }

        return transformedInput;
    }

    public String getTransformedInputForView() {
        byte[] transformedInput = getTransformedInput();
        String transformedInputForView = new String(transformedInput); // ascii default

        switch (viewAs) {
            case "ascii":
                break;
            case "hex":
                transformedInputForView = new String(Hex.encodeHex(transformedInput)).toUpperCase();
                break;
            case "base64":
                transformedInputForView = new String(Base64.encodeBase64(transformedInput));
                break;
        }

        return transformedInputForView;
    }

}
