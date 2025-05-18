import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

class EtapasProdutivas {
    private List<EtapasProdutivas> etapasProdutivas;
    private String descricaoEtapa;
    private  int numeroEtapa;

    public EtapasProdutivas(String descricaoEtapa, int codigoEtapa) {
        this.etapasProdutivas = new ArrayList<>();
        this.descricaoEtapa = descricaoEtapa;
        this.numeroEtapa = codigoEtapa;

        if (!isCodigoExistente(codigoEtapa)) {
            etapasProdutivas.add(this);
        }
    }
// verifica se a etapa existe
    private boolean isCodigoExistente(int codigoEtapa) {
        for (EtapasProdutivas etapa : etapasProdutivas) {
            if (etapa.getNumeroEtapa() == codigoEtapa) {
                return true;
            }
        }
        return false;
    }

    public String getDescricaoEtapa() {
        return descricaoEtapa;
    }

    public int getNumeroEtapa() {
        return numeroEtapa;
    }

    //listar todas as etapas produtivas
    public List<EtapasProdutivas> getEtapasProdutivas() {
        StringBuilder listarTodasEtapas = new StringBuilder("Listagem de todas as etapas: \n");
        for (int i = 0; i < etapasProdutivas.size(); i++) {
            listarTodasEtapas.append(etapasProdutivas.get(i).toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null,listarTodasEtapas.toString());
        return etapasProdutivas;
    }
}
