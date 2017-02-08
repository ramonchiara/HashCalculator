package br.com.hashcalculator.jsf;

import br.com.hashcalculator.HashInput;
import br.com.hashcalculator.HashOutput;
import br.com.hashcalculator.HashService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class Index {

    private HashInput input = new HashInput();
    private List<HashOutput> outputs;

    public Index() {
        doHash();
    }

    public HashInput getInput() {
        return input;
    }

    public void setInput(HashInput input) {
        this.input = input;
    }

    public List<HashOutput> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<HashOutput> outputs) {
        this.outputs = outputs;
    }

    public final void doHash() {
        HashService hashService = new HashService();
        List<String> algorithms = hashService.getAlgorithms();
        outputs = new ArrayList<>();
        for (String algorithm : algorithms) {
            byte[] hash = hashService.getHash(input.getTransformedInput(), algorithm);
            outputs.add(new HashOutput(algorithm, hash));
        }
    }

}
