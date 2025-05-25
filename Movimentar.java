package org.example;
import javax.swing.*;

// Classe para movimentar insumos e materiais
class Movimentar {
    public HistoricoMovimentacao registrar;

    public Movimentar() {
        registrar = new HistoricoMovimentacao();
    }

    public void movimentarMateriaPrima(int codigoInsumo, String tipoOperacao, int deposito, double quantidade) {
        try {
            Insumo insumo = Insumo.buscarInsumo(codigoInsumo);
            Deposito deposit = Deposito.buscarDeposito(deposito);
            if (insumo == null || deposit == null) return;

            if (tipoOperacao.equals("E")) {
                // Entrada no estoque materia prima
                Deposito.quantidadesMateriaPrima.put(deposito, Deposito.quantidadesMateriaPrima.getOrDefault(deposito, 0.0) + quantidade);
                Insumo.quantidadesInsumos.put(codigoInsumo, Insumo.quantidadesInsumos.getOrDefault(codigoInsumo, 0.0) + quantidade);

                registrar.registrarMovimentacaoInsumo(codigoInsumo, quantidade, "Entrada");
                registrar.registrarMovimentacaoDeposito(deposito, quantidade, "Entrada de Materia Prima");

                JOptionPane.showMessageDialog(null, "Entrada de " + quantidade + " unidades de "
                        + codigoInsumo + " " + insumo.getDescricaoInsumo() + " no depósito " + deposit.getDeposito() + ": " + deposit.getDescricaoDeposito());
            } else if (tipoOperacao.equals("S")) {
                // Saída do estoque materia prima
                double quantidadeAtualInsumo = Insumo.quantidadesInsumos.getOrDefault(codigoInsumo, 0.0);
                double quantidadeAtualDeposito = Deposito.quantidadesMateriaPrima.getOrDefault(deposito, 0.0);
                if (quantidadeAtualInsumo >= quantidade && quantidadeAtualDeposito >= quantidade) {
                    Deposito.quantidadesMateriaPrima.put(deposito, quantidadeAtualDeposito - quantidade);
                    Insumo.quantidadesInsumos.put(codigoInsumo, quantidadeAtualInsumo - quantidade);

                    registrar.registrarMovimentacaoInsumo(codigoInsumo, -quantidade, "Saída");
                    registrar.registrarMovimentacaoDeposito(deposito, -quantidade, "Saída de Materia Prima");

                    JOptionPane.showMessageDialog(null, "Saída de " + quantidade + " unidades de "
                            + codigoInsumo + " "  + insumo.getDescricaoInsumo() + " no depósito " + deposit.getDeposito() + ": " + deposit.getDescricaoDeposito());
                } else {
                    JOptionPane.showMessageDialog(null, "Estoque insuficiente.");
                }
            } else if (tipoOperacao.equals("T")) {
                // Transferir saldo para um novo depósito ou insumo
                double quantidadeAtualInsumo = Insumo.quantidadesInsumos.getOrDefault(codigoInsumo, 0.0);
                double quantidadeAtualDeposito = Deposito.quantidadesMateriaPrima.getOrDefault(deposito, 0.0);

                if (quantidadeAtualInsumo >= quantidade) {
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
                            Deposito.quantidadesMateriaPrima.put(deposito, quantidadeAtualDeposito - quantidade);
                            registrar.registrarMovimentacaoDeposito(deposito, -quantidade, "Transferência de Materia Prima");
                            Deposito.quantidadesMateriaPrima.put(novoDeposito, Deposito.quantidadesMateriaPrima.getOrDefault(novoDeposito, 0.0) + quantidade);
                            registrar.registrarMovimentacaoDeposito(novoDeposito, quantidade, "Transferência de Materia Prima");
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

                    JOptionPane.showMessageDialog(null, "Transferência de " + quantidade + " unidades de "
                            + codigoInsumo + " "  + insumo.getDescricaoInsumo() + " para o depósito " + novoDeposito);
                } else {
                    JOptionPane.showMessageDialog(null, "Estoque do insumo insuficiente.");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao movimentar insumo: " + e.getMessage());
        }
    }

    public void movimentarMaterialElaborado(int codigoMaterial, int codigoEtapaProducao, String tipoOperacao, double quantidade, int codigoDeposito) {
        try {
            Materiais material = Materiais.buscarMaterial(codigoMaterial);
            EtapasProdutivas etapa = EtapasProdutivas.buscarEtapa(codigoEtapaProducao);
            Deposito depositoOrigem = Deposito.buscarDeposito(codigoDeposito);

            if (material == null || etapa == null || depositoOrigem == null) return;

            if (tipoOperacao.equals("E")) {

                // Entrada no estoque de material elaborado
                Deposito.depositosElaborados.put(codigoDeposito, Deposito.depositosElaborados.getOrDefault(codigoDeposito, 0.0) + quantidade);
                Materiais.quantidadesMateriaisElaborado.put(codigoMaterial, Materiais.quantidadesMateriaisElaborado.getOrDefault(codigoMaterial, 0.0) + quantidade);
                EtapasProdutivas.quantidadesEtapas.put(codigoEtapaProducao, EtapasProdutivas.quantidadesEtapas.getOrDefault(codigoMaterial, 0.0) + quantidade);

                // Registrar movimentações
                registrar.registrarMovimentacaoMaterialElaborado(codigoMaterial, quantidade, "Entrada");
                registrar.registrarMovimentacaoDeposito(codigoDeposito, quantidade, "Entrada de Material Elaborado");
                registrar.registrarMovimentacaoEtapaProdutiva(codigoEtapaProducao, quantidade, "Entrada");


                JOptionPane.showMessageDialog(null, "Entrada de " + quantidade + " unidades de material elaborado: \n" +
                        material.getCodigoMaterial()+ " - " +material.getDescricaoMaterial()
                        + " no depósito "+ depositoOrigem.getDeposito() + " - " + depositoOrigem.getDescricaoDeposito() +
                        " da etapa " + etapa.getNumeroEtapa() + " - " + etapa.getDescricaoEtapa());
            } else if (tipoOperacao.equals("S")) {
                // Saída do estoque de material elaborado
                double quantidadeAtualMaterialElaborado = Materiais.quantidadesMateriaisElaborado.getOrDefault(codigoMaterial, 0.0);
                double quantidadeAtualDeposito = Deposito.depositosElaborados.getOrDefault(codigoDeposito, 0.0);
                double quantidadeAtualEtapa = EtapasProdutivas.quantidadesEtapas.getOrDefault(codigoEtapaProducao, 0.0);

                if (quantidadeAtualMaterialElaborado >= quantidade) {
                    Materiais.quantidadesMateriaisElaborado.put(codigoMaterial, quantidadeAtualMaterialElaborado - quantidade);
                    Deposito.depositosElaborados.put(codigoDeposito, quantidadeAtualDeposito - quantidade);
                    EtapasProdutivas.quantidadesEtapas.put(codigoEtapaProducao, quantidadeAtualEtapa - quantidade);

                    // Registrar movimentações
                    registrar.registrarMovimentacaoMaterialElaborado(codigoMaterial, -quantidade, "Saída");
                    registrar.registrarMovimentacaoDeposito(codigoDeposito, -quantidade, "Saída de Material Elaborado");
                    registrar.registrarMovimentacaoEtapaProdutiva(codigoEtapaProducao, -quantidade, "Saída");

                    JOptionPane.showMessageDialog(null, "Saída de " + quantidade + " unidades de material elaborado: \n" +
                            material.getCodigoMaterial()+ " - " +material.getDescricaoMaterial()
                            + " no depósito "+ depositoOrigem.getDeposito() + " - " + depositoOrigem.getDescricaoDeposito() +
                            " da etapa " + etapa.getNumeroEtapa() + " - " + etapa.getDescricaoEtapa());
                } else {
                    JOptionPane.showMessageDialog(null, "Estoque de material elaborado insuficiente.");
                }



            } else if (tipoOperacao.equals("T")) {
                // Transferir saldo para um novo depósito ou material
                double quantidadeAtualMaterialElaborado = Materiais.quantidadesMateriaisElaborado.getOrDefault(codigoMaterial, 0.0);
                double quantidadeAtualDeposito = Deposito.depositosElaborados.getOrDefault(codigoDeposito, 0.0);
                double quantidadeAtualEtapa = EtapasProdutivas.quantidadesEtapas.getOrDefault(codigoEtapaProducao, 0.0);

                if (quantidadeAtualMaterialElaborado >= quantidade) {
                    // Transferir para um novo depósito
                    int resposta = JOptionPane.showConfirmDialog(null, "Deseja informar um novo depósito para o saldo?", "Transferência", JOptionPane.YES_NO_OPTION);
                    int novoDeposito = 0;
                    if (resposta == JOptionPane.YES_OPTION) {
                        novoDeposito = Integer.parseInt(JOptionPane.showInputDialog("Informe o novo depósito:"));
                        Deposito depositoTransferencia = Deposito.buscarDeposito(novoDeposito);
                        if (depositoTransferencia == null) {
                            return;
                        } else {
                            // Atualiza a quantidade no depósito original
                            Deposito.depositosElaborados.put(codigoDeposito, quantidadeAtualDeposito - quantidade);
                            registrar.registrarMovimentacaoDeposito(codigoDeposito, -quantidade, "Transferência de Material Elaborado" );

                            // Atualiza a quantidade no novo depósito
                            Deposito.depositosElaborados.put(novoDeposito, Deposito.depositosElaborados.getOrDefault(novoDeposito, 0.0) + quantidade);
                            registrar.registrarMovimentacaoDeposito(novoDeposito, quantidade, "Transferência de Material Elaborado");
                        }
                    }

                    // Transferir para um novo material
                    resposta = JOptionPane.showConfirmDialog(null, "Deseja informar um novo material para o saldo?", "Transferência", JOptionPane.YES_NO_OPTION);
                    int novoCodigoMaterial = 0;
                    if (resposta == JOptionPane.YES_OPTION) {
                        novoCodigoMaterial = Integer.parseInt(JOptionPane.showInputDialog("Informe o novo material:"));
                        Materiais materialTransferencia = Materiais.buscarMaterial(novoCodigoMaterial);
                        if (materialTransferencia == null) {
                            return;
                        } else {
                            // Atualiza a quantidade do material original
                            Materiais.quantidadesMateriaisElaborado.put(codigoMaterial, quantidadeAtualMaterialElaborado - quantidade);
                            registrar.registrarMovimentacaoMaterialElaborado(codigoMaterial, -quantidade, "Transferência");

                            // Atualiza a quantidade no novo material
                            Materiais.quantidadesMateriaisElaborado.put(novoCodigoMaterial, Materiais.quantidadesMateriaisElaborado.getOrDefault(novoCodigoMaterial, 0.0) + quantidade);
                            registrar.registrarMovimentacaoMaterialElaborado(novoCodigoMaterial, quantidade, "Transferência");
                        }
                    }

                    // Transferir para uma nova etapa
                    resposta = JOptionPane.showConfirmDialog(null, "Deseja informar uma nova etapa para o saldo?", "Transferência", JOptionPane.YES_NO_OPTION);
                    int novoCodigoEtapa = 0;
                    if (resposta == JOptionPane.YES_OPTION) {
                        novoCodigoEtapa = Integer.parseInt(JOptionPane.showInputDialog("Informe a nova etapa:"));
                        EtapasProdutivas etapaTransferencia = EtapasProdutivas.buscarEtapa(novoCodigoEtapa);
                        if (etapaTransferencia == null) {
                            return;
                        } else {
                            // Atualiza a quantidade da etapa original
                            EtapasProdutivas.quantidadesEtapas.put(codigoEtapaProducao, quantidadeAtualEtapa - quantidade);
                            registrar.registrarMovimentacaoEtapaProdutiva(codigoEtapaProducao, -quantidade, "Transferência");

                            // Atualiza a quantidade na nova etapa
                            EtapasProdutivas.quantidadesEtapas.put(novoCodigoEtapa, EtapasProdutivas.quantidadesEtapas.getOrDefault(novoCodigoEtapa, 0.0) + quantidade);
                            registrar.registrarMovimentacaoEtapaProdutiva(novoCodigoEtapa, quantidade, "Transferência");
                        }
                    }

                    JOptionPane.showMessageDialog(null, "Transferência de " + quantidade + " unidades de " +
                            material.getCodigoMaterial() + " - " + material.getDescricaoMaterial() +
                            " para o depósito " + novoDeposito + " e material " + novoCodigoMaterial + " na etapa " + novoCodigoEtapa);
                } else {
                    JOptionPane.showMessageDialog(null, "Estoque do material elaborado insuficiente.");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao movimentar material elaborado: " + e.getMessage());
        }
    }

    public void movimentarMaterialAcabado(int codigoMaterial, String tipoOperacao, int codigoDeposito, double quantidade) {
        try {
            Materiais material = Materiais.buscarMaterial(codigoMaterial);
            Deposito deposito = Deposito.buscarDeposito(codigoDeposito);

            if (material == null || deposito == null) {
                return;
            }

            if (tipoOperacao.equals("E")) {
                // Entrada no estoque de material acabado
                Deposito.depositosAcabado.put(codigoDeposito, Deposito.depositosAcabado.getOrDefault(codigoDeposito, 0.0) + quantidade);
                Materiais.quantidadesMateriaisAcabado.put(codigoMaterial, Materiais.quantidadesMateriaisAcabado.getOrDefault(codigoMaterial, 0.0) + quantidade);

                registrar.registrarMovimentacaoMaterialAcabado(codigoMaterial, quantidade, "Entrada");
                registrar.registrarMovimentacaoDeposito(codigoDeposito, quantidade, "Entrada de Material Acabado");

                JOptionPane.showMessageDialog(null, "Entrada de " + quantidade + " unidades de material acabado: \n" +
                        material.getCodigoMaterial() + " - " + material.getDescricaoMaterial() +
                        " no depósito " + deposito.getDeposito() + " - " + deposito.getDescricaoDeposito());
            } else if (tipoOperacao.equals("S")) {
                // Saída do estoque de material acabado
                double quantidadeAtualMaterial = Materiais.quantidadesMateriaisAcabado.getOrDefault(codigoMaterial, 0.0);
                double quantidadeAtualDeposito = Deposito.depositosAcabado.getOrDefault(codigoDeposito, 0.0);

                if (quantidadeAtualMaterial >= quantidade) {
                    Materiais.quantidadesMateriaisAcabado.put(codigoMaterial, quantidadeAtualMaterial - quantidade);
                    Deposito.depositosAcabado.put(codigoDeposito, quantidadeAtualDeposito - quantidade);
                    Materiais.quantidadesMateriaisFaturados.put(codigoMaterial, Materiais.quantidadesMateriaisFaturados.getOrDefault(codigoMaterial, 0.0) + quantidade);

                    registrar.registrarMovimentacaoMaterialAcabado(codigoMaterial, -quantidade, "Saída");
                    registrar.registrarMovimentacaoDeposito(codigoDeposito, -quantidade, "Saída de Material Acabado");
                    registrar.registrarMovimentacaoMaterialFaturado(codigoMaterial, quantidade, "Entrada");

                    JOptionPane.showMessageDialog(null, "Saída de " + quantidade + " unidades de material acabado: \n" +
                            material.getCodigoMaterial() + " - " + material.getDescricaoMaterial() +
                            " no depósito " + deposito.getDeposito() + " - " + deposito.getDescricaoDeposito());
                } else {
                    JOptionPane.showMessageDialog(null, "Estoque de material acabado insuficiente.");
                }
            } else if (tipoOperacao.equals("T")) {
                // Transferir saldo para um novo depósito ou material
                double quantidadeAtualMaterial = Materiais.quantidadesMateriaisAcabado.getOrDefault(codigoMaterial, 0.0);

                if (quantidadeAtualMaterial >= quantidade) {
                    // Transferir para um novo depósito
                    int resposta = JOptionPane.showConfirmDialog(null, "Deseja informar um novo depósito para o saldo?", "Transferência", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        int novoDeposito = Integer.parseInt(JOptionPane.showInputDialog("Informe o novo depósito:"));
                        Deposito depositoTransferencia = Deposito.buscarDeposito(novoDeposito);
                        if (depositoTransferencia == null) {
                            return;
                        } else {
                            // Atualiza a quantidade no depósito original
                            Deposito.depositosAcabado.put(codigoDeposito,
                                    Deposito.depositosAcabado.getOrDefault(codigoDeposito, 0.0) - quantidade);
                            registrar.registrarMovimentacaoDeposito(codigoDeposito, -quantidade, "Transferência de Material Acabado");

                            // Atualiza a quantidade no novo depósito
                            Deposito.depositosAcabado.put(novoDeposito,
                                    Deposito.depositosAcabado.getOrDefault(novoDeposito, 0.0) + quantidade);
                            registrar.registrarMovimentacaoDeposito(novoDeposito, quantidade, "Transferência de Material Acabado");
                        }
                    }

                    // Transferir para um novo material
                    resposta = JOptionPane.showConfirmDialog(null, "Deseja informar um novo material para o saldo?", "Transferência", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        int novoCodigoMaterial = Integer.parseInt(JOptionPane.showInputDialog("Informe o novo material:"));
                        Materiais materialTransferencia = Materiais.buscarMaterial(novoCodigoMaterial);
                        if (materialTransferencia == null) {
                            return;
                        } else {
                            // Atualiza a quantidade do material original
                            Materiais.quantidadesMateriaisAcabado.put(codigoMaterial, quantidadeAtualMaterial - quantidade);
                            registrar.registrarMovimentacaoMaterialAcabado(codigoMaterial, -quantidade, "Transferência");

                            // Atualiza a quantidade no novo material
                            Materiais.quantidadesMateriaisAcabado.put(novoCodigoMaterial,
                                    Materiais.quantidadesMateriaisAcabado.getOrDefault(novoCodigoMaterial, 0.0) + quantidade);
                            registrar.registrarMovimentacaoMaterialAcabado(novoCodigoMaterial, quantidade, "Transferência");
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Estoque do material elaborado insuficiente.");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao movimentar material elaborado: " + e.getMessage());
        }
    }
}
