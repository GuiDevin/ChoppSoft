import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Deposito {
    private int deposito;
    private String descricao;
    public static HashMap<Integer, Double> quantidadesDepositos = new HashMap<>();
    public static List<Deposito> depositos = new ArrayList<>();

    public void DepositoCadastro(int codigoDeposito, String descricaoDeposito) {

            this.deposito = codigoDeposito;
            this.descricao = descricaoDeposito;
            depositos.add(this);
            JOptionPane.showMessageDialog(null, "Depósito cadastrado com sucesso!");


    }

    public String getDescricaoDeposito() {
        return descricao;
    }

    public int getDeposito() {
        return deposito;
    }

    public static Deposito buscarDeposito(int codigoDeposito) {
        for (Deposito dep : depositos) {
            if (dep.getDeposito() == codigoDeposito) {
                return dep;
            }
        }
        JOptionPane.showMessageDialog(null, "Depósito com código " + codigoDeposito + " não encontrado.");
        return null;
    }
    public static Deposito verificarDepositoExistente(int codigoDeposito) {
        for (Deposito dep : depositos) {
            if (dep.getDeposito() == codigoDeposito) {
                return dep;
            }
        }
        return null;
    }

}
