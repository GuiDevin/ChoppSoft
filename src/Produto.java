import javax.swing.*;
import java.time.LocalDateTime;

public class Produto {
    public static void main(String[] args) {
        String situacao = "C";
        EtapasProdutivas etapasProdutivas = new EtapasProdutivas("Moagem", 10);
        EstoqueChopp estoquePilsen = new EstoqueChopp("Pilsen", 10);
        Movimentar estoque = new Movimentar();

        JOptionPane.showMessageDialog(null, "Produto: " + estoquePilsen.getProduto() + " " + estoquePilsen.getDescricaoProduto() +
                "\nEtapa do Processo: " + etapasProdutivas.getDescricaoEtapa() + " " + etapasProdutivas.getNumeroEtapa() +
                "\nSequencial: " + estoquePilsen.getSequencial() +
                "\nPeso: " + estoquePilsen.getPeso() + " kg" +
                "\nData de Registro: " + estoquePilsen.getDataRegistro());
    }
}
