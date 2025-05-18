
import java.time.LocalDateTime;
import java.util.List;

class EstoqueChopp {
    private static int proximoSequencial = 1;
    private static int proximoProduto = 1;
    private int produto = 0;
    private String descricaoProduto;
    private int sequencial = 0;
    private double peso;
    private LocalDateTime dataHoraRegistro;


    public EstoqueChopp(String descricaoProduto,double peso) {
        this.descricaoProduto = descricaoProduto;
        this.produto = proximoProduto++;
        this.sequencial = proximoSequencial++;
        this.peso = peso;
        this.dataHoraRegistro = LocalDateTime.now();
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public int getProduto(){
        return produto;
    }

    public int getSequencial() {
        return sequencial;
    }

    public double getPeso() {
        return peso;
    }

    public LocalDateTime getDataRegistro() {
        return dataHoraRegistro;
    }

}
