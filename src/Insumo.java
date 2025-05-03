public class Insumo {


    private String codigo;
    private String descricao;
    private String deposito;

    public Insumo(String codigo, String descricao, String deposito) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.deposito = deposito;

    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
    public String getDeposito(){
        return deposito;
    }
}
