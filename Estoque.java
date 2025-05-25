package org.example;
import javax.swing.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Estoque {



    // -------- METODO INSUMOS ---------

    // Método para salvar insumos em arquivo TXT
    public static void salvarInsumos(List<Insumo> insumos, String caminhoArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (Insumo insumo : insumos) {
                writer.write( insumo.getCodigoInsumo() + ";" + insumo.getDescricaoInsumo());

                writer.newLine();

            }
            System.out.println("Insumos salvos com sucesso");
        } catch (IOException e) {
            System.err.println("erro ao salvar insumos: " + e.getMessage());
        }
    }

    // metodo para salvar a quantidade de insumos
    public static void salvarQuantidadesInsumos(String caminho) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            for (Map.Entry<Integer, Double> entry : Insumo.quantidadesInsumos.entrySet()) {
                writer.write(entry.getKey() + ";" + entry.getValue());
                writer.newLine();
            }
            System.out.println("Quantidades de insumos salvas com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar quantidades de insumos: " + e.getMessage());
        }
    }


    // Método para carregar Insumos de um arquivo txt
    public static List<Insumo> carregarInsumos(String caminhoArquivo) {
        List<Insumo> insumos = new ArrayList<>();
        File file = new File(caminhoArquivo);
        if (!file.exists()) {
            System.out.println("Arquivo de insumos não encontrado: " + caminhoArquivo);
            return insumos;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] dados = linha.split(";");
                if (dados.length < 2) {
                    System.err.println("Linha inválida: " + linha);
                    continue;
                }

                try {
                    int codigo = Integer.parseInt(dados[0].trim());
                    String descricao = dados[1].trim();
                    Insumo insumo = new Insumo();
                    insumo.InsumoCadastro(codigo, descricao);
                    insumos.add(insumo);
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao converter código de insumo: " + dados[0]);
                }
            }
            System.out.println("Insumos carregados com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao carregar insumos: " + e.getMessage());
        }
        return insumos;
    }

    // metodo para carregar a quantidade de insumos
    public static void carregarQuantidadesInsumos(String caminho) {
        File file = new File(caminho);
        if (!file.exists()) {
            System.out.println("Arquivo de quantidades de insumos não encontrado: " + caminho);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 2) {
                    int codigo = Integer.parseInt(partes[0].trim());
                    double quantidade = Double.parseDouble(partes[1].trim());
                    Insumo.quantidadesInsumos.put(codigo, quantidade);
                }
            }
            System.out.println("Quantidades de insumos carregadas com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao carregar quantidades de insumos: " + e.getMessage());
        }
    }





    // METODO DEPOSITO

    // Método para salvar deposito em arquivo txt
    public static void salvarDepositos(List<Deposito> depositos, String caminhoArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (Deposito deposito : depositos) {
                writer.write( deposito.getDeposito() + ";" + deposito.getDescricaoDeposito());
                writer.newLine();
            }
            System.out.println("Depósitos salvos com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar depósitos: " + e.getMessage());
        }
    }

    public static void salvarQuantidadesMateriaPrima(String caminho) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            for (Map.Entry<Integer, Double> entry : Deposito.quantidadesMateriaPrima.entrySet()) {
                writer.write(entry.getKey() + ";" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar quantidades de matéria-prima: " + e.getMessage());
        }
    }

    public static void salvarQuantidadesElaboradoDep(String caminho) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            for (Map.Entry<Integer, Double> entry : Deposito.depositosElaborados.entrySet()) {
                writer.write(entry.getKey() + ";" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar quantidades elaboradas: " + e.getMessage());
        }
    }

    public static void salvarQuantidadesAcabadoDep(String caminho) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            for (Map.Entry<Integer, Double> entry : Deposito.depositosAcabado.entrySet()) {
                writer.write(entry.getKey() + ";" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar quantidades acabadas: " + e.getMessage());
        }
    }


    // Método para carregar deposito de um arquivo TXT
    public static List<Deposito> carregarDepositos(String caminhoArquivo) {
        List<Deposito> depositos = new ArrayList<>();
        File file = new File(caminhoArquivo);
        if (!file.exists()) {
            System.out.println("Arquivo de depósitos não encontrado: " + caminhoArquivo);
            return depositos;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] dados = linha.split(";");
                if (dados.length < 2) {
                    System.err.println("Linha inválida: " + linha);
                    continue;
                }

                try {
                    int codigo = Integer.parseInt(dados[0].trim());
                    String descricao = dados[1].trim();
                    Deposito deposito = new Deposito();
                    deposito.DepositoCadastro(codigo, descricao);
                    depositos.add(deposito);

                } catch (NumberFormatException e) {
                    System.err.println("Erro ao converter código de depósito: " + dados[0]);
                }
            }
            System.out.println("Depósitos carregados com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao carregar depósitos: " + e.getMessage());
        }
        return depositos;
    }

    // metodo para carregar qauntidade de materia prima
    public static void carregarQuantidadesMateriaPrima(String caminho) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int codigo = Integer.parseInt(dados[0].trim());
                double quantidade = Double.parseDouble(dados[1].trim());
                Deposito.quantidadesMateriaPrima.put(codigo, quantidade);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar quantidades de matéria-prima: " + e.getMessage());
        }
    }

    // metodo para carregar quantidade elaborada do deposito
    public static void carregarQuantidadesElaboradoDep(String caminho) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int codigo = Integer.parseInt(dados[0].trim());
                double quantidade = Double.parseDouble(dados[1].trim());
                Deposito.depositosElaborados.put(codigo, quantidade);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar quantidades elaboradas: " + e.getMessage());
        }
    }

    // metoodo para carregar quantidade acabado do deposito
    public static void carregarQuantidadesAcabadoDep(String caminho) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                int codigo = Integer.parseInt(dados[0].trim());
                double quantidade = Double.parseDouble(dados[1].trim());
                Deposito.depositosAcabado.put(codigo, quantidade);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar quantidades acabadas: " + e.getMessage());
        }
    }


    // ---------- HISTÓRICO DE MOVIMENTAÇÕES ----------
    public static void salvarHistorico(List<HistoricoMovimentacao.RegistroMovimentacao> historico, String caminhoArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (HistoricoMovimentacao.RegistroMovimentacao registro : historico) {
                writer.write(
                        registro.getCodigo() + ";" +
                                registro.getQuantidade() + ";" +
                                registro.getTipo() + ";" +
                                registro.getDataHoraFormatada()
                );
                writer.newLine();
            }
            System.out.println("Histórico salvo com sucesso: " + caminhoArquivo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar histórico: " + e.getMessage());
        }
    }

    public static List<HistoricoMovimentacao.RegistroMovimentacao> carregarHistorico(String caminhoArquivo) {
        List<HistoricoMovimentacao.RegistroMovimentacao> historico = new ArrayList<>();
        File file = new File(caminhoArquivo);
        if (!file.exists()) return historico;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                String[] dados = linha.split(";");
                if (dados.length != 4) continue;

                try {
                    int codigo = Integer.parseInt(dados[0]);
                    double quantidade = Double.parseDouble(dados[1]);
                    String tipo = dados[2];
                    String dataHora = dados[3];


                    HistoricoMovimentacao historicoTemp = new HistoricoMovimentacao();
                    HistoricoMovimentacao.RegistroMovimentacao registro = historicoTemp.new RegistroMovimentacao(codigo, quantidade, tipo);
                    historico.add(registro);

                } catch (Exception e) {
                    System.err.println("Erro ao processar linha: " + linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar histórico: " + e.getMessage());
        }
        System.out.println("Historicos carregados com sucesso");
        return historico;
    }


    // ---------- ETAPAS ----------

    // salvar etapas
    public static void salvarEtapas(String caminhoArquivoEtapas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivoEtapas))) {
            for (EtapasProdutivas etapa : EtapasProdutivas.etapasProdutivas.values()) {
                // Linha: código;descrição
                writer.write( etapa.getNumeroEtapa() + ";" + etapa.getDescricaoEtapa());
                writer.newLine();
            }
            System.out.println("Etapas salvas com sucesso em " + caminhoArquivoEtapas);
        } catch (IOException e) {
            System.err.println("Erro ao salvar etapas: " + e.getMessage());
        }
    }

    // carregar etapas
    public static void carregarEtapas(String caminhoArquivoEtapas) {
        File file = new File(caminhoArquivoEtapas);
        if (!file.exists()) {
            System.out.println("Arquivo de etapas não encontrado: " + caminhoArquivoEtapas);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] dados = linha.split(";");
                if (dados.length < 2) {
                    System.err.println("Linha inválida em etapas.txt: " + linha);
                    continue;
                }

                try {
                    int codigo = Integer.parseInt(dados[0].trim());
                    String descricao = dados[1].trim();

                    // Cadastrar a etapa caso ainda não exista
                    if (EtapasProdutivas.verificarEtapaExistente(codigo) == null) {
                        EtapasProdutivas etapa = new EtapasProdutivas();
                        etapa.CadastroEtapasProdutivas(descricao, codigo);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao converter código da etapa: " + dados[0]);
                }
            }
            System.out.println("Etapas carregadas com sucesso de " + caminhoArquivoEtapas);
        } catch (IOException e) {
            System.err.println("Erro ao carregar etapas: " + e.getMessage());
        }
    }

    //Salvar quantidades em quantidades_etapas.txt
    public static void salvarQuantidades(String caminhoArquivoQuantidades) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivoQuantidades))) {
            for (Integer codigo : EtapasProdutivas.quantidadesEtapas.keySet()) {
                double quantidade = EtapasProdutivas.quantidadesEtapas.getOrDefault(codigo, 0.0);
                // Linha: código;quantidade
                writer.write( codigo + ";" + quantidade);
                writer.newLine();
            }
            System.out.println("Quantidades salvas com sucesso em " + caminhoArquivoQuantidades);
        } catch (IOException e) {
            System.err.println("Erro ao salvar quantidades: " + e.getMessage());
        }
    }

    // Carregar quantidades de quantidades_etapas.txt
    public static void carregarQuantidades(String caminhoArquivoQuantidades) {
        File file = new File(caminhoArquivoQuantidades);
        if (!file.exists()) {
            System.out.println("Arquivo de quantidades não encontrado: " + caminhoArquivoQuantidades);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;

                String[] dados = linha.split(";");
                if (dados.length < 2) {
                    System.err.println("Linha inválida em quantidades_etapas.txt: " + linha);
                    continue;
                }

                try {
                    int codigo = Integer.parseInt(dados[0].trim());
                    double quantidade = Double.parseDouble(dados[1].trim());
                    EtapasProdutivas.quantidadesEtapas.put(codigo, quantidade);
                } catch (NumberFormatException e) {
                    System.err.println("Erro ao converter dados da quantidade: " + linha);
                }
            }
            System.out.println("Quantidades carregadas com sucesso de " + caminhoArquivoQuantidades);
        } catch (IOException e) {
            System.err.println("Erro ao carregar quantidades: " + e.getMessage());
        }
    }


    //    --------- MATERIAIS -----------

    // Salvar Armazena os materiais cadastrados

    // Salva os materiais cadastrados
    public static void salvarMateriais(String caminhoMateriais) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoMateriais))) {
            for (Materiais material : Materiais.materiais) {
                writer.write(material.getCodigoMaterial() + ";" + material.getDescricaoMaterial());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar materiais: " + e.getMessage());
        }
    }

    // Salva as quantidades de materiais elaborados
    public static void salvarQuantidadesElaborado(String caminhoQuantidadesElaborado ) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoQuantidadesElaborado ))) {
            for (Map.Entry<Integer, Double> entry : Materiais.quantidadesMateriaisElaborado.entrySet()) {
                writer.write(entry.getKey() + ";" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar quantidades elaboradas: " + e.getMessage());
        }
    }

    // Salva as quantidades de materiais acabados
    public static void salvarQuantidadesAcabado(String caminhoQuantidadesAcabado) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoQuantidadesAcabado))) {
            for (Map.Entry<Integer, Double> entry : Materiais.quantidadesMateriaisAcabado.entrySet()) {
                writer.write(entry.getKey() + ";" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar quantidades acabadas: " + e.getMessage());
        }
    }

    // Salavar quantidades de materiais faturados
    public static void salvarQuantidadesFaturado(String caminho) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            for (Map.Entry<Integer, Double> entry : Materiais.quantidadesMateriaisFaturados.entrySet()) {
                writer.write(entry.getKey() + ";" + entry.getValue());
                writer.newLine();
            }
            System.out.println("Materiais faturados salvos com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar materiais faturados: " + e.getMessage());
        }
    }


    // metodo para carregar a classe materiais

    public static void carregarMateriais(String caminhoMateriais) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoMateriais))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length < 2) continue; // Ignora linhas inválidas

                int codigo = Integer.parseInt(partes[0].trim());
                String descricao = partes[1].trim();

                // Evita duplicatas pelo código
                if (Materiais.buscarMaterial(codigo) != null) continue;

                Materiais material = new Materiais();
                material.codigoMaterial = codigo;
                material.descricaoMaterial = descricao;
                material.setDataCadastro(LocalDateTime.now());

                Materiais.materiais.add(material);
                Materiais.quantidadesMateriaisElaborado.put(codigo, 0.0);
                Materiais.quantidadesMateriaisAcabado.put(codigo, 0.0);
                Materiais.quantidadesMateriaisFaturados.put(codigo, 0.0);
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar materiais: " + e.getMessage());
        }
    }


    // Carrega as quantidades de materiais elaborados
        public static void carregarQuantidadesElaborado(String caminhoQuantidadesElaborado) {
            try (BufferedReader reader = new BufferedReader(new FileReader(caminhoQuantidadesElaborado))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    String[] partes = linha.split(";");
                    int codigo = Integer.parseInt(partes[0]);
                    double quantidade = Double.parseDouble(partes[1]);
                    Materiais.quantidadesMateriaisElaborado.put(codigo, quantidade);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar quantidades elaboradas: " + e.getMessage());
            }
        }

        // Carrega as quantidades de materiais acabados
        public static void carregarQuantidadesAcabado(String caminhoQuantidadesAcabado) {
            try (BufferedReader reader = new BufferedReader(new FileReader(caminhoQuantidadesAcabado))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    String[] partes = linha.split(";");
                    int codigo = Integer.parseInt(partes[0]);
                    double quantidade = Double.parseDouble(partes[1]);
                    Materiais.quantidadesMateriaisAcabado.put(codigo, quantidade);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar quantidades acabadas: " + e.getMessage());
            }
        }

    public static void carregarQuantidadesFaturado(String caminho) {
        try (BufferedReader reader = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                int codigo = Integer.parseInt(partes[0]);
                double quantidade = Double.parseDouble(partes[1]);

                Materiais.quantidadesMateriaisFaturados.put(codigo, quantidade);

                // Garante que o material esteja na lista
                if (Materiais.buscarMaterial(codigo) == null) {
                    Materiais novoMaterial = new Materiais();
                    novoMaterial.codigoMaterial = codigo;
                    novoMaterial.descricaoMaterial = "Material " + codigo; // ou buscar de outro TXT se existir
                    novoMaterial.setDataCadastro(LocalDateTime.now()); // ou carregar se tiver salvo
                    Materiais.materiais.add(novoMaterial);
                }
            }

            System.out.println("Quantidades faturadas carregadas com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao carregar materiais faturados: " + e.getMessage());
        }

    }
    }




