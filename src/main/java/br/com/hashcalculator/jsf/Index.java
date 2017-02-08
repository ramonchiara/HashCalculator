package br.com.hashcalculator.jsf;

import br.com.hashcalculator.HashService;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class Index {

    private HashService hashService = new HashService();

    private String input;
    private List<String> output;

    public List<String> getAlgorithms() {
        return hashService.getAlgorithms();
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public List<String> getOutput() {
        return output;
    }

    public void setOutput(List<String> output) {
        this.output = output;
    }

    public void doHash() throws NoSuchAlgorithmException {
        List<String> algorithms = hashService.getAlgorithms();
        output = new ArrayList<>();
        for (String algorithm : algorithms) {
            byte[] hash = hashService.getHash(input, algorithm);
            output.add(new String(hash));
        }
    }

}
