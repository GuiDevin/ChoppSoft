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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Insumo {
    private int codigo; // Código do insumo
    private String descricao; // Descrição do insumo
    private LocalDateTime dataCadastro; // Data e hora do cadastro
    public static HashMap<Integer, Double> quantidadesInsumos = new HashMap<>(); // Armazena as quantidades de insumos
    public static List<Insumo> insumos = new ArrayList<>(); // Lista para armazenar insumos cadastrados

    // Método para cadastrar um novo insumo
    public void InsumoCadastro(int codigoInsumo, String descricaoInsumo) {
        this.codigo = codigoInsumo; // Define o código do insumo
        this.descricao = descricaoInsumo; // Define a descrição do insumo
        this.dataCadastro = LocalDateTime.now(); // Registra a data e hora do cadastro
        insumos.add(this); // Adiciona o insumo à lista de insumos

    }

    // Método para obter a data de cadastro do insumo
    public String getDataCadastroFormatada() {
        if (dataCadastro == null) {
        return "Data não definida";
    }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dataCadastro.format(formatter); // Retorna a data formatada
    }

    // Método para obter o código do insumo
    public int getCodigoInsumo() {
        return codigo; // Retorna o código do insumo
    }

    // Método para obter a descrição do insumo
    public String getDescricaoInsumo() {
        return descricao; // Retorna a descrição do insumo
    }

    // Método para buscar um insumo pelo código
    public static Insumo buscarInsumo(int codigoInsumo) {
        for (Insumo insum : insumos) { // Percorre a lista de insumos
            if (insum.getCodigoInsumo() == codigoInsumo) { // Verifica se o código corresponde
                return insum; // Retorna o insumo encontrado
            }
        }
        JOptionPane.showMessageDialog(null, "Insumo com código " + codigoInsumo + " não encontrado."); // Mensagem de erro
        return null; // Retorna null se não encontrado
    }

    // Método para verificar se um insumo existe
    public static Insumo verificarInsumoExistente(int codigoInsumo) {
        for (Insumo insum : insumos) { // Percorre a lista de insumos
            if (insum.getCodigoInsumo() == codigoInsumo) { // Verifica se o código corresponde
                return insum; // Retorna o insumo se encontrado
            }
        }
        return null; // Retorna null se não encontrado
    }

    // Método para calcular a quantidade total de insumos
    public static double quantidadeTotalInsumos() {
        double total = 0; // Inicializa o total
        for (Double quantidade : quantidadesInsumos.values()) { // Percorre as quantidades
            total += quantidade; // Soma as quantidades
        }
        return total; // Retorna o total
    }

    // Método para excluir um insumo com tratamento de exceções
    public static void excluirInsumo(int codigoInsumo) {
        try {
            Insumo insumo = verificarInsumoExistente(codigoInsumo); // Verifica se o insumo existe
            if (insumo != null) { // Se encontrado
                insumos.remove(insumo); // Remove da lista de insumos
                quantidadesInsumos.remove(codigoInsumo); // Remove da HashMap de quantidades
                JOptionPane.showMessageDialog(null, "Insumo com código " + codigoInsumo + " e descrição '" + insumo.descricao + "' excluído com sucesso."); // Mensagem de sucesso
            } else {
                JOptionPane.showMessageDialog(null, "Insumo com código " + codigoInsumo + " não encontrado."); // Mensagem de erro
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir insumo: " + e.getMessage()); // Mensagem de erro
        }
    }

    // Método para listar todos os insumos cadastrados
    public static void criarArquivoExcelInsumos(File file) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Insumos");

        // Cabeçalhos
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Código");
        headerRow.createCell(1).setCellValue("Descrição");
        headerRow.createCell(2).setCellValue("Data de Cadastro, do Insumo");
        headerRow.createCell(3).setCellValue("Quantidade de cada Insumo");

        // Preencher a planilha com os dados do HashMap
        int rowNum = 1;
        for (Insumo insumo : insumos) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(insumo.getCodigoInsumo());
            row.createCell(1).setCellValue(insumo.getDescricaoInsumo());
            row.createCell(2).setCellValue(insumo.getDataCadastroFormatada());
            row.createCell(3).setCellValue(quantidadesInsumos.getOrDefault(insumo.getCodigoInsumo(), 0.0));
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
}