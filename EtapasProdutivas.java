package org.example;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

// Classe que representa as etapas produtivas
class EtapasProdutivas {
    public static HashMap<Integer, EtapasProdutivas> etapasProdutivas = new HashMap<>(); // Armazena as etapas produtivas
    public static HashMap<Integer, Double> quantidadesEtapas = new HashMap<>(); // Armazena quantidades para cada etapa
    private String descricaoEtapa; // Descrição da etapa
    private int numeroEtapa; // Número da etapa
    private LocalDateTime dataCadastro; // Data e hora do cadastro


    // Método para cadastrar uma nova etapa produtiva
    public void CadastroEtapasProdutivas(String descricaoEtapa, int numeroEtapa) {
        this.descricaoEtapa = descricaoEtapa; // Define a descrição da etapa
        this.numeroEtapa = numeroEtapa; // Define o número da etapa
        this.dataCadastro = LocalDateTime.now(); // Registra a data e hora do cadastro

        // Verifica se o código da etapa já existe
        if (!isCodigoExistente(numeroEtapa)) {
            etapasProdutivas.put(numeroEtapa, this); // Adiciona a etapa ao HashMap

        } else {
            JOptionPane.showMessageDialog(null, "Código da etapa já existe."); // Mensagem de erro
        }
    }


    // Verifica se a etapa existe pelo código
    private boolean isCodigoExistente(int numeroEtapa) {
        return etapasProdutivas.containsKey(numeroEtapa); // Retorna true se o código existir
    }

    // Método para obter a descrição da etapa
    public String getDescricaoEtapa() {
        return descricaoEtapa; // Retorna a descrição da etapa
    }

    // Método para obter o número da etapa
    public int getNumeroEtapa() {
        return numeroEtapa; // Retorna o número da etapa
    }

    // Método para formatar a data de cadastro
    public String getDataCadastroFormatada() {
        if (dataCadastro == null) {
            return "Data não definida";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dataCadastro.format(formatter); // Retorna a data formatada
    }

    // Verifica se a etapa existe e retorna a etapa se existir
    public static EtapasProdutivas verificarEtapaExistente(int numeroEtapa) {
        return etapasProdutivas.get(numeroEtapa); // Retorna a etapa correspondente ao código
    }

    // Listar todas as etapas produtivas
    public static void criarArquivoExcelEtapasProdutivas(File file) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Etapas Produtivas");

        // Cabeçalhos
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Código");
        headerRow.createCell(1).setCellValue("Descrição");
        headerRow.createCell(2).setCellValue("Data de Cadastro, da etapa");
        headerRow.createCell(3).setCellValue("Quantidade em estoque da etapa");

        // Preencher a planilha com os dados do HashMap
        int rowNum = 1;
        for (EtapasProdutivas etapa : etapasProdutivas.values()) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(etapa.getNumeroEtapa());
            row.createCell(1).setCellValue(etapa.getDescricaoEtapa());
            row.createCell(2).setCellValue(etapa.getDataCadastroFormatada());
            row.createCell(3).setCellValue(quantidadesEtapas.getOrDefault(etapa.getNumeroEtapa(), 0.0));
        }

        // Escrever o arquivo Excel
        try (FileOutputStream fileOut = new FileOutputStream(file)) {
            workbook.write(fileOut);
            JOptionPane.showMessageDialog(null, "Arquivo Excel gerado com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar o arquivo: " + e.getMessage());
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Pesquisar uma etapa pelo código
    public static EtapasProdutivas buscarEtapa(int numeroEtapa) {
        EtapasProdutivas etapa = etapasProdutivas.get(numeroEtapa); // Busca a etapa pelo código
        if (etapa != null) {
            return etapa; // Retorna a etapa se encontrada
        } else {
            JOptionPane.showMessageDialog(null, "Etapa com código " + numeroEtapa + " não encontrada."); // Mensagem de erro
            return null; // Retorna null se não encontrada
        }
    }

    // Excluir uma etapa com tratamento de exceções
    public static void excluirEtapa(int numeroEtapa) {
        try {
            if (etapasProdutivas.containsKey(numeroEtapa)) { // Verifica se a etapa existe
                etapasProdutivas.remove(numeroEtapa); // Remove a etapa do HashMap
                quantidadesEtapas.remove(numeroEtapa); // Remove a quantidade associada à etapa
                JOptionPane.showMessageDialog(null, "Etapa com código " + numeroEtapa + " excluída com sucesso."); // Mensagem de sucesso
            } else {
                JOptionPane.showMessageDialog(null, "Etapa com código " + numeroEtapa + " não encontrada."); // Mensagem de erro
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir etapa: " + e.getMessage()); // Mensagem de erro
        }
    }

    // Método para representar a etapa como uma string
    @Override
    public String toString() {
        return "Etapa " + numeroEtapa + ": " + descricaoEtapa; // Retorna a descrição da etapa
    }
}