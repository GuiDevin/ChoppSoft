package org.example;
import javax.swing.*;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class SistemaEstoque {
    public static void main(String[] args) {
        Movimentar movimentar = new Movimentar();

        // caminho para insumos
        String caminhoInsumo="C:\\Users\\blasi\\OneDrive\\Documentos\\Documentos\\ChoppSoft\\src\\main\\java\\org\\example\\Insumos.txt";
        String caminhoQuantidadesInsumos = "C:\\Users\\blasi\\OneDrive\\Documentos\\Documentos\\ChoppSoft\\src\\main\\java\\org\\example\\quantidade_insumos.txt";

        // caminho deposito
        String caminhoDeposito="C:\\Users\\blasi\\OneDrive\\Documentos\\Documentos\\ChoppSoft\\src\\main\\java\\org\\example\\Deposito.txt";
        String caminhoQuantidadeMateriaPrima = "C:\\Users\\blasi\\OneDrive\\Documentos\\Documentos\\ChoppSoft\\src\\main\\java\\org\\example\\quantidade_materia_prima.txt";
        String caminhoQuantidadeElaboradoDep = "C:\\Users\\blasi\\OneDrive\\Documentos\\Documentos\\ChoppSoft\\src\\main\\java\\org\\example\\quantidade_elaborado_dep.txt";
        String caminhoQuantidadeAcabadoDep = "C:\\Users\\blasi\\OneDrive\\Documentos\\Documentos\\ChoppSoft\\src\\main\\java\\org\\example\\quantidade_acabado_dep.txt";

        //Historicos
        String CaminhoHistorico_acabado="C:\\Users\\blasi\\OneDrive\\Documentos\\Documentos\\ChoppSoft\\src\\main\\java\\org\\example\\historico_acabado.txt";
        String CaminhoHistorico_depositos="C:\\Users\\blasi\\OneDrive\\Documentos\\Documentos\\ChoppSoft\\src\\main\\java\\org\\example\\historico_depositos.txt";
        String CaminhoHistorico_elaborado="C:\\Users\\blasi\\OneDrive\\Documentos\\Documentos\\ChoppSoft\\src\\main\\java\\org\\example\\historico_elaborado.txt";
        String CaminhoHistorico_etapas="C:\\Users\\blasi\\OneDrive\\Documentos\\Documentos\\ChoppSoft\\src\\main\\java\\org\\example\\historico_etapas.txt";
        String CaminhoHistorico_insumos="C:\\Users\\blasi\\OneDrive\\Documentos\\Documentos\\ChoppSoft\\src\\main\\java\\org\\example\\historico_insumos.txt";
        String caminhoHistorico_faturado="C:\\Users\\blasi\\OneDrive\\Documentos\\Documentos\\ChoppSoft\\src\\main\\java\\org\\example\\historico_faturado.txt";

        // etapas
        String caminhoEtapas = "C:\\Users\\blasi\\OneDrive\\Documentos\\Documentos\\ChoppSoft\\src\\main\\java\\org\\example\\etapas.txt";
        String caminhoQuantidadesEtapas = "C:\\Users\\blasi\\OneDrive\\Documentos\\Documentos\\ChoppSoft\\src\\main\\java\\org\\example\\quantidade_etapas.txt";

        // Materiais
        String caminhoMateriais = "C:\\Users\\blasi\\OneDrive\\Documentos\\Documentos\\ChoppSoft\\src\\main\\java\\org\\example\\materiais.txt";
        String caminhoQuantidadesElaborado = "C:\\Users\\blasi\\OneDrive\\Documentos\\Documentos\\ChoppSoft\\src\\main\\java\\org\\example\\quantidade_elaborado.txt";
        String caminhoQuantidadesAcabado = "C:\\Users\\blasi\\OneDrive\\Documentos\\Documentos\\ChoppSoft\\src\\main\\java\\org\\example\\quantidade_acabado.txt";
        String caminhoQuantidadeFaturado="C:\\Users\\blasi\\OneDrive\\Documentos\\Documentos\\ChoppSoft\\src\\main\\java\\org\\example\\quantidade_materiais_faturados.txt";


        // ------- CARREGAR -------
        // carregar insumos
        List<Insumo> insumosCarregados = Estoque.carregarInsumos(caminhoInsumo);
        Insumo.insumos = insumosCarregados; // Atualiza a lista estática da classe Insumo
        Estoque.carregarQuantidadesInsumos(caminhoQuantidadesInsumos);


        // carregar deposito
        List<Deposito> depositosCarregados = Estoque.carregarDepositos(caminhoDeposito);
        Deposito.depositos = depositosCarregados;
        Estoque.carregarQuantidadesMateriaPrima(caminhoQuantidadeMateriaPrima);
        Estoque.carregarQuantidadesElaboradoDep(caminhoQuantidadeElaboradoDep);
        Estoque.carregarQuantidadesAcabadoDep(caminhoQuantidadeAcabadoDep);

// Carregar os históricos salvos
        movimentar.registrar.setHistoricoInsumos(Estoque.carregarHistorico(CaminhoHistorico_insumos));
        movimentar.registrar.setHistoricoDepositos(Estoque.carregarHistorico(CaminhoHistorico_depositos));
        movimentar.registrar.setHistoricoMaterialElaborado(Estoque.carregarHistorico(CaminhoHistorico_elaborado));
        movimentar.registrar.setHistoricoMaterialAcabado(Estoque.carregarHistorico(CaminhoHistorico_acabado));
        movimentar.registrar.setHistoricoEtapasProdutivas(Estoque.carregarHistorico(CaminhoHistorico_etapas));
        movimentar.registrar.setHistoricoMaterialFaturado(Estoque.carregarHistorico(caminhoHistorico_faturado));


        // Carregar a parte de etapas
        Estoque.carregarEtapas(caminhoEtapas);
        Estoque.carregarQuantidades(caminhoQuantidadesEtapas);


        // Carregar Materiais
        Estoque.carregarMateriais(caminhoMateriais);
        Estoque.carregarQuantidadesElaborado(caminhoQuantidadesElaborado);
        Estoque.carregarQuantidadesAcabado(caminhoQuantidadesAcabado);
        Estoque.carregarQuantidadesFaturado(caminhoQuantidadeFaturado);


        boolean continuar = true;

        while (continuar) {
            String[] opcoes = {
                    "Cadastrar Insumo",
                    "Cadastrar Depósito",
                    "Cadastrar Etapa",
                    "Cadastrar Material",
                    "Movimentar Insumo",
                    "Movimentar Material Elaborado",
                    "Movimentar Material Acabado",
                    "Listar Deposito de Materia Prima",
                    "Listar Deposito Elaborado",
                    "Listar Deposito acabado",
                    "Listar todos Deposito em Exel",
                    "Buscar Insumo",
                    "Buscar Etapa",
                    "Buscar Material",
                    "Listar Insumos em Exel",
                    "Listar Etapas em Exel",
                    "Listar Materiais Elaborado",
                    "Listar Materiais Acabado",
                    "Listar Materiais Faturados",
                    "Listar Materiais em Exel",
                    "Excluir Insumo",
                    "Excluir Depósito",
                    "Excluir Etapa",
                    "Excluir Material",
                    "Excluir Quantidade Faturada",
                    "Ver Histórico de Insumos em Exel",
                    "Ver Histórico de Depósitos em Exel",
                    "Ver Histórico de Material Elaborado em Exel",
                    "Ver Histórico de Material Acabado em Exel",
                    "Ver Histórico de Material Faturado em Exel",
                    "Ver Histórico de Etapas Produtivas em Exel",
                    "Ver Total de Matéria Prima",
                    "Ver Total de Material Elaborado",
                    "Ver Total de Material Acabado",
                    "Ver Total de Material Faturado",
                    "Sair"
            };

            String escolha = (String) JOptionPane.showInputDialog(null, "Escolha uma ação:", "Menu", JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes);

            switch (escolha) {
                case "Cadastrar Insumo":
                    cadastrarInsumo();
                    break;

                case "Cadastrar Depósito":
                    cadastrarDeposito();
                    break;

                case "Cadastrar Etapa":
                    cadastrarEtapa();
                    break;

                case "Cadastrar Material":
                    cadastrarMaterial();
                    break;

                case "Movimentar Insumo":
                    movimentarInsumo(movimentar);
                    break;

                case "Movimentar Material Elaborado":
                    movimentarMaterialElaborado(movimentar);
                    break;

                case "Movimentar Material Acabado":
                    movimentarMaterialAcabado(movimentar);
                    break;

                case "Buscar Insumo":
                    buscarInsumo();
                    break;

                case "Buscar Depósito":
                    buscarDeposito();
                    break;

                case "Buscar Etapa":
                    buscarEtapa();
                    break;

                case "Buscar Material":
                    buscarMaterial();
                    break;

                case "Listar Etapas em Exel":
                    listarEtapasEmExel();
                    break;

                case "Listar Materiais Elaborado":
                    listarMateriaisElaborado();
                    break;

                case "Listar Materiais Acabado":
                    listarMateriaisAcabado();
                    break;

                case "Listar Materiais Faturados":
                    listarMateriaisFaturados();
                    break;

                case "Listar Materiais em Exel":
                    listarMateriaisEmExel();
                    break;


                case "Listar Insumos em Exel":
                    listarInsumosEmExel();
                    break;

                case "Listar Deposito de Materia Prima":
                    listarDepositosMateriaPrima();
                    break;

                case "Listar Deposito Elaborado":
                    listarDepositosElaborado();
                    break;

                case "Listar Deposito acabado":
                    listarDepositosAcabado();
                    break;

                case "Listar todos Deposito em Exel":
                    listarTodosOsDepositosEmExel();
                    break;

                case "Excluir Insumo":
                    excluirInsumo();
                    break;

                case "Excluir Depósito":
                    excluirDeposito();
                    break;

                case "Excluir Etapa":
                    excluirEtapa();
                    break;

                case "Excluir Material":
                    excluirMaterial();
                    break;

                case "Excluir Quantidade Faturada":
                    excluirMaterialFaturado();
                    break;

                case "Ver Histórico de Insumos em Exel":
                    verHistoricoInsumos(movimentar);
                    break;

                case "Ver Histórico de Depósitos em Exel":
                    verHistoricoDepositos(movimentar);
                    break;

                case "Ver Histórico de Material Elaborado em Exel":
                    verHistoricoMaterialElaborado(movimentar);
                    break;

                case "Ver Histórico de Material Acabado em Exel":
                    verHistoricoMaterialAcabado(movimentar);
                    break;

                case "Ver Histórico de Material Faturado em Exel":
                    verHistoricoMaterialFaturado(movimentar);
                    break;


                case "Ver Histórico de Etapas Produtivas em Exel":
                    verHistoricoEtapasProdutivas(movimentar);
                    break;

                case "Ver Total de Matéria Prima":
                    verTotalMateriaPrima();
                    break;

                case "Ver Total de Material Elaborado":
                    verTotalMaterialElaborado();
                    break;

                case "Ver Total de Material Acabado":
                    verTotalMaterialAcabado();
                    break;

                case "Ver Total de Material Faturado":
                    verTotalMaterialFaturado();
                    break;

                case "Sair":

                    // -------- SALVAR --------
                    // Antes de sair, salvar os dados atuais nos arquivos
                    //salvar Insumos
                    Estoque.salvarInsumos(Insumo.insumos,caminhoInsumo);
                    Estoque.salvarQuantidadesInsumos(caminhoQuantidadesInsumos);


                    // salvar Deposito
                    Estoque.salvarDepositos(Deposito.depositos, caminhoDeposito);
                    Estoque.salvarQuantidadesMateriaPrima(caminhoQuantidadeMateriaPrima);
                    Estoque.salvarQuantidadesElaboradoDep(caminhoQuantidadeElaboradoDep);
                    Estoque.salvarQuantidadesAcabadoDep(caminhoQuantidadeAcabadoDep);

                    // salvar os historicos
                    Estoque.salvarHistorico(movimentar.registrar.getHistoricoInsumos(), CaminhoHistorico_insumos);
                    Estoque.salvarHistorico(movimentar.registrar.getHistoricoDepositos(), CaminhoHistorico_depositos);
                    Estoque.salvarHistorico(movimentar.registrar.getHistoricoMaterialElaborado(), CaminhoHistorico_elaborado);
                    Estoque.salvarHistorico(movimentar.registrar.getHistoricoMaterialAcabado(), CaminhoHistorico_acabado);
                    Estoque.salvarHistorico(movimentar.registrar.getHistoricoEtapasProdutivas(), CaminhoHistorico_etapas);
                    Estoque.salvarHistorico( movimentar.registrar.getHistoricoMaterialFaturado(),caminhoHistorico_faturado);



                    // Salvar aa parte das etapas
                    Estoque.salvarEtapas(caminhoEtapas);
                    Estoque.salvarQuantidades(caminhoQuantidadesEtapas);

                    // Salvar materiais
                    Estoque.salvarMateriais(caminhoMateriais);
                    Estoque.salvarQuantidadesElaborado(caminhoQuantidadesElaborado);
                    Estoque.salvarQuantidadesAcabado(caminhoQuantidadesAcabado);
                    Estoque.salvarQuantidadesFaturado(caminhoQuantidadeFaturado);
                    continuar = false;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }

    private static void cadastrarInsumo() {
        int codigoInsumo;
        String codigoInsumoStr = JOptionPane.showInputDialog("Informe o código do insumo:");

        if (codigoInsumoStr == null || codigoInsumoStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Código não pode ser vazio.");
            return;
        }

        try {
            codigoInsumo = Integer.parseInt(codigoInsumoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Código deve ser um número inteiro.");
            return;
        }

        if (Insumo.verificarInsumoExistente(codigoInsumo) == null) {
            String descricaoInsumo = JOptionPane.showInputDialog("Informe a descrição do insumo:");
            if (descricaoInsumo == null || descricaoInsumo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Descrição não pode ser vazia.");
                return;
            }

            Insumo novoInsumo = new Insumo();
            novoInsumo.InsumoCadastro(codigoInsumo, descricaoInsumo);
            JOptionPane.showMessageDialog(null, "Insumo cadastrado com sucesso!"); // Mensagem de sucesso
        } else {
            JOptionPane.showMessageDialog(null, "Insumo com código " + codigoInsumo + " já existe.");
        }
    }

    private static void cadastrarDeposito() {
        int codigoDeposito;
        String codigoDepositoStr = JOptionPane.showInputDialog("Informe o código do depósito:");

        if (codigoDepositoStr == null || codigoDepositoStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Código não pode ser vazio.");
            return;
        }

        try {
            codigoDeposito = Integer.parseInt(codigoDepositoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Código deve ser um número inteiro.");
            return;
        }

        if (Deposito.verificarDepositoExistente(codigoDeposito) == null) {
            String descricaoDeposito = JOptionPane.showInputDialog("Informe a descrição do depósito (opcional):");
            Deposito novoDeposito = new Deposito();
            novoDeposito.DepositoCadastro(codigoDeposito, descricaoDeposito);
            JOptionPane.showMessageDialog(null, "Depósito cadastrado com sucesso!"); // Mensagem de sucesso
        } else {
            JOptionPane.showMessageDialog(null, "Depósito com código " + codigoDeposito + " já existe.");
        }
    }

    private static void cadastrarEtapa() {

        String codigoEtapaStr = JOptionPane.showInputDialog("Informe o código da etapa:");
        if (codigoEtapaStr == null || codigoEtapaStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Código não pode ser vazio.");
            return;
        }

        int codigoEtapa;
        try {
            codigoEtapa = Integer.parseInt(codigoEtapaStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Código deve ser um número inteiro.");
            return;
        }

        String descricaoEtapa = JOptionPane.showInputDialog("Informe a descrição da etapa:");
        if (descricaoEtapa == null || descricaoEtapa.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Descrição não pode ser vazia.");
            return;
        }

        if (EtapasProdutivas.verificarEtapaExistente(codigoEtapa) == null) {
            EtapasProdutivas novaEtapa = new EtapasProdutivas();
            novaEtapa.CadastroEtapasProdutivas(descricaoEtapa, codigoEtapa);
            JOptionPane.showMessageDialog(null, "Etapa cadastrada com sucesso!"); // Mensagem de sucesso
        } else {
            JOptionPane.showMessageDialog(null, "Etapa com código " + codigoEtapa + " já existe.");
        }
    }
    public static void cadastrarMaterial() {

            String codigoMaterialStr = JOptionPane.showInputDialog("Informe o código do material:");
            if (codigoMaterialStr == null || codigoMaterialStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Código não pode ser vazio.");
                return;
            }

            int codigoMaterial;
            try {
                codigoMaterial = Integer.parseInt(codigoMaterialStr.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Código deve ser um número inteiro.");
                return;
            }

            String descricaoMaterial = JOptionPane.showInputDialog("Informe a descrição do material:");
            if (descricaoMaterial == null || descricaoMaterial.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Descrição não pode ser vazia.");
                return;
            }else {
            Materiais material = new Materiais();
            material.CadastroMaterial(codigoMaterial,descricaoMaterial);
        }
    }

    private static void movimentarInsumo(Movimentar movimentar) {
        String codigoInsumoStr = JOptionPane.showInputDialog("Informe o código do insumo:");
        if (codigoInsumoStr == null || codigoInsumoStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Código do insumo não pode ser vazio.");
            return;
        }

        int codigoInsumo;
        try {
            codigoInsumo = Integer.parseInt(codigoInsumoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Código do insumo deve ser um número inteiro.");
            return;
        }

        String tipoOperacao = JOptionPane.showInputDialog("Informe o tipo (E - Entrada, S - Saída, T - Transferência):").toUpperCase();
        if (tipoOperacao == null || (!tipoOperacao.equals("E") && !tipoOperacao.equals("S") && !tipoOperacao.equals("T"))) {
            JOptionPane.showMessageDialog(null, "Tipo de operação deve ser E, S ou T.");
            return;
        }

        String depositoStr = JOptionPane.showInputDialog("Informe o depósito:");
        if (depositoStr == null || depositoStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Depósito não pode ser vazio.");
            return;
        }

        int deposito;
        try {
            deposito = Integer.parseInt(depositoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Depósito deve ser um número inteiro.");
            return;
        }

        String quantidadeStr = JOptionPane.showInputDialog("Informe a quantidade:");
        if (quantidadeStr == null || quantidadeStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Quantidade não pode ser vazia.");
            return;
        }

        double quantidade;
        try {
            quantidade = Double.parseDouble(quantidadeStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Quantidade deve ser um número.");
            return;
        }

        movimentar.movimentarMateriaPrima(codigoInsumo, tipoOperacao, deposito, quantidade);
    }

    private static void movimentarMaterialElaborado(Movimentar movimentar) {
        String codigoMaterialStr = JOptionPane.showInputDialog("Informe o código do material:");
        if (codigoMaterialStr == null || codigoMaterialStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Código do material não pode ser vazio.");
            return;
        }

        int codigoMaterial;
        try {
            codigoMaterial = Integer.parseInt(codigoMaterialStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Código do material deve ser um número inteiro.");
            return;
        }

        String codigoEtapaStr = JOptionPane.showInputDialog("Informe o código da etapa:");
        if (codigoEtapaStr == null || codigoEtapaStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Código da etapa não pode ser vazio.");
            return;
        }

        int codigoEtapa;
        try {
            codigoEtapa = Integer.parseInt(codigoEtapaStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Código da etapa deve ser um número inteiro.");
            return;
        }

        String tipoOperacao = JOptionPane.showInputDialog("Informe o tipo (E - Entrada, S - Saída, T - Transferência):").toUpperCase();
        if (tipoOperacao == null || (!tipoOperacao.equals("E") && !tipoOperacao.equals("S") && !tipoOperacao.equals("T"))) {
            JOptionPane.showMessageDialog(null, "Tipo de operação deve ser E, S ou T.");
            return;
        }

        String depositoStr = JOptionPane.showInputDialog("Informe o depósito:");
        if (depositoStr == null || depositoStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Depósito não pode ser vazio.");
            return;
        }

        int deposito;
        try {
            deposito = Integer.parseInt(depositoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Depósito deve ser um número inteiro.");
            return;
        }

        String quantidadeStr = JOptionPane.showInputDialog("Informe a quantidade:");
        if (quantidadeStr == null || quantidadeStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Quantidade não pode ser vazia.");
            return;
        }

        double quantidade;
        try {
            quantidade = Double.parseDouble(quantidadeStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Quantidade deve ser um número.");
            return;
        }

        // Chama o método para movimentar o material elaborado
        movimentar.movimentarMaterialElaborado(codigoMaterial, codigoEtapa, tipoOperacao, quantidade, deposito);

    }

    private static void movimentarMaterialAcabado(Movimentar movimentar) {
        String codigoMaterialStr = JOptionPane.showInputDialog("Informe o código do material acabado:");
        if (codigoMaterialStr == null || codigoMaterialStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Código do material não pode ser vazio.");
            return;
        }

        int codigoMaterial;
        try {
            codigoMaterial = Integer.parseInt(codigoMaterialStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Código do material deve ser um número inteiro.");
            return;
        }

        String tipoOperacao = JOptionPane.showInputDialog("Informe o tipo (E - Entrada, S - Saída, T - Transferência):").toUpperCase();
        if (tipoOperacao == null || (!tipoOperacao.equals("E") && !tipoOperacao.equals("S") && !tipoOperacao.equals("T"))) {
            JOptionPane.showMessageDialog(null, "Tipo de operação deve ser E, S ou T.");
            return;
        }

        String depositoStr = JOptionPane.showInputDialog("Informe o depósito:");
        if (depositoStr == null || depositoStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Depósito não pode ser vazio.");
            return;
        }

        int deposito;
        try {
            deposito = Integer.parseInt(depositoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Depósito deve ser um número inteiro.");
            return;
        }

        String quantidadeStr = JOptionPane.showInputDialog("Informe a quantidade:");
        if (quantidadeStr == null || quantidadeStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Quantidade não pode ser vazia.");
            return;
        }

        double quantidade;
        try {
            quantidade = Double.parseDouble(quantidadeStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Quantidade deve ser um número.");
            return;
        }

        // Chama o método para movimentar o material acabado
        movimentar.movimentarMaterialAcabado(codigoMaterial, tipoOperacao, deposito, quantidade);
    }

    private static void buscarInsumo() {
        String codigoInsumoStr = JOptionPane.showInputDialog("Informe o código do insumo:");
        if (codigoInsumoStr == null || codigoInsumoStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Código não pode ser vazio.");
            return;
        }

        int codigoInsumo;
        try {
            codigoInsumo = Integer.parseInt(codigoInsumoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Código deve ser um número inteiro.");
            return;
        }

        Insumo insumoBuscado = Insumo.buscarInsumo(codigoInsumo);
        if (insumoBuscado != null) {
            JOptionPane.showMessageDialog(null, "Insumo encontrado: " + insumoBuscado.getDescricaoInsumo() +
                    "\nQuantidade: " + Insumo.quantidadesInsumos.get(codigoInsumo));
        }
    }

    private static void buscarDeposito() {
        String codigoDepositoStr = JOptionPane.showInputDialog("Informe o código do depósito:");
        if (codigoDepositoStr == null || codigoDepositoStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Código não pode ser vazio.");
            return;
        }

        int codigoDeposito;
        try {
            codigoDeposito = Integer.parseInt(codigoDepositoStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Código deve ser um número inteiro.");
            return;
        }

        Deposito depositoBuscado = Deposito.buscarDeposito(codigoDeposito);
        if (depositoBuscado != null) {
            JOptionPane.showMessageDialog(null, "Depósito encontrado: " + depositoBuscado.getDeposito() +
                    " " + depositoBuscado.getDescricaoDeposito() +
                    "\nQuantidade: " + Deposito.quantidadesMateriaPrima.get(codigoDeposito));
        }
    }

    private static void buscarEtapa() {
        String codigoEtapaStr = JOptionPane.showInputDialog("Informe o código da etapa:");
        if (codigoEtapaStr == null || codigoEtapaStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Código não pode ser vazio.");
            return;
        }

        int codigoEtapa;
        try {
            codigoEtapa = Integer.parseInt(codigoEtapaStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Código deve ser um número inteiro.");
            return;
        }

        EtapasProdutivas etapaBuscada = EtapasProdutivas.buscarEtapa(codigoEtapa);
        if (etapaBuscada != null) {
            JOptionPane.showMessageDialog(null, "Etapa encontrada: " + etapaBuscada.getNumeroEtapa() +
                    "\nQuantidade: " + EtapasProdutivas.quantidadesEtapas.get(codigoEtapa));
        }
    }

    private static void buscarMaterial() {
        String codigoMaterialStr = JOptionPane.showInputDialog("Informe o código do material elaborado:");
        if (codigoMaterialStr == null || codigoMaterialStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Código não pode ser vazio.");
            return;
        }

        int codigoMaterial;
        try {
            codigoMaterial = Integer.parseInt(codigoMaterialStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Código deve ser um número inteiro.");
            return;
        }

        Materiais materialBuscado = Materiais.buscarMaterial(codigoMaterial);
        if (materialBuscado != null) {
            JOptionPane.showMessageDialog(null, "Material encontrado: " + materialBuscado.getDescricaoMaterial() +
                    "\nCódigo: " + materialBuscado.getCodigoMaterial() +
                    "\nQuantidade: " + Materiais.quantidadesMateriaisElaborado.get(codigoMaterial));
        }
    }

    private static void listarEtapasEmExel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar Arquivo Excel");
        fileChooser.setSelectedFile(new File("etapas_produtivas.xlsx")); // Nome padrão

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            EtapasProdutivas.criarArquivoExcelEtapasProdutivas(fileToSave);
        }
    }

    private static LocalDate solicitarData(String tipo) {
        while (true) {
            try {
                String dataStr = JOptionPane.showInputDialog("Informe a data de " + tipo + " (dd/MM/yyyy):");
                return LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Data inválida. Por favor, insira no formato dd/MM/yyyy.");
            }
        }
    }

    private static void listarMateriaisEmExel() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar Arquivo Excel");
        fileChooser.setSelectedFile(new File("materiais.xlsx")); // Nome padrão

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            Materiais.gerarArquivoExcelMateriais(fileToSave);
        }
    }

    private static void listarMateriaisElaborado() {

        String materiaisListados = Materiais.listarMateriaisElaborado();
        JOptionPane.showMessageDialog(null, materiaisListados);
    }

    private static void listarMateriaisAcabado() {

        String materiaisListados = Materiais.listarMateriaisAcabados();
        JOptionPane.showMessageDialog(null, materiaisListados);
    }

    private static void listarMateriaisFaturados() {

        String materiaisListados = Materiais.listarMateriaisFaturados();
        JOptionPane.showMessageDialog(null, materiaisListados);
    }

    private static void listarInsumosEmExel() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar Arquivo Excel");
        fileChooser.setSelectedFile(new File("insumos.xlsx")); // Nome padrão

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            Insumo.criarArquivoExcelInsumos(fileToSave);
        }
    }
    private static void listarDepositosAcabado() {

        String depositosListados = Deposito.listarDepositosAcabado();
        JOptionPane.showMessageDialog(null, depositosListados);
    }

    private static void listarDepositosElaborado() {

        String depositosListados = Deposito.listarDepositosElaborado();
        JOptionPane.showMessageDialog(null, depositosListados);
    }

    private static void listarDepositosMateriaPrima() {

        String depositosListados = Deposito.listarDepositosMateriaPrima();
        JOptionPane.showMessageDialog(null, depositosListados);
    }

    private static void listarTodosOsDepositosEmExel()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar Arquivo Excel");
        fileChooser.setSelectedFile(new File("depositos.xlsx")); // Nome padrão

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            Deposito.criarArquivoExcelDepositos(fileToSave);
        }
    }

    private static void excluirInsumo() {
        int codigoInsumo = Integer.parseInt(JOptionPane.showInputDialog("Informe o código do insumo a ser excluído:"));
        Insumo.excluirInsumo(codigoInsumo);
    }

    private static void excluirDeposito() {
        int codigoDeposito = Integer.parseInt(JOptionPane.showInputDialog("Informe o código do depósito a ser excluído:"));
        Deposito.excluirDeposito(codigoDeposito);
    }

    private static void excluirMaterial() {
        int codigoMaterial = Integer.parseInt(JOptionPane.showInputDialog("Informe o código do Material a ser excluído:"));
        Materiais.excluirMaterial(codigoMaterial);
    }

    private static void excluirMaterialFaturado() {
        int codigoMaterial = Integer.parseInt(JOptionPane.showInputDialog("Informe o código do Material a ser excluído e quantidade faturada:"));
        Materiais.excluirQuantidadeFaturada(codigoMaterial);
    }

    private static void excluirEtapa() {
        int codigoEtapa =Integer.parseInt(JOptionPane.showInputDialog("Informe o código da etapa a ser excluída:"));
        EtapasProdutivas.excluirEtapa(codigoEtapa);
    }

    private static void verHistoricoInsumos(Movimentar movimentar) {
        LocalDate dataInicio = solicitarData("início");
        LocalDate dataFim = solicitarData("fim");

        StringBuilder historico = new StringBuilder();
        historico.append("Histórico de Insumos:\n");

        for (HistoricoMovimentacao.RegistroMovimentacao reg : movimentar.registrar.getHistoricoInsumos()) {
            LocalDate dataHora = reg.getDataHora();
            if (dataHora != null && !dataHora.isBefore(dataInicio) && !dataHora.isAfter(dataFim)) {
                historico.append("Código: ").append(reg.getCodigo())
                        .append(", Quantidade: ").append(reg.getQuantidade())
                        .append(", Tipo: ").append(reg.getTipo())
                        .append(", Data/Hora: ").append(reg.getDataHoraFormatada()).append("\n");
            }
        }

        if (historico.length() == "Histórico de Insumos:\n".length()) {
            historico.append("Nenhum registro encontrado para o período informado.");
        }

        JOptionPane.showMessageDialog(null, historico.toString(), "Histórico de Insumos", JOptionPane.INFORMATION_MESSAGE);
       HistoricoMovimentacao.gerarArquivoExcelHistoricoMOvimentacao (movimentar.registrar.getHistoricoInsumos(), dataInicio, dataFim,
                "Histórico Insumos", "historico_insumos.xlsx");
    }

    private static void verHistoricoDepositos(Movimentar movimentar) {
        LocalDate dataInicio = solicitarData("início");
        LocalDate dataFim = solicitarData("fim");

        StringBuilder historico = new StringBuilder();
        historico.append("Histórico de Depósitos:\n");

        for (HistoricoMovimentacao.RegistroMovimentacao reg : movimentar.registrar.getHistoricoDepositos()) {
            LocalDate dataHora = reg.getDataHora();
            if (dataHora != null && !dataHora.isBefore(dataInicio) && !dataHora.isAfter(dataFim)) {
                historico.append("Código: ").append(reg.getCodigo())
                        .append(", Quantidade: ").append(reg.getQuantidade())
                        .append(", Tipo: ").append(reg.getTipo())
                        .append(", Data/Hora: ").append(reg.getDataHoraFormatada()).append("\n");
            }
        }

        if (historico.length() == "Histórico de Depósitos:\n".length()) {
            historico.append("Nenhum registro encontrado para o período informado.");
        }

        JOptionPane.showMessageDialog(null, historico.toString(), "Histórico de Depósitos", JOptionPane.INFORMATION_MESSAGE);
        HistoricoMovimentacao.gerarArquivoExcelHistoricoMOvimentacao(movimentar.registrar.getHistoricoDepositos(), dataInicio, dataFim,
                "Histórico Depósitos", "historico_depositos.xlsx");
    }

    private static void verHistoricoMaterialElaborado(Movimentar movimentar) {
        LocalDate dataInicio = solicitarData("início");
        LocalDate dataFim = solicitarData("fim");

        StringBuilder historico = new StringBuilder();
        historico.append("Histórico de Material Elaborado:\n");

        for (HistoricoMovimentacao.RegistroMovimentacao reg : movimentar.registrar.getHistoricoMaterialElaborado()) {
            LocalDate dataHora = reg.getDataHora();
            if (dataHora != null && !dataHora.isBefore(dataInicio) && !dataHora.isAfter(dataFim)) {
                historico.append("Código: ").append(reg.getCodigo())
                        .append(", Quantidade: ").append(reg.getQuantidade())
                        .append(", Tipo: ").append(reg.getTipo())
                        .append(", Data/Hora: ").append(reg.getDataHoraFormatada()).append("\n");
            }
        }

        if (historico.length() == "Histórico de Material Elaborado:\n".length()) {
            historico.append("Nenhum registro encontrado para o período informado.");
        }

        JOptionPane.showMessageDialog(null, historico.toString(), "Histórico de Material Elaborado", JOptionPane.INFORMATION_MESSAGE);
        HistoricoMovimentacao.gerarArquivoExcelHistoricoMOvimentacao(movimentar.registrar.getHistoricoMaterialElaborado(), dataInicio, dataFim,
                "Histórico Material Elaborado", "historico_material_elaborado.xlsx");
    }

    private static void verHistoricoMaterialAcabado(Movimentar movimentar) {
        LocalDate dataInicio = solicitarData("início");
        LocalDate dataFim = solicitarData("fim");

        StringBuilder historico = new StringBuilder();
        historico.append("Histórico de Material Acabado:\n");

        for (HistoricoMovimentacao.RegistroMovimentacao reg : movimentar.registrar.getHistoricoMaterialAcabado()) {
            LocalDate dataHora = reg.getDataHora();
            if (dataHora != null && !dataHora.isBefore(dataInicio) && !dataHora.isAfter(dataFim)) {
                historico.append("Código: ").append(reg.getCodigo())
                        .append(", Quantidade: ").append(reg.getQuantidade())
                        .append(", Tipo: ").append(reg.getTipo())
                        .append(", Data/Hora: ").append(reg.getDataHoraFormatada()).append("\n");
            }
        }

        if (historico.length() == "Histórico de Material Acabado:\n".length()) {
            historico.append("Nenhum registro encontrado para o período informado.");
        }

        JOptionPane.showMessageDialog(null, historico.toString(), "Histórico de Material Acabado", JOptionPane.INFORMATION_MESSAGE);
        HistoricoMovimentacao.gerarArquivoExcelHistoricoMOvimentacao(movimentar.registrar.getHistoricoMaterialAcabado(), dataInicio, dataFim,
                "Histórico Material Acabado", "historico_material_acabado.xlsx");
    }

    private static void verHistoricoMaterialFaturado(Movimentar movimentar) {
        LocalDate dataInicio = solicitarData("início");
        LocalDate dataFim = solicitarData("fim");

        StringBuilder historico = new StringBuilder();
        historico.append("Histórico de Material Faturado:\n");

        for (HistoricoMovimentacao.RegistroMovimentacao reg : movimentar.registrar.getHistoricoMaterialFaturado()) {
            LocalDate dataHora = reg.getDataHora();
            if (dataHora != null && !dataHora.isBefore(dataInicio) && !dataHora.isAfter(dataFim)) {
                historico.append("Código: ").append(reg.getCodigo())
                        .append(", Quantidade: ").append(reg.getQuantidade())
                        .append(", Tipo: ").append(reg.getTipo())
                        .append(", Data/Hora: ").append(reg.getDataHoraFormatada()).append("\n");
            }
        }

        if (historico.length() == "Histórico de Material Faturado:\n".length()) {
            historico.append("Nenhum registro encontrado para o período informado.");
        }

        JOptionPane.showMessageDialog(null, historico.toString(), "Histórico de Material Faturado", JOptionPane.INFORMATION_MESSAGE);
        HistoricoMovimentacao.gerarArquivoExcelHistoricoMOvimentacao(movimentar.registrar.getHistoricoMaterialFaturado(), dataInicio, dataFim,
                "Histórico Material Faturado", "historico_material_faturado.xlsx");
    }

    private static void verHistoricoEtapasProdutivas(Movimentar movimentar) {
        LocalDate dataInicio = solicitarData("início");
        LocalDate dataFim = solicitarData("fim");

        StringBuilder historico = new StringBuilder();
        historico.append("Histórico de Etapas Produtivas:\n");

        for (HistoricoMovimentacao.RegistroMovimentacao reg : movimentar.registrar.getHistoricoEtapasProdutivas()) {
            LocalDate dataHora = reg.getDataHora();
            if (dataHora != null && !dataHora.isBefore(dataInicio) && !dataHora.isAfter(dataFim)) {
                historico.append("Código: ").append(reg.getCodigo())
                        .append(", Quantidade: ").append(reg.getQuantidade())
                        .append(", Tipo: ").append(reg.getTipo())
                        .append(", Data/Hora: ").append(reg.getDataHoraFormatada()).append("\n");
            }
        }

        if (historico.length() == "Histórico de Etapas Produtivas:\n".length()) {
            historico.append("Nenhum registro encontrado para o período informado.");
        }

        JOptionPane.showMessageDialog(null, historico.toString(), "Histórico de Etapas Produtivas", JOptionPane.INFORMATION_MESSAGE);
        HistoricoMovimentacao.gerarArquivoExcelHistoricoMOvimentacao(movimentar.registrar.getHistoricoEtapasProdutivas(), dataInicio, dataFim,
                "Histórico Etapas Produtivas", "historico_etapas_produtivas.xlsx");
    }

    private static void verTotalMateriaPrima() {
        double total = Insumo.quantidadeTotalInsumos();
        JOptionPane.showMessageDialog(null, "Total de matéria prima no estoque: " + total);
    }

    private static void verTotalMaterialElaborado() {
        double total = Materiais.totalEmEstoqueElaborado();
        JOptionPane.showMessageDialog(null, "Total de material elaborado no estoque: " + total);
    }

    private static void verTotalMaterialAcabado() {
        double total = Materiais.totalEmEstoqueAcabado();
        JOptionPane.showMessageDialog(null, "Total de material Acabado no estoque: " + total);
    }

    private static void verTotalMaterialFaturado() {
        double total = Materiais.totalEmEstoqueFaturado();
        JOptionPane.showMessageDialog(null, "Total de material Faturado: " + total);
    }
}
