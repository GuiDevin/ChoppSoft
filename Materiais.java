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

public class Materiais {
    public static HashMap<Integer, Double> quantidadesMateriaisElaborado = new HashMap<>();
    public static HashMap<Integer, Double> quantidadesMateriaisAcabado = new HashMap<>();
    public static HashMap<Integer, Double> quantidadesMateriaisFaturados = new HashMap<>();
    public static List<Materiais> materiais = new ArrayList<>();

    int codigoMaterial;
    String descricaoMaterial;
    private LocalDateTime dataCadastro;


    public void CadastroMaterial(int codigoMateril, String descricaoMaterialElaborado) {
        if (descricaoMaterialElaborado == null || descricaoMaterialElaborado.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Descrição do material não pode ser vazia.");
            return;
        }
        this.codigoMaterial = codigoMateril;
        this.descricaoMaterial = descricaoMaterialElaborado.trim();
        this.dataCadastro = LocalDateTime.now();
        quantidadesMateriaisElaborado.put(codigoMaterial, 0.0);
        quantidadesMateriaisAcabado.put(codigoMaterial, 0.0);
        quantidadesMateriaisFaturados.put(codigoMaterial, 0.0);
        materiais.add(this);
        JOptionPane.showMessageDialog(null, "Material cadastrado com sucesso!");
    }

    public String getDataCadastroFormatada() {
        if (dataCadastro == null) {
            return "Data não definida";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return dataCadastro.format(formatter);
    }

    public static Materiais buscarMaterial(int codigo) {
        for (Materiais material : materiais) {
            if (material.getCodigoMaterial() == codigo) {
                return material;
            }
        }
        return null;
    }

    public String getDescricaoMaterial() {
        return descricaoMaterial != null ? descricaoMaterial : "Sem descrição";
    }

    public int getCodigoMaterial() {
        return codigoMaterial;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public static void gerarArquivoExcelMateriais(File file) {
        if (materiais.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum material cadastrado para gerar o arquivo Excel.");
            return;
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Materiais");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Código");
        headerRow.createCell(1).setCellValue("Descrição");
        headerRow.createCell(2).setCellValue("Data de Cadastro");
        headerRow.createCell(3).setCellValue("Quantidade Elaborado");
        headerRow.createCell(4).setCellValue("Quantidade Acabado");
        headerRow.createCell(5).setCellValue("Quantidade Faturado");

        int rowNum = 1;
        for (Materiais material : materiais) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(material.getCodigoMaterial());
            row.createCell(1).setCellValue(material.getDescricaoMaterial());
            row.createCell(2).setCellValue(material.getDataCadastroFormatada());
            row.createCell(3).setCellValue(quantidadesMateriaisElaborado.getOrDefault(material.getCodigoMaterial(), 0.0));
            row.createCell(4).setCellValue(quantidadesMateriaisAcabado.getOrDefault(material.getCodigoMaterial(), 0.0));
            row.createCell(5).setCellValue(quantidadesMateriaisFaturados.getOrDefault(material.getCodigoMaterial(), 0.0));
        }

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

    public static String listarMateriaisElaborado() {
        StringBuilder lista = new StringBuilder("Listagem de Materiais Elaborados:\n");
        for (Materiais material : materiais) {
            lista.append("Código: ").append(material.getCodigoMaterial())
                    .append(", Descrição: ").append(material.getDescricaoMaterial())
                    .append(", Quantidade: ").append(quantidadesMateriaisElaborado.getOrDefault(material.getCodigoMaterial(), 0.0))
                    .append(", Data de Cadastro: ").append(material.getDataCadastroFormatada())
                    .append("\n");
        }
        return lista.toString();
    }

    public static String listarMateriaisAcabados() {
        StringBuilder lista = new StringBuilder("Listagem de Materiais Acabados:\n");
        for (Materiais material : materiais) {
            lista.append("Código: ").append(material.getCodigoMaterial())
                    .append(", Descrição: ").append(material.getDescricaoMaterial())
                    .append(", Quantidade: ").append(quantidadesMateriaisAcabado.getOrDefault(material.getCodigoMaterial(), 0.0))
                    .append(", Data de Cadastro: ").append(material.getDataCadastroFormatada())
                    .append("\n");
        }
        return lista.toString();
    }

    public static String listarMateriaisFaturados() {
        StringBuilder lista = new StringBuilder("Listagem de Materiais Faturados:\n");
        for (Materiais material : materiais) {
            double quantidadeFaturada = quantidadesMateriaisFaturados.getOrDefault(material.getCodigoMaterial(), 0.0);
            if (quantidadeFaturada > 0) {
                lista.append("Código: ").append(material.getCodigoMaterial())
                        .append(", Descrição: ").append(material.getDescricaoMaterial())
                        .append(", Quantidade: ").append(quantidadeFaturada)
                        .append(", Data de Cadastro: ").append(material.getDataCadastroFormatada())
                        .append("\n");
            }
        }
        return lista.toString();
    }

    public static void excluirMaterial(int codigo) {
        Materiais material = buscarMaterial(codigo);
        if (material != null) {
            materiais.remove(material);
            quantidadesMateriaisElaborado.remove(codigo);
            quantidadesMateriaisAcabado.remove(codigo);
            quantidadesMateriaisFaturados.remove(codigo);
            JOptionPane.showMessageDialog(null, "Material com código " + codigo + " excluído com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Material com código " + codigo + " não encontrado.");
        }
    }

    public static void excluirQuantidadeFaturada(int codigo) {
        if (quantidadesMateriaisFaturados.containsKey(codigo)) {
            quantidadesMateriaisFaturados.put(codigo, 0.0);
            JOptionPane.showMessageDialog(null, "Quantidade faturada do material com código " + codigo + " excluída com sucesso.");
        } else {
            JOptionPane.showMessageDialog(null, "Material com código " + codigo + " não encontrado.");
        }
    }

    public static double totalEmEstoqueElaborado() {
        double total = 0; // Inicializa o total
        for (Double quantidade : quantidadesMateriaisElaborado.values()) { // Percorre as quantidades
            total += quantidade; // Soma as quantidades
        }
        return total; // Retorna o total
    }

    public static double totalEmEstoqueAcabado() {
        double total = 0; // Inicializa o total
        for (Double quantidade : quantidadesMateriaisAcabado.values()) { // Percorre as quantidades
            total += quantidade; // Soma as quantidades
        }
        return total; // Retorna o total
    }

    public static double totalEmEstoqueFaturado() {
        double total = 0; // Inicializa o total
        for (Double quantidade : quantidadesMateriaisFaturados.values()) { // Percorre as quantidades
            total += quantidade; // Soma as quantidades
        }
        return total; // Retorna o total
    }
}