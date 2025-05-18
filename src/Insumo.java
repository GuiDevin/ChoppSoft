import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Insumo {
    private int codigo;
    private String descricao;
    public static HashMap<Integer, Double> quantidadesInsumos = new HashMap<>();
    public static List<Insumo> insumos = new ArrayList<>();

    public void InsumoCadastro(int codigoInsumo, String descricaoInsumo) {

            this.codigo = codigoInsumo;
            this.descricao = descricaoInsumo;
            insumos.add(this);
            JOptionPane.showMessageDialog(null, "Insumo cadastrado com sucesso!");

    }

    public int getCodigoInsumo() {
        return codigo;
    }

    public String getDescricaoInsumo() {
        return descricao;
    }

    public static Insumo buscarInsumo(int codigoInsumo) {
        for (Insumo insum : insumos) {
            if (insum.getCodigoInsumo() == codigoInsumo) {
                return insum;
            }
        }
        JOptionPane.showMessageDialog(null, "Depósito com código " + codigoInsumo + " não encontrado.");
        return null;
    }
    public static Insumo verificarInsumoExistente(int codigoInsumo) {
        for (Insumo insum : insumos) {
            if (insum.getCodigoInsumo() == codigoInsumo) {
                return insum;
            }
        }
        return null;
    }

    public static double quantidadeTotalInsumos() {
        double total = 0;
        for (Double quantidade : quantidadesInsumos.values()) {
            total += quantidade;
        }
        return total;
    }
}