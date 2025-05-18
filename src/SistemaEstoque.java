import javax.swing.*;

public class SistemaEstoque {
    public static void main(String[] args) {
        Movimentar movimentar = new Movimentar();
        boolean continuar = true;

        while (continuar) {
            String[] opcoes = {
                    "Cadastrar Insumo",
                    "Cadastrar Depósito",
                    "Movimentar Insumo",
                    "Buscar Insumo",
                    "Buscar Depósito",
                    "Excluir Insumo",
                    "Excluir Depósito",
                    "Ver Histórico de Movimentação",
                    "Ver Total de Matéria Prima",
                    "Sair"
            };

            String escolha = (String) JOptionPane.showInputDialog(null, "Escolha uma ação:", "Menu", JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

            switch (escolha) {
                case "Cadastrar Insumo":
                    Insumo insumo = new Insumo();
                    int codigoInsumoCadastro = Integer.parseInt(JOptionPane.showInputDialog("Informe o código do insumo:"));
                    if (insumo.verificarInsumoExistente(codigoInsumoCadastro) == null) {
                        String descricaoInsumo = JOptionPane.showInputDialog("Informe a descrição do insumo:");
                        insumo.InsumoCadastro(codigoInsumoCadastro, descricaoInsumo);
                    } else {
                        JOptionPane.showMessageDialog(null, "Insumo com código " + codigoInsumoCadastro + " já existe.");
                    }
                    break;

                case "Cadastrar Depósito":
                    Deposito deposito = new Deposito();
                    int codigoDepositoCadastro = Integer.parseInt(JOptionPane.showInputDialog("Informe o código do depósito:"));
                    if (deposito.verificarDepositoExistente(codigoDepositoCadastro) == null) {
                        String descricaoDeposito = JOptionPane.showInputDialog("Informe a descrição do depósito:");
                        deposito.DepositoCadastro(codigoDepositoCadastro, descricaoDeposito);
                    } else {
                        JOptionPane.showMessageDialog(null, "Depósito com código " + codigoDepositoCadastro + " já existe.");
                    }
                    break;

                case "Movimentar Insumo":
                    int codigoMovimentarInsumo = Integer.parseInt(JOptionPane.showInputDialog("Informe o código do insumo:"));
                    String tipoOperacao = JOptionPane.showInputDialog("Informe o tipo (E - Entrada, S - Saída, T - Transferência):").toUpperCase();
                    int depositoMovimentar = Integer.parseInt(JOptionPane.showInputDialog("Informe o depósito:"));
                    double quantidade = Double.parseDouble(JOptionPane.showInputDialog("Informe a quantidade:"));
                    movimentar.movimentar(codigoMovimentarInsumo, tipoOperacao, depositoMovimentar, quantidade);
                    break;

                case "Buscar Insumo":
                    int codigoInsumo = Integer.parseInt(JOptionPane.showInputDialog("Informe o código do insumo:"));
                    Insumo insumoBuscado = Insumo.buscarInsumo(codigoInsumo);
                    if (insumoBuscado != null) {
                        JOptionPane.showMessageDialog(null, "Insumo encontrado: " + insumoBuscado.getDescricaoInsumo() + "\nQuantidade: " + Insumo.quantidadesInsumos.get(codigoInsumo));
                    }
                    break;

                case "Buscar Depósito":
                    int codigoBuscaDeposito = Integer.parseInt(JOptionPane.showInputDialog("Informe o depósito:"));
                    Deposito depositoBuscado = Deposito.buscarDeposito(codigoBuscaDeposito);
                    if (depositoBuscado != null) {
                        JOptionPane.showMessageDialog(null, "Depósito encontrado: " + depositoBuscado.getDeposito() + " " + depositoBuscado.getDescricaoDeposito() + "\nQuantidade: " + Deposito.quantidadesDepositos.get(codigoBuscaDeposito));
                    }
                    break;

                case "Excluir Insumo":
                    int codigoInsumoExcluir = Integer.parseInt(JOptionPane.showInputDialog("Informe o código do insumo a ser excluído:"));
                    Insumo.excluirInsumo(codigoInsumoExcluir);
                    break;

                case "Excluir Depósito":
                    int codigoDepositoExcluir = Integer.parseInt(JOptionPane.showInputDialog("Informe o código do depósito a ser excluído:"));
                    Deposito.excluirDeposito(codigoDepositoExcluir);
                    break;

                case "Ver Histórico de Movimentação":
                    StringBuilder historico = new StringBuilder();
                    historico.append("Histórico de Insumos:\n");
                    for (HistoricoMovimentacao.RegistroMovimentacao reg : movimentar.registrar.getHistoricoInsumos()) {
                        historico.append("Código: ").append(reg.getCodigo())
                                .append(", Quantidade: ").append(reg.getQuantidade())
                                .append(", Tipo: ").append(reg.getTipo())
                                .append(", Data/Hora: ").append(reg.getDataHoraFormatada()).append("\n");
                    }

                    historico.append("\nHistórico de Depósitos:\n");
                    for (HistoricoMovimentacao.RegistroMovimentacao reg : movimentar.registrar.getHistoricoDepositos()) {
                        historico.append("Código: ").append(reg.getCodigo())
                                .append(", Quantidade: ").append(reg.getQuantidade())
                                .append(", Tipo: ").append(reg.getTipo())
                                .append(", Data/Hora: ").append(reg.getDataHoraFormatada()).append("\n");
                    }

                    JOptionPane.showMessageDialog(null, historico.toString());
                    break;

                case "Ver Total de Matéria Prima":
                    double total = Insumo.quantidadeTotalInsumos();
                    JOptionPane.showMessageDialog(null, "Total de matéria prima no estoque: " + total);
                    break;

                case "Sair":
                    continuar = false;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }
}