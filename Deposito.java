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

// Classe que representa um depósito
class Deposito {
    private int deposito; // Código do depósito
    private String descricao; // Descrição do depósito
    private LocalDateTime dataCadastro; // Data e hora do cadastro

    public static HashMap<Integer, Double> quantidadesMateriaPrima = new HashMap<>(); // Armazena quantidades de matéria-prima nos depósitos
    public static HashMap<Integer, Double> depositosElaborados = new HashMap<>(); // Armazena quantidades de produtos elaborados
    public static HashMap<Integer, Double> depositosAcabado = new HashMap<>(); // Armazena quantidades de produtos acabados
    public static List<Deposito> depositos = new ArrayList<>(); // Lista para armazenar depósitos cadastrados

    // Método para cadastrar um novo depósito
    public void DepositoCadastro(int codigoDeposito, String descricaoDeposito) {
        this.deposito = codigoDeposito; // Define o código do depósito
        this.descricao = descricaoDeposito; // Define a descrição do depósito
        this.dataCadastro = LocalDateTime.now(); // Registra a data e hora do cadastro
        depositos.add(this); // Adiciona o depósito à lista de depósitos

    }

    // Método para obter a descrição do depósito
    public String getDescricaoDeposito() {
        return descricao; // Retorna a descrição do depósito
    }

    // Método para obter o código do depósito
    public int getDeposito() {
        return deposito; // Retorna o código do depósito
    }

    // Método para formatar a data de cadastro
    public String getDataCadastroFormatada() {
        if (dataCadastro == null) {
            return "Data não definida";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dataCadastro.format(formatter); // Retorna a data formatada
    }

    // Método para buscar um depósito pelo código
    public static Deposito buscarDeposito(int codigoDeposito) {
        for (Deposito dep : depositos) { // Percorre a lista de depósitos
            if (dep.getDeposito() == codigoDeposito) { // Verifica se o código corresponde
                return dep; // Retorna o depósito encontrado
            }
        }
        JOptionPane.showMessageDialog(null, "Depósito com código " + codigoDeposito + " não encontrado."); // Mensagem de erro
        return null; // Retorna null se não encontrado
    }

    // Método para verificar se um depósito existe pelo código
    public static Deposito verificarDepositoExistente(int codigoDeposito) {
        for (Deposito dep : depositos) { // Percorre a lista de depósitos
            if (dep.getDeposito() == codigoDeposito) { // Verifica se o código corresponde
                return dep; // Retorna o depósito se encontrado
            }
        }
        return null; // Retorna null se não encontrado
    }

    // Excluir depósito com tratamento de exceções
    public static void excluirDeposito(int codigoDeposito) {
        try {
            Deposito deposito = verificarDepositoExistente(codigoDeposito); // Verifica se o depósito existe
            if (deposito != null) { // Se encontrado
                depositos.remove(deposito); // Remove o depósito da lista
                depositosAcabado.remove(codigoDeposito); // Remove do HashMap de depósitos acabados
                quantidadesMateriaPrima.remove(codigoDeposito); // Remove do HashMap de quantidades de matéria-prima
                depositosElaborados.remove(codigoDeposito); // Remove do HashMap de depósitos elaborados
                JOptionPane.showMessageDialog(null, "Depósito com código " + codigoDeposito + " excluído com sucesso."); // Mensagem de sucesso
            } else {
                JOptionPane.showMessageDialog(null, "Depósito com código " + codigoDeposito + " não encontrado."); // Mensagem de erro
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir depósito: " + e.getMessage()); // Mensagem de erro
        }
    }

    // Método para listar depósitos de matéria-prima

    public static String listarDepositosMateriaPrima() {
        StringBuilder lista = new StringBuilder("Listagem de Depósitos de Matéria-Prima:\n"); // Inicializa a lista

        for (Deposito dep : depositos) { // Percorre a lista de depósitos
            double quantidade = quantidadesMateriaPrima.getOrDefault(dep.getDeposito(), 0.0);
            if (quantidade > 0) { // Verifica se há quantidade cadastrada
                lista.append("Código: ").append(dep.getDeposito())
                        .append(", Descrição: ").append(dep.getDescricaoDeposito())
                        .append(", Quantidade: ").append(quantidade)
                        .append(", Data de Cadastro: ").append(dep.getDataCadastroFormatada())
                        .append("\n"); // Nova linha
            }
        }
        return lista.length() > 0 ? lista.toString() : "Nenhum depósito de matéria-prima encontrado."; // Retorna a lista formatada
    }

    public static String listarDepositosElaborado() {
        StringBuilder lista = new StringBuilder("Listagem de Depósitos Elaborados:\n"); // Inicializa a lista

        for (Deposito dep : depositos) { // Percorre a lista de depósitos
            double quantidade = depositosElaborados.getOrDefault(dep.getDeposito(), 0.0);
            if (quantidade > 0) { // Verifica se há quantidade cadastrada
                lista.append("Código: ").append(dep.getDeposito())
                        .append(", Descrição: ").append(dep.getDescricaoDeposito())
                        .append(", Quantidade: ").append(quantidade)
                        .append(", Data de Cadastro: ").append(dep.getDataCadastroFormatada())
                        .append("\n"); // Nova linha
            }
        }
        return lista.length() > 0 ? lista.toString() : "Nenhum depósito elaborado encontrado."; // Retorna a lista formatada
    }

    public static String listarDepositosAcabado() {
        StringBuilder lista = new StringBuilder("Listagem de Depósitos Acabados:\n"); // Inicializa a lista

        for (Deposito dep : depositos) { // Percorre a lista de depósitos
            double quantidade = depositosAcabado.getOrDefault(dep.getDeposito(), 0.0);
            if (quantidade > 0) { // Verifica se há quantidade cadastrada
                lista.append("Código: ").append(dep.getDeposito())
                        .append(", Descrição: ").append(dep.getDescricaoDeposito())
                        .append(", Quantidade: ").append(quantidade)
                        .append(", Data de Cadastro: ").append(dep.getDataCadastroFormatada())
                        .append("\n"); // Nova linha
            }
        }
        return lista.length() > 0 ? lista.toString() : "Nenhum depósito acabado encontrado."; // Retorna a lista formatada
    }

    public static void criarArquivoExcelDepositos(File file) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Depositos");

        // Cabeçalhos
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Código deposito");
        headerRow.createCell(1).setCellValue("Descrição deposito");
        headerRow.createCell(2).setCellValue("Data de Cadastro, do Deposito");
        headerRow.createCell(3).setCellValue("Quantidade no deposito de Insumos");
        headerRow.createCell(4).setCellValue("Quantidade no deposito de Material Elaborado");
        headerRow.createCell(5).setCellValue("Quantidade no deposito de Material Acabado");

        // Preencher a planilha com os dados do HashMap
        int rowNum = 1;
        for (Deposito deposito : depositos) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(deposito.getDeposito());
            row.createCell(1).setCellValue(deposito.getDescricaoDeposito());
            row.createCell(2).setCellValue(deposito.getDataCadastroFormatada());
            row.createCell(3).setCellValue(quantidadesMateriaPrima.getOrDefault(deposito.getDeposito(), 0.0));
            row.createCell(4).setCellValue(depositosElaborados.getOrDefault(deposito.getDeposito(), 0.0));
            row.createCell(5).setCellValue(depositosAcabado.getOrDefault(deposito.getDeposito(), 0.0));
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
