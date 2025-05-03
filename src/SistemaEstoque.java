import javax.swing.*;

public class SistemaEstoque {

        public static void main(String[] args) {
            Movimentar estoque = new Movimentar();

            //Cadastrar insumoo
            Insumo malte = new Insumo("001", "Malte de Cevada","A1");
            Insumo lupulo = new Insumo("002", "Lúpulo Amargo", "A2");

            // Movimentações de exemplo
            estoque.movimentar("001", "E", 100);
            estoque.movimentar("002", "E", 50);
            estoque.movimentar("001", "S", 30);
            estoque.movimentar("002", "S", 60);

            JOptionPane.showMessageDialog(null,
                    "Quantidade total em estoque: " + estoque.quantidadeTotalMateriaPrima());
        }

}
