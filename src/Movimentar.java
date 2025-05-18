import javax.swing.*;

// Classe para movimentar insumos
class Movimentar {
    public HistoricoMovimentacao registrar;

    public Movimentar() {
        registrar = new HistoricoMovimentacao();
    }

    public void movimentar(int codigoInsumo, String tipoOperacao, int deposito, double quantidade) {
        try {
            Insumo insumo = Insumo.buscarInsumo(codigoInsumo);
            Deposito deposit = Deposito.buscarDeposito(deposito);
            if (insumo == null || deposit == null) {
                JOptionPane.showMessageDialog(null, "Insumo ou deposito não estão cadastrados!");
                return;
            }

            if (tipoOperacao.equals("E")) {
                // Entrada no estoque
                Deposito.quantidadesDepositos.put(deposito, Deposito.quantidadesDepositos.getOrDefault(deposito, 0.0) + quantidade);
                Insumo.quantidadesInsumos.put(codigoInsumo, Insumo.quantidadesInsumos.getOrDefault(codigoInsumo, 0.0) + quantidade);

                registrar.registrarMovimentacaoInsumo(codigoInsumo, quantidade, "Entrada");
                registrar.registrarMovimentacaoDeposito(deposito, quantidade, "Entrada");

                JOptionPane.showMessageDialog(null, "Entrada de " + quantidade + " unidades de " + insumo.getDescricaoInsumo() + " no depósito " + deposit.getDeposito() + ": " + deposit.getDescricaoDeposito());
            } else if (tipoOperacao.equals("S")) {
                // Saída do estoque
                double quantidadeAtualInsumo = Insumo.quantidadesInsumos.getOrDefault(codigoInsumo, 0.0);
                double quantidadeAtualDeposito = Deposito.quantidadesDepositos.getOrDefault(deposito, 0.0);
                if (quantidadeAtualInsumo >= quantidade && quantidadeAtualDeposito >= quantidade) {
                    Deposito.quantidadesDepositos.put(deposito, quantidadeAtualDeposito - quantidade);
                    Insumo.quantidadesInsumos.put(codigoInsumo, quantidadeAtualInsumo - quantidade);

                    registrar.registrarMovimentacaoInsumo(codigoInsumo, -quantidade, "Saída");
                    registrar.registrarMovimentacaoDeposito(deposito, -quantidade, "Saída");

                    JOptionPane.showMessageDialog(null, "Saída de " + quantidade + " unidades de " + insumo.getDescricaoInsumo() + " no depósito " + deposit.getDeposito() + ": " + deposit.getDescricaoDeposito());
                } else {
                    JOptionPane.showMessageDialog(null, "Estoque insuficiente.");
                }
            } else if (tipoOperacao.equals("T")) {
                // Transferir saldo para um novo depósito ou insumo
                double quantidadeAtualInsumo = Insumo.quantidadesInsumos.getOrDefault(codigoInsumo, 0.0);
                double quantidadeAtualDeposito = Deposito.quantidadesDepositos.getOrDefault(deposito, 0.0);

                if (quantidadeAtualInsumo >= quantidade && quantidadeAtualDeposito >= quantidade) {
                    // Transferir para um novo depósito
                    int resposta = JOptionPane.showConfirmDialog(null, "Deseja informar um novo depósito para o saldo?", "Transferência", JOptionPane.YES_NO_OPTION);
                    int novoDeposito = 0;
                    if (resposta == JOptionPane.YES_OPTION) {
                        novoDeposito = Integer.parseInt(JOptionPane.showInputDialog("Informe o novo depósito:"));
                        Deposito depositoTransferencia = Deposito.buscarDeposito(novoDeposito);
                        if (depositoTransferencia == null) {
                            JOptionPane.showMessageDialog(null, "Depósito com código " + novoDeposito + " não encontrado.");
                            return;
                        } else {
                            Deposito.quantidadesDepositos.put(deposito, quantidadeAtualDeposito - quantidade);
                            registrar.registrarMovimentacaoDeposito(deposito, -quantidade, "Transferência");
                            Deposito.quantidadesDepositos.put(novoDeposito, Deposito.quantidadesDepositos.getOrDefault(novoDeposito, 0.0) + quantidade);
                            registrar.registrarMovimentacaoDeposito(novoDeposito, quantidade, "Transferência");
                        }
                    }

                    // Transferir para um novo insumo
                    resposta = JOptionPane.showConfirmDialog(null, "Deseja informar um novo insumo para o saldo?", "Transferência", JOptionPane.YES_NO_OPTION);
                    int novoCodigoInsumo = 0;
                    if (resposta == JOptionPane.YES_OPTION) {
                        novoCodigoInsumo = Integer.parseInt(JOptionPane.showInputDialog("Informe o novo insumo:"));
                        Insumo insumoTransferencia = Insumo.buscarInsumo(novoCodigoInsumo);
                        if (insumoTransferencia == null) {
                            JOptionPane.showMessageDialog(null, "Insumo com código " + novoCodigoInsumo + " não encontrado.");
                            return;
                        } else {
                            Insumo.quantidadesInsumos.put(codigoInsumo, quantidadeAtualInsumo - quantidade);
                            registrar.registrarMovimentacaoInsumo(codigoInsumo, -quantidade, "Transferência");
                            Insumo.quantidadesInsumos.put(novoCodigoInsumo, Insumo.quantidadesInsumos.getOrDefault(novoCodigoInsumo, 0.0) + quantidade);
                            registrar.registrarMovimentacaoInsumo(novoCodigoInsumo, quantidade, "Transferência");
                        }
                    }

                    JOptionPane.showMessageDialog(null, "Transferência de " + quantidade + " unidades de " + insumo.getDescricaoInsumo() + " para o depósito " + novoDeposito);
                } else {
                    JOptionPane.showMessageDialog(null, "Estoque do insumo ou depósito insuficiente.");
                }
                JOptionPane.showMessageDialog(null, "Transferência encerrada.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao movimentar insumo: " + e.getMessage());
        }
    }
}




