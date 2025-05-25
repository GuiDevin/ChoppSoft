package org.example;
import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

// Classe que gerencia o histórico de movimentação de insumos, depósitos, material elaborado, acabado e faturado
class HistoricoMovimentacao {
    // Listas para armazenar os registros de movimentação
    public List<RegistroMovimentacao> historicoInsumos;
    public List<RegistroMovimentacao> historicoDepositos;
    public List<RegistroMovimentacao> historicoMaterialElaborado;
    public List<RegistroMovimentacao> historicoMaterialAcabado;
    public List<RegistroMovimentacao> historicoMaterialFaturado; // Histórico para materiais faturados
    public List<RegistroMovimentacao> historicoEtapasProdutivas; // Histórico para etapas produtivas

    // Construtor que inicializa as listas
    public HistoricoMovimentacao() {
        historicoInsumos = new ArrayList<>();
        historicoDepositos = new ArrayList<>();
        historicoMaterialElaborado = new ArrayList<>();
        historicoMaterialAcabado = new ArrayList<>();
        historicoMaterialFaturado = new ArrayList<>(); // Inicialização do novo histórico
        historicoEtapasProdutivas = new ArrayList<>();
    }

    // Método para registrar movimentação de insumos
    public void registrarMovimentacaoInsumo(int codigoInsumo, double quantidade, String tipo) {
        try {
            RegistroMovimentacao movimentacao = new RegistroMovimentacao(codigoInsumo, quantidade, tipo);
            historicoInsumos.add(movimentacao);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar movimentação de insumo: " + e.getMessage());
        }
    }

    // Método para registrar movimentação de depósitos
    public void registrarMovimentacaoDeposito(int codigoDeposito, double quantidade, String tipo) {
        try {
            RegistroMovimentacao movimentacao = new RegistroMovimentacao(codigoDeposito, quantidade, tipo);
            historicoDepositos.add(movimentacao);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar movimentação de depósito: " + e.getMessage());
        }
    }

    // Método para registrar movimentação de material elaborado
    public void registrarMovimentacaoMaterialElaborado(int codigoMaterial, double quantidade, String tipo) {
        try {
            RegistroMovimentacao movimentacao = new RegistroMovimentacao(codigoMaterial, quantidade, tipo);
            historicoMaterialElaborado.add(movimentacao);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar movimentação de material elaborado: " + e.getMessage());
        }
    }

    // Método para registrar movimentação de material acabado
    public void registrarMovimentacaoMaterialAcabado(int codigoMaterial, double quantidade, String tipo) {
        try {
            RegistroMovimentacao movimentacao = new RegistroMovimentacao(codigoMaterial, quantidade, tipo);
            historicoMaterialAcabado.add(movimentacao);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar movimentação de material acabado: " + e.getMessage());
        }
    }

    // Método para registrar movimentação de materiais faturados
    public void registrarMovimentacaoMaterialFaturado(int codigoMaterial, double quantidade, String tipo) {
        try {
            RegistroMovimentacao movimentacao = new RegistroMovimentacao(codigoMaterial, quantidade, tipo);
            historicoMaterialFaturado.add(movimentacao); // Adiciona ao histórico de materiais faturados
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar movimentação de material faturado: " + e.getMessage());
        }
    }

    // Método para registrar movimentação de etapas produtivas
    public void registrarMovimentacaoEtapaProdutiva(int codigoEtapa, double quantidade, String tipo) {
        try {
            RegistroMovimentacao movimentacao = new RegistroMovimentacao(codigoEtapa, quantidade, tipo);
            historicoEtapasProdutivas.add(movimentacao);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar movimentação de etapa produtiva: " + e.getMessage());
        }
    }

    // Getters para acessar os históricos
    public List<RegistroMovimentacao> getHistoricoInsumos() {
        return historicoInsumos;
    }

    public List<RegistroMovimentacao> getHistoricoDepositos() {
        return historicoDepositos;
    }

    public List<RegistroMovimentacao> getHistoricoMaterialElaborado() {
        return historicoMaterialElaborado;
    }

    public List<RegistroMovimentacao> getHistoricoMaterialAcabado() {
        return historicoMaterialAcabado;
    }

