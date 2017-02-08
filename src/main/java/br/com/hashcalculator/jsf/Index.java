package br.com.hashcalculator.jsf;

import br.com.hashcalculator.HashOutput;
import br.com.hashcalculator.HashService;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class Index {

    private HashService hashService = new HashService();

    private String input;
    private List<HashOutput> outputs;

    public Index() throws NoSuchAlgorithmException {
        doHash();
    }

    public List<String> getAlgorithms() {
        return hashService.getAlgorithms();
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public List<HashOutput> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<HashOutput> outputs) {
        this.outputs = outputs;
    }

    public void doHash() throws NoSuchAlgorithmException {
        List<String> algorithms = hashService.getAlgorithms();
        outputs = new ArrayList<>();
        for (String algorithm : algorithms) {
            byte[] hash = hashService.getHash(input, algorithm);
            outputs.add(new HashOutput(algorithm, hash));
        }
    }

}
