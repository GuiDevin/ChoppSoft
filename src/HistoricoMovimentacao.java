import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class HistoricoMovimentacao {
    private List<RegistroMovimentacao> historicoInsumos;
    private List<RegistroMovimentacao> historicoDepositos;

    public HistoricoMovimentacao() {
        historicoInsumos = new ArrayList<>();
        historicoDepositos = new ArrayList<>();
    }

    public void registrarMovimentacaoInsumo(int codigoInsumo, double quantidade, String tipo) {
        RegistroMovimentacao movimentacao = new RegistroMovimentacao(codigoInsumo, quantidade, tipo);
        historicoInsumos.add(movimentacao);
    }

    public void registrarMovimentacaoDeposito(int codigoDeposito, double quantidade, String tipo) {
        RegistroMovimentacao movimentacao = new RegistroMovimentacao(codigoDeposito, quantidade, tipo);
        historicoDepositos.add(movimentacao);
    }

    public List<RegistroMovimentacao> getHistoricoInsumos() {
        return historicoInsumos;
    }

    public List<RegistroMovimentacao> getHistoricoDepositos() {
        return historicoDepositos;
    }

    class RegistroMovimentacao {
        private LocalDateTime dataHora;
        private double quantidade;
        private int codigo;
        private String tipo; 

        public RegistroMovimentacao(int codigo, double quantidade, String tipo) {
            this.dataHora = LocalDateTime.now();
            this.quantidade = quantidade;
            this.codigo = codigo;
            this.tipo = tipo;
        }

        public String getDataHoraFormatada() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return dataHora.format(formatter);
        }

        public double getQuantidade() {
            return quantidade;
        }

        public int getCodigo() {
            return codigo;
        }

        public String getTipo() {
            return tipo;
        }
    }
}