    public List<RegistroMovimentacao> getHistoricoMaterialFaturado() {
        return historicoMaterialFaturado;
    }

    public List<RegistroMovimentacao> getHistoricoEtapasProdutivas() {
        return historicoEtapasProdutivas;
    }

    public void setHistoricoInsumos(List<RegistroMovimentacao> historicoInsumos) {
        this.historicoInsumos = historicoInsumos;
    }

    public void setHistoricoDepositos(List<RegistroMovimentacao> historicoDepositos) {
        this.historicoDepositos = historicoDepositos;
    }

    public void setHistoricoMaterialElaborado(List<RegistroMovimentacao> historicoMaterialElaborado) {
        this.historicoMaterialElaborado = historicoMaterialElaborado;
    }

    public void setHistoricoMaterialAcabado(List<RegistroMovimentacao> historicoMaterialAcabado) {
        this.historicoMaterialAcabado = historicoMaterialAcabado;
    }

    public void setHistoricoMaterialFaturado(List<RegistroMovimentacao> historicoMaterialFaturado) {
        this.historicoMaterialFaturado = historicoMaterialFaturado;
    }

    public void setHistoricoEtapasProdutivas(List<RegistroMovimentacao> historicoEtapasProdutivas) {
        this.historicoEtapasProdutivas = historicoEtapasProdutivas;
    }

    // Classe interna para representar um registro de movimentação
    class RegistroMovimentacao {
        private LocalDateTime dataHora; // Data e hora da movimentação
        private double quantidade; // Quantidade movimentada
        private int codigo; // Código do item movimentado
        private String tipo; // Tipo de movimentação (Entrada/Saída)

        // Construtor para inicializar um registro de movimentação
        public RegistroMovimentacao(int codigo, double quantidade, String tipo) {
            this.dataHora = LocalDateTime.now(); // Captura a data e hora atual
            this.quantidade = quantidade; // Define a quantidade
            this.codigo = codigo; // Define o código do item
            this.tipo = tipo; // Define o tipo de movimentação
        }

        // Formata a data e hora para exibição
        public String getDataHoraFormatada() {
            if (dataHora == null) {
                return "Data não definida";
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return dataHora.format(formatter);
        }

        public LocalDate getDataHora() {
            return dataHora.toLocalDate(); // Adicionando o método para obter LocalDate
        }

        public double getQuantidade() {
            return quantidade; // Retorna a quantidade movimentada
        }

        public int getCodigo() {
            return codigo; // Retorna o código do item
        }

        public String getTipo() {
            return tipo; // Retorna o tipo de movimentação
        }
    }

    public static void gerarArquivoExcelHistoricoMOvimentacao(List<HistoricoMovimentacao.RegistroMovimentacao> registros,
                                          LocalDate dataInicio, LocalDate dataFim,
                                          String sheetName, String fileName) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar Arquivo Excel - " + sheetName);
        fileChooser.setSelectedFile(new File(fileName));

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return; // User canceled the save operation
        }

        File fileToSave = fileChooser.getSelectedFile();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);

        // Cabeçalhos
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Código");
        headerRow.createCell(1).setCellValue("Quantidade");
        headerRow.createCell(2).setCellValue("Tipo");
        headerRow.createCell(3).setCellValue("Data/Hora");

        // Preencher a planilha com os dados filtrados
        int rowNum = 1;
        for (HistoricoMovimentacao.RegistroMovimentacao reg : registros) {
            LocalDate dataHora = reg.getDataHora();
            if (dataHora != null && !dataHora.isBefore(dataInicio) && !dataHora.isAfter(dataFim)) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(reg.getCodigo());
                row.createCell(1).setCellValue(reg.getQuantidade());
                row.createCell(2).setCellValue(reg.getTipo());
                row.createCell(3).setCellValue(reg.getDataHoraFormatada());
            }
        }

        // Verificar se há registros para evitar arquivo vazio
        if (rowNum == 1) {
            JOptionPane.showMessageDialog(null, "Nenhum registro encontrado para o período informado. Arquivo não gerado.");
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        // Escrever o arquivo Excel
        try (FileOutputStream fileOut = new FileOutputStream(fileToSave)) {
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
