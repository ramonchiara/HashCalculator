package br.com.hashcalculator.jsf;

import br.com.hashcalculator.services.HashService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class Index {

    private IndexInput input = new IndexInput();
    private List<IndexOutput> outputs;

    public Index() {
        doHash();
    }

    public IndexInput getInput() {
        return input;
    }

    public List<IndexOutput> getOutputs() {
        return outputs;
    }

    public final void doHash() {
        HashService hashService = new HashService();
        List<String> algorithms = hashService.getAlgorithms();
        outputs = new ArrayList<>();
        for (String algorithm : algorithms) {
            byte[] hash = hashService.getHash(input.getTransformedInput(), algorithm);
            outputs.add(new IndexOutput(algorithm, hash));
        }
    }

}
